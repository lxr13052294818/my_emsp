package com.volvo.controller;

import com.volvo.model.dto.CardDTO;
import com.volvo.model.vo.RR;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述: 卡片管理
 *
 * @author lxr
 * @date 2025/2/23
 */
@RestController
@RefreshScope
@RequestMapping("/card")
public class CardController {

    /**
     * 创建账户
     *
     * @param cardDTO
     */
    @PostMapping
    public RR<String> createCard(@RequestBody CardDTO cardDTO) {
        return RR.ok("创建成功");
    }
}
