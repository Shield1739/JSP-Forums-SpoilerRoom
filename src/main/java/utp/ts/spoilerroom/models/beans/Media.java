package utp.ts.spoilerroom.models.beans;

import java.sql.Timestamp;

public class Media extends BaseBean
{
	private Integer mediaTypeId;
	private String title;
	private String posterImageUrl;
	private Integer episodes;
	private Integer runtime;
	private Timestamp airedStartDate;
	private Timestamp airedEndDate;

	private String mediaTypeName;
	private Integer threadCount;
	private Integer postCount;

	public Media()
	{
	}

	/**
	 * Getters & Setters
	 */

	public Integer getMediaTypeId()
	{
		return mediaTypeId;
	}

	public void setMediaTypeId(Integer mediaTypeId)
	{
		this.mediaTypeId = mediaTypeId;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getPosterImageUrl()
	{
		return posterImageUrl;
	}

	public void setPosterImageUrl(String posterImageUrl)
	{
		this.posterImageUrl = posterImageUrl;
	}

	public Integer getEpisodes()
	{
		return episodes;
	}

	public void setEpisodes(Integer episodes)
	{
		this.episodes = episodes;
	}

	public Integer getRuntime()
	{
		return runtime;
	}

	public void setRuntime(Integer runtime)
	{
		this.runtime = runtime;
	}

	public Timestamp getAiredStartDate()
	{
		return airedStartDate;
	}

	public void setAiredStartDate(Timestamp airedStartDate)
	{
		this.airedStartDate = airedStartDate;
	}

	public Timestamp getAiredEndDate()
	{
		return airedEndDate;
	}

	public void setAiredEndDate(Timestamp airedEndDate)
	{
		this.airedEndDate = airedEndDate;
	}

	public String getMediaTypeName()
	{
		return mediaTypeName;
	}

	public void setMediaTypeName(String mediaTypeName)
	{
		this.mediaTypeName = mediaTypeName;
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
