package onlinestore;

import onlinestore.repository.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Scanner;

public class RegistrationFormV2 {
    public static void main(String[] args) {
        RegistrationFormV2 registrationForm = new RegistrationFormV2();
        registrationForm.startForm();
    }

    MailRepository mailRepository = new MailRepository();
    UserRepository userRepository = new UserRepository();
    DocumentRepository documentRepository = new DocumentRepositoryImpl();
    MapChannelRepository mapChannelRepository = new MapChannelRepository();
    Scanner scanner = new Scanner(System.in);
    Long idGenerator = 0L;
    Long id = generateID();
    LocalDate adminsBirthday = LocalDate.of(1952, 10, 7);
    User administrator = new User(id, "admin", "admin", 228, "admin", User.Role.ADMIN, adminsBirthday, "admin69@mail.ru");

    public Long generateID() {
        return idGenerator++;
    }

    public void startForm() {
        userRepository.save(administrator);
        System.out.println("1. Войти");
        System.out.println("2. Зарегистрироваться");
        int enteredNumber = Integer.parseInt(scanner.nextLine());
        if (enteredNumber == 1) {
            enter();
        } else if (enteredNumber == 2) {
            registration();
        } else {
            System.out.println("чел ты...");
        }
    }

    public void enter() {
        System.out.println("Введите никнейм:");
        String nickname = scanner.nextLine();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();

        User user = userRepository.findByNickname(nickname);
        if (user != null && user.getPassword().equals(password)) {
            if (user.getRole() == User.Role.USER) {
                enteredAsUser(user);
            } else {
                enteredAsAdmin();
            }
        } else {
            System.out.println("неверный логин или пароль");
            System.out.println();
        }
        startForm();

    }

    public void registration() {
        System.out.println("Введите имя:");
        String name = scanner.nextLine();
        System.out.println("Введите никнейм:");
        String nickname = scanner.nextLine();

        System.out.println("Введите год рождения:");
        int yearOfBirth = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите месяц рождения (цифрой):");
        int monthOfBirth = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите день рождения:");
        int dayOfBirth = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите адрес электронной почты:");
        String mail = scanner.nextLine() + "@koshka.com";
        User userForMail = userRepository.findByEMail(mail);
        if (userForMail != null) {
            System.out.println("будь оригинальнее");
            startForm();
        }

        System.out.println("Введите пароль(минимум 8 символов):");
        String password = scanner.nextLine();
        if (password.length() < 8) {
            System.out.println("ты че долбаеб? заново теперь всё вводи, конченный");
            startForm();
        }

        LocalDate birthDate = LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth);
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(birthDate, currentDate).getYears();
        if (age < 18) {
            System.out.println("пездюкам не положено");
            startForm();
        }

