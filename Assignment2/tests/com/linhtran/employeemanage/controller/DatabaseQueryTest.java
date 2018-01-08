package com.linhtran.employeemanage.controller;

import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseQueryTest {
    DatabaseQuery databaseQuery = new DatabaseQuery();

    @Test
    public void getInformation2() throws Exception {
        String name = databaseQuery.getCellValue("EmployeeDB", "name",
                                                   "username", "rinsama");
        assertEquals("linh", name);

    }

}