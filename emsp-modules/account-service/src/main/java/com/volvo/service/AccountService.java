package com.volvo.service;

import com.volvo.client.CardClient;
import com.volvo.entity.Account;
import com.volvo.mapper.AccountMapper;
import com.volvo.model.CardDTO;
import com.volvo.model.dto.AccountDTO;
import com.volvo.model.vo.AccountVO;
import com.volvo.model.vo.RR;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
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
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private CardClient cardClient;

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
