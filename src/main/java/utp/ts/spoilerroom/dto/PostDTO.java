package utp.ts.spoilerroom.dto;

import java.sql.Timestamp;
import utp.ts.spoilerroom.models.beans.Account;
import utp.ts.spoilerroom.models.beans.Post;
import utp.ts.spoilerroom.models.beans.Thread;
import utp.ts.spoilerroom.models.beans.Vote;

public class PostDTO extends Post
{
	private Integer threadId;
	private String threadTitle;

	private Integer ownerAccountId;
	private String ownerAccountUsername;
	private Timestamp ownerAccountCreationDate;
	private Timestamp ownerAccountLastActiveDate;
	private String ownerAccountProfileImageUrl;
	private String ownerAccountTitle;
	private Integer ownerAccountPostCount;

	private Integer lastEditorAccountId;
	private String lastEditorAccountUsername;

	private Integer accountVote;

	public PostDTO()
	{
	}

	/**
	 * Beans
	 */

	public Post getPost()
	{
		Post post = new Post();

		post.setId(getId());
		post.setParentId(getParentId());
		post.setCreationDate(getCreationDate());
		post.setLastEditDate(getLastEditDate());
		post.setContent(getContent());
		post.setCheese(getCheese());

		return post;
	}

	public Thread getThread()
	{
		Thread thread = new Thread();

		thread.setId(this.threadId);
		thread.setTitle(this.threadTitle);

		return thread;
	}

	public Account getOwnerAccount()
	{
		Account ownerAccount = new Account();

		ownerAccount.setId(this.ownerAccountId);
		ownerAccount.setUsername(this.ownerAccountUsername);
		ownerAccount.setCreationDate(this.ownerAccountCreationDate);
		ownerAccount.setLastActiveDate(this.ownerAccountLastActiveDate);
		ownerAccount.setProfileImageUrl(this.ownerAccountProfileImageUrl);
		ownerAccount.setTitle(this.ownerAccountTitle);
		ownerAccount.setPostCount(this.ownerAccountPostCount);

		return ownerAccount;
	}

	public Account getLastEditorAccount()
	{
		Account lastEditorAccount = new Account();

		lastEditorAccount.setId(this.lastEditorAccountId);
		lastEditorAccount.setUsername(this.lastEditorAccountUsername);

		return lastEditorAccount;
	}

	public Vote getVote()
	{
		Vote vote = new Vote();

		vote.setVoteType(accountVote);

		return vote;
	}

	/**
	 * Getters & Setters
	 */

	public Integer getThreadId()
	{
		return threadId;
	}

	public void setThreadId(Integer threadId)
	{
		this.threadId = threadId;
	}

	public String getThreadTitle()
	{
		return threadTitle;
	}

	public void setThreadTitle(String threadTitle)
	{
		this.threadTitle = threadTitle;
	}

	public Integer getOwnerAccountId()
	{
		return ownerAccountId;
	}

	public void setOwnerAccountId(Integer ownerAccountId)
	{
		this.ownerAccountId = ownerAccountId;
	}

	public String getOwnerAccountUsername()
	{
		return ownerAccountUsername;
	}

	public void setOwnerAccountUsername(String ownerAccountUsername)
	{
		this.ownerAccountUsername = ownerAccountUsername;
	}

	public Timestamp getOwnerAccountCreationDate()
	{
		return ownerAccountCreationDate;
	}

	public void setOwnerAccountCreationDate(Timestamp ownerAccountCreationDate)
	{
		this.ownerAccountCreationDate = ownerAccountCreationDate;
	}

	public Timestamp getOwnerAccountLastActiveDate()
	{
		return ownerAccountLastActiveDate;
	}

	public void setOwnerAccountLastActiveDate(Timestamp ownerAccountLastActiveDate)
	{
		this.ownerAccountLastActiveDate = ownerAccountLastActiveDate;
	}

	public String getOwnerAccountProfileImageUrl()
	{
		return ownerAccountProfileImageUrl;
	}

	public void setOwnerAccountProfileImageUrl(String ownerAccountProfileImageUrl)
	{
		this.ownerAccountProfileImageUrl = ownerAccountProfileImageUrl;
	}

	public String getOwnerAccountTitle()
	{
		return ownerAccountTitle;
	}

	public void setOwnerAccountTitle(String ownerAccountTitle)
	{
		this.ownerAccountTitle = ownerAccountTitle;
	}

	public Integer getOwnerAccountPostCount()
	{
		return ownerAccountPostCount;
	}

	public void setOwnerAccountPostCount(Integer ownerAccountPostCount)
	{
		this.ownerAccountPostCount = ownerAccountPostCount;
	}

	public Integer getLastEditorAccountId()
	{
		return lastEditorAccountId;
	}

	public void setLastEditorAccountId(Integer lastEditorAccountId)
	{
		this.lastEditorAccountId = lastEditorAccountId;
	}

	public String getLastEditorAccountUsername()
	{
		return lastEditorAccountUsername;
	}

	public void setLastEditorAccountUsername(String lastEditorAccountUsername)
	{
		this.lastEditorAccountUsername = lastEditorAccountUsername;
	}

	public Integer getAccountVote()
	{
		return accountVote;
	}

	public void setAccountVote(Integer accountVote)
	{
		this.accountVote = accountVote;
	}
}
