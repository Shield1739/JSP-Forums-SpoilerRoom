package utp.ts.spoilerroom.dao.implementations.h2;

import java.sql.Timestamp;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utp.ts.spoilerroom.dao.interfaces.AccountDAO;
import utp.ts.spoilerroom.dto.AccountDTO;
import utp.ts.spoilerroom.utilities.RSUtils;

public class H2AccountDAO implements AccountDAO
{
	// language=SQL
	private static final String FETCH_ACCOUNT_WHERE_EMAIL =
		"SELECT * FROM \"Account\" WHERE \"Email\"=?";

	// language=SQL
	private static final String FETCH_TOP_ACCOUNT =
		"SELECT TOP(1) a.*, COUNT(p.\"Id\") AS \"postCount\" FROM \"Account\" a " +
			"JOIN \"Post\" p ON a.\"Id\" = p.\"OwnerAccountId\" " +
			"GROUP BY a.\"Id\" " +
			"ORDER BY \"postCount\" DESC";

	// language=SQL
	private static final String INSERT_NEW_ACCOUNT =
		"INSERT INTO \"Account\" (\"AccountTypeId\", \"Email\", \"Username\", \"PasswordSalt\", \"PasswordHash\", " +
			"\"CreationDate\", \"LastActiveDate\") VALUES (?, ?, ?, ?, ?, ?, ?);";

	// language=SQL
	private static final String UPDATE_ACCOUNT_LAST_ACTIVE_DATE_WHERE_ID =
		"UPDATE \"Account\" SET \"LastActiveDate\"=? WHERE \"Id\"=?";

	// language=SQL
	private static final String FETCH_ACCOUNT_WHERE_ID =
		"SELECT a.*, at.\"Name\" AS \"accountTypeName\" , COUNT(t.\"Id\") AS \"threadCount\", COUNT(p.\"Id\") AS \"postCount\" " +
			"FROM \"Account\" a " +
			"JOIN \"AccountType\" at ON a.\"AccountTypeId\" = at.\"Id\" " +
			"JOIN \"Thread\" t ON a.\"Id\" = t.\"OwnerAccountId\" " +
			"JOIN \"Post\" p ON a.\"Id\" = p.\"OwnerAccountId\" " +
			"WHERE a.\"Id\"=?";

	private RSUtils<AccountDTO> rsUtils;
	private final QueryRunner queryRunner;

	public H2AccountDAO()
	{
		this.rsUtils = new RSUtils<>();
		this.queryRunner = new QueryRunner();
	}

	/**
	 * Private Methods
	 */

	private ResultSetHandler<List<AccountDTO>> getBeanListHandler()
	{
		return new BeanListHandler<>(AccountDTO.class);
	}

	private ResultSetHandler<AccountDTO> getBeanHandler()
	{
		return new BeanHandler<>(AccountDTO.class);
	}

	private List<AccountDTO> fetchList(String query, Object param)
	{
		return rsUtils.fetchList(this.queryRunner, query, getBeanListHandler(), param);
	}

	private List<AccountDTO> fetchList(String query)
	{
		return rsUtils.fetchList(this.queryRunner, query, getBeanListHandler());
	}

	private AccountDTO fetchSingle(String query, Object param)
	{
		return rsUtils.fetchSingle(this.queryRunner, query, getBeanHandler(), param);
	}

	private AccountDTO fetchSingle(String query)
	{
		return rsUtils.fetchSingle(this.queryRunner, query, getBeanHandler());
	}

	/**
	 *	Account DAO Methods
	 */

	@Override
	public AccountDTO fetchWhereEmail(String email)
	{
		return fetchSingle(FETCH_ACCOUNT_WHERE_EMAIL, email);
	}

	@Override
	public AccountDTO fetchTopCheeser()
	{
		return fetchSingle(FETCH_TOP_ACCOUNT);
	}

	@Override
	public void updateLastActiveDateWhereId(int id)
	{
		rsUtils.updateSingle(queryRunner, UPDATE_ACCOUNT_LAST_ACTIVE_DATE_WHERE_ID, new Timestamp(System.currentTimeMillis()), id);
	}


	/**
	 *	Base DAO Methods
	 */

	@Override
	public List<AccountDTO> fetchAll()
	{
		return null;
	}

	@Override
	public AccountDTO fetchById(int id)
	{
		return fetchSingle(FETCH_ACCOUNT_WHERE_ID, id);
	}

	@Override
	public AccountDTO insert(AccountDTO accountDTO)
	{
		return rsUtils.insertSingle(queryRunner, INSERT_NEW_ACCOUNT, getBeanHandler(), accountDTO.getAccountTypeId(),
			accountDTO.getEmail(), accountDTO.getUsername(), accountDTO.getPasswordSalt(),
			accountDTO.getPasswordHash(), accountDTO.getCreationDate().toString(),
			accountDTO.getLastActiveDate().toString());
	}

	@Override
	public void update(AccountDTO accountDTO)
	{

	}

	@Override
	public void delete(AccountDTO accountDTO)
	{

	}
}
