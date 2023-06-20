package ru.expensesincomeaccountingapp.DAO.interfaces;

import ru.expensesincomeaccountingapp.entity.UserEntity;

import java.util.List;

public interface UserRepositoryInterface {
    public void updateUserState(UserEntity user);
    public void deleteUser(UserEntity user);
    public void createUserRecord(UserEntity user);
    public List<UserEntity> getAllUserRecords();
    public UserEntity getUserByUserAlias(String userAlias);
}
