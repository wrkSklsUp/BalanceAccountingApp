package ru.expensesincomeaccountingapp.services;

import java.util.Date;

import ru.expensesincomeaccountingapp.enums.Curencies;
import ru.expensesincomeaccountingapp.enums.FinanceOperationTypes;

public class FinancePreparer {
	
	private int preparedFinanceValue;
	private FinanceOperationTypes flag;
	private Date date;
	private String place;
	private Curencies currency; 
	
	private int castFinanceValue(int notPrepareFinance, 
			FinanceOperationTypes financeOperationType) {
		
		return notPrepareFinance * financeOperationType.getValueCastConstant();
	}
	
	/*
	 * TODO: Implements this method;
	 */
//	private int convertFinanceValueToInt(String financeValueFromClient) {
//		
//	}
	
	public String convertFinanceValueToString(int resultToReturn) {
		int VALUE_TO_ADD_DOT = 3;
		String DOT_SYMBOL = ".";
		
		String[] splList = String.valueOf(resultToReturn).split("");	
		splList[String.valueOf(resultToReturn).length() - VALUE_TO_ADD_DOT] = 
		splList[String.valueOf(resultToReturn).length() - VALUE_TO_ADD_DOT] + DOT_SYMBOL;
		
		return String.join("", splList);
		
	}
	
	public void setFinanceValue(int finValue, FinanceOperationTypes financeOperationType) {
		this.preparedFinanceValue = castFinanceValue(finValue, financeOperationType);
	}
	
	public int getFinanceValue() {
		return preparedFinanceValue;
	}
	
	
}
