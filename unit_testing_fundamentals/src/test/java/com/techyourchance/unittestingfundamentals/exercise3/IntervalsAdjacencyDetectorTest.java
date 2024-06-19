package com.techyourchance.unittestingfundamentals.exercise3;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.techyourchance.unittestingfundamentals.example3.Interval;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IntervalsAdjacencyDetectorTest {

    IntervalsAdjacencyDetector SUT;

    @Before
    public void setUp() throws Exception {
        SUT = new IntervalsAdjacencyDetector();
    }

    @Test
    public void adjacent_interval1BeforeInterval2_returnsFalse() {
        Interval i1 = new Interval(0, 1);
        Interval i2 = new Interval(3, 4);
        boolean result = SUT.isAdjacent(i1, i2);

        Assert.assertFalse(result);
    }

    @Test
    public void adjacent_interval1AdjacentInterval2_returnsFalse() {
        Interval i1 = new Interval(0, 1);
        Interval i2 = new Interval(1, 2);
        boolean result = SUT.isAdjacent(i1, i2);

        Assert.assertTrue(result);
    }

    @Test
    public void adjacent_interval1equalsInterval2_returnsFalse() {
        Interval i1 = new Interval(0, 1);
        Interval i2 = new Interval(0, 1);
        boolean result = SUT.isAdjacent(i1, i2);

        Assert.assertFalse(result);
    }

    @Test
    public void isAdjacent_interval1BeforeInterval2_falseReturned() throws Exception {
        Interval interval1 = new Interval(-1, 5);
        Interval interval2 = new Interval(8, 12);
        boolean result = SUT.isAdjacent(interval1, interval2);
        assertThat(result, is(false));
    }

    @Test
    public void isAdjacent_interval1BeforeAndAdjacentToInterval2_trueReturned() throws Exception {
        Interval interval1 = new Interval(-1, 5);
        Interval interval2 = new Interval(5, 12);
        boolean result = SUT.isAdjacent(interval1, interval2);
        assertThat(result, is(true));
    }

    @Test
    public void isAdjacent_interval1OverlapsInterval2OnStart_falseReturned() throws Exception {
        Interval interval1 = new Interval(-1, 5);
        Interval interval2 = new Interval(3, 12);
        boolean result = SUT.isAdjacent(interval1, interval2);
        assertThat(result, is(false));
    }

    @Test
    public void isAdjacent_interval1ContainedWithinInterval2_falseReturned() throws Exception {
        Interval interval1 = new Interval(-1, 5);
        Interval interval2 = new Interval(-4, 12);
        boolean result = SUT.isAdjacent(interval1, interval2);
        assertThat(result, is(false));
    }

    @Test
    public void isAdjacent_interval1ContainsInterval2_falseReturned() throws Exception {
        Interval interval1 = new Interval(-1, 5);
        Interval interval2 = new Interval(0, 3);
        boolean result = SUT.isAdjacent(interval1, interval2);
        assertThat(result, is(false));
    }

    @Test
    public void isAdjacent_interval1EqualsInterval2_falseReturned() throws Exception {
        Interval interval1 = new Interval(-1, 5);
        Interval interval2 = new Interval(-1, 5);
        boolean result = SUT.isAdjacent(interval1, interval2);
        assertThat(result, is(false));
    }

    @Test
    public void isAdjacent_interval1OverlapsInterval2OnEnd_falseReturned() throws Exception {
        Interval interval1 = new Interval(-1, 5);
        Interval interval2 = new Interval(-4, 4);
        boolean result = SUT.isAdjacent(interval1, interval2);
        assertThat(result, is(false));
    }

    @Test
    public void isAdjacent_interval1AfterAndAdjacentToInterval2_trueReturned() throws Exception {
        Interval interval1 = new Interval(12, 15);
        Interval interval2 = new Interval(5, 12);
        boolean result = SUT.isAdjacent(interval1, interval2);
        assertThat(result, is(true));
    }

    @Test
    public void isAdjacent_interval1AfterInterval2_falseReturned() throws Exception {
        Interval interval1 = new Interval(-1, 5);
        Interval interval2 = new Interval(-10, -3);
        boolean result = SUT.isAdjacent(interval1, interval2);
        assertThat(result, is(false));
    }
}