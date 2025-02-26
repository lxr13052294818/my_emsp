package com.volvo.client;

import com.volvo.model.CardDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 描述:
 *
 * @author lxr
 * @date 2025/2/26
 */
@FeignClient(name = "card-service", path = "/card")
public interface CardClient {
    @GetMapping("/{id}")
    CardDTO getCarById(@PathVariable("id") String id);
}
