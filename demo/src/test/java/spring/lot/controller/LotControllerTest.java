package spring.lot.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import spring.lot.service.LotService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class LotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LotService service;

    @BeforeEach
    public void setup() {
        service.getLot().clearAllSpaces(); // You'd need to write this method in Lot.java
    }

    @Test
    public void test_AddSpace_ShouldSucceed() throws Exception {
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
    public void test_AddSpace_ShouldFail_WhenLength_Is0() throws Exception {
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

    @Test
    public void test_AddSpace_ShouldFail_WhenWidth_Is0() throws Exception {
        mockMvc.perform(
            post("/addSpace")
            .param("length", "2")
            .param("width","0")
            .param("x", "2")
            .param("y", "2")
        )
        .andExpect(status().isBadRequest())
        .andExpect(content().string(containsString("was not")));
    }

    @Test
    public void test_AddSpace_ShouldFail_WhenX_IsNegative() throws Exception {
        mockMvc.perform(
            post("/addSpace")
            .param("length", "2")
            .param("width","2")
            .param("x", "-2")
            .param("y", "2")
        )
        .andExpect(status().isBadRequest())
        .andExpect(content().string(containsString("was not")));
    }

    @Test
    public void test_AddSpace_ShouldFail_WhenX_IsAbove9() throws Exception {
        mockMvc.perform(
            post("/addSpace")
            .param("length", "2")
            .param("width","2")
            .param("x", "10")
            .param("y", "2")
        )
        .andExpect(status().isBadRequest())
        .andExpect(content().string(containsString("was not")));
    }

    @Test
    public void test_AddSpace_ShouldFail_WhenY_IsNegative() throws Exception {
        mockMvc.perform(
            post("/addSpace")
            .param("length", "2")
            .param("width","2")
            .param("x", "2")
            .param("y", "-2")
        )
        .andExpect(status().isBadRequest())
        .andExpect(content().string(containsString("was not")));
    }

    @Test
    public void test_AddSpace_ShouldFail_WhenY_IsAbove9() throws Exception {
        mockMvc.perform(
            post("/addSpace")
            .param("length", "2")
            .param("width","2")
            .param("x", "2")
            .param("y", "12")
        )
        .andExpect(status().isBadRequest())
        .andExpect(content().string(containsString("was not")));
    }

    @Test
    public void test_AddSpace_ShouldFail_WhenXPlusLength_ResultIn_OutOfBounds() throws Exception {
        mockMvc.perform(
            post("/addSpace")
            .param("length", "9")
            .param("width","2")
            .param("x", "2")
            .param("y", "2")
        )
        .andExpect(status().isBadRequest())
        .andExpect(content().string(containsString("was not")));
    }

    @Test
    public void test_AddSpace_ShouldFail_WhenYPlusWidth_ResultIn_OutOfBounds() throws Exception {
        mockMvc.perform(
            post("/addSpace")
            .param("length", "2")
            .param("width","2")
            .param("x", "2")
            .param("y", "9")
        )
        .andExpect(status().isBadRequest())
        .andExpect(content().string(containsString("was not")));
    }
}
