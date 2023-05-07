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
//        System.out.println(" Зарегистрироваться => 1");
//        System.out.println(" Войти => 2");
//        String login = sc.nextLine();
//
//        int enterLog = Integer.parseInt(login);
//        if (enterLog == 1) {
//            enter();
//        } else if (enterLog == 2) {
//            reg();
//        } else {
//            System.out.println(" ТЫ НЕ ПРОЙДЁШЬ! ");
//        }
//
//
//    }
//
//    private void reg() {
//        System.out.print(" Введите логин => ");
//        String login = sc.nextLine();
//        user.setLogin(login);
//        int enterReg = Integer.parseInt(login);
//        if (enterReg != 1) {
//            System.out.println(" Такого пользователя не существует! ");
//            reg();
//        } else {
//            System.out.print(" Введите пароль => ");
//            String password = sc.nextLine();
//            user.setPassword(password);
//        }
//
//
//    }
//
////    private void enter() {
////        System.out.print(" Введите логин => ");
////        String login = sc.nextLine();
////        user.setLogin(login);
////
////
////        System.out.print(" Введите пароль => ");
////        String password = sc.nextLine();
////        user.setPassword(password);
////        int enterPass = Integer.parseInt(password);
////        if (enterPass != 123) {
////            System.out.println(" Неверный пароль!");
////            enter();
////        } else {
////            System.out.print(" Введите никнейм => ");
////            String nik = sc.nextLine();
////            user.setNik(nik);
////            String enterNik = new String(nik);
////            if (enterNik.equals("nik")) {
////                System.out.print(" Введите возраст => ");
////                int age = Integer.parseInt(sc.nextLine());
////                user.setAge(age);
////            } else {
////                System.out.println(" Неверный никнейм! ");
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
