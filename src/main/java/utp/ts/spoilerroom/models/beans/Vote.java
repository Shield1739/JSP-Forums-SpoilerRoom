package utp.ts.spoilerroom.models.beans;

import java.sql.Timestamp;

public class Vote
{
	private Integer postId;
	private Integer accountId;
	private Integer voteType;
	private Timestamp creationDate;

	public Vote()
	{
	}

	/**
	 * Getters & Setters
	 */

	public Integer getPostId()
	{
		return postId;
	}

	public void setPostId(Integer postId)
	{
		this.postId = postId;
	}

	public Integer getAccountId()
	{
		return accountId;
	}

	public void setAccountId(Integer accountId)
	{
		this.accountId = accountId;
	}

	public Integer getVoteType()
	{
		return voteType;
	}

	public void setVoteType(Integer voteType)
	{
		this.voteType = voteType;
	}

	public Timestamp getCreationDate()
	{
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate)
	{
		this.creationDate = creationDate;
	}
}
