package com.eps.common.core.exception.file;

import com.eps.common.core.exception.BaseException;

/**
 * 文件信息异常类
 * 
 * @author liuizhimin
 */
public class FileException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}
