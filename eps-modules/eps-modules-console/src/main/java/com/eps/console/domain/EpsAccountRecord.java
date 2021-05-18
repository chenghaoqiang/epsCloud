package com.eps.console.domain;

import com.eps.common.core.annotation.Excel;
import com.eps.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 账户流水对象 eps_account_record
 * 
 * @author liuzhimin
 * @date 2021-02-07
 */
public class EpsAccountRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal payAmount;

    /** 账户余额 */
    @Excel(name = "账户余额")
    private BigDecimal balanceAmount;

    /** 订单类型 10-下单 20-充值 30-推广 */
    @Excel(name = "订单类型 10-下单 20-充值 30-推广")
    private Integer orderType;

    /** 交易类型 1-收入 2-支出 */
    @Excel(name = "交易类型 1-收入 2-支出")
    private Integer tradeType;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 用户名称 */
    @Excel(name = "用户名称")
    private String userName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPayAmount(BigDecimal payAmount)
    {
        this.payAmount = payAmount;
    }

    public BigDecimal getPayAmount()
    {
        return payAmount;
    }
    public void setBalanceAmount(BigDecimal balanceAmount)
    {
        this.balanceAmount = balanceAmount;
    }

    public BigDecimal getBalanceAmount()
    {
        return balanceAmount;
    }
    public void setOrderType(Integer orderType)
    {
        this.orderType = orderType;
    }

    public Integer getOrderType()
    {
        return orderType;
    }
    public void setTradeType(Integer tradeType)
    {
        this.tradeType = tradeType;
    }

    public Integer getTradeType()
    {
        return tradeType;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("payAmount", getPayAmount())
            .append("balanceAmount", getBalanceAmount())
            .append("orderType", getOrderType())
            .append("tradeType", getTradeType())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("createTime", getCreateTime())
            .toString();
    }
}
