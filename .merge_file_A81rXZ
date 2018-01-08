package com.linhtran.unimanager.model.employee;

import static org.junit.Assert.*;

public class LecturerTest {
    //Upper cast constructor
    Employee lecturer1 = new Lecturer("Tran Linh", 1988, "Hue", 3.0,
                                        "Xay dung", "Thac sy");
    Lecturer lecturer2 = new Lecturer("Nguyen Hoan", 1980, "Da Nang", 3.0,
                                      "Kien truc", "Tien sy", 300);

    @org.junit.Test
    public void testConstructor() {
        assertEquals("Tran Linh", lecturer1.getFullName());
        assertEquals("Tien sy", lecturer2.getDegree());
    }

    @org.junit.Test (expected = IllegalArgumentException.class)
    public void testConstructor_IllegalAgument() {
        //degree = "Thac si" is not accepted (it should be "Thac sy")

        Employee lecturer3 = new Lecturer("Tran Linh", 1988, "Hue", 3.0,
                "Xay dung", "Thac si");
    }

    @org.junit.Test
    public void testSetGetFaculty() {
        lecturer2.setFaculty("Xay dung");
        assertEquals("Xay dung", lecturer2.getFaculty());

    }

    @org.junit.Test
    public void testSetGetDegree() {
        lecturer2.setDegree("Thac sy");
        assertEquals("Thac sy", lecturer2.getDegree());
    }

    @org.junit.Test
    public void testGetSetTeachingQuantity() {
        lecturer2.setTeachingQuantity(250);
        assertEquals(250, lecturer2.getTeachingQuantity());
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testSetTeachingQuantity_throw_Exception() {
        lecturer2.setTeachingQuantity(-1);
    }

    @org.junit.Test
    public void testGetAllowance() {
        assertEquals(2000, lecturer2.getAllowance(), 0.0001);
    }

    @org.junit.Test
    public void getSalary() {
        //Salary expected of lecturer2
        double expected = 3.0 * 730 + 2000 + 300 * 45;
        assertEquals(expected, lecturer2.getSalary(), 0.0001);

        //Set degree for lecturer 2
        lecturer2.setDegree("Cu nhan");
        expected = 3.0 * 730 + 300 + 300 * 45;
        assertEquals(expected, lecturer2.getSalary(), 0.0001);
    }

    @org.junit.Test
    public void testSetGetFullName() {
        lecturer1.setFullName("Tran Dinh Manh Linh");
        assertEquals("Tran Dinh Manh Linh", lecturer1.getFullName());
    }

    @org.junit.Test
    public void testSetGetBirthYear() {
        lecturer1.setBirthYear(1989);
        assertEquals(1989, lecturer1.getBirthYear());
    }

    @org.junit.Test
    public void testSetGetHomeTown() {
        lecturer1.setHomeTown("Dien Loc");
        assertEquals("Dien Loc", lecturer1.getHomeTown());
    }

    @org.junit.Test
    public void testSetGetSalaryFactor() {
        lecturer2.setSalaryFactor(5);
        assertEquals(5, lecturer2.getSalaryFactor(), 0.001);
    }

    @org.junit.Test
    public void testReturnType() {
        assertEquals("GV", lecturer1.getType());
    }

    @org.junit.Test
    public void compareByNameTo() {
        //compare lecturer1 and lecturer2
        assertTrue(lecturer1.compareByNameTo(lecturer2) > 0);

        //set lecterer1's name to "Dao Linh"
        lecturer1.setFullName("Dao Linh");
        assertTrue(lecturer1.compareByNameTo(lecturer2) < 0);

        //set lecturer2's name to "Dao Linh"
        lecturer2.setFullName("Dao Linh");
        assertEquals(0, lecturer1.compareByNameTo(lecturer2));

    }

    @org.junit.Test
    public void compareBySalaryTo() {
        //Compare the salary of lecturer1 and lecturer2
        assertEquals(-1, lecturer1.compareBySalaryTo(lecturer2));

        //Set the degree of lecturer2 to "Thac sy" and teachingQuantity to 0
        lecturer2.setDegree("Thac sy");
        lecturer2.setTeachingQuantity(0);
        assertEquals(0, lecturer1.compareBySalaryTo(lecturer2));

        //Set the degree of lecturer2 to "Cu nhan"
        lecturer2.setDegree("Cu nhan");
        assertEquals(1, lecturer1.compareBySalaryTo(lecturer2));
    }

    @org.junit.Test
    public void testToString() {
        String expected = "Lecturer[name=Tran Linh, degree=Thac sy, " +
                          "birth year=1988, faculty=Xay dung, " +
                          "teaching quantities=0]";
        assertEquals(expected, lecturer1.toString());

    }

//     return "Lecturer[name=" + getFullName() + ", degree=" + getDegree() +
//            ", birth year=" + getBirthYear() + ", faculty=" + getFaculty() +
//            ", teaching quantities=" + teachingQuantity + "]";

}