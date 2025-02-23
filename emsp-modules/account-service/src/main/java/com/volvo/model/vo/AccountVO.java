package com.volvo.model.vo;

import com.volvo.entity.Account;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

/**
 * 描述:
 *
 * @author lxr
 * @date 2025/2/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountVO {

    @ApiModelProperty(value = "ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "用户名", example = "lxr")
    private String email;

    @ApiModelProperty(value = "状态", example = "Active")
    private String status;

    @Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    interface AccountMapper {
        AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

        AccountVO toAccountVO(Account dto);
    }

    public static AccountVO toAccountVO(Account dto) {
        return AccountMapper.INSTANCE.toAccountVO(dto);
    }
}
