package com.linhtran.employeemanage.model;

public class Officer extends Employee {
    private String department;
    private int numberOfWorkingDays;
    private String position;

    public Officer(String fullName, int birthYear, String homeTown, double salaryFactor,
                   String department, String position, int numberOfWorkingDays, String username) {
        super(fullName, birthYear, homeTown, salaryFactor, username);
        this.department = department;
        this.numberOfWorkingDays = numberOfWorkingDays;
        this.position = position;
        setPay();
    }


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getNumberOfWorkingDays() {
        return numberOfWorkingDays;
    }

    public void setNumberOfWorkingDays(int numberOfWorkingDays) {
        this.numberOfWorkingDays = numberOfWorkingDays;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
        setPay();
    }

    @Override
    public String toString() {
        return "Officer[name=" + getFullName() + ", position=" + position +
                ", birth year=" + getBirthYear() + ", number of working days=" +
                numberOfWorkingDays + "]";
    }

    @Override
    public double getSalary() {
        return super.getSalary() + this.numberOfWorkingDays * 30;
    }

    @Override
    public String getType() {
        return "Officer";
    }

    //Set allowance according to their potition
    private void setPay() {
        switch (position.toLowerCase()) {
            case "head of department":
                setAllowance(EmployeeHelpers.HEAD_DEPARTMENT_ALLOWANCE);
                break;

            case "deputy of department":
                setAllowance(EmployeeHelpers.DEPUTY_DEPARTMENT_ALLOWANCE);
                break;

            case "regular employee":
                setAllowance(EmployeeHelpers.REGULAR_EMPLOYEE_ALLOWANCE);
                break;
            case "admin":
                setAllowance(EmployeeHelpers.ADMIN_ALLOWANCE);
                break;

            default:
                throw new IllegalArgumentException("Nhap chuc vu nhan vien khong dung. \n" +
                        "Chuc vu nhan vien phai la: Regular employee, Deputy of department, Head of department" +
                        "hoac admin.");
        }
    }

}
