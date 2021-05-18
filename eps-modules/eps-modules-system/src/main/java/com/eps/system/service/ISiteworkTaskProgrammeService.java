package com.eps.system.service;

import java.util.List;
import com.eps.system.domain.SiteworkTaskProgramme;

/**
 * 作业方案信息Service接口
 * 
 * @author chenghq
 * @date 2021-05-18
 */
public interface ISiteworkTaskProgrammeService 
{
    /**
     * 查询作业方案信息
     * 
     * @param objId 作业方案信息ID
     * @return 作业方案信息
     */
    public SiteworkTaskProgramme selectSiteworkTaskProgrammeById(String objId);

    /**
     * 查询作业方案信息列表
     * 
     * @param siteworkTaskProgramme 作业方案信息
     * @return 作业方案信息集合
     */
    public List<SiteworkTaskProgramme> selectSiteworkTaskProgrammeList(SiteworkTaskProgramme siteworkTaskProgramme);

    /**
     * 新增作业方案信息
     * 
     * @param siteworkTaskProgramme 作业方案信息
     * @return 结果
     */
    public int insertSiteworkTaskProgramme(SiteworkTaskProgramme siteworkTaskProgramme);

    /**
     * 修改作业方案信息
     * 
     * @param siteworkTaskProgramme 作业方案信息
     * @return 结果
     */
    public int updateSiteworkTaskProgramme(SiteworkTaskProgramme siteworkTaskProgramme);

    /**
     * 批量删除作业方案信息
     * 
     * @param objIds 需要删除的作业方案信息ID
     * @return 结果
     */
    public int deleteSiteworkTaskProgrammeByIds(String[] objIds);

    /**
     * 删除作业方案信息信息
     * 
     * @param objId 作业方案信息ID
     * @return 结果
     */
    public int deleteSiteworkTaskProgrammeById(String objId);
}