        User user = userRepository.findByNickname(nickname);
        if (user != null) {
            System.out.println("такой никнейм уже существует");
            startForm();
        }
        Long id = generateID();
        User newUser = new User(id, name, nickname, age, password, User.Role.USER, birthDate, mail);
        userRepository.save(newUser);
        startForm();
    }

    public void printUserInfo(User user) {
        System.out.println("Имя пользователя: " + user.getName());
        System.out.println("Возраст: " + user.getAge());
        System.out.println("Никнейм: " + user.getNickname());
        System.out.println("Дата рождения: " + user.getBirthDate());
        System.out.println("Электронная почта: " + user.getMail());
        System.out.println("Пароль: ********");
        System.out.println();
        System.out.println("1. Редактировать профиль");
        System.out.println("2. Посмотреть аватар");
        System.out.println("3. Назад");
        int enteredNumber = Integer.parseInt(scanner.nextLine());
        if (enteredNumber == 1) {
            updateUserInfo(user);
        }
        if (enteredNumber == 2) {
            showProfilePicture(user);
        }
        if (enteredNumber == 3) {
            enteredAsUser(user);
        }
    }

    public void addProfilePicture(User user) {
        System.out.println("Укажите адрес ссылки на фото:");
        String profilePicturePath = scanner.nextLine();
        user.setProfilePicturePath(profilePicturePath);
    }

    public void showProfilePicture(User user) {
//        try {
//            Desktop.getDesktop().open(new File(user.getProfilePicturePath()));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        Path path = Path.of(user.getProfilePicturePath());
        Path parentPath = Path.of(user.getProfilePicturePath()).getParent();
        try {
            byte[] readAllBytes = Files.readAllBytes(path);
            BufferedImage resultImage = ImageIO.read(new ByteArrayInputStream(readAllBytes));
            ImageIO.write(resultImage, ".jpg", new File(parentPath.toString(), "ProfilePicture.jpg"));
            Desktop.getDesktop().open(new File(parentPath.toString(), "ProfilePicture.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUserInfo(User user) {
        System.out.println("1. Изменить имя пользователя");
        System.out.println("2. Изменить никнейм");
        System.out.println("3. Изменить дату рождения");
        System.out.println("4. Изменить адрес электронной почты");
        System.out.println("5. Изменить пароль");
        System.out.println("6. Изменить фотографию профиля");
        System.out.println("7. Назад");
        int enteredNumber = Integer.parseInt(scanner.nextLine());
        if (enteredNumber == 1) {
            System.out.println("Введите новое имя:");
            String newName = scanner.nextLine();
            user.setName(newName);
        }
        if (enteredNumber == 2) {
            System.out.println("Введите новый никнейм:");
            String newNickname = scanner.nextLine();
            User userWithEnteredNickname = userRepository.findByNickname(newNickname);
            if (userWithEnteredNickname != null) {
                System.out.println("такой никнейм уже существует");
                updateUserInfo(user);
            }
            user.setNickname(newNickname);
            userRepository.update(user);
        }
        if (enteredNumber == 3) {
            System.out.println("Введите год рождения:");
            int newYearOfBirth = Integer.parseInt(scanner.nextLine());
            System.out.println("Введите месяц рождения (цифрой):");
            int newMonthOfBirth = Integer.parseInt(scanner.nextLine());
            System.out.println("Введите день рождения:");
            int newDayOfBirth = Integer.parseInt(scanner.nextLine());

            LocalDate newBirthDate = LocalDate.of(newYearOfBirth, newMonthOfBirth, newDayOfBirth);
            LocalDate currentDate = LocalDate.now();
            int newAge = Period.between(newBirthDate, currentDate).getYears();
            if (newAge >= 18) {
                user.setBirthDate(newBirthDate);
                user.setAge(newAge);
            } else {
                System.out.println("Это не место для хоббита");
                System.out.println();
                updateUserInfo(user);
            }
        }
        if (enteredNumber == 4) {
            System.out.println("Введите новый адрес электронной почты:");
            String newMail = scanner.nextLine() + "@koshka.com";
            user.setMail(newMail);
        }
        if (enteredNumber == 5) {
            System.out.println("Введите новый пароль:");
            String newPassword = scanner.nextLine();
            if (newPassword.length() >= 8) {
                user.setPassword(newPassword);
            } else {
                System.out.println("Слишком короткий(как твой член)");
                updateUserInfo(user);
            }
        }
        if (enteredNumber == 6) {
            addProfilePicture(user);
        }
        if (enteredNumber == 7) {
            printUserInfo(user);
        }
        updateUserInfo(user);
    }

    public void enteredAsUser(User user) {
        System.out.println("1. Посмотреть профиль");
        System.out.println("2. Написать письмо");
        System.out.println("3. Посмотреть входящие");
        System.out.println("4. Посмотреть исходящие");
        System.out.println("5. Документы");
        System.out.println("6. Каналы");
        System.out.println("7. Выход");
        int enteredNumber = Integer.parseInt(scanner.nextLine());
        if (enteredNumber == 1) {
            printUserInfo(user);
            System.out.println();
            exit();
        }
        if (enteredNumber == 2) {
            writeLetter(user);
        }
        if (enteredNumber == 3) {
            List<Letter> receivedLetters = mailRepository.getReceivedLetters(user.getMail());
            printLetters(receivedLetters);
            System.out.println("1. Назад");
            int enteredNumber1 = Integer.parseInt(scanner.nextLine());
            if (enteredNumber1 == 1) {
                enteredAsUser(user);
            }
        }
        if (enteredNumber == 4) {
            List<Letter> sentLetters = mailRepository.getSentLetters(user.getMail());
            printLetters(sentLetters);
            System.out.println("1. Назад");
            int enteredNumber1 = Integer.parseInt(scanner.nextLine());
            if (enteredNumber1 == 1) {
                enteredAsUser(user);
            }
        }
        if (enteredNumber == 5) {
            documents(user);
        }
        if (enteredNumber == 6) {
            channels(user);
        }
        if (enteredNumber == 7) {
            System.out.println();
            startForm();
        }
        enteredAsUser(user);
    }

    private void printLetters(List<Letter> letters) {
        letters.forEach(letter -> {
            System.out.println("****");
            System.out.println(" - Получатель: " + letter.getReceiver());
            System.out.println(" - Отправитель: " + letter.getSender());
            System.out.println(" - Тема письма:" + letter.getSubject());
            System.out.println(" - Содержание:" + letter.getLetterBody());
            System.out.println();
            System.out.println();
        });
    }

    public void documents(User user) {
        System.out.println("1. Посмотреть документы");
        System.out.println("2. Добавить документ");
        int enteredNumber = Integer.parseInt(scanner.nextLine());
        if (enteredNumber == 1) {
            showDocumentList(user);
        }
        if (enteredNumber == 2) {
            addDocuments(user);
        }
    }

    public void addDocuments(User user) {
        System.out.println("Введите адрес ссылки на документ:");
        String enteredFilePath = scanner.nextLine();
        System.out.println("Введите название документа");
        String docName = scanner.nextLine();
        Document document = new Document(user.getId(), docName, enteredFilePath);
        documentRepository.addDocument(document);
    }

    public void showDocumentList(User user) {
        for (Document document : documentRepository.findAllByUserID(user.getId())) {
            System.out.println("- " + document.getDocID() + " - " + document.getDocName());
        }
        System.out.println();
        System.out.println("Введите номер документа");
        Long enteredNumber = Long.parseLong(scanner.nextLine());
        Document document = documentRepository.findByID(enteredNumber);
        if (enteredNumber.equals(document.getDocID())) {
//            try {
//                Desktop.getDesktop().open(new File(document.getDocPath()));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
            Path docPath = Path.of(document.getDocPath());
            try {
                List<String> docContent = Files.readAllLines(docPath);
                docContent.forEach(System.out::println);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void writeLetter(User user) {
        System.out.println("Введите электронный адрес получателя:");
        String receiversMail = scanner.nextLine();
        System.out.println("Введите тему письма:");
        String lettersSubject = scanner.nextLine();
        System.out.println("Введите текст:");
        String lettersBody = scanner.nextLine();
        Letter letter = new Letter(user.getMail(), receiversMail, lettersSubject, Letter.LetterType.SEND, lettersBody);
        System.out.println("1. Отправить письмо");
        int enteredNumber = Integer.parseInt(scanner.nextLine());
        if (enteredNumber == 1) {
            mailRepository.sendLetter(letter);
        } else {
            System.out.println("Либо это миссклик, либо ты даун");
            writeLetter(user);
        }

    }

    public void channels(User user) {
        System.out.println("1. Подписки");
        System.out.println("2. Список всех каналов");
        System.out.println("3. Мои каналы");
        System.out.println("4. Создать канал");
        System.out.println("5. Назад");

        int enteredNumber = Integer.parseInt(scanner.nextLine());

        if (enteredNumber == 1) {
            followedChannels(user);
        }
        if (enteredNumber == 2) {
            allChannels(user);
        }
        if (enteredNumber == 3) {
            usersOwnChannels(user);
        }
        if (enteredNumber == 4) {
            createChannel(user);
        }
        if (enteredNumber == 5) {
            enteredAsUser(user);
        }
    }

    public void usersOwnChannels(User user) {
        for (Channel channel : mapChannelRepository.findOwnChannelsByUserId(user.getId())) {
            System.out.println("- " + channel.getId() + " - " + channel.getName() + " - ");
        }
        System.out.println();
        System.out.println("Введите ID анала: ");
        Long enteredLong = Long.parseLong(scanner.nextLine());
        Channel channel = mapChannelRepository.findById(enteredLong);
        printChannelMessages(channel);
        System.out.println("1. Новая запись");
        channel.getMessages().add(scanner.nextLine());
        try {
            System.out.println(channel.getMessages().get(channel.getMessages().size() - 1));
        } catch (Exception e) {
            channels(user);
        }
    }

    public void createChannel(User user) {
        Channel channel = new Channel();
        System.out.println("Введите название канала: ");
        channel.setName(scanner.nextLine());
        channel.setUserId((user.getId()));
        mapChannelRepository.create(channel);
        channels(user);
    }

    public void allChannels(User user) {
        for (Channel channel : mapChannelRepository.findALl()) {
            System.out.println("- " + channel.getId() + " - " + channel.getName() + " - ");
        }
        System.out.println();
        System.out.println("Введите ID канала: ");
        try {
            Long enteredLong = Long.parseLong(scanner.nextLine());
            Channel channel = mapChannelRepository.findById(enteredLong);
            printChannelMessages(channel);
            System.out.println("1. Подписаться");
            System.out.println("2. Вернуться к списку каналов");
            int enteredNumber = Integer.parseInt(scanner.nextLine());
            if (enteredNumber == 1) {
                mapChannelRepository.follow(channel, user.getId());
                channels(user);
            }
            if (enteredNumber == 2) {
                channels(user);
            }
        } catch (Exception e) {
            System.out.println("ты Invalid");
            channels(user);
        }
    }

    public void printChannelMessages(Channel channel) {
        for (String message : channel.getMessages()) {
            System.out.println("<" + message + ">");
        }
    }

    public void followedChannels(User user) {
        for (Channel channel : mapChannelRepository.findFollowedChannelsByUserId(user.getId())) {
            System.out.println("- " + channel.getId() + " - " + channel.getName() + " - ");
        }
        System.out.println();
        System.out.println("Введите ID канала: ");
        try {
            Long enteredLong = Long.parseLong(scanner.nextLine());
            Channel channel = mapChannelRepository.findById(enteredLong);
            printChannelMessages(channel);
        } catch (Exception e) {
            System.out.println("Канала с таким ID не существует");
            channels(user);
        }
        System.out.println("1. Вернуться в меню каналов");
        System.out.println("2. Вернуться к моим каналам");
        int enteredNumber = Integer.parseInt(scanner.nextLine());
        try {
            if (enteredNumber == 1) {
                channels(user);
            }
            if (enteredNumber == 2) {
                followedChannels(user);
            }
        } catch (Exception e) {
            System.out.println("В следующий раз будь осторожен...");
            channels(user);
        }
    }

    public void enteredAsAdmin() {
        System.out.println("1. Посмотреть список пользователей");
        System.out.println("2. Выйти");
        int enteredNumber = Integer.parseInt(scanner.nextLine());
        if (enteredNumber == 1) {
            System.out.println();
            usersListForAdmin();
        } else if (enteredNumber == 2) {
            System.out.println();
            startForm();
        } else {
            System.out.println("чел ты...");
            enteredAsAdmin();
        }
    }

    public void usersListForAdmin() {
        for (User user : userRepository.findAll()) {
            System.out.println("- " + user.getId() + " - " + user.getNickname());
        }

        System.out.println();
        System.out.println("1. Посмотреть профиль пользователя");
        System.out.println("2. Дать роль администратора");
        System.out.println("3. Выйти");
        int enteredNumber = Integer.parseInt(scanner.nextLine());
        if (enteredNumber == 1) {
            printUserProfileByID();
        } else if (enteredNumber == 2) {
            giveAdminRole();
        } else if (enteredNumber == 3) {
            enteredAsAdmin();
        } else {
            System.out.println("чел хватит...");
            enteredAsAdmin();
        }
    }

    public void giveAdminRole() {
        System.out.println("Введите id пользователя:");
        Long enteredID = Long.parseLong(scanner.nextLine());
        User user = userRepository.findByID(enteredID);
        if (user != null) {
            user.setRole(User.Role.ADMIN);
        }
        usersListForAdmin();
    }

    public void printUserProfileByID() {
        System.out.println("Введите id пользователя:");
        Long enteredID = Long.parseLong(scanner.nextLine());
        User user = userRepository.findByID(enteredID);
        if (user != null) {
            printUserInfo(user);

        } else {
            System.out.println("Такого пользователя не существует");
            System.out.println();
            printUserProfileByID();
        }
    }

    public void exit() {
        System.out.println("1. Выход");
        int enteredNumber = Integer.parseInt(scanner.nextLine());
        if (enteredNumber == 1) {
            System.out.println();
            startForm();
        } else {
            System.out.println("чел...");
            System.out.println();
            exit();
        }
    }
}