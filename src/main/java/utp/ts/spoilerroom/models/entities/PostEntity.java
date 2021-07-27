package utp.ts.spoilerroom.models.entities;

import utp.ts.spoilerroom.models.beans.Account;
import utp.ts.spoilerroom.models.beans.Post;
import utp.ts.spoilerroom.models.beans.Thread;
import utp.ts.spoilerroom.models.beans.Vote;

public class PostEntity
{
	private final Post post;
	private final Thread thread;
	private final Account ownerAccount;
	private final Account lastEditorAccount;
	private final Vote accountVote;

	public PostEntity(Post post, Thread thread, Account ownerAccount, Account lastEditorAccount)
	{
		this.post = post;
		this.thread = thread;
		this.ownerAccount = ownerAccount;
		this.lastEditorAccount = lastEditorAccount;
		this.accountVote = null;
	}

	public PostEntity(Post post, Thread thread, Account ownerAccount, Account lastEditorAccount, Vote accountVote)
	{
		this.post = post;
		this.thread = thread;
		this.ownerAccount = ownerAccount;
		this.lastEditorAccount = lastEditorAccount;
		this.accountVote = accountVote;
	}

	/**
	 * Getters
	 */

	public Post getPost()
	{
		return post;
	}

	public Thread getThread()
	{
		return thread;
	}

	public Account getOwnerAccount()
	{
		return ownerAccount;
	}

	public Account getLastEditorAccount()
	{
		return lastEditorAccount;
	}

	public Vote getAccountVote()
	{
		return accountVote;
	}
}


