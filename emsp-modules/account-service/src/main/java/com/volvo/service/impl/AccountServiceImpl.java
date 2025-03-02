package com.volvo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.volvo.client.CardClient;
import com.volvo.entity.Account;
import com.volvo.mapper.AccountMapper;
import com.volvo.model.CardDTO;
import com.volvo.model.dto.AccountDTO;
import com.volvo.model.vo.AccountVO;
import com.volvo.model.vo.RR;
import com.volvo.service.AccountService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * 描述:
 *
 * @author lxr
 * @date 2025/2/23
 */
@Service
@Slf4j
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private CardClient cardClient;

    /**
     * 创建账户
     *
     * @param accountDTO
     */
    @Override
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
    @Override
    @CircuitBreaker(name = "account-service", fallbackMethod = "getAccountFallback")
    @TimeLimiter(name = "account-service", fallbackMethod = "getAccountFallback")
    public CompletionStage<AccountVO> getAccount(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("getAccountFuture =========== " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            // 远程调用
            RR card = cardClient.createCard(CardDTO.builder()
                    .build());
            System.out.println("getCardFuture =========== " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            // 账户查询
            Account account = accountMapper.selectById(id);
            if (account == null) {
                return null;
            }
            // 转 AccountDTO
            AccountVO accountVO = AccountVO.builder().build();
            BeanUtils.copyProperties(account, accountVO);
            return accountVO;
        });
    }

    @Override
    public CompletionStage<AccountVO> getAccountFallback(Long id, Throwable e) {
        // 打印时间 yyyy-MM-dd HH:mm:ss
        System.out.println("错了错了错了错了错了错了 =========== " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return CompletableFuture.completedFuture(AccountVO.builder().build());
    }

    /**
     * 更改账户状态
     *
     * @param id
     * @param status
     */
    @Override
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

    /**
     * 账户分页
     *
     * @return
     */
    @Override
    public IPage<AccountVO> accountList() {
        // page
        int page = 1;
        int size = 10;
        // 分页器
        Page<Account> pageObj = new Page<>(page, size);
        // 分页查询
        IPage<Account> pageResult = page(pageObj, Wrappers.<Account>lambdaQuery()
                .select(Account::getId, Account::getEmail, Account::getStatus)
                .orderByDesc(Account::getId));
        // 转 AccountVO
        return pageResult.convert(account -> AccountVO.builder()
                .id(account.getId())
                .email(account.getEmail())
                .status(account.getStatus())
                .build());
    }

    /**
     * seataTest
     *
     * @return
     */
    @Override
    @GlobalTransactional(name = "seata-server")
    public String seataTest() {
        // 生成 accountId
        Long accountId = System.currentTimeMillis();
        // 新增 account
        this.save(Account.builder()
                .id(accountId)
                .email("lxr@lxr.com")
                .status("1")
                .build());
        if (accountId > 1) {
            //throw new RuntimeException("模拟报错==========");
        }
        return "seataTest";
    }
}
