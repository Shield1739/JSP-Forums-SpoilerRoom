package utp.ts.spoilerroom.dto;

public class HomeDTO
{
	private Integer memberCount;
	private Integer threadCount;
	private Integer postCount;

	public HomeDTO()
	{
	}

	/**
	 * Getters
	 */

	public Integer getMemberCount()
	{
		return memberCount;
	}

	public void setMemberCount(Integer memberCount)
	{
		this.memberCount = memberCount;
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
}
