package ru.expensesincomeaccountingapp.DAO.interfaces;

import ru.expensesincomeaccountingapp.entity.UserEntity;

import java.util.List;

public interface UserDAOInterface {
    public void updateUser(UserEntity user);
    public void softDeleteUser(UserEntity user);
    public void saveUser(UserEntity user);
    public List<UserEntity> readUser();
    public UserEntity readUser(String userAlias);
}
