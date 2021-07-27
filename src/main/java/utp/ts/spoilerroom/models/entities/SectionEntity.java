package utp.ts.spoilerroom.models.entities;

import utp.ts.spoilerroom.models.beans.Section;

public class SectionEntity
{
	private final Section section;

	public SectionEntity(Section section)
	{
		this.section = section;
	}

	/**
	 * Getters
	 */

	public Section getSection()
	{
		return section;
	}
}
