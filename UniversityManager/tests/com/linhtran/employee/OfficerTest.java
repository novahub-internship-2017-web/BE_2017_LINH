package com.linhtran.employee;

import org.junit.Test;

import static org.junit.Assert.*;

public class OfficerTest {
    //Upper cast for Officer
    Employee officer1 = new Officer("Tran Manh", 1989, "Quang Nam", 3.33,
                                    "Dao Tao", 26, "Nhan Vien");
    //Down cast for Officer
    Officer officer2 = new Officer("Nguyen Van", 1980, "Ha Noi", 5.00,
                                   "Thanh Tra", "Truong Phong");
    @Test
    public void testSetGetDepartment() {
        assertEquals("Thanh Tra", officer2.getDepartment());
        //Change the department of officer2 to "Tong Hop"
        officer2.setDepartment("Tong Hop");
        assertEquals("Tong Hop", officer2.getDepartment());
    }

    @Test
    public void testSetGetNumberOfWorkingDays() {
        //Set number of working days of officer2 to 20
        officer2.setNumberOfWorkingDays(20);
        assertEquals(20, officer2.getNumberOfWorkingDays());
    }

    @Test
    public void testSetGetPosition() {
        //Set potition of officer2 to "Pho Phong"
        officer2.setPosition("Pho Phong");
        assertEquals("Pho Phong", officer2.getPosition());

        //Set potition of officer2 to "Pho Phong"
        officer2.setPosition("Nhan Vien");
        assertEquals("Nhan Vien", officer2.getPosition());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetGetPosition_Exception (){
        officer2.setPosition("Lao Cong");
    }

    @Test
    public void testToString() {
        String expected = "Officer[name=Tran Manh, position=Nhan Vien, " +
                           "birth year=1989, number of working days=26]";
        assertEquals(expected, officer1.toString());
    }

    @Test
    public void getSalary() {
        double expectedSalary = 3.33 * 730 + 400 + 26 * 30;
        assertEquals(expectedSalary, officer1.getSalary(), 0.001);

        //change the position of officer2 to "Pho Phong" and set number of working days to 20
        officer2.setPosition("Pho Phong");
        officer2.setNumberOfWorkingDays(20);
        expectedSalary = 5 * 730 + 600 + 20 * 30;
        assertEquals(expectedSalary, officer2.getSalary(),0.001);
    }
}