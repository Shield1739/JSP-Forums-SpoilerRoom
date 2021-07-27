package utp.ts.spoilerroom.dto;

import utp.ts.spoilerroom.models.beans.Section;

public class SectionDTO extends Section
{
	public SectionDTO()
	{
	}

	/**
	 * Beans
	 */

	public Section getSection()
	{
		Section section = new Section();

		section.setId(this.getId());
		section.setTitle(this.getTitle());
		section.setWeight(this.getWeight());

		return section;
	}
}
