package utp.ts.spoilerroom.models.beans;

import java.sql.Timestamp;

public class Post extends BaseBean
{
	private Integer parentId;
	private Timestamp creationDate;
	private Timestamp lastEditDate;

	private String content;
	private Integer cheese;

	public Post()
	{
	}

	/**
	 * Getters & Setters
	 */

	public Integer getParentId()
	{
		return parentId;
	}

	public void setParentId(Integer parentId)
	{
		this.parentId = parentId;
	}

	public Timestamp getCreationDate()
	{
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate)
	{
		this.creationDate = creationDate;
	}

	public Timestamp getLastEditDate()
	{
		return lastEditDate;
	}

	public void setLastEditDate(Timestamp lastEditDate)
	{
		this.lastEditDate = lastEditDate;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
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
