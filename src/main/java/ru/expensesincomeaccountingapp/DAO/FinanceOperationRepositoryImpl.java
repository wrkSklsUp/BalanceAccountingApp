package ru.expensesincomeaccountingapp.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.expensesincomeaccountingapp.DAO.interfaces.FinanceOperationRepositoryInterface;
import ru.expensesincomeaccountingapp.entity.FinanceOperationEntity;
import ru.expensesincomeaccountingapp.enums.FinanceOperationTypes;

import java.time.LocalDate;
import java.util.List;

@Service
public class FinanceOperationRepositoryImpl implements FinanceOperationRepositoryInterface {
    @Autowired
    FinanceOperationDAO financeOperationDAO;
    @Override
    public List<FinanceOperationEntity> getAllFinanceOperations(){
        return financeOperationDAO.fetchFinanceOperation();
    }
    @Override
    public List<FinanceOperationEntity> getAllFinanceOperationsByDate(LocalDate dateFinancialTransactional){
        return financeOperationDAO.fetchFinanceOperation(dateFinancialTransactional);
    }
    @Override
    public List<FinanceOperationEntity> getAllFinanceOperationsByType(FinanceOperationTypes typeFinanceOperation){
        return financeOperationDAO.fetchFinanceOperation(typeFinanceOperation);
    }
    @Override
    @Transactional
    public void createRecordFinanceOperation(FinanceOperationEntity financeOperation){
        financeOperationDAO.saveFinanceOperation(financeOperation);
    }
    @Override
    @Transactional
    public void deleteRecordFinanceOperation(FinanceOperationEntity financeOperation){
        financeOperationDAO.softDeleteFinanceOperationTable(financeOperation);
    }
}
