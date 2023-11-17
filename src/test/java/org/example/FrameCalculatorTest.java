package org.example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class FrameCalculatorTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public FrameCalculatorTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( FrameCalculatorTest.class );
    }

    /**
     * Test
     */
    public void testFrameCalculator()
    {
        /* Please insert test cases into the args array here.
           Please note that since Java Arrays are type specific, all numerical values are passed in as strings.
           Gutter balls or open frames can be set as 0 or "-". */
        String[] args = {"4", "4", "X", "9", "/", "X", "X", "X", "5", "3", "-", "/"};
        Integer[] expectedOutput = {8, 20, 20, 30, 25, 18, 8};

        String[] args2 = {"X", "X", "X", "X", "5", "4", "5", "/", "8", "1", "X", "5", "/", "4", "2"};
        Integer[] expectedOutput2 = {30, 30, 25, 19, 9, 18, 9, 20, 14, 6};

        FrameCalculator frameCalculator = new FrameCalculator();

        assertTrue(Arrays.equals(expectedOutput, frameCalculator.calculateFrames(args)));
        assertTrue(Arrays.equals(expectedOutput2, frameCalculator.calculateFrames(args2)));
    }
}
