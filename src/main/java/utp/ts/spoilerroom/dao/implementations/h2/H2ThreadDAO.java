package utp.ts.spoilerroom.dao.implementations.h2;

import java.sql.Connection;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utp.ts.spoilerroom.dao.interfaces.ThreadDAO;
import utp.ts.spoilerroom.dto.ThreadDTO;
import utp.ts.spoilerroom.utilities.RSUtils;

public class H2ThreadDAO implements ThreadDAO
{
	// language=SQL
	private final static String FETCH_THREAD_WHERE_ID =
		"SELECT t.*, b.\"Title\" AS \"boardTitle\", s.\"Title\" AS \"sectionTitle\", COUNT(p.\"Id\") AS \"postCount\", " +
			"m.\"Title\" AS \"mediaTitle\", mt.\"Name\" AS \"mediaTypeName\", lp.\"lastPostId\", COALESCE(v.\"votes\", 0) AS \"cheese\"  " +
			"FROM \"Thread\" t " +
			"JOIN \"Board\" b ON t.\"BoardId\" = b.\"Id\" " +
			"JOIN \"Section\" s ON b.\"SectionId\" = s.\"Id\" " +
			"JOIN \"Post\" p ON t.\"Id\" = p.\"ThreadId\" " +
			"LEFT JOIN \"Media\" m ON t.\"MediaId\" = m.\"Id\" " +
			"LEFT JOIN \"MediaType\" mt ON m.\"MediaTypeId\" = mt.\"Id\" " +
			"JOIN (SELECT MAX(\"Id\") AS \"lastPostId\", \"ThreadId\" FROM \"Post\" " +
				"GROUP BY \"ThreadId\") lp ON t.\"Id\"=lp.\"ThreadId\" " +
			"LEFT JOIN (SELECT (COUNT(CASEWHEN(\"VoteType\"=1, 1, null)) - COUNT(CASEWHEN(\"VoteType\"=2, 1, null))) AS \"votes\", " +
				"\"PostId\" FROM \"Vote\" GROUP BY \"PostId\") v ON v.\"PostId\"= " +
					"(SELECT \"Id\" FROM \"Post\" p WHERE p.\"ParentId\" IS null AND t.\"Id\" = p.\"ThreadId\") " +
			"WHERE t.\"Id\"=?";

	// language=SQL
	private final static String FETCH_TOP_5_THREADS =
		"SELECT TOP(5) t.*, b.\"Title\" AS \"boardTitle\", COUNT(p.\"Id\") AS \"postCount\", " +
			"oa.\"Username\" AS \"ownerAccountUsername\", oa.\"ProfileImageUrl\" AS \"ownerAccountProfileImageUrl\", " +
			"COALESCE(v.\"votes\", 0) AS \"cheese\" " +
			"FROM \"Thread\" t " +
			"JOIN \"Board\" b ON t.\"BoardId\" = b.\"Id\" " +
			"JOIN \"Post\" p ON t.\"Id\" = p.\"ThreadId\" " +
			"JOIN \"Account\" oa ON t.\"OwnerAccountId\" = oa.\"Id\" " +
			"LEFT JOIN (SELECT (COUNT(CASEWHEN(\"VoteType\"=1, 1, null)) - COUNT(CASEWHEN(\"VoteType\"=2, 1, null))) AS \"votes\", " +
				"\"PostId\" FROM \"Vote\" GROUP BY \"PostId\") v ON v.\"PostId\"= " +
					"(SELECT \"Id\" FROM \"Post\" p WHERE p.\"ParentId\" IS null AND t.\"Id\" = p.\"ThreadId\") " +
			"GROUP BY t.\"Id\"";

	// language=SQL
	private final static String FETCH_TOP_5_ANNOUNCEMENTS =
		"SELECT TOP(5) t.*, b.\"Title\" AS \"boardTitle\", COUNT(p.\"Id\") AS \"postCount\", " +
			"oa.\"Username\" AS \"ownerAccountUsername\", oa.\"ProfileImageUrl\" AS \"ownerAccountProfileImageUrl\", " +
			"COALESCE(v.\"votes\", 0) AS \"cheese\" " +
			"FROM \"Thread\" t " +
			"JOIN \"Board\" b ON t.\"BoardId\" = b.\"Id\" " +
			"JOIN \"Post\" p ON t.\"Id\" = p.\"ThreadId\" " +
			"JOIN \"Account\" oa ON t.\"OwnerAccountId\" = oa.\"Id\" " +
			"LEFT JOIN (SELECT (COUNT(CASEWHEN(\"VoteType\"=1, 1, null)) - COUNT(CASEWHEN(\"VoteType\"=2, 1, null))) AS \"votes\", " +
			"\"PostId\" FROM \"Vote\" GROUP BY \"PostId\") v ON v.\"PostId\"= " +
			"(SELECT \"Id\" FROM \"Post\" p WHERE p.\"ParentId\" IS null AND t.\"Id\" = p.\"ThreadId\") " +
			"WHERE b.\"Id\"=1 " +
			"GROUP BY t.\"Id\" " +
			"ORDER BY t.\"CreationDate\" DESC";

