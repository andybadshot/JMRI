package jmri.configurexml.turnoutoperations;

import jmri.util.JUnitUtil;

import org.junit.jupiter.api.*;
import org.junit.Assert;

/**
 *
 * @author Paul Bender Copyright (C) 2017
 */
public class RawTurnoutOperationXmlTest {

    @Test
    public void testCTor() {
        RawTurnoutOperationXml t = new RawTurnoutOperationXml();
        Assert.assertNotNull("exists",t);
    }

    @BeforeEach
    public void setUp() {
        JUnitUtil.setUp();
    }

    @AfterEach
    public void tearDown() {
        JUnitUtil.tearDown();
    }

    // private final static Logger log = LoggerFactory.getLogger(RawTurnoutOperationXmlTest.class);

}
