package com.ifactory.client.greenwich;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for Client
 */
public class ClientTest extends TestCase {
	
	final double lat = 51.550927;
	final double lng = -0.180676;
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ClientTest(String testName) {
        super(testName);
                
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(ClientTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }
    
    public void testGetSingleWeather() {
    	
    }
}
