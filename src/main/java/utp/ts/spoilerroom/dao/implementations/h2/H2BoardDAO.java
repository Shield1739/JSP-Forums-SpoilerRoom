package utp.ts.spoilerroom.dao.implementations.h2;

import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utp.ts.spoilerroom.dao.interfaces.BoardDAO;
import utp.ts.spoilerroom.dto.BoardDTO;
import utp.ts.spoilerroom.utilities.RSUtils;

public class H2BoardDAO implements BoardDAO
{
	// language=SQL
	private final static String FETCH_ALL_BOARDS_WHERE_SECTION_ID =
		"SELECT b.*, COUNT(t.\"Id\") AS \"threadCount\", SUM(pc.\"postCount\") AS \"postCount\", " +
			"lp.\"lastPostId\", lpd.\"CreationDate\" AS \"lastPostCreationDate\", lpd.\"OwnerAccountId\" AS \"lastPostOwnerAccountId\", " +
			"lpoa.\"Username\" AS \"lastPostOwnerAccountUsername\", lpoa.\"ProfileImageUrl\" AS \"lastPostOwnerAccountProfileImageUrl\", " +
			"lpd.\"ThreadId\" AS \"lastPostThreadId\", lt.\"Title\" AS \"lastPostThreadTitle\", COALESCE(v.\"votes\", 0) AS \"cheese\", " +
			"COALESCE(lpv.\"votes\", 0) AS \"lastPostCheese\" " +
			"FROM \"Board\" b " +
			"JOIN \"Thread\" t ON b.\"Id\" = t.\"BoardId\" " +
			"JOIN (SELECT tc.\"Id\", COUNT(p.\"Id\") AS \"postCount\" FROM \"Thread\" tc " +
			"JOIN \"Post\" p on tc.\"Id\" = p.\"ThreadId\" " +
			"GROUP BY tc.\"Id\") pc ON t.\"Id\" = pc.\"Id\" " +
			"JOIN (SELECT MAX(lps.\"lastPostId\") AS \"lastPostId\", \"BoardId\" FROM \"Thread\" " +
			"JOIN (SELECT MAX(\"Id\") AS \"lastPostId\", \"ThreadId\" FROM \"Post\" GROUP BY \"ThreadId\") " +
				"lps ON \"Id\"=lps.\"ThreadId\" GROUP BY \"BoardId\") " +
					"lp ON b.\"Id\" = lp.\"BoardId\" " +
			"JOIN \"Post\" lpd ON lp.\"lastPostId\" = lpd.\"Id\" " +
			"JOIN \"Account\" lpoa ON lpd.\"OwnerAccountId\" = lpoa.\"Id\" " +
			"JOIN \"Thread\" lt ON lpd.\"ThreadId\" = lt.\"Id\" " +
			"LEFT JOIN (SELECT (COUNT(CASEWHEN(\"VoteType\"=1, 1, null)) - COUNT(CASEWHEN(\"VoteType\"=2, 1, null))) AS \"votes\", " +
				"\"PostId\" FROM \"Vote\" GROUP BY \"PostId\") v ON v.\"PostId\"= " +
					"(SELECT \"Id\" FROM \"Post\" p WHERE p.\"ParentId\" IS null AND lpd.\"ThreadId\" = p.\"ThreadId\") " +
			"LEFT JOIN (SELECT (COUNT(CASEWHEN(\"VoteType\"=1, 1, null)) - COUNT(CASEWHEN(\"VoteType\"=2, 1, null))) AS \"votes\", " +
				"\"PostId\" FROM \"Vote\" GROUP BY \"PostId\") lpv ON v.\"PostId\"= lp.\"lastPostId\" " +
			"WHERE b.\"SectionId\"=? " +
			"GROUP BY b.\"Id\" ";

	// language=SQL
	private final static String FETCH_BOARD_WHERE_ID =
		"SELECT b.*, s.\"Title\" AS \"sectionTitle\", COUNT(t.\"Id\") AS \"threadCount\", SUM(pc.\"postCount\") AS \"postCount\"" +
			"FROM \"Board\" b " +
			"JOIN \"Section\" s ON b.\"SectionId\" = s.\"Id\" " +
			"JOIN \"Thread\" t ON b.\"Id\" = t.\"BoardId\" " +
			"JOIN (SELECT tc.\"Id\", COUNT(p.\"Id\") AS \"postCount\" FROM \"Thread\" tc " +
				"JOIN \"Post\" p on tc.\"Id\" = p.\"ThreadId\" GROUP BY tc.\"Id\") pc ON t.\"Id\" = pc.\"Id\" " +
			"WHERE b.\"Id\"=? " +
			"GROUP BY b.\"Id\"" ;

	// language=SQL
	private final static String FETCH_BOARD_WHERE_THREAD_TITLE_LIKE =
		"SELECT SUM(\"threadCount\") AS \"threadCount\" " +
			"FROM ( SELECT b.*, COUNT(t.\"Id\") AS \"threadCount\" FROM \"Board\" b " +
				"JOIN (SELECT * FROM \"Thread\" t WHERE LOWER(t.\"Title\") LIKE LOWER(?)) t ON b.\"Id\" = t.\"BoardId\" " +
				"GROUP BY b.\"Id\")" ;

	private RSUtils<BoardDTO> rsUtils;
	private final QueryRunner queryRunner;

	public H2BoardDAO()
	{
		this.rsUtils = new RSUtils<>();
		this.queryRunner = new QueryRunner();
	}

	/**
	 * Private Methods
	 */

	private ResultSetHandler<List<BoardDTO>> getBeanListHandler()
	{
		return new BeanListHandler<>(BoardDTO.class);
	}

	private ResultSetHandler<BoardDTO> getBeanHandler()
	{
		return new BeanHandler<>(BoardDTO.class);
	}

	private List<BoardDTO> fetchList(String query, Object param)
	{
		return rsUtils.fetchList(this.queryRunner, query, getBeanListHandler(), param);
	}

	private List<BoardDTO> fetchList(String query)
	{
		return rsUtils.fetchList(this.queryRunner, query, getBeanListHandler());
	}

	private BoardDTO fetchSingle(String query, Object param)
	{
		return rsUtils.fetchSingle(this.queryRunner, query, getBeanHandler(), param);
	}

	/**
	 *	Board DAO Methods
	 */

	@Override
	public List<BoardDTO> fetchAllBoardsWhereSectionId(int sectionID)
	{
		return fetchList(FETCH_ALL_BOARDS_WHERE_SECTION_ID, sectionID);
	}

	@Override
	public BoardDTO fetchBoardWhereThreadTitleLike(String titleLike)
	{
		return fetchSingle(FETCH_BOARD_WHERE_THREAD_TITLE_LIKE, "%" + titleLike + "%");
	}

	/**
	 *	Base DAO Methods
	 */

	@Override
	public List<BoardDTO> fetchAll()
	{
		return null;
	}

	@Override
	public BoardDTO fetchById(int id)
	{
		return fetchSingle(FETCH_BOARD_WHERE_ID, id);
	}

	@Override
	public BoardDTO insert(BoardDTO boardDTO)
	{
		return null;
	}

	@Override
	public void update(BoardDTO boardDTO)
	{

	}

	@Override
	public void delete(BoardDTO boardDTO)
	{

	}
}
