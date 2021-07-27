package utp.ts.spoilerroom.dao.implementations.h2;

import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utp.ts.spoilerroom.dao.interfaces.PostDAO;
import utp.ts.spoilerroom.dto.PostDTO;
import utp.ts.spoilerroom.utilities.RSUtils;

public class H2PostDAO implements PostDAO
{
	// language=SQL
	private final static String FETCH_ALL_POSTS_WHERE_THREAD_ID =
		"SELECT p.*, oa.\"Username\" AS \"ownerAccountUsername\", oa.\"CreationDate\" AS \"ownerAccountCreationDate\", " +
			"oa.\"LastActiveDate\" AS \"ownerAccountLastActiveDate\", oa.\"ProfileImageUrl\" AS \"ownerAccountProfileImageUrl\", " +
			"oa.\"Title\" AS \"ownerAccountTitle\", lea.\"Username\" AS \"lastEditorAccountUsername\", COUNT(count.\"Id\") AS \"ownerAccountPostCount\", " +
			"pc.\"Content\", COALESCE(v.\"votes\", 0) AS \"cheese\" "+
			"FROM \"Post\" p " +
			"JOIN \"Account\" oa ON p.\"OwnerAccountId\" = oa.\"Id\" " +
			"LEFT JOIN \"Account\" lea ON p.\"LastEditorAccountId\" = lea.\"Id\" " +
			"JOIN \"Post\" count ON oa.\"Id\" = count.\"OwnerAccountId\" " +
			"JOIN \"PostContent\" pc ON p.\"Id\" = pc.\"PostId\" " +
			"LEFT JOIN (SELECT (COUNT(CASEWHEN(\"VoteType\"=1, 1, null)) - COUNT(CASEWHEN(\"VoteType\"=2, 1, null))) AS \"votes\", " +
				"\"PostId\" FROM \"Vote\" GROUP BY \"PostId\") v ON v.\"PostId\"=p.\"Id\" " +
			"WHERE p.\"ThreadId\"=? " +
			"GROUP BY p.\"Id\"";

	// language=SQL
	private final static String FETCH_ALL_POSTS_WHERE_THREAD_ID_BETWEEN_ROW =
		"WITH cte AS " +
			"(" +
				"SELECT p.*, oa.\"Username\" AS \"ownerAccountUsername\", oa.\"CreationDate\" AS \"ownerAccountCreationDate\", " +
				"oa.\"LastActiveDate\" AS \"ownerAccountLastActiveDate\", oa.\"ProfileImageUrl\" AS \"ownerAccountProfileImageUrl\", " +
				"oa.\"Title\" AS \"ownerAccountTitle\", lea.\"Username\" AS \"lastEditorAccountUsername\", COUNT(count.\"Id\") AS \"ownerAccountPostCount\"," +
				"pc.\"Content\", COALESCE(v.\"votes\", 0) AS \"cheese\", ROW_NUMBER() over () AS \"rn\" "+
				"FROM \"Post\" p " +
				"JOIN \"Account\" oa ON p.\"OwnerAccountId\" = oa.\"Id\" " +
				"LEFT JOIN \"Account\" lea ON p.\"LastEditorAccountId\" = lea.\"Id\" " +
				"JOIN \"Post\" count ON oa.\"Id\" = count.\"OwnerAccountId\" " +
				"JOIN \"PostContent\" pc ON p.\"Id\" = pc.\"PostId\" " +
				"LEFT JOIN (SELECT (COUNT(CASEWHEN(\"VoteType\"=1, 1, null)) - COUNT(CASEWHEN(\"VoteType\"=2, 1, null))) AS \"votes\", " +
					"\"PostId\" FROM \"Vote\" GROUP BY \"PostId\") v ON v.\"PostId\"=p.\"Id\" " +
				"WHERE p.\"ThreadId\"=? " +
				"GROUP BY p.\"Id\" " +
			") " +
			"SELECT * FROM cte " +
			"WHERE cte.\"rn\" BETWEEN ? and ?";

