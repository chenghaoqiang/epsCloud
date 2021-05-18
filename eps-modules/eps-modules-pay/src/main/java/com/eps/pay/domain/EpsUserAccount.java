package com.eps.pay.domain;

import com.eps.common.core.annotation.Excel;
import com.eps.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 用户账户对象 eps_user_account
 * 
 * @author liuzhimin
 * @date 2021-02-07
 */
public class EpsUserAccount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 账户余额 */
    @Excel(name = "账户余额")
    private BigDecimal balanceAmount;

    /** 账户冻结 */
    @Excel(name = "账户冻结")
    private BigDecimal freezeAmount;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBalanceAmount(BigDecimal balanceAmount)
    {
        this.balanceAmount = balanceAmount;
    }

    public BigDecimal getBalanceAmount()
    {
        return balanceAmount;
    }
    public void setFreezeAmount(BigDecimal freezeAmount)
    {
        this.freezeAmount = freezeAmount;
    }

    public BigDecimal getFreezeAmount()
    {
        return freezeAmount;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("balanceAmount", getBalanceAmount())
            .append("freezeAmount", getFreezeAmount())
            .append("userId", getUserId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
