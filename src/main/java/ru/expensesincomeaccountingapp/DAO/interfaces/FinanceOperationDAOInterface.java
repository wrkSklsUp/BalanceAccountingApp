package ru.expensesincomeaccountingapp.DAO.interfaces;

import ru.expensesincomeaccountingapp.entity.FinanceOperationEntity;
import ru.expensesincomeaccountingapp.enums.FinanceOperationTypes;

import java.time.LocalDate;
import java.util.List;

public interface FinanceOperationDAOInterface {
    public List<FinanceOperationEntity> fetchFinanceOperation();
    public List<FinanceOperationEntity> fetchFinanceOperation(FinanceOperationTypes typeFinanceOperation);
    public List<FinanceOperationEntity> fetchFinanceOperation(LocalDate dateFinancialTransactional);
    public void saveFinanceOperation(FinanceOperationEntity financeOperation);
    public void softDeleteFinanceOperationTable(FinanceOperationEntity financeOperationRow);
}
