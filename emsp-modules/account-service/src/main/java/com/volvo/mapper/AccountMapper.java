package com.volvo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.volvo.entity.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * 描述:
 *
 * @author lxr
 * @date 2025/2/23
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}
