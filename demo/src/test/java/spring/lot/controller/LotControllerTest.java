package spring.lot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class LotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddSpaceSuccess() throws Exception {
        mockMvc.perform(
            post("/addSpace")
            .param("length", "2")
            .param("width","4")
            .param("x", "2")
            .param("y", "2")
        )
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("success")));      
    }

    @Test
    public void testAddSpaceFail() throws Exception {
        mockMvc.perform(
            post("/addSpace")
            .param("length", "0")
            .param("width","4")
            .param("x", "2")
            .param("y", "2")
        )
        .andExpect(status().isBadRequest())
        .andExpect(content().string(containsString("was not")));
    }
}
