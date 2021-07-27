package utp.ts.spoilerroom.models.beans;

public class Section extends BaseBean
{
	private String title;
	private Integer weight;

	public Section()
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

	public Integer getWeight()
	{
		return weight;
	}

	public void setWeight(Integer weight)
	{
		this.weight = weight;
	}
}
