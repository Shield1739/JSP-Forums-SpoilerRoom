package utp.ts.spoilerroom.models.entities;

import utp.ts.spoilerroom.models.beans.Account;
import utp.ts.spoilerroom.models.beans.Board;
import utp.ts.spoilerroom.models.beans.Section;
import utp.ts.spoilerroom.models.beans.Thread;

public class ThreadEntity
{
	private final Thread thread;
	private final Account ownerAccount;
	private final Board board;
	private final Section section;

	public ThreadEntity(Thread thread, Account ownerAccount, Board board, Section section)
	{
		this.thread = thread;
		this.ownerAccount = ownerAccount;
		this.board = board;
		this.section = section;
	}

	/**
	 * Getters
	 */

	public Thread getThread()
	{
		return thread;
	}

	public Account getOwnerAccount()
	{
		return ownerAccount;
	}

	public Board getBoard()
	{
		return board;
	}

	public Section getSection()
	{
		return section;
	}
}
