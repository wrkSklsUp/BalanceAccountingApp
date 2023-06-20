package ru.expensesincomeaccountingapp.DAO.interfaces;

import java.time.LocalDate;
import java.util.List;

import ru.expensesincomeaccountingapp.entity.FinanceOperationEntity;
import ru.expensesincomeaccountingapp.enums.FinanceOperationTypes;

public interface FinanceOperationRepositoryInterface {
    public List<FinanceOperationEntity> getAllFinanceOperations();
    public List<FinanceOperationEntity> getAllFinanceOperationsByDate(LocalDate dateFinancialTransactional);
    public List<FinanceOperationEntity> getAllFinanceOperationsByType(FinanceOperationTypes typeFinanceOperation);
    public void createRecordFinanceOperation(FinanceOperationEntity financeOperation);
    public void deleteRecordFinanceOperation(FinanceOperationEntity financeOperation);
}
