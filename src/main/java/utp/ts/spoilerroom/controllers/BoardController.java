package utp.ts.spoilerroom.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import utp.ts.spoilerroom.dao.factories.DAOFactory;
import utp.ts.spoilerroom.dao.factories.H2DAOFactory;
import utp.ts.spoilerroom.dao.interfaces.BoardDAO;
import utp.ts.spoilerroom.dao.interfaces.PostDAO;
import utp.ts.spoilerroom.dao.interfaces.ThreadDAO;
import utp.ts.spoilerroom.dto.BoardDTO;
import utp.ts.spoilerroom.dto.PostDTO;
import utp.ts.spoilerroom.dto.ThreadDTO;
import utp.ts.spoilerroom.models.BoardModel;
import utp.ts.spoilerroom.models.ThreadModel;
import utp.ts.spoilerroom.models.entities.BoardEntity;
import utp.ts.spoilerroom.models.entities.MediaEntity;
import utp.ts.spoilerroom.models.entities.PostEntity;
import utp.ts.spoilerroom.models.entities.ThreadEntity;

public class BoardController
{
	private final BoardModel boardModel;

	public BoardController(int boardId)
	{
		BoardDTO boardDTO = boardDAO().fetchById(boardId);

		if (boardDTO == null)
		{
			this.boardModel = null;
			return;
		}

		BoardEntity boardEntity = new BoardEntity(
			boardDTO.getBoard(), boardDTO.getSection());

		this.boardModel = new BoardModel(boardEntity, null, null);
	}

	public BoardController(int boardId, int rowNumber)
	{
		BoardDTO boardDTO = boardDAO().fetchById(boardId);
		BoardEntity boardEntity = new BoardEntity(
			boardDTO.getBoard(), boardDTO.getSection());

		PostEntity lastPostEntity = new PostEntity(
			boardDTO.getLastPost(), boardDTO.getLastPostThread(),
			boardDTO.getLastPostOwnerAccount(), null);

		List<ThreadDTO> threadDTOList = threadDAO().fetchAllThreadsWhereBoardIdBetweenRow(boardId, rowNumber);
		List<ThreadModel> threadModelList = new ArrayList<>();

		for (ThreadDTO threadDTO : threadDTOList)
		{
			ThreadEntity threadEntity = new ThreadEntity(
				threadDTO.getThread(), threadDTO.getOwnerAccount(),
				threadDTO.getBoard(), threadDTO.getSection());

			PostEntity threadLastPostEntity = new PostEntity(
				threadDTO.getLastPost(), null, threadDTO.getLastPostOwnerAccount(), null);

			threadModelList.add(new ThreadModel(threadEntity, threadLastPostEntity, new MediaEntity(threadDTO.getMedia()), null));
		}

		this.boardModel = new BoardModel(boardEntity, lastPostEntity, threadModelList);
	}

	public BoardController(String search, int rowNumber)
	{
		BoardDTO boardDTO = boardDAO().fetchBoardWhereThreadTitleLike(search);
		BoardEntity boardEntity = new BoardEntity(
			boardDTO.getBoard(), null);

		List<ThreadDTO> threadDTOList = threadDAO().fetchAllThreadsWhereTitleLikeBetweenRow(search, rowNumber);
		List<ThreadModel> threadModelList = new ArrayList<>();

		for (ThreadDTO threadDTO : threadDTOList)
		{
			ThreadEntity threadEntity = new ThreadEntity(
				threadDTO.getThread(), threadDTO.getOwnerAccount(),
				threadDTO.getBoard(), threadDTO.getSection());

			PostEntity threadLastPostEntity = new PostEntity(
				threadDTO.getLastPost(), null, threadDTO.getLastPostOwnerAccount(), null);

			threadModelList.add(new ThreadModel(threadEntity, threadLastPostEntity, new MediaEntity(threadDTO.getMedia()), null));
		}

		this.boardModel = new BoardModel(boardEntity, null, threadModelList);
	}

	public static int postNewThread(int boardId, int accountId, String threadTitle, String postContent)
	{
		ThreadDTO threadDTO = new ThreadDTO();
		threadDTO.setBoardId(boardId);
		threadDTO.setOwnerAccountId(accountId);
		threadDTO.setMediaId(null); // TODO MEDIA
		Timestamp now = new Timestamp(System.currentTimeMillis());
		threadDTO.setCreationDate(now);
		threadDTO.setTitle(threadTitle);

		ThreadDTO insertedThread = threadDAO().insert(threadDTO);

		PostDTO postDTO = new PostDTO();
		postDTO.setThreadId(insertedThread.getId());
		postDTO.setParentId(null);
		postDTO.setOwnerAccountId(accountId);
		postDTO.setCreationDate(now);
		postDTO.setContent(postContent);

		postDAO().insert(postDTO);

		return insertedThread.getId();
	}

	/**
	 * DAO
	 */

	private static BoardDAO boardDAO()
	{
		return Objects.requireNonNull(H2DAOFactory.getDAOFactory(DAOFactory.H2)).getBoardDAO();
	}

	private static ThreadDAO threadDAO()
	{
		return Objects.requireNonNull(H2DAOFactory.getDAOFactory(DAOFactory.H2)).getThreadDAO();
	}

	private static PostDAO postDAO()
	{
		return Objects.requireNonNull(H2DAOFactory.getDAOFactory(DAOFactory.H2)).getPostDAO();
	}

	/**
	 * Getters
	 */

	public BoardModel getBoardModel()
	{
		return boardModel;
	}
}
