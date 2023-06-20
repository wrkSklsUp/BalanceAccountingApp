package ru.expensesincomeaccountingapp.DAO.interfaces;

import ru.expensesincomeaccountingapp.entity.FinanceOperationEntity;
import ru.expensesincomeaccountingapp.enums.FinanceOperationTypes;

import java.time.LocalDate;
import java.util.List;

public interface FinanceOperationDAOInterface {
    List<FinanceOperationEntity> fetchFinanceOperation();
    List<FinanceOperationEntity> fetchFinanceOperation(FinanceOperationTypes typeFinanceOperation);
    List<FinanceOperationEntity> fetchFinanceOperation(LocalDate dateFinancialTransactional);
    void saveFinanceOperation(FinanceOperationEntity financeOperation);
    void softDeleteFinanceOperationTable(FinanceOperationEntity financeOperationRow);
}
