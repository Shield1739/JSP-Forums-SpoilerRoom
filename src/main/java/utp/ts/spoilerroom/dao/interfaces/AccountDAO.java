package utp.ts.spoilerroom.dao.interfaces;

import utp.ts.spoilerroom.dto.AccountDTO;

public interface AccountDAO extends BaseDAO<AccountDTO>
{
	AccountDTO fetchWhereEmail(String email);
	AccountDTO fetchTopCheeser();

	void updateLastActiveDateWhereId(int id);
}
