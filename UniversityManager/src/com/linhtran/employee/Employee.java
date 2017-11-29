package com.linhtran.employee;

public abstract class Employee {
    private  String fullName;   //Ho va ten
    private  int birthYear;  //Nam sinh
    private  String homeTown;   //Que quan (tinh)
    private double salaryFactor; //He so luong
    private double allowance;   //Phu cap

    protected Employee(String fullName, int birthYear, String homeTown, double salaryFactor) {
        this.fullName = fullName;
        this.birthYear = birthYear;
        this.homeTown = homeTown;
        this.salaryFactor = salaryFactor;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public double getSalaryFactor() {
        return salaryFactor;
    }

    public void setSalaryFactor(double salaryFactor) {
        this.salaryFactor = salaryFactor;
    }

    public double getAllowance() {
        return allowance;
    }

    protected void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    public double getSalary() {
        return salaryFactor * EmployeeHelpers.UNIT_SALARY + allowance;
    }

    //Compare two employee by name
    public int compareByNameTo(Employee another) {
        return this.getFullName().compareTo(another.getFullName());
    }

    //Compare two employee by their salary
    public int compareBySalaryTo(Employee another) {
        return Double.compare(this.getSalary(), another.getSalary());
    }
}
