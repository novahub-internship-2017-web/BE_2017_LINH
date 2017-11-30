package com.linhtran.unimanager.view;

import com.linhtran.unimanager.model.employee.Employee;
import com.linhtran.unimanager.model.employee.Lecturer;
import com.linhtran.unimanager.model.employee.Officer;

import java.util.List;
import java.util.Scanner;
import static java.lang.System.out;



//This class plays the role of print everything on screen

public class PrintScreen {




    //Main screen of program
    public int printMainMenu() {
        Scanner keyboard = new Scanner(System.in);
        int choice = 0;
        out.println();
        out.println("CHUONG TRINH QUAN LY NHAN VIEN");
        out.println("---------------------------------------");
        out.println("1. Them moi can bo. \n" +
                           "2. Chinh sua thong tin can bo. \n" +
                           "3. Xoa can bo. \n" +
                           "4. Hien thi danh sach can bo. \n" +
                           "5. Thoat chuong trinh.");
        while (choice < 1 || choice > 5) {
            out.print("Chon tinh nang: <1-5> ");
            choice = keyboard.nextInt();
        }
        return choice;
    }

    //--------------------------------------------------------------------------
    //Add the employee in the employee list
    public int printMenu1() {
        Scanner keyboard = new Scanner(System.in);
        int choice ;
        out.println("---------------------------------- \n" +
                "Them can bo vao danh sach. Nhan:\n" +
                "1 --> Them can bo vao cuoi danh sach \n" +
                "2 --> Them can bo vao dau danh sach \n" +
                "3 --> Them can bo vao sau vi tri k \n" +
                "4 --> Quay lai muc truoc.");
        out.print("Your choice: ");
        choice = keyboard.nextInt();
        return choice;
    }

    //Add new employee
    public Employee inputNewEmployee() {
        Scanner scanner = new Scanner(System.in);
        String type;
        System.out.println("-------------------------------------------\n" +
                "Vui long nhap thong tin can bo can them: ");
        do {
            System.out.print("Loai can bo (GV or NV): ");
            type = scanner.nextLine();
        } while (!(type.equalsIgnoreCase("GV") || type.equalsIgnoreCase("NV")));


        System.out.print("Ho va ten: ");
        String fullName = scanner.nextLine();

        System.out.print("Nam sinh: ");
        int birthYear = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Que quan: ");
        String homeTown = scanner.nextLine();

        System.out.print("He so luong: ");
        double salaryFactor = scanner.nextDouble();
        scanner.nextLine();

        if (type.equalsIgnoreCase("GV")) {
            System.out.print("Khoa: ");
            String faculty = scanner.nextLine();

            System.out.print("Trinh do: ");
            String degree = scanner.nextLine();

            System.out.print("So tiet day: ");
            int teachingQuantity = scanner.nextInt();
            scanner.nextLine();

            /*
            Constructor of Lecturer
             public Lecturer(String fullName, int birthYear, String homeTown,
                    double salaryFactor, String faculty, String degree, int quantity)
             */
            return new Lecturer(fullName, birthYear, homeTown, salaryFactor, faculty,
                    degree, teachingQuantity);
        } else  {
            System.out.print("Phong ban: ");
            String  department= scanner.nextLine();

            System.out.print("Chuc vu: ");
            String position = scanner.nextLine();

            System.out.print("So ngay cong: ");
            int workingDays = scanner.nextInt();

            /*
            Constructor of officer
            public Officer(String fullName, int birthYear, String homeTown, double salaryFactor,
            String department, int numberOfWorkingDays, String position)
            */
            return new Officer(fullName, birthYear, homeTown, salaryFactor, department,
                    workingDays, position);
        }

    }

    //Input and check index of new employee when adding with menu 1.3
    public int inputEmployeeIndex(List<Employee> employees) {
        Scanner scanner = new Scanner(System.in);
        int k;
        boolean quitLoop = false;
        do {
            System.out.println("So thu tu cua nhan vien?");
            System.out.print("(So nhap phai >= 0 va <=" + employees.size() + "): ");
            k = scanner.nextInt();
            if (k < 0 || k > employees.size()) {
                System.out.println("So thu tu nhap khong dung! Vui long nhap lai.");
                quitLoop = false;
            } else {
                quitLoop = true;
            }
        } while (!quitLoop);

        return k;
    }


    //-----------------------------------------------------------------------------------------------
    //Modify the employee's information
    public int printMenu2() {
        Scanner keyboard = new Scanner(System.in);
        int choice ;
        out.println("---------------------------------- \n" +
                    "Chinh sua thong tin can bo");
        out.print("So thu tu can bo can nhap (bat dau tu 0): ");
        choice = keyboard.nextInt();
        return choice;
    }

