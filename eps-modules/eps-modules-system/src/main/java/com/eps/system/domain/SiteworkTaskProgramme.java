package com.eps.system.domain;

import java.util.Date;

import com.eps.common.core.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.eps.common.core.annotation.Excel;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 作业方案信息对象 t_ds_sitework_task_programme
 * 
 * @author chenghq
 * @date 2021-05-18
 */
public class SiteworkTaskProgramme extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 标识 */
    private String objId;

    /** 计划编号 */
    @Excel(name = "计划编号")
    private String planNo;

    /** 计划类型 */
    @Excel(name = "计划类型")
    private String planType;

    /** 计划版本 */
    @Excel(name = "计划版本")
    private String planVersion;

    /** 勘察编号 */
    @Excel(name = "勘察编号")
    private String surveyNo;

    /** 检修范围 */
    @Excel(name = "检修范围")
    private String repairRange;

    /** 检修开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检修开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date repairStartTime;

    /** 检修结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检修结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date repairEndTime;

    /** 停电开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "停电开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date powercutStartTime;

    /** 停电结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "停电结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date powercutEndTime;

    /** 列行检修项目 */
    @Excel(name = "列行检修项目")
    private String ordinaryRepairItems;

    /** 特殊性检修项目 */
    @Excel(name = "特殊性检修项目")
    private String specialRepairItems;

    /** 技改项目 */
    @Excel(name = "技改项目")
    private String technicalItems;

    /** 主要消缺项目 */
    @Excel(name = "主要消缺项目")
    private String defectTreatmentItems;

    /** 组织措施 */
    @Excel(name = "组织措施")
    private String organizationMeasure;

    /** 安全措施 */
    @Excel(name = "安全措施")
    private String securityMeasure;

    /** 技术措施 */
    @Excel(name = "技术措施")
    private String technologyMeasure;

    /** 进度控制措施 */
    @Excel(name = "进度控制措施")
    private String progressControlMeasure;

    /** 标准卡数量 */
    @Excel(name = "标准卡数量")
    private Long standardWorkNumber;

    /** 验收卡数量 */
    @Excel(name = "验收卡数量")
    private Long repairCheckNumber;

    public void setObjId(String objId) 
    {
        this.objId = objId;
    }

    public String getObjId() 
    {
        return objId;
    }
    public void setPlanNo(String planNo) 
    {
        this.planNo = planNo;
    }

    public String getPlanNo() 
    {
        return planNo;
    }
    public void setPlanType(String planType) 
    {
        this.planType = planType;
    }

    public String getPlanType() 
    {
        return planType;
    }
    public void setPlanVersion(String planVersion) 
    {
        this.planVersion = planVersion;
    }

    public String getPlanVersion() 
    {
        return planVersion;
    }
    public void setSurveyNo(String surveyNo) 
    {
        this.surveyNo = surveyNo;
    }

    public String getSurveyNo() 
    {
        return surveyNo;
    }
    public void setRepairRange(String repairRange) 
    {
        this.repairRange = repairRange;
    }

    public String getRepairRange() 
    {
        return repairRange;
    }
    public void setRepairStartTime(Date repairStartTime) 
    {
        this.repairStartTime = repairStartTime;
    }

    public Date getRepairStartTime() 
    {
        return repairStartTime;
    }
    public void setRepairEndTime(Date repairEndTime) 
    {
        this.repairEndTime = repairEndTime;
    }

    public Date getRepairEndTime() 
    {
        return repairEndTime;
    }
    public void setPowercutStartTime(Date powercutStartTime) 
    {
        this.powercutStartTime = powercutStartTime;
    }

    public Date getPowercutStartTime() 
    {
        return powercutStartTime;
    }
    public void setPowercutEndTime(Date powercutEndTime) 
    {
        this.powercutEndTime = powercutEndTime;
    }

    public Date getPowercutEndTime() 
    {
        return powercutEndTime;
    }
    public void setOrdinaryRepairItems(String ordinaryRepairItems) 
    {
        this.ordinaryRepairItems = ordinaryRepairItems;
    }

    public String getOrdinaryRepairItems() 
    {
        return ordinaryRepairItems;
    }
    public void setSpecialRepairItems(String specialRepairItems) 
    {
        this.specialRepairItems = specialRepairItems;
    }

    public String getSpecialRepairItems() 
    {
        return specialRepairItems;
    }
    public void setTechnicalItems(String technicalItems) 
    {
        this.technicalItems = technicalItems;
    }

    public String getTechnicalItems() 
    {
        return technicalItems;
    }
    public void setDefectTreatmentItems(String defectTreatmentItems) 
    {
        this.defectTreatmentItems = defectTreatmentItems;
    }

    public String getDefectTreatmentItems() 
    {
        return defectTreatmentItems;
    }
    public void setOrganizationMeasure(String organizationMeasure) 
    {
        this.organizationMeasure = organizationMeasure;
    }

    public String getOrganizationMeasure() 
    {
        return organizationMeasure;
    }
    public void setSecurityMeasure(String securityMeasure) 
    {
        this.securityMeasure = securityMeasure;
    }

    public String getSecurityMeasure() 
    {
        return securityMeasure;
    }
    public void setTechnologyMeasure(String technologyMeasure) 
    {
        this.technologyMeasure = technologyMeasure;
    }

    public String getTechnologyMeasure() 
    {
        return technologyMeasure;
    }
    public void setProgressControlMeasure(String progressControlMeasure) 
    {
        this.progressControlMeasure = progressControlMeasure;
    }

    public String getProgressControlMeasure() 
    {
        return progressControlMeasure;
    }
    public void setStandardWorkNumber(Long standardWorkNumber) 
    {
        this.standardWorkNumber = standardWorkNumber;
    }

    public Long getStandardWorkNumber() 
    {
        return standardWorkNumber;
    }
    public void setRepairCheckNumber(Long repairCheckNumber) 
    {
        this.repairCheckNumber = repairCheckNumber;
    }

    public Long getRepairCheckNumber() 
    {
        return repairCheckNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("objId", getObjId())
            .append("planNo", getPlanNo())
            .append("planType", getPlanType())
            .append("planVersion", getPlanVersion())
            .append("surveyNo", getSurveyNo())
            .append("repairRange", getRepairRange())
            .append("repairStartTime", getRepairStartTime())
            .append("repairEndTime", getRepairEndTime())
            .append("powercutStartTime", getPowercutStartTime())
            .append("powercutEndTime", getPowercutEndTime())
            .append("ordinaryRepairItems", getOrdinaryRepairItems())
            .append("specialRepairItems", getSpecialRepairItems())
            .append("technicalItems", getTechnicalItems())
            .append("defectTreatmentItems", getDefectTreatmentItems())
            .append("organizationMeasure", getOrganizationMeasure())
            .append("securityMeasure", getSecurityMeasure())
            .append("technologyMeasure", getTechnologyMeasure())
            .append("progressControlMeasure", getProgressControlMeasure())
            .append("standardWorkNumber", getStandardWorkNumber())
            .append("repairCheckNumber", getRepairCheckNumber())
            .toString();
    }
}
