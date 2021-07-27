package utp.ts.spoilerroom.models.beans;

import java.sql.Timestamp;

public class Thread extends BaseBean
{
	private Timestamp creationDate;
	private Timestamp closedDate;
	private String title;
	private Integer viewCount;

	private Integer postCount;
	private Integer cheese;

	public Thread()
	{
	}

	/**
	 * Getters & Setters
	 */

	public Timestamp getCreationDate()
	{
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate)
	{
		this.creationDate = creationDate;
	}

	public Timestamp getClosedDate()
	{
		return closedDate;
	}

	public void setClosedDate(Timestamp closedDate)
	{
		this.closedDate = closedDate;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public Integer getViewCount()
	{
		return viewCount;
	}

	public void setViewCount(Integer viewCount)
	{
		this.viewCount = viewCount;
	}

	public Integer getPostCount()
	{
		return postCount;
	}

	public void setPostCount(Integer postCount)
	{
		this.postCount = postCount;
	}

	public Integer getCheese()
	{
		return cheese;
	}

	public void setCheese(Integer cheese)
	{
		this.cheese = cheese;
	}
}
