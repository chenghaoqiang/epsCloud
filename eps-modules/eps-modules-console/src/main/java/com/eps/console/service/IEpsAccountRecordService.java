package com.eps.console.service;

import com.eps.console.domain.EpsAccountRecord;

import java.util.List;

/**
 * 账户流水Service接口
 * 
 * @author liuzhimin
 * @date 2021-02-07
 */
public interface IEpsAccountRecordService 
{
    /**
     * 查询账户流水
     * 
     * @param id 账户流水ID
     * @return 账户流水
     */
    public EpsAccountRecord selectEpsAccountRecordById(Long id);

    /**
     * 查询账户流水列表
     * 
     * @param epsAccountRecord 账户流水
     * @return 账户流水集合
     */
    public List<EpsAccountRecord> selectEpsAccountRecordList(EpsAccountRecord epsAccountRecord);

    /**
     * 新增账户流水
     * 
     * @param epsAccountRecord 账户流水
     * @return 结果
     */
    public int insertEpsAccountRecord(EpsAccountRecord epsAccountRecord);

    /**
     * 修改账户流水
     * 
     * @param epsAccountRecord 账户流水
     * @return 结果
     */
    public int updateEpsAccountRecord(EpsAccountRecord epsAccountRecord);

    /**
     * 批量删除账户流水
     * 
     * @param ids 需要删除的账户流水ID
     * @return 结果
     */
    public int deleteEpsAccountRecordByIds(Long[] ids);

    /**
     * 删除账户流水信息
     * 
     * @param id 账户流水ID
     * @return 结果
     */
    public int deleteEpsAccountRecordById(Long id);
}
