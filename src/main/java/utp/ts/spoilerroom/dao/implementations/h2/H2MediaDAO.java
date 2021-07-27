package utp.ts.spoilerroom.dao.implementations.h2;

import java.sql.Connection;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utp.ts.spoilerroom.dao.interfaces.MediaDAO;
import utp.ts.spoilerroom.dto.MediaDTO;
import utp.ts.spoilerroom.utilities.RSUtils;

public class H2MediaDAO implements MediaDAO
{
	// language=SQL
	private final static String FETCH_ALL_STREAMING_MEDIA =
		"SELECT m.*, mt.\"Name\" AS \"mediaTypeName\", COUNT(t.\"Id\") AS \"threadCount\", " +
			"COALESCE(SUM(pc.\"postCount\"), 0) AS \"postCount\" " +
			"FROM \"Media\" m " +
			"JOIN \"MediaType\" mt ON m.\"MediaTypeId\" = mt.\"Id\" " +
			"JOIN \"StreamingMedia\" sm ON m.\"Id\" = sm.\"MediaId\" " +
			"LEFT JOIN \"Thread\" t ON m.\"Id\" = t.\"MediaId\" " +
			"LEFT JOIN (SELECT tc.\"Id\", COUNT(p.\"Id\") AS \"postCount\" FROM \"Thread\" tc " +
				"JOIN \"Post\" p on tc.\"Id\" = p.\"ThreadId\" " +
				"GROUP BY tc.\"Id\") pc ON t.\"Id\" = pc.\"Id\" " +
			"GROUP BY m.\"Id\"";

	private final RSUtils<MediaDTO> rsUtils;
	private final QueryRunner queryRunner;

	private Connection c;

	public H2MediaDAO()
	{
		this.rsUtils = new RSUtils<>();
		this.queryRunner = new QueryRunner();
	}

	/**
	 * Private Methods
	 */

	private ResultSetHandler<List<MediaDTO>> getBeanListHandler()
	{
		return new BeanListHandler<>(MediaDTO.class);
	}

	private ResultSetHandler<MediaDTO> getBeanHandler()
	{
		return new BeanHandler<>(MediaDTO.class);
	}

	private List<MediaDTO> fetchList(String query, Object param)
	{
		return rsUtils.fetchList(this.queryRunner, query, getBeanListHandler(), param);
	}

	private List<MediaDTO> fetchList(String query)
	{
		return rsUtils.fetchList(this.queryRunner, query, getBeanListHandler());
	}

	private MediaDTO fetchSingle(String query, Object param)
	{
		return rsUtils.fetchSingle(this.queryRunner, query, getBeanHandler(), param);
	}

	/**
	 *	Media DAO Methods
	 */

	@Override
	public List<MediaDTO> fetchAllStreamingMedia()
	{
		return fetchList(FETCH_ALL_STREAMING_MEDIA);
	}

	/**
	 *	Base DAO Methods
	 */


	@Override
	public List<MediaDTO> fetchAll()
	{
		return null;
	}

	@Override
	public MediaDTO fetchById(int id)
	{
		return null;
	}

	@Override
	public MediaDTO insert(MediaDTO mediaEntity)
	{
		return null;
	}

	@Override
	public void update(MediaDTO mediaEntity)
	{

	}

	@Override
	public void delete(MediaDTO mediaEntity)
	{

	}
}
