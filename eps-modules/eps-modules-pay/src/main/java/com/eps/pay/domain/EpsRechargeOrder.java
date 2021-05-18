package com.eps.pay.domain;

import com.eps.common.core.annotation.Excel;
import com.eps.common.core.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 发货地址对象 eps_recharge_order
 * 
 * @author liuzhimin
 * @date 2021-02-05
 */
public class EpsRechargeOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 1：支付成功 */
    private Integer returnCode;

    /** 金额。单位：元 */
    @Excel(name = "金额。单位：元")
    private BigDecimal totalFee;

    /** 用户端自主生成的订单号 */
    private String outTradeNo;

    /** PAYJS 订单号 */
    @Excel(name = "订单号")
    private String payjsOrderId;

    /** 微信用户手机显示订单号 */
    @Excel(name = "微信订单号")
    private String transactionId;

    /** 支付成功时间 */
    @Excel(name = "支付成功时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date paidTime;

    /** 用户id */
    private Long userId;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(Integer returnCode) {
        this.returnCode = returnCode;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getPayjsOrderId() {
        return payjsOrderId;
    }

    public void setPayjsOrderId(String payjsOrderId) {
        this.payjsOrderId = payjsOrderId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Date getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(Date paidTime) {
        this.paidTime = paidTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
