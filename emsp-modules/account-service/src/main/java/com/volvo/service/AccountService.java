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
    public AccountVO getAccount(Long id) {
        // 远程调用
        CompletionStage<RR> card = this.getCardFuture(id);
        card.thenAccept(rr -> {
            System.out.println("r=========" + rr);
        });

        // 账户查询
        Account account = accountMapper.selectById(id);
        if (account == null) {
            return null;
        }
        // 转 AccountDTO
        AccountVO accountVO = AccountVO.builder().build();
        BeanUtils.copyProperties(account, accountVO);
        return accountVO;
    }

    @CircuitBreaker(name = "accountService", fallbackMethod = "getCardFutureFallback")
    @TimeLimiter(name = "accountService")
    public CompletionStage<RR> getCardFuture(Long id) {
        // 打印时间 yyyy-MM-dd HH:mm:ss
        System.out.println("getCardFuture =========== " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        CardDTO dto = CardDTO.builder().build();
        RR<String> rr = cardClient.createCard(dto);
        return CompletableFuture.completedFuture(rr);
    }

    public AccountVO getCardFutureFallback(Long id, Throwable e) {
        // 打印时间 yyyy-MM-dd HH:mm:ss
        log.error("getCardFutureFallback =========== {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return AccountVO.builder().build();
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
