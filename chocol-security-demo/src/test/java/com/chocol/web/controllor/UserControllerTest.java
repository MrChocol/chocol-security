package com.chocol.web.controllor;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .param("username", "zhangsan")
                .param("age", "20")
                .param("ageTo", "60")
                .param("xxx", "yyy")
                //PageAble 对象参数
//                .param("page","3")
//                .param("size","10")
//                .param("sort","username,age,desc")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                //语法可参照https://github.com/json-path/JsonPath $--原数据
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
    }

    @Test
    public void whenGenInfoSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("zhangsan"));
    }

    @Test
    public void whenGenInfoFail() throws Exception {
        //正则测试
        mockMvc.perform(MockMvcRequestBuilders.get("/user/a")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}
