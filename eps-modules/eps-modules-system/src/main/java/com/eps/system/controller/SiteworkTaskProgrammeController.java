package com.eps.system.controller;

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
import com.eps.system.domain.SiteworkTaskProgramme;
import com.eps.system.service.ISiteworkTaskProgrammeService;
import com.eps.common.core.web.controller.BaseController;
import com.eps.common.core.web.domain.AjaxResult;
import com.eps.common.core.utils.poi.ExcelUtil;
import com.eps.common.core.web.page.TableDataInfo;

/**
 * 作业方案信息Controller
 * 
 * @author chenghq
 * @date 2021-05-18
 */
@RestController
@RequestMapping("/programme")
public class SiteworkTaskProgrammeController extends BaseController
{
    @Autowired
    private ISiteworkTaskProgrammeService siteworkTaskProgrammeService;

    /**
     * 查询作业方案信息列表
     */
    @PreAuthorize(hasPermi = "system:programme:list")
    @GetMapping("/list")
    public TableDataInfo list(SiteworkTaskProgramme siteworkTaskProgramme)
    {
        startPage();
        List<SiteworkTaskProgramme> list = siteworkTaskProgrammeService.selectSiteworkTaskProgrammeList(siteworkTaskProgramme);
        return getDataTable(list);
    }

    /**
     * 导出作业方案信息列表
     */
    @PreAuthorize(hasPermi = "system:programme:export")
    @Log(title = "作业方案信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SiteworkTaskProgramme siteworkTaskProgramme) throws IOException
    {
        List<SiteworkTaskProgramme> list = siteworkTaskProgrammeService.selectSiteworkTaskProgrammeList(siteworkTaskProgramme);
        ExcelUtil<SiteworkTaskProgramme> util = new ExcelUtil<SiteworkTaskProgramme>(SiteworkTaskProgramme.class);
        util.exportExcel(response, list, "programme");
    }

    /**
     * 获取作业方案信息详细信息
     */
    @PreAuthorize(hasPermi = "system:programme:query")
    @GetMapping(value = "/{objId}")
    public AjaxResult getInfo(@PathVariable("objId") String objId)
    {
        return AjaxResult.success(siteworkTaskProgrammeService.selectSiteworkTaskProgrammeById(objId));
    }

    /**
     * 新增作业方案信息
     */
    @PreAuthorize(hasPermi = "system:programme:add")
    @Log(title = "作业方案信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SiteworkTaskProgramme siteworkTaskProgramme)
    {
        return toAjax(siteworkTaskProgrammeService.insertSiteworkTaskProgramme(siteworkTaskProgramme));
    }

    /**
     * 修改作业方案信息
     */
    @PreAuthorize(hasPermi = "system:programme:edit")
    @Log(title = "作业方案信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SiteworkTaskProgramme siteworkTaskProgramme)
    {
        return toAjax(siteworkTaskProgrammeService.updateSiteworkTaskProgramme(siteworkTaskProgramme));
    }

    /**
     * 删除作业方案信息
     */
    @PreAuthorize(hasPermi = "system:programme:remove")
    @Log(title = "作业方案信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{objIds}")
    public AjaxResult remove(@PathVariable String[] objIds)
    {
        return toAjax(siteworkTaskProgrammeService.deleteSiteworkTaskProgrammeByIds(objIds));
    }
}
