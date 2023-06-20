package ru.expensesincomeaccountingapp.DAO.interfaces;

import ru.expensesincomeaccountingapp.entity.UserEntity;
import ru.expensesincomeaccountingapp.entity.WalletEntity;
import ru.expensesincomeaccountingapp.enums.Curencies;

import java.util.List;

public interface WalletRepositoryInterface {
    public void closeWallet(WalletEntity wallet);
    public int refreshWalletBalance(WalletEntity wallet);
    public WalletEntity getWallet(UserEntity walletOwner, Curencies currency);
    public List<WalletEntity> getWalletList(UserEntity walletOwner);

}
