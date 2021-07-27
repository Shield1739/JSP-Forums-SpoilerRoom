package utp.ts.spoilerroom.models.entities;

import utp.ts.spoilerroom.models.beans.Account;

public class AccountEntity
{
	private final Account account;

	public AccountEntity(Account account)
	{
		this.account = account;
	}

	/**
	 * Getters
	 */

	public Account getAccount()
	{
		return account;
	}
}
