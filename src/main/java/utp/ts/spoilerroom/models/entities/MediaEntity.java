package utp.ts.spoilerroom.models.entities;

import utp.ts.spoilerroom.models.beans.Media;

public class MediaEntity
{
	private final Media media;

	public MediaEntity(Media media)
	{
		this.media = media;
	}

	/**
	 * Getters
	 */

	public Media getMedia()
	{
		return media;
	}
}
