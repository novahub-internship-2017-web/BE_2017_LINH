package com.linhtran.employeemanage.model;

public abstract class Employee {
    private  String fullName;   //Ho va ten
    private  int birthYear;  //Nam sinh
    private  String homeTown;   //Que quan (tinh)
    private double salaryFactor; //He so luong
    private double allowance;   //Phu cap
    private String username;



    protected Employee(String fullName, int birthYear, String homeTown, double salaryFactor, String username) {
        this.fullName = fullName;
        this.birthYear = birthYear;
        this.homeTown = homeTown;
        this.salaryFactor = salaryFactor;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public double getSalary() {
        return salaryFactor * EmployeeHelpers.UNIT_SALARY + allowance;
    }

    //Get type of employee, if type is "Giang vien" return "GV", if type is "Nhan vien" return "NV"
    public abstract String getType();

    //Compare two employee by name
    public int compareByNameTo(Employee that) {
        String[] thisFullNameArr = this.getFullName().split(" ");
        String[] thatFullNameArr = that.getFullName().split(" ");
        String thisLastName = thisFullNameArr[thisFullNameArr.length - 1].toLowerCase();
        String thatLastName = thatFullNameArr[thatFullNameArr.length - 1].toLowerCase();
        int compare = thisLastName.compareTo(thatLastName);
        if (compare != 0) {
            return compare;
        } else {
            return this.getFullName().toLowerCase().compareTo(that.getFullName().toLowerCase());
        }

    }

    //Compare two employee by their salary
    public int compareBySalaryTo(Employee another) {
        return Double.compare(this.getSalary(), another.getSalary());
    }
}
