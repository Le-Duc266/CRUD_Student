package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<Student>();
        StudentDAO studentDAO = new StudentDAO(DatabaseConnection.getConnection());
        do {
            System.out.println("--------Menu---------");
            System.out.println("1. Danh sách sinh vien.");
            System.out.println("2. Thêm mới sinh vien. ");
            System.out.println("3. Sửa thông tin sinh viên.");
            System.out.println("4. Xoa sinh viên");
            System.out.println("5. Tìm kiếm sinh viên theo tên");
            System.out.println("6. Tìm kiếm sinh viên theo địa chỉ");
            System.out.println("7. Sắp xếp giảm dần theo tuổi");
            System.out.println("8. Thoat");
            System.out.println("+++++++++++++++++++++");
            System.out.println("Moi ban chon: ");
            Scanner sc = new Scanner(System.in);
            int chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1:
                    System.out.println("Danh sách sinh viên");
                    students = studentDAO.getALl();
                    for (Student student : students) {
                        student.display();
                    }
                    break;

                case 2:
                    System.out.println("Nhập số sinh viên muốn thêm");
                    int n = Integer.parseInt(sc.nextLine());
                    for (int i = 0; i < n; i++) {
                        Student student = new Student();
                        student.input();
                        studentDAO.insert(student);
                    }
                    break;

                case 3:
                    System.out.println("nhap id sinh vien muon sua: ");
                    int id = Integer.parseInt(sc.nextLine());

                    for (Student st : students) {
                        if (st.getId() == id) {
                            st.input();
                            studentDAO.update(st);
                        }
                    }
                    break;

                case 4:
                    System.out.println("Nhap ma sinh vien muon xoa: ");
                    int code = Integer.parseInt(sc.nextLine());
                    studentDAO.delete(code);
                    System.out.println("Xóa thành công");
                    break;

                case 5:
                    System.out.println("Nhap ten sinh vien muon tim kiem: ");
                    String name = sc.nextLine();
                    List<Student> rs = studentDAO.getByName(name);
                    System.out.println("Sinh vien tim thay: ");
                    for (Student st : rs) {
                        st.display();
                    }
                    break;

                case 6:
                    System.out.println("Nhap dia chi sinh vien muon tim kiem: ");
                    String address = sc.nextLine();
                    List<Student> result = studentDAO.getByAddress(address);
                    System.out.println("Sinh vien tim thay: ");
                    for (Student st : result) {
                        st.display();
                    }
                    break;
                case 7:
                    System.out.println("Danh sach sau khi sap xep giam dan theo tuoi: ");
                    students = studentDAO.sortByAge();
                    for (Student student : students) {
                        student.display();
                    }
                    break;

                case 8:
                    System.out.println("Ban da thoat chuong trinh");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Moi ban chon tu 1 den 6");
                    break;

            }
        } while (true);
    }
}