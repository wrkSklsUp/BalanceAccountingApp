package ru.expensesincomeaccountingapp.entity;

import java.util.List;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import ru.expensesincomeaccountingapp.enums.RowStates;

@Entity
@Table(name = "UserTable")
@SQLDelete(sql = "UPDATE UserTable SET user_state = 'DELETED' WHERE user_id = ?")
@Where(clause = "user_state = 'ACCESSIBLE'")

@FilterDef(name = "filterUsersByAlias", parameters = {
		@ParamDef(name = "userAlias", type = String.class)})

@Filter(name = "filterUsersByAlias", condition = "user_alias = :userAlias")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "user_state")
	private RowStates userState = RowStates.ACCESSIBLE;
	
	@Column(name = "user_alias", unique = true)
	private String uniqueUserAlias;
	
	@OneToMany(mappedBy = "walletOwner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<WalletEntity> wallets = new ArrayList<>();	
	
	public UserEntity() {
		
	}
	
	public UserEntity(
		String uniqueUserAlias
	) 
	
	{
		this.uniqueUserAlias = uniqueUserAlias;	
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public RowStates getUserState() {
		return userState;
	}
	
	public void setUserState(RowStates userState) {
		this.userState = userState;
	}
	
	public String getUniqueUserAlias() {
		return uniqueUserAlias;
	}
	
	public void setUniqueUserAlias(String uniqueUserAlias) {
		this.uniqueUserAlias = uniqueUserAlias;
	}
		
	public List<WalletEntity> getWallets(){
		return wallets;
	}
	
	public void setWallets(List<WalletEntity> wallets) {
		this.wallets = wallets;
	}
	
	public void addWallet(WalletEntity wallet) {
		this.wallets.add(wallet);
		wallet.setWalletOwner(this);
	}
		
	@Override
	public String toString() {
		return "User {user_id: " + getUserId() +
				", user_name: " + getUniqueUserAlias() + 
				", my_wallets: " + getWallets() + "}";
	}
		
}
