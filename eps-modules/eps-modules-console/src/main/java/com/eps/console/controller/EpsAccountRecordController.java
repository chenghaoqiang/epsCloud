package com.eps.console.controller;

import com.eps.common.core.utils.poi.ExcelUtil;
import com.eps.common.core.web.controller.BaseController;
import com.eps.common.core.web.domain.AjaxResult;
import com.eps.common.core.web.page.TableDataInfo;
import com.eps.common.log.annotation.Log;
import com.eps.common.log.enums.BusinessType;
import com.eps.common.security.annotation.PreAuthorize;
import com.eps.console.domain.EpsAccountRecord;
import com.eps.console.service.IEpsAccountRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 账户流水Controller
 * 
 * @author liuzhimin
 * @date 2021-02-07
 */
@RestController
@RequestMapping("/record")
public class EpsAccountRecordController extends BaseController
{
    @Autowired
    private IEpsAccountRecordService epsAccountRecordService;

    /**
     * 查询账户流水列表
     */
    @PreAuthorize(hasPermi = "console:record:list")
    @GetMapping("/list")
    public TableDataInfo list(EpsAccountRecord epsAccountRecord)
    {
        startPage();
        List<EpsAccountRecord> list = epsAccountRecordService.selectEpsAccountRecordList(epsAccountRecord);
        return getDataTable(list);
    }

    /**
     * 导出账户流水列表
     */
    @PreAuthorize(hasPermi = "console:record:export")
    @Log(title = "账户流水", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EpsAccountRecord epsAccountRecord) throws IOException
    {
        List<EpsAccountRecord> list = epsAccountRecordService.selectEpsAccountRecordList(epsAccountRecord);
        ExcelUtil<EpsAccountRecord> util = new ExcelUtil<EpsAccountRecord>(EpsAccountRecord.class);
        util.exportExcel(response, list, "record");
    }

    /**
     * 获取账户流水详细信息
     */
    @PreAuthorize(hasPermi = "console:record:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(epsAccountRecordService.selectEpsAccountRecordById(id));
    }

    /**
     * 新增账户流水
     */
    @PreAuthorize(hasPermi = "console:record:add")
    @Log(title = "账户流水", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EpsAccountRecord epsAccountRecord)
    {
        return toAjax(epsAccountRecordService.insertEpsAccountRecord(epsAccountRecord));
    }

    /**
     * 修改账户流水
     */
    @PreAuthorize(hasPermi = "console:record:edit")
    @Log(title = "账户流水", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EpsAccountRecord epsAccountRecord)
    {
        return toAjax(epsAccountRecordService.updateEpsAccountRecord(epsAccountRecord));
    }

    /**
     * 删除账户流水
     */
    @PreAuthorize(hasPermi = "console:record:remove")
    @Log(title = "账户流水", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(epsAccountRecordService.deleteEpsAccountRecordByIds(ids));
    }
}
