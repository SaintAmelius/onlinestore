package onlinestore;//package onlinestore;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//
//public class Registration {
//    public static void main(String[] args) {
//        Registration registration = new Registration();
//        registration.startForm();
//    }
//
//    private final List<User> store = new ArrayList<>();
//
//    Scanner sc = new Scanner(System.in);
//
//    User user = new User();
//
//    public void startForm() {
//        System.out.println(" ������������������ => 1");
//        System.out.println(" ����� => 2");
//        String login = sc.nextLine();
//
//        int enterLog = Integer.parseInt(login);
//        if (enterLog == 1) {
//            enter();
//        } else if (enterLog == 2) {
//            reg();
//        } else {
//            System.out.println(" �� �� ����Ĩ��! ");
//        }
//
//
//    }
//
//    private void reg() {
//        System.out.print(" ������� ����� => ");
//        String login = sc.nextLine();
//        user.setLogin(login);
//        int enterReg = Integer.parseInt(login);
//        if (enterReg != 1) {
//            System.out.println(" ������ ������������ �� ����������! ");
//            reg();
//        } else {
//            System.out.print(" ������� ������ => ");
//            String password = sc.nextLine();
//            user.setPassword(password);
//        }
//
//
//    }
//
////    private void enter() {
////        System.out.print(" ������� ����� => ");
////        String login = sc.nextLine();
////        user.setLogin(login);
////
////
////        System.out.print(" ������� ������ => ");
////        String password = sc.nextLine();
////        user.setPassword(password);
////        int enterPass = Integer.parseInt(password);
////        if (enterPass != 123) {
////            System.out.println(" �������� ������!");
////            enter();
////        } else {
////            System.out.print(" ������� ������� => ");
////            String nik = sc.nextLine();
////            user.setNik(nik);
////            String enterNik = new String(nik);
////            if (enterNik.equals("nik")) {
////                System.out.print(" ������� ������� => ");
////                int age = Integer.parseInt(sc.nextLine());
////                user.setAge(age);
////            } else {
////                System.out.println(" �������� �������! ");
////                enter();
////            }
////        }
////
////
////    }
//
//
//    private void printUserData(User user) {
////        System.out.println(user.getLogin());
//        System.out.println(user.getPassword());
//        System.out.println(user.getAge());
//        System.out.println(user.getNik());
//    }
//}
