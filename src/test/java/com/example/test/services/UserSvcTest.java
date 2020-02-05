package com.example.test.services;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserSvcTest {
    private SillySvc sillySvc;

    @Before
    public void setUp(){
        sillySvc = new SillySvc();
    }

    //! Sanity test
    @Test
    public void svcNotNull(){
        assertNotNull(sillySvc);
    }

    @Test
    public void twoIsTwo(){
        assertEquals(2,2);
    }

    @Test
    public void svcReturnsHi(){
        assertEquals("Hi", sillySvc.sayHi());
    }


}
