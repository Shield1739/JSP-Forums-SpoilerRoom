package utp.ts.spoilerroom.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import utp.ts.spoilerroom.dao.factories.DAOFactory;
import utp.ts.spoilerroom.dao.factories.H2DAOFactory;
import utp.ts.spoilerroom.dao.interfaces.BoardDAO;
import utp.ts.spoilerroom.dao.interfaces.SectionDAO;
import utp.ts.spoilerroom.dto.BoardDTO;
import utp.ts.spoilerroom.dto.SectionDTO;
import utp.ts.spoilerroom.models.BoardModel;
import utp.ts.spoilerroom.models.SectionModel;
import utp.ts.spoilerroom.models.entities.BoardEntity;
import utp.ts.spoilerroom.models.entities.PostEntity;
import utp.ts.spoilerroom.models.entities.SectionEntity;

public class ForumController
{
	private final List<SectionModel> sectionModelList;

	public ForumController()
	{
		this.sectionModelList = new ArrayList<>();

		List<SectionDTO> sectionDTOList = sectionDAO().fetchAll();
		for (SectionDTO sectionDTO : sectionDTOList)
		{
			List<BoardDTO> boardDTOList = boardDAO().fetchAllBoardsWhereSectionId(sectionDTO.getId());

			List<BoardModel> boardModelList = new ArrayList<>();
			for (BoardDTO boardDTO : boardDTOList)
			{
				BoardEntity boardEntity = new BoardEntity(
					boardDTO.getBoard(), boardDTO.getSection());

				PostEntity lastPostEntity = new PostEntity(
					boardDTO.getLastPost(), boardDTO.getLastPostThread(),
					boardDTO.getLastPostOwnerAccount(), null);

				boardModelList.add(new BoardModel(boardEntity, lastPostEntity, null));
			}

			sectionModelList.add(new SectionModel(
				new SectionEntity(sectionDTO.getSection()), boardModelList));
		}

	}

	/**
	 * DAO
	 */

	private static SectionDAO sectionDAO()
	{
		return Objects.requireNonNull(H2DAOFactory.getDAOFactory(DAOFactory.H2)).getSectionDAO();
	}

	private static BoardDAO boardDAO()
	{
		return Objects.requireNonNull(H2DAOFactory.getDAOFactory(DAOFactory.H2)).getBoardDAO();
	}

	/**
	 * Getters
	 */

	public List<SectionModel> getSectionModelList()
	{
		return sectionModelList;
	}
}
