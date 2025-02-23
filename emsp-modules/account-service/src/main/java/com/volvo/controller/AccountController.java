package com.volvo.controller;

import com.volvo.model.dto.AccountDTO;
import com.volvo.model.vo.AccountVO;
import com.volvo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 描述:
 *
 * @author lxr
 * @date 2025/2/23
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /**
     * 创建账户
     *
     * @param accountDTO
     */
    @PostMapping
    public void createAccount(@RequestBody AccountDTO accountDTO) {
        accountService.createAccount(accountDTO);
    }

    /**
     * 查询账户
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public AccountVO getAccount(@PathVariable Long id) {
        return accountService.getAccount(id);
    }

    /**
     * 更改账户状态
     *
     * @param id
     * @param status
     */
    @PutMapping("/{id}/status")
    public void changeAccountStatus(@PathVariable Long id, @RequestParam String status) {
        accountService.changeAccountStatus(id, status);
    }
}
