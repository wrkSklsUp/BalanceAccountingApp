package ru.expensesincomeaccountingapp.entity;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import ru.expensesincomeaccountingapp.enums.Curencies;
import ru.expensesincomeaccountingapp.enums.FinanceOperationTypes;
import ru.expensesincomeaccountingapp.enums.RowStates;

@Entity
@Table(name = "FinanceOperationTable")
@SQLDelete(sql = "UPDATE FinanceOperationTable SET state_finance_operation_row = 'DELETED' WHERE finance_operation_id = ?")
@Where(clause = "state_finance_operation_row = 'ACCESSIBLE'")

@FilterDef(name = "financeTypeOF", parameters = {
		               @ParamDef(name = "typeFO", type = String.class)
})

@FilterDef(name = "financeDateOF", parameters = {
        @ParamDef(name = "dateFO", type = String.class)
})


@Filters({
	@Filter(name = "financeTypeOF", condition = "type_finance_operation = :typeFO"),
	@Filter(name = "financeDateOF", condition = "date_finance_operation = :dateFO")
})
public class FinanceOperationEntity {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "finance_operation_id")
	private int financeOperationId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "state_finance_operation_row")
	private RowStates stateFinanceOperation;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type_finance_operation")
	private FinanceOperationTypes financeOperationType;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "currency")
	private Curencies currency;
	
	@Column(name = "finance_value")
	private BigDecimal financeValue;
	
	@Column(name = "date_finance_operation")
	private LocalDate dateFinanceOperation;
	
	@Column(name = "time_finance_operation")
	private LocalTime timeFinanceOperation;
	
	@Column(name = "place_finance_operation")
	private String plaseFinanceOperation;
	
	public FinanceOperationEntity() {
		
	}
	
	public FinanceOperationEntity(
		FinanceOperationTypes financeOperationType, 
		Curencies currency, 
		BigDecimal financeValue,
		LocalDate dateFinanceOperation,
		LocalTime timeFinanceOperation,
		String plaseFinanceOperation) 
	{
		this.stateFinanceOperation = RowStates.ACCESSIBLE;
		this.financeOperationType = financeOperationType;
		this.currency = currency;
		this.financeValue = financeValue;
		this.dateFinanceOperation = dateFinanceOperation;
		this.timeFinanceOperation = timeFinanceOperation;
		this.plaseFinanceOperation = plaseFinanceOperation;
		
	} 
	
	public int getFinanceOperationId() {
		return financeOperationId;
	}
	
	public void setFinanceOperationId(int financeOperationId) {
		this.financeOperationId = financeOperationId;
	}
	
	public RowStates getState() {
		return stateFinanceOperation;
	}
	
	public void setState(RowStates stateFinanceOperation) {
		this.stateFinanceOperation = stateFinanceOperation;
	}
	
	public FinanceOperationTypes getTypeFinanceOperation() {
		return financeOperationType;
	}
	
	public void setTypeFinanceOperationType(FinanceOperationTypes financeOperationType) {
		this.financeOperationType = financeOperationType;
	}
	
	public Curencies getCurrency() {
		return currency;
	}
	
	public void setCurrency(Curencies currency) {
		this.currency = currency;
	}
		
	public BigDecimal getFinanceValue() {
		return financeValue;
	}
	
	public void setFinanceValue(BigDecimal financeValue) {
		this.financeValue = financeValue;
	}
	
	
	public LocalDate getDateFinanceOperation() {
		return dateFinanceOperation;
	}
	
	public void setDateFinanceOperation(LocalDate dateFinanceOperation) {
		this.dateFinanceOperation = dateFinanceOperation;
	}
	
	public LocalTime getTimeFinanceOperation() {
		return timeFinanceOperation;
	}
	
	public void setTimeFinanceOperation(LocalTime timeFinanceOperation) {
		this.timeFinanceOperation = timeFinanceOperation;
	}
	
	public String getPlaseFinanceOPeration() {
		return plaseFinanceOperation;
	}
	
	public void setPlaseFinanceOPeration(String plaseFinanceOperation) {
		this.plaseFinanceOperation = plaseFinanceOperation;
	}
	
	
	public String toString() {
		return "Type fin.Oper: " + getTypeFinanceOperation() + 
				", currency: " + getCurrency() + 
				", fin. val: " + getFinanceValue() + 
				", Date: " + getDateFinanceOperation() + 
				", Time: " + getTimeFinanceOperation() + 
				", State: " + getState();
	}
	
}

