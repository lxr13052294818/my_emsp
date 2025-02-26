package com.volvo.client;

import com.volvo.model.AccountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 描述:
 *
 * @author lxr
 * @date 2025/2/26
 */
@FeignClient(name = "account-service", path = "/account")
public interface AccountClient {
    @GetMapping("/{email}")
    AccountDTO getAccountByEmail(@PathVariable("email") String email);
}
