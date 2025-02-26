package com.volvo.service;

import com.volvo.entity.Card;
import com.volvo.mapper.CardMapper;
import com.volvo.model.dto.CardDTO;
import com.volvo.model.vo.CardVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述:
 *
 * @author lxr
 * @date 2025/2/23
 */
@Service
@Slf4j
public class CardService {

    @Autowired
    private CardMapper cardMapper;

    /**
     * 创建账户
     *
     * @param cardDTO
     */
    public void createCard(CardDTO cardDTO) {
        if (cardDTO == null) {
            return;
        }
        // CardDTO 转 Card
        Card card = Card.builder().build();
        BeanUtils.copyProperties(cardDTO, card);
        // 插入数据库
        if (cardMapper.insert(card) < 1) {
            log.error("创建账户失败: {}", card);
        }
    }

    /**
     * 查询账户
     *
     * @param id
     * @return
     */
    public CardVO getCard(Long id) {
        Card card = cardMapper.selectById(id);
        if (card == null) {
            return null;
        }
        // 转 CardDTO
        CardVO cardVO = CardVO.builder().build();
        BeanUtils.copyProperties(card, cardVO);
        return cardVO;
    }

    /**
     * 更改账户状态
     *
     * @param id
     * @param status
     */
    public void changeCardStatus(Long id, String status) {
        if (id == null || status == null) {
            return;
        }
        Card card = cardMapper.selectById(id);
        if (card == null) {
            return;
        }
        card.setStatus(status);
        if (cardMapper.updateById(card) < 1) {
            log.error("更改账户状态失败: {}", card);
        }
    }
}
