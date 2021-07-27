package utp.ts.spoilerroom.dao.implementations.h2;

import java.sql.Connection;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utp.ts.spoilerroom.dao.interfaces.SectionDAO;
import utp.ts.spoilerroom.dto.SectionDTO;
import utp.ts.spoilerroom.utilities.RSUtils;

public class H2SectionDAO implements SectionDAO
{
	// language=SQL
	private final static String FETCH_ALL_SECTIONS =
		"SELECT * FROM \"Section\" ORDER BY \"Weight\"";

	private final RSUtils<SectionDTO> rsUtils;
	private final QueryRunner queryRunner;

	private Connection c;

	public H2SectionDAO()
	{
		this.rsUtils = new RSUtils<>();
		this.queryRunner = new QueryRunner();
	}

	/**
	 * Private Methods
	 */

	private ResultSetHandler<List<SectionDTO>> getBeanListHandler()
	{
		return new BeanListHandler<>(SectionDTO.class);
	}

	private ResultSetHandler<SectionDTO> getBeanHandler()
	{
		return new BeanHandler<>(SectionDTO.class);
	}

	private List<SectionDTO> fetchList(String query, Object param)
	{
		return rsUtils.fetchList(this.queryRunner, query, getBeanListHandler(), param);
	}

	private List<SectionDTO> fetchList(String query)
	{
		return rsUtils.fetchList(this.queryRunner, query, getBeanListHandler());
	}

	private SectionDTO fetchSingle(String query, Object param)
	{
		return rsUtils.fetchSingle(this.queryRunner, query, getBeanHandler(), param);
	}

	/**
	 * Base DAO Methods
	 */

	@Override
	public List<SectionDTO> fetchAll()
	{
		return fetchList(FETCH_ALL_SECTIONS);
	}

	@Override
	public SectionDTO fetchById(int id)
	{
		return null;
	}

	@Override
	public SectionDTO insert(SectionDTO section)
	{
		return null;
	}

	@Override
	public void update(SectionDTO section)
	{

	}

	@Override
	public void delete(SectionDTO section)
	{

	}
}
