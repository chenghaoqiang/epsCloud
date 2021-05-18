package com.eps.pay.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author chengtianqi
 * @create 2020/8/28 17:13
 */
public class WxCheckDTO {

    // 1：支付成功
    private Integer return_code;
    // 金额。单位：分
    private BigDecimal total_fee;
    // 用户端自主生成的订单号
    private String out_trade_no;
    // PAYJS 订单号
    private String payjs_order_id;
    // 微信用户手机显示订单号
    private String transaction_id;
    // 支付成功时间
    private Date paid_time;
    //
    private String openid;
    // 用户自定义数据
    private String attach;
    // 商户号
    private String mchid;

    private String sign;

    // 0：未支付，1：支付成功
    private Integer status;

    public Integer getReturn_code() {
        return return_code;
    }

    public void setReturn_code(Integer return_code) {
        this.return_code = return_code;
    }

    public BigDecimal getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(BigDecimal total_fee) {
        this.total_fee = total_fee;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getPayjs_order_id() {
        return payjs_order_id;
    }

    public void setPayjs_order_id(String payjs_order_id) {
        this.payjs_order_id = payjs_order_id;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public Date getPaid_time() {
        return paid_time;
    }

    public void setPaid_time(Date paid_time) {
        this.paid_time = paid_time;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
