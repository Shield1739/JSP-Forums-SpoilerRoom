package utp.ts.spoilerroom.dao.implementations.h2;

import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utp.ts.spoilerroom.dao.interfaces.VoteDAO;
import utp.ts.spoilerroom.dto.VoteDTO;
import utp.ts.spoilerroom.utilities.RSUtils;

public class H2VoteDAO implements VoteDAO
{
	// language=SQL
	private final static String INSERT_VOTE =
		"INSERT INTO \"Vote\" (\"PostId\", \"AccountId\", \"VoteType\", \"CreationDate\") VALUES (?, ?, ?, ?)";

	// language=SQL
	private final static String UPDATE_VOTE =
		"UPDATE \"Vote\" SET \"VoteType\"=?, \"CreationDate\"=? WHERE \"PostId\"=? AND \"AccountId\"=?";

	// language=SQL
	private final static String DELETE_VOTE =
		"DELETE FROM \"Vote\" WHERE \"PostId\"=? AND \"AccountId\"=?";

	private RSUtils<VoteDTO> rsUtils;
	private final QueryRunner queryRunner;

	public H2VoteDAO()
	{
		this.rsUtils = new RSUtils<>();
		this.queryRunner = new QueryRunner();
	}

	/**
	 * Private Methods
	 */

	private ResultSetHandler<List<VoteDTO>> getBeanListHandler()
	{
		return new BeanListHandler<>(VoteDTO.class);
	}

	private ResultSetHandler<VoteDTO> getBeanHandler()
	{
		return new BeanHandler<>(VoteDTO.class);
	}

	private List<VoteDTO> fetchList(String query, Object param)
	{
		return rsUtils.fetchList(this.queryRunner, query, getBeanListHandler(), param);
	}

	private List<VoteDTO> fetchList(String query)
	{
		return rsUtils.fetchList(this.queryRunner, query, getBeanListHandler());
	}

	private VoteDTO fetchSingle(String query, Object param)
	{
		return rsUtils.fetchSingle(this.queryRunner, query, getBeanHandler(), param);
	}

	/**
	 *	Vote DAO Methods
	 */

	/**
	 *	Base DAO Methods
	 */

	@Override
	public List<VoteDTO> fetchAll()
	{
		return null;
	}

	@Override
	public VoteDTO fetchById(int id)
	{
		return null;
	}

	@Override
	public VoteDTO insert(VoteDTO voteDTO)
	{
		VoteDTO insertedVote = rsUtils.insertSingleQuietly(queryRunner, INSERT_VOTE, getBeanHandler(),
			voteDTO.getPostId(), voteDTO.getAccountId(), voteDTO.getVoteType(), voteDTO.getCreationDate());

		if (insertedVote == null)
		{
			update(voteDTO);
			return voteDTO;
		}

		return null;
	}

	@Override
	public void update(VoteDTO voteDTO)
	{
		rsUtils.updateSingle(queryRunner, UPDATE_VOTE,
			voteDTO.getVoteType(), voteDTO.getCreationDate(), voteDTO.getPostId(), voteDTO.getAccountId());
	}

	@Override
	public void delete(VoteDTO voteDTO)
	{
		rsUtils.updateSingle(queryRunner, DELETE_VOTE,
			voteDTO.getPostId(), voteDTO.getAccountId());
	}
}