package com.volvo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.volvo.entity.Card;
import com.volvo.model.dto.CardDTO;
import com.volvo.model.vo.CardVO;

/**
 * 描述:
 *
 * @author lxr
 * @date 2025/3/1
 */
public interface CardService extends IService<Card> {

    /**
     * 创建账户
     *
     * @param cardDTO
     */
    void createCard(CardDTO cardDTO);

    /**
     * 查询账户
     *
     * @param id
     * @return
     */
    CardVO getCard(Long id);

    /**
     * 更改账户状态
     *
     * @param id
     * @param status
     */
    void changeCardStatus(Long id, String status);

    /**
     * 保存卡片
     */
    void saveCard();
}
