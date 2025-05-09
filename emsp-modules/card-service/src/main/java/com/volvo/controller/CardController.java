package com.volvo.controller;

import com.volvo.model.dto.CardDTO;
import com.volvo.model.vo.RR;
import com.volvo.service.CardService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private CardService cardService;

    /**
     * 创建账户
     *
     * @param cardDTO
     */
    @PostMapping
    public RR<String> createCard(@RequestBody CardDTO cardDTO) {
        // 睡眠 5 秒
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return RR.ok("创建成功");
    }

    /**
     * 保存卡片
     *
     * @param cardDTO
     * @return
     */
    @PostMapping("/save")
    @ApiOperation(value = "保存卡片")
    public RR<String> saveCard(@RequestBody CardDTO cardDTO) {
        cardService.saveCard();
        return RR.ok("保存成功");
    }
}
