package com.geneology;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void testJohnWheeler() {
        String fileName = "src/test/resources/31332.html";
        MathematicianParser parser = new MathematicianParser();
        Mathematician mathematician = parser.parse(fileName);
        assertEquals(31332, mathematician.getId());
        assertEquals("John Archibald Wheeler", mathematician.getName());
        assertEquals("The Johns Hopkins University", mathematician.getAlmaMater());
        assertEquals(1933, mathematician.getYear());
        assertEquals("UnitedStates", mathematician.getFlag());
    }
}
