package com.volvo;

import com.volvo.entity.Account;
import com.volvo.model.dto.AccountDTO;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

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
//    @MockBean
//    private AccountMapper accountMapper;
//
//    /**
//     * 实例化 AccountService
//     */
//    @Autowired
//    private AccountService accountService;

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
}
