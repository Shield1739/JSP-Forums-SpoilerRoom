package utp.ts.spoilerroom.dao.implementations.h2;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import utp.ts.spoilerroom.dao.interfaces.HomeDAO;
import utp.ts.spoilerroom.dto.HomeDTO;
import utp.ts.spoilerroom.utilities.RSUtils;

public class H2HomeDAO implements HomeDAO
{
	// language=SQL
	private final static String FETCH =
		"SELECT COUNT(t.\"Id\") AS \"threadCount\", p.\"postId\" AS \"postCount\", " +
			"a.\"accountId\" AS \"memberCount\" FROM \"Thread\" t " +
			"LEFT OUTER JOIN (SELECT COUNT(\"Id\") AS \"postId\" FROM \"Post\") p " +
			"LEFT OUTER JOIN (SELECT COUNT(\"Id\") AS \"accountId\" FROM \"Account\") a ";

	private final RSUtils<HomeDTO> rsUtils;
	private final QueryRunner queryRunner;

	public H2HomeDAO()
	{
		this.rsUtils = new RSUtils<>();
		this.queryRunner = new QueryRunner();
	}

	/**
	 * Private Methods
	 */

	private ResultSetHandler<HomeDTO> getBeanHandler()
	{
		return new BeanHandler<>(HomeDTO.class);
	}

	/**
	 * Home DAO
	 */

	@Override
	public HomeDTO fetch()
	{
		return rsUtils.fetchSingle(queryRunner, FETCH, getBeanHandler());
	}
}
