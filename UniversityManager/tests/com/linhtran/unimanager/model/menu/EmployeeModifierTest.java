package com.linhtran.unimanager.model.menu;

import com.linhtran.unimanager.model.employee.Lecturer;
import com.linhtran.unimanager.model.employee.Officer;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeModifierTest {
    Lecturer lecturer2 = new Lecturer("Nguyen Hoan", 1980, "Da Nang", 3.0,
            "Kien truc", "Tien sy", 300);
    Officer officer2 = new Officer("Nguyen Van", 1980, "Ha Noi", 5.00,
            "Thanh Tra", "Truong Phong");
    EmployeeModifier employeeModifier = new EmployeeModifier();
    @Test
    public void modifyEmployeeInformation() {
        //Modify name
        employeeModifier.modifyEmployeeInformation(lecturer2, 1, "Hoan");
        assertEquals("Hoan", lecturer2.getFullName());

        //Modify birth year
        employeeModifier.modifyEmployeeInformation(lecturer2, 2, "1980");
        assertEquals(1980, lecturer2.getBirthYear());

        //Modify home town
        employeeModifier.modifyEmployeeInformation(lecturer2, 3, "Hue");
        assertEquals("Hue", lecturer2.getHomeTown());

        //Modify salary factor
        employeeModifier.modifyEmployeeInformation(lecturer2, 4, "2.34");
        assertEquals(2.34, lecturer2.getSalaryFactor(), 0.001);

        //Modify faculty
        employeeModifier.modifyEmployeeInformation(lecturer2, 5, "Xay dung");
        assertEquals("Xay dung", lecturer2.getFaculty());

        //Modify department
        employeeModifier.modifyEmployeeInformation(officer2, 5, "dao tao");
        assertEquals("dao tao", officer2.getDepartment());

        //Modify degree
        employeeModifier.modifyEmployeeInformation(lecturer2, 6, "cu nhan");
        assertEquals("cu nhan", lecturer2.getDegree());

        //Modify position
        employeeModifier.modifyEmployeeInformation(officer2, 6, "pho phong");
        assertEquals("pho phong", officer2.getPosition());

        //Modify teaching quantity
        employeeModifier.modifyEmployeeInformation(lecturer2, 7, "230");
        assertEquals(230, lecturer2.getTeachingQuantity());

        //Modify number of working days
        employeeModifier.modifyEmployeeInformation(officer2, 7, "23");
        assertEquals(23, officer2.getNumberOfWorkingDays());

        //Modify nothing
        employeeModifier.modifyEmployeeInformation(lecturer2, 8, "1980");

    }

}