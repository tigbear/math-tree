package com.geneology;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
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
        assertEquals("The Johns Hopkins University", mathematician.getSchool());
        assertEquals(1933, mathematician.getYear());
        assertEquals("UnitedStates", mathematician.getFlag());

        LinkedList<Student> students = mathematician.getStudents();
        HashMap<Integer, Student> map = new HashMap<Integer, Student>();
        for (Student student : students) {
            map.put(student.getId(), student);
        }
        assertEquals(17, map.keySet().size());
    }

    @Test
    public void testTanyaSalyers() {
        String fileName = "src/test/resources/169822.html";
        MathematicianParser parser = new MathematicianParser();
        Mathematician mathematician = parser.parse(fileName);
        assertEquals(169822, mathematician.getId());
        assertEquals("Tanya Salyers", mathematician.getName());
        assertEquals("University of Notre Dame", mathematician.getSchool());
        assertEquals(2012, mathematician.getYear());
        assertEquals("UnitedStates", mathematician.getFlag());

        LinkedList<Student> students = mathematician.getStudents();
        assertEquals(0, students.size());
    }

}
