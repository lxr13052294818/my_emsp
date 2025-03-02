package com.volvo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.volvo.constant.AccountMsgConstant;
import com.volvo.model.dto.AccountDTO;
import com.volvo.model.vo.AccountVO;
import com.volvo.model.vo.RR;
import com.volvo.service.AccountService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

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
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

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
            return RR.ok(accountService.getAccount(id).toCompletableFuture().get());
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

    /**
     * 分页查询
     *
     * @return
     */
    @GetMapping("/page")
    public RR<IPage> page() {
        return RR.ok(accountService.accountList());
    }

    /**
     * redissonClient 测试
     *
     * @return
     */
    @GetMapping("/redisson")
    public RR<String> redisson() {
        RLock lock = redissonClient.getLock("lock");
        try {
            if (lock.tryLock(1, 20, TimeUnit.SECONDS)) {
                try {
                    System.out.println("获取锁：成功" + Thread.currentThread().getName());
                    // 睡眠 10 秒
                    Thread.sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println("unlock" + Thread.currentThread().getName());
                }
            } else {
                System.out.println("获取锁：失败" + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return RR.ok("redissonClient 测试");
    }

    /**
     * 测试 Seata
     *
     * @return
     */
    @GetMapping("/seata")
    public RR<String> seataTest() {
        accountService.seataTest();
        return RR.ok("seata 测试");
    }

    /**
     * 测试 rocketMQ
     *
     * @return
     */
    @GetMapping("/rocketMQ")
    public RR<String> rocketMQ() {
        rocketMQTemplate.convertAndSend(
                AccountMsgConstant.ACCOUNT_TOPIC,
                "hello rocketMQ=========="
        );
        return RR.ok("rocketMQ 测试");
    }
}
