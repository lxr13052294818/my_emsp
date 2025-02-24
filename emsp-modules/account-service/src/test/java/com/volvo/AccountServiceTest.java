package com.volvo;

import com.volvo.entity.Account;
import com.volvo.mapper.AccountMapper;
import com.volvo.model.dto.AccountDTO;
import com.volvo.model.vo.AccountVO;
import com.volvo.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

/**
 * 描述: AccountService 的测试类
 *
 * @author lxr
 * @date 2025/2/23
 */
@SpringBootTest
public class AccountServiceTest {

    /**
     * 模拟 AccountMapper
     */
    @MockBean
    private AccountMapper accountMapper;

    /**
     * 实例化 AccountService
     */
    @Autowired
    private AccountService accountService;

    private AccountDTO accountDTO;
    private Account account;

    @BeforeEach
    public void setUp() {
        accountDTO = AccountDTO.builder()
                .id(1L)
                .email("test@volvo.com")
                .status("CREATED")
                .build();
        account = Account.builder()
                .id(1L)
                .email("test@volvo.com")
                .status("CREATED")
                .build();
    }

    /**
     * 测试 createAccount 方法
     */
    @Test
    public void testCreateAccount() {
        // 模拟 accountMapper.insert() 方法
        when(accountMapper.insert(any(Account.class))).thenReturn(1);

        // 调用 service 方法
        accountService.createAccount(accountDTO);

        System.out.println("创建成功");
    }

    /**
     * 测试 getAccount 方法
     */
    @Test
    public void testGetAccount() {
        // 模拟 accountMapper.selectById() 返回数据
        when(accountMapper.selectById(1L)).thenReturn(account);

        // 调用 service 方法
        AccountVO accountVO = accountService.getAccount(1L);

        System.out.println("accountVO = " + accountVO);
    }

    /**
     * 测试 changeAccountStatus 方法
     */
    @Test
    public void testChangeAccountStatus() {
        // 模拟 accountMapper.selectById() 返回数据
        when(accountMapper.selectById(1L)).thenReturn(account);
        // 模拟 accountMapper.updateById() 返回数据
        when(accountMapper.updateById(any(Account.class))).thenReturn(1);

        // 调用 service 方法
        accountService.changeAccountStatus(1L, "inactive");

        System.out.println("更新成功");
    }
}
