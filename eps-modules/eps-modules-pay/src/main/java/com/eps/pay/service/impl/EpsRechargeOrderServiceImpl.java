package com.eps.pay.service.impl;

import java.util.List;
import com.eps.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eps.pay.mapper.EpsRechargeOrderMapper;
import com.eps.pay.domain.EpsRechargeOrder;
import com.eps.pay.service.IEpsRechargeOrderService;

/**
 * 发货地址Service业务层处理
 * 
 * @author liuzhimin
 * @date 2021-02-05
 */
@Service
public class EpsRechargeOrderServiceImpl implements IEpsRechargeOrderService 
{
    @Autowired
    private EpsRechargeOrderMapper epsRechargeOrderMapper;

    /**
     * 查询充值订单
     * 
     * @param id 充值订单ID
     * @return 充值订单
     */
    @Override
    public EpsRechargeOrder selectEpsRechargeOrderById(Long id)
    {
        return epsRechargeOrderMapper.selectEpsRechargeOrderById(id);
    }

    /**
     * 查询充值订单列表
     * 
     * @param epsRechargeOrder 充值订单
     * @return 充值订单
     */
    @Override
    public List<EpsRechargeOrder> selectEpsRechargeOrderList(EpsRechargeOrder epsRechargeOrder)
    {
        return epsRechargeOrderMapper.selectEpsRechargeOrderList(epsRechargeOrder);
    }

    /**
     * 新增充值订单
     * 
     * @param epsRechargeOrder 充值订单
     * @return 结果
     */
    @Override
    public int insertEpsRechargeOrder(EpsRechargeOrder epsRechargeOrder)
    {
        epsRechargeOrder.setCreateTime(DateUtils.getNowDate());
        return epsRechargeOrderMapper.insertEpsRechargeOrder(epsRechargeOrder);
    }

    /**
     * 修改充值订单
     * 
     * @param epsRechargeOrder 充值订单
     * @return 结果
     */
    @Override
    public int updateEpsRechargeOrder(EpsRechargeOrder epsRechargeOrder)
    {
        return epsRechargeOrderMapper.updateEpsRechargeOrder(epsRechargeOrder);
    }

    /**
     * 批量删除充值订单
     * 
     * @param ids 需要删除的充值订单ID
     * @return 结果
     */
    @Override
    public int deleteEpsRechargeOrderByIds(Long[] ids)
    {
        return epsRechargeOrderMapper.deleteEpsRechargeOrderByIds(ids);
    }

    /**
     * 删除充值订单信息
     * 
     * @param id 充值订单ID
     * @return 结果
     */
    @Override
    public int deleteEpsRechargeOrderById(Long id)
    {
        return epsRechargeOrderMapper.deleteEpsRechargeOrderById(id);
    }
}
