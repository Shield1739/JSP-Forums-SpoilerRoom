package utp.ts.spoilerroom.models;

import java.util.List;
import utp.ts.spoilerroom.models.entities.MediaEntity;

public class HomeModel
{
	private final List<MediaEntity> streamingMediaEntityList;
	private final List<ThreadModel> mostActiveThreadModelList;
	private final List<ThreadModel> latestThreadModelList;
	private final List<ThreadModel> announcementsThreadModelList;
	private final AccountModel accountModel;
	private final Integer memberCount;
	private final Integer threadCount;
	private final Integer postCount;

	public HomeModel(
		List<MediaEntity> streamingMediaEntityList, List<ThreadModel> mostActiveThreadModelList,
		List<ThreadModel> latestThreadModelList, List<ThreadModel> announcementsThreadModelList,
		AccountModel accountModel, Integer memberCount, Integer threadCount, Integer postCount)
	{
		this.streamingMediaEntityList = streamingMediaEntityList;
		this.mostActiveThreadModelList = mostActiveThreadModelList;
		this.latestThreadModelList = latestThreadModelList;
		this.announcementsThreadModelList = announcementsThreadModelList;
		this.accountModel = accountModel;
		this.memberCount = memberCount;
		this.threadCount = threadCount;
		this.postCount = postCount;
	}

	/**
	 * Getters
	 */

	public List<MediaEntity> getStreamingMediaEntityList()
	{
		return streamingMediaEntityList;
	}

	public List<ThreadModel> getMostActiveThreadModelList()
	{
		return mostActiveThreadModelList;
	}

	public List<ThreadModel> getLatestThreadModelList()
	{
		return latestThreadModelList;
	}

	public List<ThreadModel> getAnnouncementsThreadModelList()
	{
		return announcementsThreadModelList;
	}

	public AccountModel getAccountModel()
	{
		return accountModel;
	}

	public Integer getMemberCount()
	{
		return memberCount;
	}

	public Integer getThreadCount()
	{
		return threadCount;
	}

	public Integer getPostCount()
	{
		return postCount;
	}
}
