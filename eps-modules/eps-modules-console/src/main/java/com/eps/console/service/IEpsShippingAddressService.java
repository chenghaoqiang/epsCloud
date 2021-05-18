package com.eps.console.service;

import com.eps.console.domain.EpsShippingAddress;

import java.util.List;

/**
 * 发货地址Service接口
 * 
 * @author liuzhimin
 * @date 2020-12-21
 */
public interface IEpsShippingAddressService 
{
    /**
     * 查询发货地址
     * 
     * @param id 发货地址ID
     * @return 发货地址
     */
    public EpsShippingAddress selectEpsShippingAddressById(Long id);

    /**
     * 查询发货地址列表
     * 
     * @param epsShippingAddress 发货地址
     * @return 发货地址集合
     */
    public List<EpsShippingAddress> selectEpsShippingAddressList(EpsShippingAddress epsShippingAddress);

    /**
     * 新增发货地址
     * 
     * @param epsShippingAddress 发货地址
     * @return 结果
     */
    public int insertEpsShippingAddress(EpsShippingAddress epsShippingAddress);

    /**
     * 修改发货地址
     * 
     * @param epsShippingAddress 发货地址
     * @return 结果
     */
    public int updateEpsShippingAddress(EpsShippingAddress epsShippingAddress);

    /**
     * 批量修改发货是否默认地址为不默认
     *
     * @param epsShippingAddress 发货地址
     * @return 结果
     */
    public int batchUpdateDefaultAddress(EpsShippingAddress epsShippingAddress);

    /**
     * 批量删除发货地址
     * 
     * @param ids 需要删除的发货地址ID
     * @return 结果
     */
    public int deleteEpsShippingAddressByIds(Long[] ids);

    /**
     * 删除发货地址信息
     * 
     * @param id 发货地址ID
     * @return 结果
     */
    public int deleteEpsShippingAddressById(Long id);
}
