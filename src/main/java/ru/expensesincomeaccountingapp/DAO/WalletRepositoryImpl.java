package ru.expensesincomeaccountingapp.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.expensesincomeaccountingapp.DAO.interfaces.WalletRepositoryInterface;
import ru.expensesincomeaccountingapp.entity.UserEntity;
import ru.expensesincomeaccountingapp.entity.WalletEntity;
import ru.expensesincomeaccountingapp.enums.Curencies;

import java.util.List;

@Service
public class WalletRepositoryImpl implements WalletRepositoryInterface {

    @Autowired
    WalletDAO walletDAO;
    @Override
    public void closeWallet(WalletEntity wallet){
        walletDAO.close(wallet);
    }
    @Override
    public int refreshWalletBalance(WalletEntity wallet){
        return walletDAO.updateBalance(wallet);
    }
    @Override
    public WalletEntity getWallet(UserEntity walletOwner, Curencies currency){
        return walletDAO.fetchWallet(walletOwner, currency);
    }
    @Override
    public List<WalletEntity> getWalletList(UserEntity walletOwner){
        return walletDAO.fetchWallet(walletOwner);
    }
}
