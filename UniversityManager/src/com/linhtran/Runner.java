package com.linhtran;

import java.util.Scanner;

public class Runner {
    public static int inputData() {
        Scanner keyboard = new Scanner(System.in);
        int choice = 0;
        System.out.println("---------------------------------------");
        System.out.println("CHUONG TRINH QUAN LY NHAN VIEN");
        System.out.println("---------------------------------------");
        System.out.println("1. Them moi can bo. \n" +
                           "2. Chinh sua thong tin can bo. \n" +
                           "3. Xoa can bo. \n" +
                           "4. Hien thi danh sach can bo. \n" +
                           "5. Thoat chuong trinh.");
        while (choice < 1 || choice > 5) {
            System.out.print("Chon tinh nang: <1-5> ");
            choice = keyboard.nextInt();
        }
        return choice;
    }

    public static void addEmployee(){

    }


}
