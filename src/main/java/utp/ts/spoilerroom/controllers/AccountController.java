package utp.ts.spoilerroom.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.shiro.util.ByteSource;
import utp.ts.spoilerroom.dao.factories.DAOFactory;
import utp.ts.spoilerroom.dao.factories.H2DAOFactory;
import utp.ts.spoilerroom.dao.interfaces.AccountDAO;
import utp.ts.spoilerroom.dao.interfaces.PostDAO;
import utp.ts.spoilerroom.dto.AccountDTO;
import utp.ts.spoilerroom.dto.PostDTO;
import utp.ts.spoilerroom.models.AccountModel;
import utp.ts.spoilerroom.models.entities.AccountEntity;
import utp.ts.spoilerroom.models.entities.PostEntity;
import utp.ts.spoilerroom.utilities.PasswordUtils;

public class AccountController
{
	private final AccountModel accountModel;

	public AccountController(int accountId, int rowNumber)
	{
		List<PostDTO> postDTOList = postDAO().fetchAllPostsWhereOwnerAccountIdBetweenRow(accountId, rowNumber);
		List<PostEntity> postEntityList = new ArrayList<>();

		for (PostDTO postDTO : postDTOList)
		{
			postEntityList.add(new PostEntity(
				postDTO.getPost(), postDTO.getThread(), postDTO.getOwnerAccount(), null));
		}

		this.accountModel = new AccountModel(
			new AccountEntity(accountDAO().fetchById(accountId).getAccount()), postEntityList);
	}

	public AccountController(String email, String password)
	{
		AccountDTO accountDTO = accountDAO().fetchWhereEmail(email);

		if (accountDTO == null)
		{
			this.accountModel = null;
			return;
		}

		if (PasswordUtils.checkPassword(password, accountDTO.getPasswordSalt(), accountDTO.getPasswordHash()))
		{
			this.accountModel = new AccountModel(
				new AccountEntity(accountDTO.getAccount()));
		}
		else
		{
			this.accountModel = null;
		}
	}

	public AccountController(String email, String username, String password)
	{
		AccountDTO newAccountDTO = new AccountDTO();

		newAccountDTO.setAccountTypeId(3);
		newAccountDTO.setEmail(email);
		newAccountDTO.setUsername(username);

		ByteSource salt = PasswordUtils.getSalt();
		newAccountDTO.setPasswordSalt(salt.toHex());
		newAccountDTO.setPasswordHash(PasswordUtils.hashPassword(password, salt));

		Timestamp now = new Timestamp(System.currentTimeMillis());
		newAccountDTO.setCreationDate(now);
		newAccountDTO.setLastActiveDate(now);

		AccountDTO insertedAccountDTO = accountDAO().insert(newAccountDTO);

		this.accountModel = new AccountModel(new AccountEntity(insertedAccountDTO.getAccount()));
	}

	public static void updateLastActiveDate(int id)
	{
		accountDAO().updateLastActiveDateWhereId(id);
	}

	//TODO DO ALL POST BY ACCOUNT

	/**
	 * DAO
	 */

	private static AccountDAO accountDAO()
	{
		return Objects.requireNonNull(H2DAOFactory.getDAOFactory(DAOFactory.H2)).getAccountDAO();
	}

	private static PostDAO postDAO()
	{
		return Objects.requireNonNull(H2DAOFactory.getDAOFactory(DAOFactory.H2)).getPostDAO();
	}

	/**
	 * Getters
	 */

	public AccountModel getAccountModel()
	{
		return accountModel;
	}
}
