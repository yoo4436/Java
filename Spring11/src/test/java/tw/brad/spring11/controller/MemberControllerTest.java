package tw.brad.spring11.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import tools.jackson.databind.ObjectMapper;
import tw.brad.spring11.dto.Login;
import tw.brad.spring11.entity.Member;
import tw.brad.spring11.service.MemberService;

@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MemberService memberService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("測試 register -> 200 + Member")
    void register_Success() throws Exception {
        //assumption
        Login login = new Login("mm@mm.tw", "123", "MM");
        Member mockMember = new Member();
        mockMember.setEmail("mm@mm.tw");

        when(memberService.register(anyString(), anyString(), anyString()))
                .thenReturn(mockMember);
        //test
        mockMvc.perform(post("/api/member/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(login))
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("mm@mm.tw"));

    }

    @Test
    @DisplayName("測試 register -> 200 + error")
    void register_Failure() throws Exception {
        //assumption
        Login login = new Login("mm@mm.tw", "123", "MM");

        when(memberService.register(anyString(), anyString(), anyString()))
                .thenThrow(new RuntimeException("xxx"));
        //test
        mockMvc.perform(post("/api/member/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(login))
        ).andExpect(status().isOk())
                .andExpect(content().string("error:xxx"));
    }
}
