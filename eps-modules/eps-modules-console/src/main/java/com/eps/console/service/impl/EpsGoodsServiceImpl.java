package com.eps.console.service.impl;

import java.util.List;
import com.eps.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eps.console.mapper.EpsGoodsMapper;
import com.eps.console.domain.EpsGoods;
import com.eps.console.service.IEpsGoodsService;

/**
 * 商品Service业务层处理
 * 
 * @author liuzhimin
 * @date 2021-02-09
 */
@Service
public class EpsGoodsServiceImpl implements IEpsGoodsService 
{
    @Autowired
    private EpsGoodsMapper epsGoodsMapper;

    /**
     * 查询商品
     * 
     * @param id 商品ID
     * @return 商品
     */
    @Override
    public EpsGoods selectEpsGoodsById(Long id)
    {
        return epsGoodsMapper.selectEpsGoodsById(id);
    }

    /**
     * 查询商品列表
     * 
     * @param epsGoods 商品
     * @return 商品
     */
    @Override
    public List<EpsGoods> selectEpsGoodsList(EpsGoods epsGoods)
    {
        return epsGoodsMapper.selectEpsGoodsList(epsGoods);
    }

    /**
     * 新增商品
     * 
     * @param epsGoods 商品
     * @return 结果
     */
    @Override
    public int insertEpsGoods(EpsGoods epsGoods)
    {
        epsGoods.setCreateTime(DateUtils.getNowDate());
        return epsGoodsMapper.insertEpsGoods(epsGoods);
    }

    /**
     * 修改商品
     * 
     * @param epsGoods 商品
     * @return 结果
     */
    @Override
    public int updateEpsGoods(EpsGoods epsGoods)
    {
        epsGoods.setUpdateTime(DateUtils.getNowDate());
        return epsGoodsMapper.updateEpsGoods(epsGoods);
    }

    /**
     * 批量删除商品
     * 
     * @param ids 需要删除的商品ID
     * @return 结果
     */
    @Override
    public int deleteEpsGoodsByIds(Long[] ids)
    {
        return epsGoodsMapper.deleteEpsGoodsByIds(ids);
    }

    /**
     * 删除商品信息
     * 
     * @param id 商品ID
     * @return 结果
     */
    @Override
    public int deleteEpsGoodsById(Long id)
    {
        return epsGoodsMapper.deleteEpsGoodsById(id);
    }
}
