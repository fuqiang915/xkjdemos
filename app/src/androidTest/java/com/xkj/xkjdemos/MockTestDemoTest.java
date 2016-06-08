package com.xkj.xkjdemos;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.RenamingDelegatingContext;
import com.xkj.demos.pages.MockTestDemo;

/**
 * Created by fuqiang on 15/2/10.
 */
public class MockTestDemoTest extends ActivityInstrumentationTestCase2<MockTestDemo> {

    private static final String PREFIX = "test.";
    private RenamingDelegatingContext mMockContext;

    public MockTestDemoTest() {
        super(MockTestDemo.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mMockContext = new RenamingDelegatingContext(getInstrumentation().getTargetContext(), PREFIX);
        mMockContext.makeExistingFilesAndDbsAccessible();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testSampleTextDisplayed(){
//        setActivityContext(mMockContext);
//        startActivity(new Intent(), null, null);

        final MockTestDemo mActivity = getActivity();
        assertNotNull(mActivity);
        String text = mActivity.readFile(mMockContext);
        assertEquals("This is *MOCK* data", text);
    }
}
