package com.eps.console.controller;

import com.eps.common.core.utils.SecurityUtils;
import com.eps.common.core.utils.poi.ExcelUtil;
import com.eps.common.core.web.controller.BaseController;
import com.eps.common.core.web.domain.AjaxResult;
import com.eps.common.core.web.page.TableDataInfo;
import com.eps.common.log.annotation.Log;
import com.eps.common.log.enums.BusinessType;
import com.eps.common.security.annotation.PreAuthorize;
import com.eps.console.domain.EpsShippingAddress;
import com.eps.console.service.IEpsShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 发货地址Controller
 * 
 * @author liuzhimin
 * @date 2020-12-21
 */
@RestController
@RequestMapping("/address")
public class EpsShippingAddressController extends BaseController
{
    @Autowired
    private IEpsShippingAddressService epsShippingAddressService;

    private static final String HAS_DEFAULT = "1";

    /**
     * 查询发货地址列表
     */
    @PreAuthorize(hasPermi = "console:address:list")
    @GetMapping("/list")
    public TableDataInfo list(EpsShippingAddress epsShippingAddress)
    {
        Long userId = SecurityUtils.getUserId();
        epsShippingAddress.setUserId(userId);
        startPage();
        List<EpsShippingAddress> list = epsShippingAddressService.selectEpsShippingAddressList(epsShippingAddress);
        return getDataTable(list);
    }

    /**
     * 导出发货地址列表
     */
    @PreAuthorize(hasPermi = "console:address:export")
    @Log(title = "发货地址", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EpsShippingAddress epsShippingAddress) throws IOException
    {
        Long userId = SecurityUtils.getUserId();
        epsShippingAddress.setUserId(userId);
        List<EpsShippingAddress> list = epsShippingAddressService.selectEpsShippingAddressList(epsShippingAddress);
        ExcelUtil<EpsShippingAddress> util = new ExcelUtil<EpsShippingAddress>(EpsShippingAddress.class);
        util.exportExcel(response, list, "address");
    }

    /**
     * 获取发货地址详细信息
     */
    @PreAuthorize(hasPermi = "console:address:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(epsShippingAddressService.selectEpsShippingAddressById(id));
    }

    /**
     * 新增发货地址
     */
    @PreAuthorize(hasPermi = "console:address:add")
    @Log(title = "发货地址", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EpsShippingAddress epsShippingAddress)
    {
        int row = epsShippingAddressService.insertEpsShippingAddress(epsShippingAddress);
        if(epsShippingAddress.getHasDefault().equals(HAS_DEFAULT)) {
            Long userId = SecurityUtils.getUserId();
            epsShippingAddress.setUserId(userId);
            epsShippingAddressService.batchUpdateDefaultAddress(epsShippingAddress);
        }
        return toAjax(row);
    }

    /**
     * 修改发货地址
     */
    @PreAuthorize(hasPermi = "console:address:edit")
    @Log(title = "发货地址", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EpsShippingAddress epsShippingAddress)
    {
        int row = epsShippingAddressService.updateEpsShippingAddress(epsShippingAddress);
        if(epsShippingAddress.getHasDefault().equals(HAS_DEFAULT)) {
            Long userId = SecurityUtils.getUserId();
            epsShippingAddress.setUserId(userId);
            epsShippingAddressService.batchUpdateDefaultAddress(epsShippingAddress);
        }
        return toAjax(row);
    }

    /**
     * 删除发货地址
     */
    @PreAuthorize(hasPermi = "console:address:remove")
    @Log(title = "发货地址", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(epsShippingAddressService.deleteEpsShippingAddressByIds(ids));
    }
}
