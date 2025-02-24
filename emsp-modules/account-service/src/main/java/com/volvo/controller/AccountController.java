package com.volvo.controller;

import com.volvo.model.dto.AccountDTO;
import com.volvo.model.vo.AccountVO;
import com.volvo.model.vo.RR;
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
    public RR<String> createAccount(@RequestBody AccountDTO accountDTO) {
        accountService.createAccount(accountDTO);
        return RR.ok("创建成功");
    }

    /**
     * 查询账户
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public RR<AccountVO> getAccount(@PathVariable Long id) {
        return RR.ok(accountService.getAccount(id));
    }

    /**
     * 更改账户状态
     *
     * @param id
     * @param status
     */
    @PutMapping("/{id}/status")
    public RR<String> changeAccountStatus(@PathVariable Long id, @RequestParam String status) {
        accountService.changeAccountStatus(id, status);
        return RR.ok("更改成功");
    }
}
