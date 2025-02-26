package com.volvo.controller;

import com.volvo.model.dto.CardDTO;
import com.volvo.model.vo.CardVO;
import com.volvo.model.vo.RR;
import com.volvo.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

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
        cardService.createCard(cardDTO);
        return RR.ok("创建成功");
    }

    /**
     * 查询账户
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public RR<CardVO> getCard(@PathVariable Long id) {
        return RR.ok(cardService.getCard(id));
    }

    /**
     * 更改账户状态
     *
     * @param id
     * @param status
     */
    @PutMapping("/{id}/status")
    public RR<String> changeCardStatus(@PathVariable Long id, @RequestParam String status) {
        cardService.changeCardStatus(id, status);
        return RR.ok("更改成功");
    }
}
