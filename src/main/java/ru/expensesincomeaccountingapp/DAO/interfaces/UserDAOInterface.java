package ru.expensesincomeaccountingapp.DAO.interfaces;

import ru.expensesincomeaccountingapp.entity.UserEntity;

import java.util.List;

public interface UserDAOInterface {
    void updateUser(UserEntity user);
    void softDeleteUser(UserEntity user);
    void saveUser(UserEntity user);
    List<UserEntity> readUser();
    UserEntity readUser(String userAlias);
}
