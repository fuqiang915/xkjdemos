package com.xkj.xkjdemos;

import com.xkj.utils.Version;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * tips:
 * 自动生成时报not found testsuite 错误，主要是因为没有继承TestCase类
 */
public class VersionTest extends TestCase{

    private final String TAG = "VersionTest";
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void testCompare() throws Exception {
        /**
         * 测试版本比较方法
         */
        assertEquals(-1,Version.compare("0.0.0","1.1.1"));
        assertEquals(-1,Version.compare("1.1.0","1.1.1"));
        assertEquals(1,Version.compare("1.1.2","1.1.1"));
        assertEquals(1,Version.compare("1.2.0","1.1.1"));
        assertEquals(1,Version.compare("2.2.0","1.1.1"));
        assertEquals(0,Version.compare("2.2.0","2.2.0"));
        assertEquals(true,Version.compare("3.","2.2.0")>0);
        assertEquals(true,Version.compare(null,"2.2.0")<0);
        assertEquals(true,Version.compare("1",null)>0);
        assertEquals(true,Version.compare(null,null)==0);
        assertEquals(true,Version.compare("","2.2.0")<0);

    }



}