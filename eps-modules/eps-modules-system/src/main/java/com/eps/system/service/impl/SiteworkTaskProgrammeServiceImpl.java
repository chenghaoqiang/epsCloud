package com.eps.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eps.system.mapper.SiteworkTaskProgrammeMapper;
import com.eps.system.domain.SiteworkTaskProgramme;
import com.eps.system.service.ISiteworkTaskProgrammeService;

/**
 * 作业方案信息Service业务层处理
 * 
 * @author chenghq
 * @date 2021-05-18
 */
@Service
public class SiteworkTaskProgrammeServiceImpl implements ISiteworkTaskProgrammeService 
{
    @Autowired
    private SiteworkTaskProgrammeMapper siteworkTaskProgrammeMapper;

    /**
     * 查询作业方案信息
     * 
     * @param objId 作业方案信息ID
     * @return 作业方案信息
     */
    @Override
    public SiteworkTaskProgramme selectSiteworkTaskProgrammeById(String objId)
    {
        return siteworkTaskProgrammeMapper.selectSiteworkTaskProgrammeById(objId);
    }

    /**
     * 查询作业方案信息列表
     * 
     * @param siteworkTaskProgramme 作业方案信息
     * @return 作业方案信息
     */
    @Override
    public List<SiteworkTaskProgramme> selectSiteworkTaskProgrammeList(SiteworkTaskProgramme siteworkTaskProgramme)
    {
        return siteworkTaskProgrammeMapper.selectSiteworkTaskProgrammeList(siteworkTaskProgramme);
    }

    /**
     * 新增作业方案信息
     * 
     * @param siteworkTaskProgramme 作业方案信息
     * @return 结果
     */
    @Override
    public int insertSiteworkTaskProgramme(SiteworkTaskProgramme siteworkTaskProgramme)
    {
        return siteworkTaskProgrammeMapper.insertSiteworkTaskProgramme(siteworkTaskProgramme);
    }

    /**
     * 修改作业方案信息
     * 
     * @param siteworkTaskProgramme 作业方案信息
     * @return 结果
     */
    @Override
    public int updateSiteworkTaskProgramme(SiteworkTaskProgramme siteworkTaskProgramme)
    {
        return siteworkTaskProgrammeMapper.updateSiteworkTaskProgramme(siteworkTaskProgramme);
    }

    /**
     * 批量删除作业方案信息
     * 
     * @param objIds 需要删除的作业方案信息ID
     * @return 结果
     */
    @Override
    public int deleteSiteworkTaskProgrammeByIds(String[] objIds)
    {
        return siteworkTaskProgrammeMapper.deleteSiteworkTaskProgrammeByIds(objIds);
    }

    /**
     * 删除作业方案信息信息
     * 
     * @param objId 作业方案信息ID
     * @return 结果
     */
    @Override
    public int deleteSiteworkTaskProgrammeById(String objId)
    {
        return siteworkTaskProgrammeMapper.deleteSiteworkTaskProgrammeById(objId);
    }
}