	// language=SQL
	private final static String FETCH_TOP_5_MOST_ACTIVE_THREADS =
		FETCH_TOP_5_THREADS + " ORDER BY \"postCount\" DESC";

	// language=SQL
	private final static String FETCH_TOP_5_LATEST_THREADS =
		FETCH_TOP_5_THREADS + " ORDER BY t.\"CreationDate\" DESC";

	// language=SQL
	private final static String FETCH_ALL_THREADS_WHERE_BOARD_ID =
		"SELECT t.*, COUNT(p.\"Id\") AS \"postCount\", m.\"Title\" AS \"mediaTitle\", mt.\"Name\" AS \"mediaTypeName\", " +
			"oa.\"Username\" AS \"ownerAccountUsername\", oa.\"ProfileImageUrl\" AS \"ownerAccountProfileImageUrl\", " +
			"lp.\"lastPostId\", lpd.\"CreationDate\" AS \"lastPostCreationDate\", lpoa.\"Id\" AS \"lastPostOwnerAccountId\", " +
			"lpoa.\"Username\" AS \"lastPostOwnerAccountUsername\", lpoa.\"ProfileImageUrl\" AS \"lastPostOwnerAccountProfileImageUrl\", " +
			"COALESCE(v.\"votes\", 0) AS \"cheese\" " +
			"FROM \"Thread\" t " +
			"JOIN \"Post\" p ON t.\"Id\" = p.\"ThreadId\" " +
			"LEFT JOIN \"Media\" m on m.\"Id\" = t.\"MediaId\" " +
			"LEFT JOIN \"MediaType\" mt ON m.\"MediaTypeId\" = mt.\"Id\" " +
			"JOIN \"Account\" oa ON t.\"OwnerAccountId\" = oa.\"Id\" " +
			"JOIN (SELECT MAX(\"Id\") AS \"lastPostId\", \"ThreadId\" FROM \"Post\" " +
				"GROUP BY \"ThreadId\") lp ON t.\"Id\"=lp.\"ThreadId\" " +
			"JOIN \"Post\" lpd ON lp.\"lastPostId\" = lpd.\"Id\" " +
			"JOIN \"Account\" lpoa ON lpd.\"OwnerAccountId\" = lpoa.\"Id\" " +
			"LEFT JOIN (SELECT (COUNT(CASEWHEN(\"VoteType\"=1, 1, null)) - COUNT(CASEWHEN(\"VoteType\"=2, 1, null))) AS \"votes\", " +
				"\"PostId\" FROM \"Vote\" GROUP BY \"PostId\") v ON v.\"PostId\"= " +
					"(SELECT \"Id\" FROM \"Post\" p WHERE p.\"ParentId\" IS null AND t.\"Id\" = p.\"ThreadId\") " +
			"WHERE t.\"BoardId\"=? GROUP BY t.\"Id\" " +
			"ORDER BY lpd.\"CreationDate\" DESC";

