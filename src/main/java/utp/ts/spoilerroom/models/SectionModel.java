package utp.ts.spoilerroom.models;

import java.util.List;
import utp.ts.spoilerroom.models.entities.SectionEntity;

public class SectionModel
{
	private final SectionEntity sectionEntity;
	private final List<BoardModel> boardModelList;

	public SectionModel(SectionEntity sectionEntity, List<BoardModel> boardModelList)
	{
		this.sectionEntity = sectionEntity;
		this.boardModelList = boardModelList;
	}

	/**
	 * Getters
	 */

	public SectionEntity getSectionEntity()
	{
		return sectionEntity;
	}

	public List<BoardModel> getBoardModelList()
	{
		return boardModelList;
	}
}
