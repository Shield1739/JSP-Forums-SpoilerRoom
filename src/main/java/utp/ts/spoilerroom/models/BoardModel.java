package utp.ts.spoilerroom.models;

import java.util.List;
import utp.ts.spoilerroom.models.entities.BoardEntity;
import utp.ts.spoilerroom.models.entities.PostEntity;

public class BoardModel
{
	private final BoardEntity boardEntity;
	private final PostEntity lastPostEntity;
	private final List<ThreadModel> threadModelList;

	public BoardModel(BoardEntity boardEntity, PostEntity lastPostEntity, List<ThreadModel> threadModelList)
	{
		this.boardEntity = boardEntity;
		this.lastPostEntity = lastPostEntity;
		this.threadModelList = threadModelList;
	}

	/**
	 * Getters
	 */

	public BoardEntity getBoardEntity()
	{
		return boardEntity;
	}

	public PostEntity getLastPostEntity()
	{
		return lastPostEntity;
	}

	public List<ThreadModel> getThreadModelList()
	{
		return threadModelList;
	}
}
