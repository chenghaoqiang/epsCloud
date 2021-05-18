package com.eps.pay.service;

import com.eps.pay.domain.EpsRechargeOrder;

import java.util.List;

/**
 * 充值订单Service接口
 * 
 * @author liuzhimin
 * @date 2021-02-05
 */
public interface IEpsRechargeOrderService 
{
    /**
     * 查询充值订单
     * 
     * @param id 充值订单ID
     * @return 充值订单
     */
    public EpsRechargeOrder selectEpsRechargeOrderById(Long id);

    /**
     * 查询充值订单列表
     * 
     * @param epsRechargeOrder 充值订单
     * @return 充值订单集合
     */
    public List<EpsRechargeOrder> selectEpsRechargeOrderList(EpsRechargeOrder epsRechargeOrder);

    /**
     * 新增充值订单
     * 
     * @param epsRechargeOrder 充值订单
     * @return 结果
     */
    public int insertEpsRechargeOrder(EpsRechargeOrder epsRechargeOrder);

    /**
     * 修改充值订单
     * 
     * @param epsRechargeOrder 充值订单
     * @return 结果
     */
    public int updateEpsRechargeOrder(EpsRechargeOrder epsRechargeOrder);

    /**
     * 批量删除充值订单
     * 
     * @param ids 需要删除的充值订单ID
     * @return 结果
     */
    public int deleteEpsRechargeOrderByIds(Long[] ids);

    /**
     * 删除发货地址信息
     * 
     * @param id 充值订单ID
     * @return 结果
     */
    public int deleteEpsRechargeOrderById(Long id);
}
