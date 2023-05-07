package onlinestore;

import java.time.LocalDate;

public class User {
    private Long id;
    private String name;
    private String nickname;
    private int age;
    private String password;
    private Role role;
    private LocalDate birthDate;
    private String mail;
    private String profilePicturePath;

    enum Role {
        USER,
        ADMIN
    }

    public User(Long id, String enteredName, String enteredNickname, int enteredAge, String enteredPassword, Role role, LocalDate birthDate, String mail) {
        this.id = id;
        this.name = enteredName;
        this.nickname = enteredNickname;
        this.age = enteredAge;
        this.password = enteredPassword;
        this.role = role;
        this.birthDate = birthDate;
        this.mail = mail;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public int getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getMail() {
        return mail;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }
}

