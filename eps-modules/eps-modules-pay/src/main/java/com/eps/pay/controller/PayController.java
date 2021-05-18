package com.eps.pay.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eps.common.core.utils.IdUtils;
import com.eps.common.core.utils.SecurityUtils;
import com.eps.common.security.annotation.PreAuthorize;
import com.eps.pay.domain.EpsAccountRecord;
import com.eps.pay.domain.EpsRechargeOrder;
import com.eps.pay.domain.EpsUserAccount;
import com.eps.pay.dto.NotifyDTO;
import com.eps.pay.dto.WxCheckDTO;
import com.eps.pay.service.IEpsAccountRecordService;
import com.eps.pay.service.IEpsRechargeOrderService;
import com.eps.pay.service.IEpsUserAccountService;
import com.eps.pay.utils.HttpsUtils;
import com.eps.pay.utils.PayjsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/payjs")
public class PayController {

    @Autowired
    private IEpsRechargeOrderService epsRechargeOrderService;

    @Autowired
    private IEpsAccountRecordService epsAccountRecordService;

    @Autowired
    private IEpsUserAccountService epsUserAccountService;

    /**
     * native
     */
    @RequestMapping("/native")
    @PreAuthorize(hasPermi = "pay:payjs:native")
    @ResponseBody
    public Object Native(BigDecimal payAmount) throws NoSuchAlgorithmException, KeyManagementException, IOException {

        Map<String,String> payData = new HashMap<>();
        payData.put("mchid", PayjsConfig.mchid);
        payData.put("total_fee", payAmount.multiply(new BigDecimal(Double.toString(100))).setScale(0, BigDecimal.ROUND_UP).toString());
        String outTradeNo = IdUtils.simpleUUID();
        // 订单号 随便输的，自己生成一下就好了
        payData.put("out_trade_no", outTradeNo);
        payData.put("body","账户充值");
        payData.put("notify_url", "https://192.168.23.226/api/pay/nitify");
        payData.put("attach",SecurityUtils.getUserId().toString());
        // 进行sign签名
        payData.put("sign", sign(payData, PayjsConfig.key));

        // 请求payjs获取二维码
        String result = HttpsUtils.sendPost(PayjsConfig.nativeUrl, JSON.toJSONString(payData),null);

        // 接口返回二维码数据
        return JSON.parseObject(result);
    }


    /**
     * check
     */
    @RequestMapping("/check")
    @ResponseBody
    public Object Check(String payjsOrderId) throws NoSuchAlgorithmException, KeyManagementException, IOException {

        Map<String,String> payData = new HashMap<>();
        payData.put("mchid", PayjsConfig.mchid);
        // payjs订单号
        payData.put("payjs_order_id", payjsOrderId);


        // 进行sign签名
        payData.put("sign", sign(payData, PayjsConfig.key));

        // 请求payjs
        String result = HttpsUtils.sendPost(PayjsConfig.checkUrl, JSON.toJSONString(payData),null);


        JSONObject jsonObject = JSON.parseObject(result);
        WxCheckDTO wxCheckDTO = JSON.toJavaObject(jsonObject, WxCheckDTO.class);
        //校验支付成功
        if(wxCheckDTO.getReturn_code().equals(1) && wxCheckDTO.getStatus().equals(1)) {
            // 创建充值订单
            EpsRechargeOrder epsRechargeOrder = new EpsRechargeOrder();
            {
                epsRechargeOrder.setReturnCode(wxCheckDTO.getReturn_code());
                epsRechargeOrder.setTotalFee(wxCheckDTO.getTotal_fee().divide(new BigDecimal(100).setScale(2)));
                epsRechargeOrder.setOutTradeNo(wxCheckDTO.getOut_trade_no());
                epsRechargeOrder.setPayjsOrderId(wxCheckDTO.getPayjs_order_id());
                epsRechargeOrder.setTransactionId(wxCheckDTO.getTransaction_id());
                epsRechargeOrder.setPaidTime(wxCheckDTO.getPaid_time());
                epsRechargeOrder.setUserId(Long.parseLong(wxCheckDTO.getAttach()));
                epsRechargeOrder.setStatus(wxCheckDTO.getStatus());
            }
            epsRechargeOrderService.insertEpsRechargeOrder(epsRechargeOrder);
            // 用户账户添加充值金额
            EpsUserAccount epsUserAccount = epsUserAccountService.selectEpsUserAccountByUserId(epsRechargeOrder.getUserId());
            if(null == epsUserAccount) {
                epsUserAccount = new EpsUserAccount();
                epsUserAccount.setUserId(epsRechargeOrder.getUserId());
                epsUserAccount.setBalanceAmount(wxCheckDTO.getTotal_fee().divide(new BigDecimal(100).setScale(2)));
                epsUserAccount.setFreezeAmount(BigDecimal.ZERO);
                epsUserAccountService.insertEpsUserAccount(epsUserAccount);
            }else {
                epsUserAccount.setBalanceAmount(epsUserAccount.getBalanceAmount().add(wxCheckDTO.getTotal_fee().divide(new BigDecimal(100).setScale(2))));
                epsUserAccountService.updateEpsUserAccount(epsUserAccount);
            }
            // 添加账户流水
            EpsAccountRecord epsAccountRecord = new EpsAccountRecord();
            {
                epsAccountRecord.setPayAmount(wxCheckDTO.getTotal_fee().divide(new BigDecimal(100).setScale(2)));
                epsAccountRecord.setBalanceAmount(epsUserAccount.getBalanceAmount());
                epsAccountRecord.setOrderType(20);
                epsAccountRecord.setTradeType(1);
                epsAccountRecord.setUserId(epsUserAccount.getUserId());
            }
            epsAccountRecordService.insertEpsAccountRecord(epsAccountRecord);
        }
        // 接口返回数据
        return jsonObject;
    }

