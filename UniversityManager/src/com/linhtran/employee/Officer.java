package com.linhtran.employee;

public class Officer extends Employee {
    private String department;
    private int numberOfWorkingDays;
    private String position;

    public Officer(String fullName, int birthYear, String homeTown, double salaryFactor,
                   String department, int numberOfWorkingDays, String position) {
        super(fullName, birthYear, homeTown, salaryFactor);
        this.department = department;
        this.numberOfWorkingDays = numberOfWorkingDays;
        this.position = position;
        setPay();
    }

    public Officer(String fullName, int birthYear, String homeTown, double salaryFactor,
                   String department, String position) {
        super(fullName, birthYear, homeTown, salaryFactor);
        this.department = department;
        this.position = position;
        this.numberOfWorkingDays = 0;
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

    //Set allowance according to their potition
    private void setPay() {
        switch (position.toLowerCase()) {
            case "truong phong":
                setAllowance(EmployeeHelpers.HEAD_DEPARTMENT_ALLOWANCE);
                break;

            case "pho phong":
                setAllowance(EmployeeHelpers.DEPUTY_DEPARTMENT_ALLOWANCE);
                break;

            case "nhan vien":
                setAllowance(EmployeeHelpers.REGULAR_EMPLOYEE_ALLOWANCE);
                break;

            default:
                throw new IllegalArgumentException();
        }
    }

}
