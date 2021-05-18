package com.eps.console.domain;

import com.eps.common.core.annotation.Excel;
import com.eps.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 发货地址对象 eps_shipping_address
 * 
 * @author liuzhimin
 * @date 2020-12-21
 */
public class EpsShippingAddress extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 发件人姓名 */
    @Excel(name = "发件人姓名")
    private String fullName;

    /** 店铺名称 */
    @Excel(name = "店铺名称")
    private String shopName;

    /** 店铺旺旺号 */
    @Excel(name = "店铺旺旺号")
    private String shopWangwang;

    /** 发件人手机号 */
    @Excel(name = "发件人手机号")
    private String phone;

    /** 发件人省 */
    @Excel(name = "发件人省")
    private String province;

    /** 发件人城市 */
    @Excel(name = "发件人城市")
    private String city;

    /** 发件人区 */
    @Excel(name = "发件人区")
    private String area;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String addressLine;

    /** 是否删除 0-否 1-是 */
    @Excel(name = "是否删除 0-否 1-是")
    private String hasDelete;

    /** 是否默认 0-否 1-是 */
    @Excel(name = "是否默认 0-否 1-是")
    private String hasDefault;

    /** 用户id */
    private Long userId;

    /** 全地址 */
    private String fullShippingAddress;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setFullName(String fullName) 
    {
        this.fullName = fullName;
    }

    public String getFullName() 
    {
        return fullName;
    }
    public void setShopName(String shopName) 
    {
        this.shopName = shopName;
    }

    public String getShopName() 
    {
        return shopName;
    }
    public void setShopWangwang(String shopWangwang) 
    {
        this.shopWangwang = shopWangwang;
    }

    public String getShopWangwang() 
    {
        return shopWangwang;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setArea(String area) 
    {
        this.area = area;
    }

    public String getArea() 
    {
        return area;
    }
    public void setAddressLine(String addressLine) 
    {
        this.addressLine = addressLine;
    }

    public String getAddressLine() 
    {
        return addressLine;
    }
    public void setHasDelete(String hasDelete)
    {
        this.hasDelete = hasDelete;
    }

    public String getHasDelete()
    {
        return hasDelete;
    }
    public void setHasDefault(String hasDefault)
    {
        this.hasDefault = hasDefault;
    }

    public String getHasDefault()
    {
        return hasDefault;
    }

    public String getFullShippingAddress() { return province + city + area + addressLine;}

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
            .append("fullName", getFullName())
            .append("shopName", getShopName())
            .append("shopWangwang", getShopWangwang())
            .append("phone", getPhone())
            .append("province", getProvince())
            .append("city", getCity())
            .append("area", getArea())
            .append("addressLine", getAddressLine())
            .append("hasDelete", getHasDelete())
            .append("hasDefault", getHasDefault())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("userId", getUserId())
            .toString();
    }
}
