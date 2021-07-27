package utp.ts.spoilerroom.models.entities;

import utp.ts.spoilerroom.models.beans.Board;
import utp.ts.spoilerroom.models.beans.Section;

public class BoardEntity
{
	private final Board board;
	private final Section section;

	public BoardEntity(Board board, Section section)
	{
		this.board = board;
		this.section = section;
	}

	/**
	 * Getters
	 */

	public Board getBoard()
	{
		return board;
	}

	public Section getSection()
	{
		return section;
	}
}
