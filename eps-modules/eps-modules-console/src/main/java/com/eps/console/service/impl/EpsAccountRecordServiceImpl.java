package com.eps.console.service.impl;

import com.eps.common.core.utils.DateUtils;
import com.eps.console.domain.EpsAccountRecord;
import com.eps.console.mapper.EpsAccountRecordMapper;
import com.eps.console.service.IEpsAccountRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账户流水Service业务层处理
 * 
 * @author liuzhimin
 * @date 2021-02-07
 */
@Service
public class EpsAccountRecordServiceImpl implements IEpsAccountRecordService
{
    @Autowired
    private EpsAccountRecordMapper epsAccountRecordMapper;

    /**
     * 查询账户流水
     * 
     * @param id 账户流水ID
     * @return 账户流水
     */
    @Override
    public EpsAccountRecord selectEpsAccountRecordById(Long id)
    {
        return epsAccountRecordMapper.selectEpsAccountRecordById(id);
    }

    /**
     * 查询账户流水列表
     * 
     * @param epsAccountRecord 账户流水
     * @return 账户流水
     */
    @Override
    public List<EpsAccountRecord> selectEpsAccountRecordList(EpsAccountRecord epsAccountRecord)
    {
        return epsAccountRecordMapper.selectEpsAccountRecordList(epsAccountRecord);
    }

    /**
     * 新增账户流水
     * 
     * @param epsAccountRecord 账户流水
     * @return 结果
     */
    @Override
    public int insertEpsAccountRecord(EpsAccountRecord epsAccountRecord)
    {
        epsAccountRecord.setCreateTime(DateUtils.getNowDate());
        return epsAccountRecordMapper.insertEpsAccountRecord(epsAccountRecord);
    }

    /**
     * 修改账户流水
     * 
     * @param epsAccountRecord 账户流水
     * @return 结果
     */
    @Override
    public int updateEpsAccountRecord(EpsAccountRecord epsAccountRecord)
    {
        return epsAccountRecordMapper.updateEpsAccountRecord(epsAccountRecord);
    }

    /**
     * 批量删除账户流水
     * 
     * @param ids 需要删除的账户流水ID
     * @return 结果
     */
    @Override
    public int deleteEpsAccountRecordByIds(Long[] ids)
    {
        return epsAccountRecordMapper.deleteEpsAccountRecordByIds(ids);
    }

    /**
     * 删除账户流水信息
     * 
     * @param id 账户流水ID
     * @return 结果
     */
    @Override
    public int deleteEpsAccountRecordById(Long id)
    {
        return epsAccountRecordMapper.deleteEpsAccountRecordById(id);
    }
}
