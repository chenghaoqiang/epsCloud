package com.eps.console.controller;

import com.eps.common.core.utils.poi.ExcelUtil;
import com.eps.common.core.web.controller.BaseController;
import com.eps.common.core.web.domain.AjaxResult;
import com.eps.common.core.web.page.TableDataInfo;
import com.eps.common.log.annotation.Log;
import com.eps.common.log.enums.BusinessType;
import com.eps.common.security.annotation.PreAuthorize;
import com.eps.console.domain.EpsUserAccount;
import com.eps.console.service.IEpsUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 用户账户Controller
 * 
 * @author liuzhimin
 * @date 2021-02-07
 */
@RestController
@RequestMapping("/account")
public class EpsUserAccountController extends BaseController
{
    @Autowired
    private IEpsUserAccountService epsUserAccountService;

    /**
     * 查询用户账户列表
     */
    @PreAuthorize(hasPermi = "console:account:list")
    @GetMapping("/list")
    public TableDataInfo list(EpsUserAccount epsUserAccount)
    {
        startPage();
        List<EpsUserAccount> list = epsUserAccountService.selectEpsUserAccountList(epsUserAccount);
        return getDataTable(list);
    }

    /**
     * 导出用户账户列表
     */
    @PreAuthorize(hasPermi = "console:account:export")
    @Log(title = "用户账户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EpsUserAccount epsUserAccount) throws IOException
    {
        List<EpsUserAccount> list = epsUserAccountService.selectEpsUserAccountList(epsUserAccount);
        ExcelUtil<EpsUserAccount> util = new ExcelUtil<EpsUserAccount>(EpsUserAccount.class);
        util.exportExcel(response, list, "account");
    }

    /**
     * 获取用户账户详细信息
     */
    @PreAuthorize(hasPermi = "console:account:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(epsUserAccountService.selectEpsUserAccountById(id));
    }

}
