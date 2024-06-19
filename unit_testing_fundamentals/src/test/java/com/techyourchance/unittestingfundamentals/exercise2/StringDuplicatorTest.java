package com.techyourchance.unittestingfundamentals.exercise2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringDuplicatorTest {

    StringDuplicator SUT;

    @Before
    public void setUp() throws Exception {
        SUT = new StringDuplicator();
    }

    @Test
    public void stringDuplicator_emptyString_returnsEmptyString() {
        String result = SUT.duplicate("");

        Assert.assertEquals("", result);
    }

    @Test
    public void duplicate_nullString_returnsNullString() {
        String result = SUT.duplicate(null);

        Assert.assertEquals("nullnull", result);
    }

    @Test
    public void duplicate_shortString_returnDuplicate() {
        String result = SUT.duplicate("abc");
        Assert.assertEquals("abcabc", result);
    }
}