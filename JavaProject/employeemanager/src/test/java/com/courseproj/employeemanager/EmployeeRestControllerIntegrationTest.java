package com.courseproj.employeemanager;

import com.courseproj.employeemanager.appEmployee.controller.EmployeeResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
//        (
//        value = SpringBootTest.WebEnvironment.MOCK,
//        classes = Application.clss)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class EmployeeRestControllerIntegrationTest  {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeResource controller;

    @Test
    public void contextLoads() throws Exception{

        assertThat(controller).isNotNull();

        this.mockMvc.perform(get("/employee/all"))
                .andDo(print())
                .andExpect(status().isOk())
                // id name email jobTitle phone imageUrl employeeCode
                .andExpect(content().string(containsString("id")))
                .andExpect(content().string(containsString("name")))
                .andExpect(content().string(containsString("email")))
                .andExpect(content().string(containsString("jobTitle")))
                .andExpect(content().string(containsString("phone")))
                .andExpect(content().string(containsString("imageUrl")))
                .andExpect(content().string(containsString("employeeCode")));
    }


}
