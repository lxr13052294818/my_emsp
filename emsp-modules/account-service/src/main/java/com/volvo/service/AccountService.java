package com.volvo.service;

import com.volvo.entity.Account;
import com.volvo.mapper.AccountMapper;
import com.volvo.model.dto.AccountDTO;
import com.volvo.model.vo.AccountVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述:
 *
 * @author lxr
 * @date 2025/2/23
 */
@Service
@Slf4j
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    /**
     * 创建账户
     *
     * @param accountDTO
     */
    public void createAccount(AccountDTO accountDTO) {
        if (accountDTO == null) {
            return;
        }
        // AccountDTO 转 Account
        Account account = Account.builder().build();
        BeanUtils.copyProperties(accountDTO, account);
        // 插入数据库
        if (accountMapper.insert(account) < 1) {
            log.error("创建账户失败: {}", account);
        }
    }

    /**
     * 查询账户
     *
     * @param id
     * @return
     */
    public AccountVO getAccount(Long id) {
        Account account = accountMapper.selectById(id);
        if (account == null) {
            return null;
        }
        // 转 AccountDTO
        AccountVO accountVO = AccountVO.builder().build();
        BeanUtils.copyProperties(account, accountVO);
        return accountVO;
    }

    /**
     * 更改账户状态
     *
     * @param id
     * @param status
     */
    public void changeAccountStatus(Long id, String status) {
        if (id == null || status == null) {
            return;
        }
        Account account = accountMapper.selectById(id);
        if (account == null) {
            return;
        }
        account.setStatus(status);
        if (accountMapper.updateById(account) < 1) {
            log.error("更改账户状态失败: {}", account);
        }
    }
}
