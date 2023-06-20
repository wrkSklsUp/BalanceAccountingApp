package ru.expensesincomeaccountingapp.DAO.interfaces;

import ru.expensesincomeaccountingapp.entity.UserEntity;
import ru.expensesincomeaccountingapp.entity.WalletEntity;
import ru.expensesincomeaccountingapp.enums.Curencies;

import java.util.List;

public interface WalletDAOInterface {

    public void close(WalletEntity wallet);
    public int updateBalance(WalletEntity wallet);
    public WalletEntity fetchWallet(UserEntity walletOwner, Curencies currency);
    public List<WalletEntity> fetchWallet(UserEntity walletOwner);

}
