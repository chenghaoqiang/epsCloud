package com.eps.console.service;

import java.util.List;
import com.eps.console.domain.EpsGoods;

/**
 * 商品Service接口
 * 
 * @author liuzhimin
 * @date 2021-02-09
 */
public interface IEpsGoodsService 
{
    /**
     * 查询商品
     * 
     * @param id 商品ID
     * @return 商品
     */
    public EpsGoods selectEpsGoodsById(Long id);

    /**
     * 查询商品列表
     * 
     * @param epsGoods 商品
     * @return 商品集合
     */
    public List<EpsGoods> selectEpsGoodsList(EpsGoods epsGoods);

    /**
     * 新增商品
     * 
     * @param epsGoods 商品
     * @return 结果
     */
    public int insertEpsGoods(EpsGoods epsGoods);

    /**
     * 修改商品
     * 
     * @param epsGoods 商品
     * @return 结果
     */
    public int updateEpsGoods(EpsGoods epsGoods);

    /**
     * 批量删除商品
     * 
     * @param ids 需要删除的商品ID
     * @return 结果
     */
    public int deleteEpsGoodsByIds(Long[] ids);

    /**
     * 删除商品信息
     * 
     * @param id 商品ID
     * @return 结果
     */
    public int deleteEpsGoodsById(Long id);
}
