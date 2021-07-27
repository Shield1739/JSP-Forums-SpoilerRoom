package utp.ts.spoilerroom.models.beans;

public class Board extends BaseBean
{
	private String title;
	private String description;
	private Integer weight;
	private boolean closed;

	private Integer threadCount;
	private Integer postCount;

	public Board()
	{
	}

	/**
	 * Getters & Setters
	 */

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Integer getWeight()
	{
		return weight;
	}

	public void setWeight(Integer weight)
	{
		this.weight = weight;
	}

	public Integer getThreadCount()
	{
		return threadCount;
	}

	public void setThreadCount(Integer threadCount)
	{
		this.threadCount = threadCount;
	}

	public Integer getPostCount()
	{
		return postCount;
	}

	public void setPostCount(Integer postCount)
	{
		this.postCount = postCount;
	}

	public boolean isClosed()
	{
		return closed;
	}

	public void setClosed(boolean closed)
	{
		this.closed = closed;
	}
}
