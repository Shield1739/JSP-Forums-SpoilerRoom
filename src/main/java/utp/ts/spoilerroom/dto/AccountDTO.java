package utp.ts.spoilerroom.dto;

import utp.ts.spoilerroom.models.beans.Account;

public class AccountDTO extends Account
{
	public AccountDTO()
	{
	}

	/**
	 * Beans
	 */

	public Account getAccount()
	{
		Account account = new Account();

		account.setId(this.getId());
		account.setAccountTypeId(this.getAccountTypeId());
		account.setEmail(this.getEmail());
		account.setUsername(this.getUsername());
		account.setPasswordSalt(this.getPasswordSalt());
		account.setPasswordHash(this.getPasswordHash());
		account.setCreationDate(this.getCreationDate());
		account.setLastActiveDate(this.getLastActiveDate());
		account.setProfileImageUrl(this.getProfileImageUrl());
		account.setTitle(this.getTitle());
		account.setLocation(this.getLocation());
		account.setAccountTypeName(this.getAccountTypeName());
		account.setThreadCount(this.getThreadCount());
		account.setPostCount(this.getPostCount());
		account.setCheese(this.getCheese());

		return account;
	}
}
