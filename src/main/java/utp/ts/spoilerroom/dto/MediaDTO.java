package utp.ts.spoilerroom.dto;

import utp.ts.spoilerroom.models.beans.Media;

public class MediaDTO extends Media
{
	public MediaDTO()
	{
	}

	/**
	 * Beans
	 */

	public Media getMedia()
	{
		Media media = new Media();

		media.setId(this.getId());
		media.setMediaTypeId(this.getMediaTypeId());
		media.setTitle(this.getTitle());
		media.setPosterImageUrl(this.getPosterImageUrl());
		media.setEpisodes(this.getEpisodes());
		media.setRuntime(this.getRuntime());
		media.setAiredStartDate(this.getAiredStartDate());
		media.setAiredEndDate(this.getAiredEndDate());
		media.setMediaTypeName(this.getMediaTypeName());
		media.setThreadCount(this.getThreadCount());
		media.setPostCount(this.getPostCount());

		return media;
	}
}
