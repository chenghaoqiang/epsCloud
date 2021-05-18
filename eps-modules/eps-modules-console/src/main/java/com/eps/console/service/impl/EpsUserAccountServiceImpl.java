package com.eps.console.service.impl;

import com.eps.common.core.utils.DateUtils;
import com.eps.console.domain.EpsUserAccount;
import com.eps.console.mapper.EpsUserAccountMapper;
import com.eps.console.service.IEpsUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户账户Service业务层处理
 * 
 * @author liuzhimin
 * @date 2021-02-07
 */
@Service
public class EpsUserAccountServiceImpl implements IEpsUserAccountService
{
    @Autowired
    private EpsUserAccountMapper epsUserAccountMapper;

    /**
     * 查询用户账户
     * 
     * @param id 用户账户ID
     * @return 用户账户
     */
    @Override
    public EpsUserAccount selectEpsUserAccountById(Long id)
    {
        return epsUserAccountMapper.selectEpsUserAccountById(id);
    }

    /**
     * 查询用户账户
     *
     * @param userId 用户ID
     * @return 用户账户
     */
    @Override
    public EpsUserAccount selectEpsUserAccountByUserId(Long userId) {
        return epsUserAccountMapper.selectEpsUserAccountByUserId(userId);
    }

    /**
     * 查询用户账户列表
     * 
     * @param epsUserAccount 用户账户
     * @return 用户账户
     */
    @Override
    public List<EpsUserAccount> selectEpsUserAccountList(EpsUserAccount epsUserAccount)
    {
        return epsUserAccountMapper.selectEpsUserAccountList(epsUserAccount);
    }

    /**
     * 新增用户账户
     * 
     * @param epsUserAccount 用户账户
     * @return 结果
     */
    @Override
    public int insertEpsUserAccount(EpsUserAccount epsUserAccount)
    {
        epsUserAccount.setCreateTime(DateUtils.getNowDate());
        return epsUserAccountMapper.insertEpsUserAccount(epsUserAccount);
    }

    /**
     * 修改用户账户
     * 
     * @param epsUserAccount 用户账户
     * @return 结果
     */
    @Override
    public int updateEpsUserAccount(EpsUserAccount epsUserAccount)
    {
        return epsUserAccountMapper.updateEpsUserAccount(epsUserAccount);
    }

    /**
     * 批量删除用户账户
     * 
     * @param ids 需要删除的用户账户ID
     * @return 结果
     */
    @Override
    public int deleteEpsUserAccountByIds(Long[] ids)
    {
        return epsUserAccountMapper.deleteEpsUserAccountByIds(ids);
    }

    /**
     * 删除用户账户信息
     * 
     * @param id 用户账户ID
     * @return 结果
     */
    @Override
    public int deleteEpsUserAccountById(Long id)
    {
        return epsUserAccountMapper.deleteEpsUserAccountById(id);
    }
}
