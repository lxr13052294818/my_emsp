package com.volvo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.volvo.entity.Account;
import com.volvo.model.dto.AccountDTO;
import com.volvo.model.vo.AccountVO;

import java.util.concurrent.CompletionStage;

/**
 * 描述:
 *
 * @author lxr
 * @date 2025/3/1
 */
public interface AccountService extends IService<Account> {


    /**
     * 创建账户
     *
     * @param accountDTO
     */
    void createAccount(AccountDTO accountDTO);

    /**
     * 查询账户
     *
     * @param id
     * @return
     */
    CompletionStage<AccountVO> getAccount(Long id);

    /**
     * 账户查询失败回调
     *
     * @param id
     * @param e
     * @return
     */
    CompletionStage<AccountVO> getAccountFallback(Long id, Throwable e);

    /**
     * 更改账户状态
     *
     * @param id
     * @param status
     */
    void changeAccountStatus(Long id, String status);

    /**
     * 账户分页
     *
     * @return
     */
    IPage<AccountVO> accountList();

    /**
     * seataTest
     *
     * @return
     */
    String seataTest();
}
