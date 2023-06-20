package ru.expensesincomeaccountingapp.entity;

import java.math.BigDecimal;

import java.util.Date;

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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import ru.expensesincomeaccountingapp.enums.Curencies;
import ru.expensesincomeaccountingapp.enums.RowStates;

@Entity
@Table(name = "WalletTable")
@SQLDelete(sql = "UPDATE WalletTable SET wallet_owner = NULL, state_wallet = 'CLOSED' WHERE wallet_id = ?")
@Where(clause = "state_wallet = 'OPEN'")

@FilterDef(name = "filterWalletsByOwnerCurrency", parameters = {
		@ParamDef(name = "walletCurrency", type = String.class), 
		@ParamDef(name = "walletOwner", type = Integer.class)
})

@FilterDef(name = "filterWalletsByOwner", parameters = {
		@ParamDef(name = "walletOwner", type = Integer.class)
})

@Filters({
	@Filter(name = "filterWalletsByOwnerCurrency", 
            condition = "currency = :walletCurrency and wallet_owner = :walletOwner"), 
	
	@Filter(name = "filterWalletsByOwner", condition = "wallet_owner = :walletOwner")
})
public class WalletEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "wallet_id")
	private int walletId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "state_wallet")
	private RowStates stateWallet = RowStates.OPEN;
	
	@ManyToOne
	@JoinColumn(name = "wallet_owner")
	private UserEntity walletOwner;
	
	@Column(name = "balance")
	private BigDecimal balance;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "currency")
	private Curencies currency;
		
	@Column(name = "balance_state_on_date")
	private Date balanceStateOnDate;
		
	
	public WalletEntity() {
		
	}
	
	public WalletEntity(
		BigDecimal balance, 
		Curencies currency, 
		Date balanceStateOnDate) 
	
	{	
		this.balance = balance;
		this.currency = currency;
		this.balanceStateOnDate = balanceStateOnDate;
		
	}
	
	public int getWalletId() {
		return walletId;
	}
	
	public void setWalletId(int walletId) {
		this.walletId = walletId;
	}
	
	public RowStates getStateWallet() {
		return stateWallet;
	}
	
	public void setStateWallet(RowStates stateWallet) {
		this.stateWallet = stateWallet;
	}
	
	public UserEntity getWalletOwner() {
		return walletOwner;
	}
	
	public void setWalletOwner(UserEntity walletOwner) {
		this.walletOwner = walletOwner;
	}
	
	public Curencies getCurrency() {
		return currency;
	}
	
	public void setCurrency(Curencies currency) {
		this.currency = currency;
	}
	
	public BigDecimal getBalance() {
		return balance;
	}
	
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	public Date getBalanceStateOnDate() {
		return balanceStateOnDate;
	}
	
	public void setBalanceStateOnDate(Date balanceStateOnDate) {
		this.balanceStateOnDate = balanceStateOnDate;
	}
		
	@Override
	public String toString() {
		return "Wallet {wallet_id: " + getWalletId() + 
				", state_wallet: " + getStateWallet() + 
				", balance: " + getBalance() + 
				", currency: " + getCurrency() + 
				", balance_state_on_date: " + getBalanceStateOnDate() + "}";
	}
	
}
