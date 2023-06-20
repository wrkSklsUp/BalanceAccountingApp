package ru.expensesincomeaccountingapp.enums;

public enum FinanceOperationTypes {
	
	RECEIPT_FINANCE(1),
	WASTE_FINANCE(-1);
	
	private int valueCastConstant;
	
	FinanceOperationTypes(int valueCastConstant) {
		this.valueCastConstant = valueCastConstant;
	}
	
	public int getValueCastConstant() {
		return valueCastConstant;
	}
}
