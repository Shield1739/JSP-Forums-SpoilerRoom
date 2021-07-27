package utp.ts.spoilerroom.models.beans;

import java.sql.Timestamp;

public class Account extends BaseBean
{
	private Integer accountTypeId;
	private String email;
	private String username;
	private String passwordSalt;
	private String passwordHash;
	private Timestamp creationDate;
	private Timestamp lastActiveDate;
	private String profileImageUrl;
	private String title;
	private String location;

	private String accountTypeName;
	private Integer threadCount;
	private Integer postCount;
	private Integer cheese;

	public Account()
	{
	}

	/**
	 * Getters & Setters
	 */

	public Integer getAccountTypeId()
	{
		return accountTypeId;
	}

	public void setAccountTypeId(Integer accountTypeId)
	{
		this.accountTypeId = accountTypeId;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPasswordSalt()
	{
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt)
	{
		this.passwordSalt = passwordSalt;
	}

	public String getPasswordHash()
	{
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash)
	{
		this.passwordHash = passwordHash;
	}

	public Timestamp getCreationDate()
	{
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate)
	{
		this.creationDate = creationDate;
	}

	public Timestamp getLastActiveDate()
	{
		return lastActiveDate;
	}

	public void setLastActiveDate(Timestamp lastActiveDate)
	{
		this.lastActiveDate = lastActiveDate;
	}

	public String getProfileImageUrl()
	{
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl)
	{
		this.profileImageUrl = profileImageUrl;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public String getAccountTypeName()
	{
		return accountTypeName;
	}

	public void setAccountTypeName(String accountTypeName)
	{
		this.accountTypeName = accountTypeName;
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

	public Integer getCheese()
	{
		return cheese;
	}

	public void setCheese(Integer cheese)
	{
		this.cheese = cheese;
	}
}
