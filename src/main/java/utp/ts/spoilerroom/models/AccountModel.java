package utp.ts.spoilerroom.models;

import java.util.List;
import utp.ts.spoilerroom.models.entities.AccountEntity;
import utp.ts.spoilerroom.models.entities.PostEntity;

public class AccountModel
{
	private final AccountEntity accountEntity;
	private final List<PostEntity> postEntityList;

	public AccountModel(AccountEntity accountEntity)
	{
		this.accountEntity = accountEntity;
		this.postEntityList = null;
	}

	public AccountModel(AccountEntity accountEntity, List<PostEntity> postEntityList)
	{
		this.accountEntity = accountEntity;
		this.postEntityList = postEntityList;
	}

	public boolean isAdmin()
	{
		//DB ID DEPENDENT
		return this.accountEntity != null && this.accountEntity.getAccount().getAccountTypeId() == 1;
	}

	public boolean isModerator()
	{
		//DB ID DEPENDENT
		return this.accountEntity != null &&
			(this.accountEntity.getAccount().getAccountTypeId() == 1
				|| this.accountEntity.getAccount().getAccountTypeId() == 2);
	}

	/**
	 * Getters
	 */

	public AccountEntity getAccountEntity()
	{
		return accountEntity;
	}

	public List<PostEntity> getPostEntityList()
	{
		return postEntityList;
	}
}
