package com.eps.pay.controller;

import com.eps.common.core.utils.SecurityUtils;
import com.eps.common.core.utils.poi.ExcelUtil;
import com.eps.common.core.web.controller.BaseController;
import com.eps.common.core.web.page.TableDataInfo;
import com.eps.common.log.annotation.Log;
import com.eps.common.log.enums.BusinessType;
import com.eps.common.security.annotation.PreAuthorize;
import com.eps.pay.domain.EpsRechargeOrder;
import com.eps.pay.service.IEpsRechargeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 充值订单Controller
 * 
 * @author liuzhimin
 * @date 2021-02-05
 */
@RestController
@RequestMapping("/order")
public class EpsRechargeOrderController extends BaseController
{
    @Autowired
    private IEpsRechargeOrderService epsRechargeOrderService;


    /**
     * 查询充值订单列表
     */
    @PreAuthorize(hasPermi = "pay:order:list")
    @GetMapping("/list")
    public TableDataInfo list(EpsRechargeOrder epsRechargeOrder)
    {
        Long userId = SecurityUtils.getUserId();
        epsRechargeOrder.setUserId(userId);
        startPage();
        List<EpsRechargeOrder> list = epsRechargeOrderService.selectEpsRechargeOrderList(epsRechargeOrder);
        return getDataTable(list);
    }

    /**
     * 导出充值订单列表
     */
    @PreAuthorize(hasPermi = "pay:order:export")
    @Log(title = "充值订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EpsRechargeOrder epsRechargeOrder) throws IOException
    {
        Long userId = SecurityUtils.getUserId();
        epsRechargeOrder.setUserId(userId);
        List<EpsRechargeOrder> list = epsRechargeOrderService.selectEpsRechargeOrderList(epsRechargeOrder);
        ExcelUtil<EpsRechargeOrder> util = new ExcelUtil<EpsRechargeOrder>(EpsRechargeOrder.class);
        util.exportExcel(response, list, "order");
    }
}
