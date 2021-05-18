package com.eps.console.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eps.common.log.annotation.Log;
import com.eps.common.log.enums.BusinessType;
import com.eps.common.security.annotation.PreAuthorize;
import com.eps.console.domain.EpsGoods;
import com.eps.console.service.IEpsGoodsService;
import com.eps.common.core.web.controller.BaseController;
import com.eps.common.core.web.domain.AjaxResult;
import com.eps.common.core.utils.poi.ExcelUtil;
import com.eps.common.core.web.page.TableDataInfo;

/**
 * 商品Controller
 * 
 * @author liuzhimin
 * @date 2021-02-09
 */
@RestController
@RequestMapping("/goods")
public class EpsGoodsController extends BaseController
{
    @Autowired
    private IEpsGoodsService epsGoodsService;

    /**
     * 查询商品列表
     */
    @PreAuthorize(hasPermi = "console:goods:list")
    @GetMapping("/list")
    public TableDataInfo list(EpsGoods epsGoods)
    {
        startPage();
        List<EpsGoods> list = epsGoodsService.selectEpsGoodsList(epsGoods);
        return getDataTable(list);
    }

    /**
     * 导出商品列表
     */
    @PreAuthorize(hasPermi = "console:goods:export")
    @Log(title = "商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EpsGoods epsGoods) throws IOException
    {
        List<EpsGoods> list = epsGoodsService.selectEpsGoodsList(epsGoods);
        ExcelUtil<EpsGoods> util = new ExcelUtil<EpsGoods>(EpsGoods.class);
        util.exportExcel(response, list, "goods");
    }

    /**
     * 获取商品详细信息
     */
    @PreAuthorize(hasPermi = "console:goods:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(epsGoodsService.selectEpsGoodsById(id));
    }

    /**
     * 新增商品
     */
    @PreAuthorize(hasPermi = "console:goods:add")
    @Log(title = "商品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EpsGoods epsGoods)
    {
        return toAjax(epsGoodsService.insertEpsGoods(epsGoods));
    }

    /**
     * 修改商品
     */
    @PreAuthorize(hasPermi = "console:goods:edit")
    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EpsGoods epsGoods)
    {
        return toAjax(epsGoodsService.updateEpsGoods(epsGoods));
    }

    /**
     * 删除商品
     */
    @PreAuthorize(hasPermi = "console:goods:remove")
    @Log(title = "商品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(epsGoodsService.deleteEpsGoodsByIds(ids));
    }
}
