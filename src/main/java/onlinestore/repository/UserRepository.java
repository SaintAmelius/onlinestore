package onlinestore.repository;

import onlinestore.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserRepository {
    private HashMap<Long, User> usersData = new HashMap<>();

    public User findByNickname(String nickname) {
        for (User user: usersData.values()) {
            if(user.getNickname().equals(nickname)){
                return user;
            }
        }
        return null;
    }

    public void save(User user) {
        usersData.put(user.getId(), user);
    }


    public List<User> findAll() {
        return new ArrayList<>(usersData.values());
    }

    public User findByID(Long id) {
        return usersData.get(id);
    }

    public User findByEMail(String mail){
        for (User user: usersData.values()) {
            if(user.getMail().equals(mail)){
                return user;
            }
        }
        return null;
    }

    public void update(User user) {

    }
}
