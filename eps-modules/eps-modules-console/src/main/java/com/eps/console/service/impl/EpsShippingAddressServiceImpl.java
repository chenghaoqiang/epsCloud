package com.eps.console.service.impl;

import com.eps.common.core.utils.DateUtils;
import com.eps.console.domain.EpsShippingAddress;
import com.eps.console.mapper.EpsShippingAddressMapper;
import com.eps.console.service.IEpsShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 发货地址Service业务层处理
 * 
 * @author liuzhimin
 * @date 2020-12-21
 */
@Service
public class EpsShippingAddressServiceImpl implements IEpsShippingAddressService 
{
    @Autowired
    private EpsShippingAddressMapper epsShippingAddressMapper;

    /**
     * 查询发货地址
     * 
     * @param id 发货地址ID
     * @return 发货地址
     */
    @Override
    public EpsShippingAddress selectEpsShippingAddressById(Long id)
    {
        return epsShippingAddressMapper.selectEpsShippingAddressById(id);
    }

    /**
     * 查询发货地址列表
     * 
     * @param epsShippingAddress 发货地址
     * @return 发货地址
     */
    @Override
    public List<EpsShippingAddress> selectEpsShippingAddressList(EpsShippingAddress epsShippingAddress)
    {
        return epsShippingAddressMapper.selectEpsShippingAddressList(epsShippingAddress);
    }

    /**
     * 新增发货地址
     * 
     * @param epsShippingAddress 发货地址
     * @return 结果
     */
    @Override
    public int insertEpsShippingAddress(EpsShippingAddress epsShippingAddress)
    {
        epsShippingAddress.setCreateTime(DateUtils.getNowDate());
        return epsShippingAddressMapper.insertEpsShippingAddress(epsShippingAddress);
    }

    /**
     * 修改发货地址
     * 
     * @param epsShippingAddress 发货地址
     * @return 结果
     */
    @Override
    public int updateEpsShippingAddress(EpsShippingAddress epsShippingAddress)
    {
        epsShippingAddress.setUpdateTime(DateUtils.getNowDate());
        return epsShippingAddressMapper.updateEpsShippingAddress(epsShippingAddress);
    }

    @Override
    public int batchUpdateDefaultAddress(EpsShippingAddress epsShippingAddress) {
        return epsShippingAddressMapper.batchUpdateDefaultAddress(epsShippingAddress);
    }

    /**
     * 批量删除发货地址
     * 
     * @param ids 需要删除的发货地址ID
     * @return 结果
     */
    @Override
    public int deleteEpsShippingAddressByIds(Long[] ids)
    {
        return epsShippingAddressMapper.deleteEpsShippingAddressByIds(ids);
    }

    /**
     * 删除发货地址信息
     * 
     * @param id 发货地址ID
     * @return 结果
     */
    @Override
    public int deleteEpsShippingAddressById(Long id)
    {
        return epsShippingAddressMapper.deleteEpsShippingAddressById(id);
    }
}
