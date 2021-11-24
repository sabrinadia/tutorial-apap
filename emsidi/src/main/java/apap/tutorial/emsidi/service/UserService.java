package apap.tutorial.emsidi.service;
import apap.tutorial.emsidi.model.*;

import java.util.List;

public interface UserService {
    UserModel addUser(UserModel user);
    public String encrypt(String password);
    List<UserModel> getListUser();
    UserModel getUserById(String username);
    void deleteUser(UserModel user);
    void updatePassword(UserModel user, String password);
    boolean checkPassword(String password);

}