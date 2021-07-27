package utp.ts.spoilerroom.dao.interfaces;

import java.util.List;
import utp.ts.spoilerroom.dto.ThreadDTO;

public interface ThreadDAO extends BaseDAO<ThreadDTO>
{
	List<ThreadDTO> fetchMostActiveThreads();
	List<ThreadDTO> fetchLatestThreads();
	List<ThreadDTO> fetchLatestAnnouncements();
	List<ThreadDTO> fetchAllThreadsWhereBoardId(int boardId);
	List<ThreadDTO> fetchAllThreadsWhereBoardIdBetweenRow(int boardId, int rowNumber);
	List<ThreadDTO> fetchAllThreadsWhereTitleLikeBetweenRow(String titleLike, int rowNumber);

	void updateThreadViewCountWhereId(int id);
}
