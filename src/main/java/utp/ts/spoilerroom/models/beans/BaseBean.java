package utp.ts.spoilerroom.models.beans;

public abstract class BaseBean
{
	private Integer id;

	public BaseBean()
	{
	}

	/**
	 * Getters & Setters
	 */

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}
}
