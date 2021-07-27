package utp.ts.spoilerroom.dao.interfaces;

import java.util.List;
import utp.ts.spoilerroom.dto.BoardDTO;

public interface BoardDAO extends BaseDAO<BoardDTO>
{
	List<BoardDTO> fetchAllBoardsWhereSectionId(int sectionID);
	BoardDTO fetchBoardWhereThreadTitleLike(String titleLike);
}
