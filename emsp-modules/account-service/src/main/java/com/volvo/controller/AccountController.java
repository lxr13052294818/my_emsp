package com.volvo.controller;

import com.volvo.model.dto.AccountDTO;
import com.volvo.model.vo.AccountVO;
import com.volvo.model.vo.RR;
import com.volvo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 描述: 账户管理
 *
 * @author lxr
 * @date 2025/2/23
 */
@RestController
@RefreshScope
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Value("${spring.datasource.url}")
    private String url;

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
        System.out.println("url===============" + url);
        try {
            AccountVO accountVO =accountService.getAccount(id).toCompletableFuture().get();
            System.out.println("AccountVO啊啊啊啊啊啊 =========== " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            return RR.ok(accountVO);
        } catch (Throwable e) {
            e.printStackTrace();
            return RR.fail("查询失败");
        }
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
