package com.eps.pay.mapper;

import com.eps.pay.domain.EpsRechargeOrder;

import java.util.List;

/**
 * 发货地址Mapper接口
 * 
 * @author liuzhimin
 * @date 2021-02-05
 */
public interface EpsRechargeOrderMapper 
{
    /**
     * 查询发货地址
     * 
     * @param id 发货地址ID
     * @return 发货地址
     */
    public EpsRechargeOrder selectEpsRechargeOrderById(Long id);

    /**
     * 查询发货地址列表
     * 
     * @param epsRechargeOrder 发货地址
     * @return 发货地址集合
     */
    public List<EpsRechargeOrder> selectEpsRechargeOrderList(EpsRechargeOrder epsRechargeOrder);

    /**
     * 新增发货地址
     * 
     * @param epsRechargeOrder 发货地址
     * @return 结果
     */
    public int insertEpsRechargeOrder(EpsRechargeOrder epsRechargeOrder);

    /**
     * 修改发货地址
     * 
     * @param epsRechargeOrder 发货地址
     * @return 结果
     */
    public int updateEpsRechargeOrder(EpsRechargeOrder epsRechargeOrder);

    /**
     * 删除发货地址
     * 
     * @param id 发货地址ID
     * @return 结果
     */
    public int deleteEpsRechargeOrderById(Long id);

    /**
     * 批量删除发货地址
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEpsRechargeOrderByIds(Long[] ids);
}
