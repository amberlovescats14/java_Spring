package com.example.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
@AutoConfigureMockMvc
public class AboutPageIntegrationTest {

    @Autowired
    private MockMvc mvc;


    //MVC sanity
    @Test
    public void mvcNotNull(){
        assertNotNull(mvc);
    }

    @Test
    public void twoIsTwo(){
        assertEquals(2,2);
    }

//    @Test
//    public void testAboutPage(){
//        mvc.perform(get())
//    }
}
