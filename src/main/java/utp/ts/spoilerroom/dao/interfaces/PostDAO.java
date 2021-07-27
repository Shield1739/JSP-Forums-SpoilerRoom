package utp.ts.spoilerroom.dao.interfaces;

import java.sql.Timestamp;
import java.util.List;
import utp.ts.spoilerroom.dto.PostDTO;

public interface PostDAO extends BaseDAO<PostDTO>
{
	List<PostDTO> fetchAllPostsWhereThreadId(int threadId);
	List<PostDTO> fetchAllPostsWhereThreadIdBetweenRow(int threadId, int rowNumber);
	List<PostDTO> fetchAllPostsWhereThreadIdBetweenRowWithAccountVote(int threadId, int rowNumber, int accountId);
	List<PostDTO> fetchAllPostsWhereOwnerAccountIdBetweenRow(int ownerAccountId, int rowNumber);
}