    /**
     * 异步通知
     * @param notifyDTO
     * @return
     */
    @RequestMapping("/notify")
    @ResponseBody
    public Object payjsNotify(NotifyDTO notifyDTO){
        Map<String,String> notifyData = new HashMap<>();
        notifyData.put("return_code",notifyDTO.getReturn_code());
        notifyData.put("total_fee",notifyDTO.getTotal_fee());
        notifyData.put("out_trade_no",notifyDTO.getOut_trade_no());
        notifyData.put("payjs_order_id",notifyDTO.getPayjs_order_id());
        notifyData.put("transaction_id",notifyDTO.getTransaction_id());
        notifyData.put("time_end",notifyDTO.getTime_end());
        notifyData.put("openid",notifyDTO.getOpenid());
        notifyData.put("mchid",notifyDTO.getMchid());
        
        // options
        if (notifyDTO.getAttach() != null) {
            notifyData.put("attach",notifyDTO.getAttach());
        }
        if (notifyDTO.getType() != null) {
            notifyData.put("type", notifyDTO.getType());
        }


        String sign = sign(notifyData, PayjsConfig.key);
        if(sign.equals(notifyDTO.getSign())){
            // 验签通过，这里修改订单状态
            // 更新账户余额
            //
            return "success";
        }

        return "failure";
    }


    //签名算法
    public String sign(Map<String, String> params, String secret){
        String sign="";
        StringBuilder sb = new StringBuilder();
        //step1：先对请求参数排序
        Set<String> keyset = params.keySet();
        TreeSet<String> sortSet = new TreeSet<String>();
        sortSet.addAll(keyset);
        Iterator<String> it = sortSet.iterator();
        //step2：把参数的key value链接起来 secretkey放在最后面，得到要加密的字符串
        while(it.hasNext())
        {
            String key = it.next();
            String value = params.get(key);
            sb.append(key).append("=").append(value).append("&");
        }
        sb.append("key=").append(secret);
        byte[] md5Digest;
        try {
            //得到Md5加密得到sign
            md5Digest = getMD5Digest(sb.toString());
            sign = byte2hex(md5Digest);
        } catch (IOException e) {
            System.out.println("生成签名错误" + e);
        }
        return sign;
    }

    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

    private static byte[] getMD5Digest(String data) throws IOException {
        byte[] bytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            bytes = md.digest(data.getBytes("UTF-8"));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse);
        }
        return bytes;
    }

}
