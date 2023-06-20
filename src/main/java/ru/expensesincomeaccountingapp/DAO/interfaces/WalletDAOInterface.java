package ru.expensesincomeaccountingapp.DAO.interfaces;

import ru.expensesincomeaccountingapp.entity.UserEntity;
import ru.expensesincomeaccountingapp.entity.WalletEntity;
import ru.expensesincomeaccountingapp.enums.Curencies;

import java.util.List;

public interface WalletDAOInterface {

    void close(WalletEntity wallet);
    int updateBalance(WalletEntity wallet);
    WalletEntity fetchWallet(UserEntity walletOwner, Curencies currency);
    List<WalletEntity> fetchWallet(UserEntity walletOwner);

}
