package com.linhtran.employee;

import com.linhtran.ConstantNumber;

public class Lecturer extends Employee {
    private String faculty;
    private String degree;
    private int teachingQuantity;  //So luong tiet day trong thang

    public Lecturer(String fullName, int birthYear, String homeTown,
                    double salaryFactor, String faculty, String degree) {
        super(fullName, birthYear, homeTown, salaryFactor);
        this.faculty = faculty;
        this.degree = degree;
        this.teachingQuantity = 0;
        setPay(); //Set initial pay according their degree
    }

    public Lecturer(String fullName, int birthYear, String homeTown,
                    double salaryFactor, String faculty, String degree, int quantity) {
        super(fullName, birthYear, homeTown, salaryFactor);
        this.degree = degree;
        this.faculty = faculty;
        this.teachingQuantity = quantity;
        setPay(); //Set initial pay according their degree
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
        setPay();
    }

    public int getTeachingQuantity() {
        return teachingQuantity;
    }

    public void setTeachingQuantity(int teachingQuantity) {
        if (teachingQuantity < 0) {
            throw new IllegalArgumentException();
        }
        this.teachingQuantity = teachingQuantity;
    }

    @Override
    public String toString() {
        return "Lecturer[name=" + getFullName() + ", degree=" + getDegree() +
                ", birth year=" + getBirthYear() + ", faculty=" + getFaculty() +
                ", teaching quantities=" + teachingQuantity + "]";
    }

    @Override
    public double getSalary() {
        return super.getSalary() + teachingQuantity * 45;
    }

    //Set first salary and allowance when instantiate lecturer object
    private void setPay() {
        switch (degree.toLowerCase()) {
            case "cu nhan":
                setAllowance(ConstantNumber.BACHELOR_ALLOWANCE);
                break;

            case "thac sy":
                setAllowance(ConstantNumber.MASTER_ALLOWANCE);
                break;

            case "tien sy":
                setAllowance(ConstantNumber.DOCTOR_ALLOWANCE);
                break;

            default:
                throw new IllegalArgumentException();
        }
    }
}