	// language=SQL
	private final static String FETCH_ALL_POSTS_WHERE_THREAD_ID_BETWEEN_ROW_ACCOUNT_VOTE =
		"WITH cte AS " +
			"(" +
			"SELECT p.*, oa.\"Username\" AS \"ownerAccountUsername\", oa.\"CreationDate\" AS \"ownerAccountCreationDate\", " +
			"oa.\"LastActiveDate\" AS \"ownerAccountLastActiveDate\", oa.\"ProfileImageUrl\" AS \"ownerAccountProfileImageUrl\", " +
			"oa.\"Title\" AS \"ownerAccountTitle\", lea.\"Username\" AS \"lastEditorAccountUsername\", COUNT(count.\"Id\") AS \"ownerAccountPostCount\"," +
			"pc.\"Content\", COALESCE(v.\"votes\", 0) AS \"cheese\", COALESCE(va.\"VoteType\", 0) AS \"accountVote\", ROW_NUMBER() over () AS \"rn\" "+
			"FROM \"Post\" p " +
			"JOIN \"Account\" oa ON p.\"OwnerAccountId\" = oa.\"Id\" " +
			"LEFT JOIN \"Account\" lea ON p.\"LastEditorAccountId\" = lea.\"Id\" " +
			"JOIN \"Post\" count ON oa.\"Id\" = count.\"OwnerAccountId\" " +
			"JOIN \"PostContent\" pc ON p.\"Id\" = pc.\"PostId\" " +
			"LEFT JOIN (SELECT (COUNT(CASEWHEN(\"VoteType\"=1, 1, null)) - COUNT(CASEWHEN(\"VoteType\"=2, 1, null))) AS \"votes\", " +
				"\"PostId\" FROM \"Vote\" GROUP BY \"PostId\") v ON v.\"PostId\"=p.\"Id\" " +
			"LEFT JOIN (SELECT * FROM \"Vote\" WHERE \"AccountId\"=?) va ON p.\"Id\"=va.\"PostId\" " +
			"WHERE p.\"ThreadId\"=? " +
			"GROUP BY p.\"Id\" " +
			") " +
			"SELECT * FROM cte " +
			"WHERE cte.\"rn\" BETWEEN ? and ?";

	// language=SQL
	private final static String FETCH_ALL_POSTS_WHERE_OWNER_ACCOUNT_ID_BETWEEN_ROW =
		"WITH cte AS " +
			"(" +
			"SELECT p.*, t.\"Id\" AS \"threadId\", t.\"Title\" AS \"threadTitle\", oa.\"Username\" AS \"ownerAccountUsername\", oa.\"CreationDate\" AS \"ownerAccountCreationDate\", " +
			"oa.\"LastActiveDate\" AS \"ownerAccountLastActiveDate\", oa.\"ProfileImageUrl\" AS \"ownerAccountProfileImageUrl\", " +
			"oa.\"Title\" AS \"ownerAccountTitle\", lea.\"Username\" AS \"lastEditorAccountUsername\", COUNT(count.\"Id\") AS \"ownerAccountPostCount\"," +
			"pc.\"Content\", COALESCE(v.\"votes\", 0) AS \"cheese\" , ROW_NUMBER() over () AS \"rn\" "+
			"FROM \"Post\" p " +
			"JOIN \"Thread\" t ON p.\"ThreadId\" = t.\"Id\" " +
			"JOIN \"Account\" oa ON p.\"OwnerAccountId\" = oa.\"Id\" " +
			"LEFT JOIN \"Account\" lea ON p.\"LastEditorAccountId\" = lea.\"Id\" " +
			"JOIN \"Post\" count ON oa.\"Id\" = count.\"OwnerAccountId\" " +
			"JOIN \"PostContent\" pc ON p.\"Id\" = pc.\"PostId\" " +
			"LEFT JOIN (SELECT (COUNT(CASEWHEN(\"VoteType\"=1, 1, null)) - COUNT(CASEWHEN(\"VoteType\"=2, 1, null))) AS \"votes\", " +
			"\"PostId\" FROM \"Vote\" GROUP BY \"PostId\") v ON v.\"PostId\"=p.\"Id\" " +
			"WHERE p.\"OwnerAccountId\"=? " +
			"GROUP BY p.\"Id\" " +
			") " +
			"SELECT * FROM cte " +
			"WHERE cte.\"rn\" BETWEEN ? and ?";

	// language=SQL
	private final static String INSERT_NEW_POST =
		"INSERT INTO \"Post\" (\"ThreadId\", \"ParentId\", \"OwnerAccountId\", \"CreationDate\") VALUES (?, ?, ?, ?)";

	// language=SQL
	private final static String INSERT_NEW_POST_CONTENT =
		"INSERT INTO \"PostContent\" (\"PostId\", \"Content\") VALUES (?, ?)";

	// language=SQL
	private final static String UPDATE_EDIT_POST_WHERE_ID =
		"UPDATE \"Post\" SET \"LastEditorAccountId\"=?, \"LastEditDate\"=? WHERE \"Id\"=?";