	// language=SQL
	private final static String FETCH_ALL_THREADS_WHERE_BOARD_ID_BETWEEN_ROW =
		"WITH cte AS " +
			"(" +
				"SELECT t.*, COUNT(p.\"Id\") AS \"postCount\", m.\"Title\" AS \"mediaTitle\", mt.\"Name\" AS \"mediaTypeName\", " +
				"oa.\"Username\" AS \"ownerAccountUsername\", oa.\"ProfileImageUrl\" AS \"ownerAccountProfileImageUrl\", " +
				"lp.\"lastPostId\", lpd.\"CreationDate\" AS \"lastPostCreationDate\", lpoa.\"Id\" AS \"lastPostOwnerAccountId\", " +
				"lpoa.\"Username\" AS \"lastPostOwnerAccountUsername\", lpoa.\"ProfileImageUrl\" AS \"lastPostOwnerAccountProfileImageUrl\", " +
				"COALESCE(v.\"votes\", 0) AS \"cheese\", COALESCE(lpv.\"votes\", 0) AS \"lastPostCheese\", " +
				"ROW_NUMBER() over (ORDER BY lpd.\"CreationDate\" DESC) AS \"rn\" " +
				"FROM \"Thread\" t " +
				"JOIN \"Post\" p ON t.\"Id\" = p.\"ThreadId\" " +
				"LEFT JOIN \"Media\" m on m.\"Id\" = t.\"MediaId\" " +
				"LEFT JOIN \"MediaType\" mt ON m.\"MediaTypeId\" = mt.\"Id\" " +
				"JOIN \"Account\" oa ON t.\"OwnerAccountId\" = oa.\"Id\" " +
				"JOIN (SELECT MAX(\"Id\") AS \"lastPostId\", \"ThreadId\" FROM \"Post\" " +
				"GROUP BY \"ThreadId\") lp ON t.\"Id\"=lp.\"ThreadId\" " +
				"JOIN \"Post\" lpd ON lp.\"lastPostId\" = lpd.\"Id\" " +
				"JOIN \"Account\" lpoa ON lpd.\"OwnerAccountId\" = lpoa.\"Id\" " +
				"LEFT JOIN (SELECT (COUNT(CASEWHEN(\"VoteType\"=1, 1, null)) - COUNT(CASEWHEN(\"VoteType\"=2, 1, null))) AS \"votes\", " +
					"\"PostId\" FROM \"Vote\" GROUP BY \"PostId\") v ON v.\"PostId\"= " +
						"(SELECT \"Id\" FROM \"Post\" p WHERE p.\"ParentId\" IS null AND t.\"Id\" = p.\"ThreadId\") " +
				"LEFT JOIN (SELECT (COUNT(CASEWHEN(\"VoteType\"=1, 1, null)) - COUNT(CASEWHEN(\"VoteType\"=2, 1, null))) AS \"votes\", " +
					"\"PostId\" FROM \"Vote\" GROUP BY \"PostId\") lpv ON lpv.\"PostId\"= lp.\"lastPostId\" " +
				"WHERE t.\"BoardId\"=? GROUP BY t.\"Id\" " +
				"ORDER BY lpd.\"CreationDate\" DESC" +
			") " +
			"SELECT * FROM cte " +
			"WHERE cte.\"rn\" BETWEEN ? and ?";

	// language=SQL
	private final static String FETCH_ALL_THREADS_WHERE_TITLE_LIKE_BETWEEN_ROW =
		"WITH cte AS " +
			"(" +
				"SELECT t.*, COUNT(p.\"Id\") AS \"postCount\", m.\"Title\" AS \"mediaTitle\", mt.\"Name\" AS \"mediaTypeName\", " +
				"oa.\"Username\" AS \"ownerAccountUsername\", oa.\"ProfileImageUrl\" AS \"ownerAccountProfileImageUrl\", " +
				"lp.\"lastPostId\", lpd.\"CreationDate\" AS \"lastPostCreationDate\", lpoa.\"Id\" AS \"lastPostOwnerAccountId\", " +
				"lpoa.\"Username\" AS \"lastPostOwnerAccountUsername\", lpoa.\"ProfileImageUrl\" AS \"lastPostOwnerAccountProfileImageUrl\", " +
				"COALESCE(v.\"votes\", 0) AS \"cheese\", b.\"Id\" AS \"boardId\", b.\"Title\" AS \"boardTitle\", " +
				"ROW_NUMBER() over (ORDER BY lpd.\"CreationDate\" DESC) AS \"rn\" " +
				"FROM \"Thread\" t " +
				"JOIN \"Post\" p ON t.\"Id\" = p.\"ThreadId\" " +
				"LEFT JOIN \"Media\" m on m.\"Id\" = t.\"MediaId\" " +
				"LEFT JOIN \"MediaType\" mt ON m.\"MediaTypeId\" = mt.\"Id\" " +
				"JOIN \"Account\" oa ON t.\"OwnerAccountId\" = oa.\"Id\" " +
				"JOIN (SELECT MAX(\"Id\") AS \"lastPostId\", \"ThreadId\" FROM \"Post\" " +
				"GROUP BY \"ThreadId\") lp ON t.\"Id\"=lp.\"ThreadId\" " +
				"JOIN \"Post\" lpd ON lp.\"lastPostId\" = lpd.\"Id\" " +
				"JOIN \"Account\" lpoa ON lpd.\"OwnerAccountId\" = lpoa.\"Id\" " +
				"LEFT JOIN (SELECT (COUNT(CASEWHEN(\"VoteType\"=1, 1, null)) - COUNT(CASEWHEN(\"VoteType\"=2, 1, null))) AS \"votes\", " +
					"\"PostId\" FROM \"Vote\" GROUP BY \"PostId\") v ON v.\"PostId\"= " +
						"(SELECT \"Id\" FROM \"Post\" p WHERE p.\"ParentId\" IS null AND t.\"Id\" = p.\"ThreadId\") " +
				"JOIN \"Board\" b ON t.\"BoardId\"=b.\"Id\" " +
				"WHERE LOWER(t.\"Title\") LIKE LOWER(?) GROUP BY t.\"Id\" " +
				"ORDER BY lpd.\"CreationDate\" DESC" +
			") " +
			"SELECT * FROM cte " +
			"WHERE cte.\"rn\" BETWEEN ? and ?";