    //print menu of employee's fields need to modify
    public int printFieldsToModify(Employee employee) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which field of this employee you want to modify? Press: \n" +
                "1 --> Ho va ten. \n" +
                "2 --> Nam sinh. \n" +
                "3 --> Que quan. \n" +
                "4 --> He so luong. \n");
        if (employee.getType().equalsIgnoreCase("GV")) {
            System.out.println("5 --> Khoa. \n" +
                    "6 --> Trinh do. \n" +
                    "7 --> So tiet day.\n" +
                    "8 --> Tro ve muc truoc.");
        } else {
            System.out.println("5 --> Phong ban. \n" +
                    "6 --> Chuc vu. \n" +
                    "7 --> So ngay cong. \n" +
                    "8 --> Tro ve muc truoc.");
        }
        int choice = scanner.nextInt();
        return choice;

    }

    //------------------------------------------------------------------------------------------------
    //Remove the employee from the list
    public int printMenu3() {
        Scanner keyboard = new Scanner(System.in);
        int choice ;
        out.println("---------------------------------- \n" +
                    "Xoa can bo tu danh sach!!!");
        out.print("So thu tu can bo can XOA (bat dau tu 0): ");
        choice = keyboard.nextInt();
        return choice;
    }

    //--------------------------------------------------------------------------------------------------
    //Print choices of display employee list
    public int printMenu4() {
        Scanner keyboard = new Scanner(System.in);
        int choice ;
        out.println("---------------------------------- \n" +
                "Hien thi danh sach can bo. Nhan:\n" +
                "1 --> Hien thi danh sach hien tai \n" +
                "2 --> Hien thi danh sach sau khi sap xep tang dan theo luong. \n" +
                "3 --> Hien thi danh sach sau khi sap xep ten theo chu cai. \n" +
                "4 --> Tim kiem can bo theo ten.\n" +
                "5 --> Tim kien can bo theo nam sinh. \n" +
                "6 --> Tro ve muc truoc");
        out.print("Your choice: ");
        choice = keyboard.nextInt();
        return choice;
    }

    //Search employee by name
    public String inputEmployeeName() {
        Scanner scanner = new Scanner(System.in);
        out.print("Name of employee: ");
        String name = scanner.nextLine();
        return name;
    }

    //Search employee by birth year
    public int inputEmployeeBirthYear() {
        Scanner keyboard = new Scanner(System.in);
        int choice ;
        out.print("Birth year of employee: ");
        choice = keyboard.nextInt();
        return choice;
    }

    //Print table of employee list
    public void printEmployeeList(List<Employee> list) {
        out.println("STT  |" + "Ho va ten              |" + "Nam sinh   |" + "Que quan     |" +
                    "Loai    |" + "C1         |" + "C2             |" + "C3         |" +
                    "C4         |" + "C5      |");
        //Number of space each column
        int[] spaceColumn = {5, 23, 11, 13, 8, 11, 15, 11, 11, 8};
        int stt = 0;
        for(Employee employee : list) {
            String sttString = Integer.toString(stt);
            String birthYearsString = Integer.toString(employee.getBirthYear());
            String allowanceString = Double.toString(employee.getAllowance());
            String salaryFactorString = Double.toString(employee.getSalaryFactor());

            printACellEmployeeTable(sttString, spaceColumn[0]); //Print "STT" cell
            printACellEmployeeTable(employee.getFullName(), spaceColumn[1]); //Print "Ten" cell
            printACellEmployeeTable(birthYearsString, spaceColumn[2]); //print "Nam sinh" cell
            printACellEmployeeTable(employee.getHomeTown(), spaceColumn[3]); //print "Que quan" cell
            printACellEmployeeTable(employee.getType(), spaceColumn[4]); //print "Loai" cell

            if(employee instanceof Lecturer) {
                Lecturer lecturer = (Lecturer) employee;
                String teachingQuantityString = Double.toString(lecturer.getTeachingQuantity());
                printACellEmployeeTable(lecturer.getFaculty(), spaceColumn[5]); //print "Khoa" cell
                printACellEmployeeTable(lecturer.getDegree(), spaceColumn[6]);  //print "Trinh do" cell
                printACellEmployeeTable(allowanceString, spaceColumn[7]); //print "Phu cap" cell
                printACellEmployeeTable(teachingQuantityString, spaceColumn[8]); //print "So tiet" cell

            } else if (employee instanceof Officer) {
                Officer officer = (Officer) employee;
                String workingDaysString = Integer.toString(officer.getNumberOfWorkingDays());
                printACellEmployeeTable(officer.getDepartment(), spaceColumn[5]); //print "Phong ban" cell
                printACellEmployeeTable(officer.getPosition(), spaceColumn[6]);  //print "Chuc vu" cell
                printACellEmployeeTable(allowanceString, spaceColumn[7]); //print "Phu cap" cell
                printACellEmployeeTable(workingDaysString, spaceColumn[8]); //print "So ngay cong" cell

            }
            printACellEmployeeTable(salaryFactorString, spaceColumn[9]); //print "He so luong" cell
            out.println();
            stt++;
        }
        System.out.println("\nC1-->C5 neu la GV: Khoa, trinh do, phu cap, so tiet, he so luong ");
        System.out.println("C1-->C5 neu la nhan vien: phong ban, chuc vu, phu cap, so ngay cong, he so luong");

    }

    //Print a cell of employee table
    private void printACellEmployeeTable(String item, int spaceColumn) {
        int length = item.length();
        out.print(item);
        while (length < spaceColumn) {
            out.print(" ");
            length++;
        }
        out.print("|");
    }

    public static void main(String[] args) {
        PrintScreen printScreen = new PrintScreen();
        int times = 0;
        while (times < 5) {
            int i = printScreen.printMenu1();
            System.out.println(i);
            times++;

        }
    }


}
