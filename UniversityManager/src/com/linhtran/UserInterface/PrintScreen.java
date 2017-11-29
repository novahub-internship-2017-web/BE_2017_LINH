package com.linhtran.UserInterface;

import com.linhtran.employee.Employee;
import java.util.List;
import java.util.Scanner;
import static java.lang.System.out;


//This class plays the role of print everything on screen

public class PrintScreen {

    Scanner keyboard = new Scanner(System.in);
    int choice = 0;

    public int printMainMenu() {
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

    public int printMenu1() {
        out.println("---------------------------------- \n" +
                "Them can bo vao danh sach. Nhan:\n" +
                "1 --> Them can bo vao cuoi danh sach \n" +
                "2 --> Them can bo vao dau danh sach \n" +
                "3 --> Them can bo vao sau vi tri k \n" +
                "4 --> Quay lai muc truoc.");
        choice = keyboard.nextInt();
        return choice;
    }

    public void printMenu2(List<Employee> list) {
        out.println();
    }


}
