package com.volvo.service;

import com.volvo.entity.Account;
import com.volvo.mapper.AccountMapper;
import com.volvo.model.dto.AccountDTO;
import com.volvo.model.vo.AccountVO;
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
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    // 创建账户
    public void createAccount(AccountDTO accountDTO) {
        Account account = Account.builder()
                .id(accountDTO.getId())
                .email(accountDTO.getEmail())
                .build();
        accountMapper.insert(account);
    }

    // 查询账户
    public AccountVO getAccount(Long id) {
        Account account = accountMapper.selectById(id);

        // 转 AccountDTO
        return AccountVO.toAccountVO(account);
    }

    // 更改账户状态
    public void changeAccountStatus(Long id, String status) {
        Account account = accountMapper.selectById(id);
        account.setStatus(status);
        accountMapper.updateById(account);
    }
}