	// language=SQL
	private final static String UPDATE_EDIT_POST_CONTENT_WHERE_ID =
		"UPDATE \"PostContent\" SET \"Content\"=? WHERE \"PostId\"=?";

	// language=SQL
	private final static String UPDATE_POST_PARENT_ID_WHERE_PARENT_ID =
		"UPDATE \"Post\" SET \"ParentId\"=? WHERE \"ParentId\"=?";

	// language=SQL
	private final static String DELETE_POST_WHERE_ID =
		"DELETE FROM \"Post\" WHERE \"Id\"=?";

	//TODO STILL NEED VOTE COUNT jesus

	private final RSUtils<PostDTO> rsUtils;
	private final QueryRunner queryRunner;

	public H2PostDAO()
	{
		this.rsUtils = new RSUtils<>();
		this.queryRunner = new QueryRunner();
	}

	/**
	 * Private Methods
	 */

	private ResultSetHandler<List<PostDTO>> getBeanListHandler()
	{
		return new BeanListHandler<>(PostDTO.class);
	}

	private ResultSetHandler<PostDTO> getBeanHandler()
	{
		return new BeanHandler<>(PostDTO.class);
	}

	private List<PostDTO> fetchList(String query, Object param)
	{
		return rsUtils.fetchList(this.queryRunner, query, getBeanListHandler(), param);
	}

	private List<PostDTO> fetchList(String query, Object... param)
	{
		return rsUtils.fetchList(this.queryRunner, query, getBeanListHandler(), param);
	}

	private List<PostDTO> fetchList(String query)
	{
		return rsUtils.fetchList(this.queryRunner, query, getBeanListHandler());
	}

	private PostDTO fetchSingle(String query, Object param)
	{
		return rsUtils.fetchSingle(this.queryRunner, query, getBeanHandler(), param);
	}

	/**
	 * Post DAO Methods
	 */

	@Override
	public List<PostDTO> fetchAllPostsWhereThreadId(int threadId)
	{
		return fetchList(FETCH_ALL_POSTS_WHERE_THREAD_ID, threadId);
	}

	@Override
	public List<PostDTO> fetchAllPostsWhereThreadIdBetweenRow(int threadId, int rowNumber)
	{
		return fetchList(FETCH_ALL_POSTS_WHERE_THREAD_ID_BETWEEN_ROW, threadId, rowNumber, rowNumber+9);
	}

	@Override
	public List<PostDTO> fetchAllPostsWhereThreadIdBetweenRowWithAccountVote(int threadId, int rowNumber, int accountId)
	{
		return fetchList(FETCH_ALL_POSTS_WHERE_THREAD_ID_BETWEEN_ROW_ACCOUNT_VOTE, accountId, threadId, rowNumber, rowNumber+9);
	}

	@Override
	public List<PostDTO> fetchAllPostsWhereOwnerAccountIdBetweenRow(int ownerAccountId, int rowNumber)
	{
		return fetchList(FETCH_ALL_POSTS_WHERE_OWNER_ACCOUNT_ID_BETWEEN_ROW, ownerAccountId, rowNumber, rowNumber+9);
	}

	/**
	 *	Base DAO Methods
	 */

	@Override
	public List<PostDTO> fetchAll()
	{
		return null;
	}

	@Override
	public PostDTO fetchById(int id)
	{
		return null;
	}

	@Override
	public PostDTO insert(PostDTO postDTO)
	{
		PostDTO insertedPost = rsUtils.insertSingle(queryRunner, INSERT_NEW_POST, getBeanHandler(),
			postDTO.getThreadId(), postDTO.getParentId(), postDTO.getOwnerAccountId(), postDTO.getCreationDate());

		rsUtils.insertSingle(queryRunner, INSERT_NEW_POST_CONTENT, getBeanHandler(),
			insertedPost.getId(), postDTO.getContent());

		return insertedPost;
	}

	@Override
	public void update(PostDTO postDTO)
	{
		rsUtils.updateSingle(queryRunner, UPDATE_EDIT_POST_WHERE_ID,
			postDTO.getLastEditorAccountId(), postDTO.getLastEditDate(), postDTO.getId());

		rsUtils.updateSingle(queryRunner, UPDATE_EDIT_POST_CONTENT_WHERE_ID, postDTO.getContent(), postDTO.getId());
	}

	@Override
	public void delete(PostDTO postDTO)
	{
		rsUtils.updateSingle(queryRunner, UPDATE_POST_PARENT_ID_WHERE_PARENT_ID, postDTO.getParentId(), postDTO.getId());
		rsUtils.updateSingle(queryRunner, DELETE_POST_WHERE_ID, postDTO.getId());
	}
}
