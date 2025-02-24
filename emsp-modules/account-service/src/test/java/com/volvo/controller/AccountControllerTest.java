package com.volvo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.volvo.model.dto.AccountDTO;
import com.volvo.model.vo.AccountVO;
import com.volvo.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AccountService accountService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateAccount() throws Exception {
        AccountDTO accountDTO = new AccountDTO();
        // 设置 AccountDTO 的属性
        accountDTO.setEmail("test@example.com");

        mockMvc.perform(post("/api/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(accountDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("创建成功"));

        // 验证 accountService.createAccount 被调用
        verify(accountService, times(1)).createAccount(accountDTO);
    }

    @Test
    public void testGetAccount() throws Exception {
        Long accountId = 1L;
        AccountVO accountVO = new AccountVO();
        accountVO.setId(accountId);
        accountVO.setEmail("test@example.com");

        // 模拟 accountService.getAccount 的行为
        when(accountService.getAccount(accountId)).thenReturn(accountVO);

        mockMvc.perform(get("/api/account/{id}", accountId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(accountId))
                .andExpect(jsonPath("$.data.email").value("test@example.com"));

        // 验证 accountService.getAccount 被调用
        verify(accountService, times(1)).getAccount(accountId);
    }

    @Test
    public void testChangeAccountStatus() throws Exception {
        Long accountId = 1L;
        String status = "ACTIVE";

        mockMvc.perform(put("/api/account/{id}/status", accountId)
                .param("status", status))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("更改成功"));

        // 验证 accountService.changeAccountStatus 被调用
        verify(accountService, times(1)).changeAccountStatus(accountId, status);
    }
}
