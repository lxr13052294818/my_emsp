package com.volvo.client;

import com.volvo.model.CardDTO;
import com.volvo.model.vo.RR;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 描述:
 *
 * @author lxr
 * @date 2025/2/26
 */
@FeignClient(name = "card-service", path = "/card")
public interface CardClient {

    @PostMapping
    RR<String> createCard(@RequestBody CardDTO cardDTO);
}
