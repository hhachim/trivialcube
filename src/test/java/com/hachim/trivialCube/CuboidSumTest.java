package com.hachim.trivialCube;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class CuboidSumTest
        extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CuboidSumTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(CuboidSumTest.class);
    }
    
    public void testProcessRow() throws Exception {
        int[] dimensionIndices = new int[]{0, 2};
        String headerRow = "vendeur,marque,date,montant";

        CuboidSum c = new CuboidSum(dimensionIndices, headerRow, 3);        
        String row1 = "jean,bmw,150217,30";  
        Row r = new Row(row1);
        String attributeName = r.getValuesAtIndexes(dimensionIndices);
        c.processRow(row1);
        assertEquals(c.getAttributValue(attributeName), 30);
        String row2 = "jean,peugeot,150217,99";  
        c.processRow(row2);
        assertEquals(c.getAttributValue(attributeName), 129);
    }
}