	// language=SQL
	private final static String UPDATE_THREAD_VIEW_COUNT_WHERE_ID =
		"UPDATE \"Thread\" SET \"ViewCount\"=\"ViewCount\"+1 WHERE \"Id\"=?";

	// language=SQL
	private final static String INSERT_NEW_THREAD =
		"INSERT INTO \"Thread\" " +
			"(\"BoardId\", \"OwnerAccountId\", \"MediaId\", \"CreationDate\", \"Title\") VALUES (?, ?, ?, ?, ?) ";

	// language=SQL
	private final static String DELETE_THREAD_WHERE_ID =
		"DELETE FROM \"Thread\" WHERE \"Id\"=?";


	private final RSUtils<ThreadDTO> rsUtils;
	private final QueryRunner queryRunner;

	private Connection c;

	public H2ThreadDAO()
	{
		this.rsUtils = new RSUtils<>();
		this.queryRunner = new QueryRunner();
	}

	/**
	 * Private Methods
	 */

	private ResultSetHandler<List<ThreadDTO>> getBeanListHandler()
	{
		return new BeanListHandler<>(ThreadDTO.class);
	}

	private ResultSetHandler<ThreadDTO> getBeanHandler()
	{
		return new BeanHandler<>(ThreadDTO.class);
	}

	private List<ThreadDTO> fetchList(String query, Object... param)
	{
		return rsUtils.fetchList(this.queryRunner, query, getBeanListHandler(), param);
	}

	private List<ThreadDTO> fetchList(String query, Object param)
	{
		return rsUtils.fetchList(this.queryRunner, query, getBeanListHandler(), param);
	}

	private List<ThreadDTO> fetchList(String query)
	{
		return rsUtils.fetchList(this.queryRunner, query, getBeanListHandler());
	}

	private ThreadDTO fetchSingle(String query, Object param)
	{
		return rsUtils.fetchSingle(this.queryRunner, query, getBeanHandler(), param);
	}

	/**
	 * Thread DAO Methods
	 */

	@Override
	public List<ThreadDTO> fetchMostActiveThreads()
	{
		return fetchList(FETCH_TOP_5_MOST_ACTIVE_THREADS);
	}

	@Override
	public List<ThreadDTO> fetchLatestThreads()
	{
		return fetchList(FETCH_TOP_5_LATEST_THREADS);
	}

	@Override
	public List<ThreadDTO> fetchLatestAnnouncements()
	{
		return fetchList(FETCH_TOP_5_ANNOUNCEMENTS);
	}

	@Override
	public List<ThreadDTO> fetchAllThreadsWhereBoardId(int boardId)
	{
		return fetchList(FETCH_ALL_THREADS_WHERE_BOARD_ID, boardId);
	}

	@Override
	public List<ThreadDTO> fetchAllThreadsWhereBoardIdBetweenRow(int boardId, int rowNumber)
	{
		return fetchList(FETCH_ALL_THREADS_WHERE_BOARD_ID_BETWEEN_ROW, boardId, rowNumber, rowNumber+9);
	}

	@Override
	public List<ThreadDTO> fetchAllThreadsWhereTitleLikeBetweenRow(String titleLike, int rowNumber)
	{
		return fetchList(FETCH_ALL_THREADS_WHERE_TITLE_LIKE_BETWEEN_ROW,
			"%" + titleLike + "%", rowNumber, rowNumber+9);
	}

	@Override
	public void updateThreadViewCountWhereId(int id)
	{
		rsUtils.updateSingle(queryRunner, UPDATE_THREAD_VIEW_COUNT_WHERE_ID, id);
	}

	/**
	 *	Base DAO Methods
	 */

	@Override
	public List<ThreadDTO> fetchAll()
	{
		return null;
	}

	@Override
	public ThreadDTO fetchById(int id)
	{
		return fetchSingle(FETCH_THREAD_WHERE_ID, id);
	}

	@Override
	public ThreadDTO insert(ThreadDTO threadDTO)
	{

		return rsUtils.insertSingle(queryRunner, INSERT_NEW_THREAD, getBeanHandler(),
			threadDTO.getBoardId(), threadDTO.getOwnerAccountId(), threadDTO.getMediaId(), threadDTO.getCreationDate(),
			threadDTO.getTitle());
	}

	@Override
	public void update(ThreadDTO threadDTO)
	{

	}

	@Override
	public void delete(ThreadDTO threadDTO)
	{
		rsUtils.updateSingle(queryRunner, DELETE_THREAD_WHERE_ID, threadDTO.getId());
	}
}
