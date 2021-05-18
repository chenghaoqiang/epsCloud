package com.eps.console.mapper;

import com.eps.console.domain.EpsUserAccount;

import java.util.List;

/**
 * 用户账户Mapper接口
 * 
 * @author liuzhimin
 * @date 2021-02-07
 */
public interface EpsUserAccountMapper 
{
    /**
     * 查询用户账户
     * 
     * @param id 用户账户ID
     * @return 用户账户
     */
    public EpsUserAccount selectEpsUserAccountById(Long id);

    /**
     * 查询用户账户
     *
     * @param userId 用户ID
     * @return 用户账户
     */
    public EpsUserAccount selectEpsUserAccountByUserId(Long userId);

    /**
     * 查询用户账户列表
     * 
     * @param epsUserAccount 用户账户
     * @return 用户账户集合
     */
    public List<EpsUserAccount> selectEpsUserAccountList(EpsUserAccount epsUserAccount);

    /**
     * 新增用户账户
     * 
     * @param epsUserAccount 用户账户
     * @return 结果
     */
    public int insertEpsUserAccount(EpsUserAccount epsUserAccount);

    /**
     * 修改用户账户
     * 
     * @param epsUserAccount 用户账户
     * @return 结果
     */
    public int updateEpsUserAccount(EpsUserAccount epsUserAccount);

    /**
     * 删除用户账户
     * 
     * @param id 用户账户ID
     * @return 结果
     */
    public int deleteEpsUserAccountById(Long id);

    /**
     * 批量删除用户账户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEpsUserAccountByIds(Long[] ids);
}
