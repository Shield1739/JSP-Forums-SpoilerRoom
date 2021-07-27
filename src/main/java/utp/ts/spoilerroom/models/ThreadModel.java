package utp.ts.spoilerroom.models;

import java.util.List;
import utp.ts.spoilerroom.models.entities.MediaEntity;
import utp.ts.spoilerroom.models.entities.PostEntity;
import utp.ts.spoilerroom.models.entities.ThreadEntity;

public class ThreadModel
{
	private final ThreadEntity threadEntity;
	private final PostEntity lastPostEntity;
	private final MediaEntity mediaEntity;
	private final List<PostEntity> postEntityList;

	public ThreadModel(ThreadEntity threadEntity, PostEntity lastPostEntity, MediaEntity mediaEntity, List<PostEntity> postEntityList)
	{
		this.threadEntity = threadEntity;
		this.lastPostEntity = lastPostEntity;
		this.mediaEntity = mediaEntity;
		this.postEntityList = postEntityList;
	}

	/**
	 * Getters
	 */

	public ThreadEntity getThreadEntity()
	{
		return threadEntity;
	}

	public PostEntity getLastPostEntity()
	{
		return lastPostEntity;
	}

	public MediaEntity getMediaEntity()
	{
		return mediaEntity;
	}

	public List<PostEntity> getPostEntityList()
	{
		return postEntityList;
	}
}
