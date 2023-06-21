package ru.expensesincomeaccountingapp.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.expensesincomeaccountingapp.DAO.interfaces.UserRepositoryInterface;
import ru.expensesincomeaccountingapp.entity.UserEntity;

import java.util.List;

@Service
public class UserRepositoryImpl implements UserRepositoryInterface {
    @Autowired
    UserDAO userDAO;
    @Override
    public void updateUserState(UserEntity user){
        userDAO.updateUser(user);
    }
    @Override
    public void deleteUser(UserEntity user){
        userDAO.softDeleteUser(user);
    }
    @Override
    public void createUserRecord(UserEntity user){
        userDAO.saveUser(user);
    }
    @Override
    public List<UserEntity> getAllUserRecords(){
        return userDAO.readUser();
    }
    @Override
    public UserEntity getUserByUserAlias(String userAlias){
        return userDAO.readUser(userAlias);
    }

}
