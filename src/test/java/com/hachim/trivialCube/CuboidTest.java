package com.hachim.trivialCube;

import java.lang.reflect.Method;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class CuboidTest
        extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CuboidTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(CuboidTest.class);
    }
    
    public void testGetAndGenerateName() throws Exception {
        assertTrue(true);
        int[] dimensionIndices = new int[]{0, 2};
        String headerRow = "vendeur,marque,date,montant";
        Cuboid c = new CuboidCount(dimensionIndices, headerRow);        
        assertEquals("vendeur,date", c.getName());    
        c = new CuboidSum(dimensionIndices, headerRow,0);
        assertEquals("vendeur,date", c.getName()); 
    }
}
