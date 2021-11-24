package apap.tutorial.emsidi.service;

import apap.tutorial.emsidi.model.CabangModel;
import apap.tutorial.emsidi.model.MenuModel;
import apap.tutorial.emsidi.model.PegawaiModel;
import apap.tutorial.emsidi.model.UserModel;
import apap.tutorial.emsidi.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDb userDb;

    @Override
    public UserModel addUser(UserModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDb.save(user);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public List<UserModel> getListUser() {
        return userDb.findAll();
    }

    @Override
    public UserModel getUserById(String username){
        UserModel user = userDb.findByUsername(username);
            return user;
    }

    @Override
    public void deleteUser(UserModel user){
        userDb.delete(user);
    }

    @Override
    public void updatePassword(UserModel user, String password){
        String newPassword = encrypt(password);
        user.setPassword(newPassword);
        userDb.save(user);
    }

    @Override
    public boolean checkPassword(String password){
        if(password.length() >= 8){
            Pattern p = Pattern.compile("\\d");
           if(p.matcher(password).find()){
               Pattern x = Pattern.compile("\\p{Upper}");
               Pattern y = Pattern.compile("\\p{Lower}");
               if(x.matcher(password).find() && y.matcher(password).find()){
                   Pattern z = Pattern.compile("\\p{Punct}");
                   if(z.matcher(password).find()){
                       return true;
                   }
                   else{
                       return false;
                   }
               }
               else{
                   return false;
               }
           }
           else{
               return false;
           }
        }
        else {
            return false;
        }
    }
}