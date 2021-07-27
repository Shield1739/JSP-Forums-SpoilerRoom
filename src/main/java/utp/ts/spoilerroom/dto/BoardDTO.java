package utp.ts.spoilerroom.dto;

import java.sql.Timestamp;
import utp.ts.spoilerroom.models.beans.Account;
import utp.ts.spoilerroom.models.beans.Board;
import utp.ts.spoilerroom.models.beans.Post;
import utp.ts.spoilerroom.models.beans.Section;
import utp.ts.spoilerroom.models.beans.Thread;

public class BoardDTO extends Board
{
	private Integer sectionId;
	private String sectionTitle;

	private Integer lastPostId;
	private Timestamp lastPostCreationDate;

	private Integer lastPostOwnerAccountId;
	private String lastPostOwnerAccountUsername;
	private String lastPostOwnerAccountProfileImageUrl;

	private Integer lastPostThreadId;
	private String lastPostThreadTitle;
	private Integer lastPostCheese;

	public BoardDTO()
	{
	}

	/**
	 * Beans
	 */

	public Board getBoard()
	{
		Board board = new Board();

		board.setId(this.getId());
		board.setTitle(this.getTitle());
		board.setDescription(this.getDescription());
		board.setWeight(this.getWeight());
		board.setThreadCount(this.getThreadCount());
		board.setPostCount(this.getPostCount());

		return board;
	}

	public Section getSection()
	{
		Section section = new Section();

		section.setId(this.sectionId);
		section.setTitle(this.sectionTitle);

		return section;
	}

	public Post getLastPost()
	{
		Post post = new Post();

		post.setId(this.lastPostId);
		post.setCreationDate(this.lastPostCreationDate);
		post.setCheese(this.lastPostCheese);

		return post;
	}

	public Account getLastPostOwnerAccount()
	{
		Account lastPostOwnerAccount = new Account();

		lastPostOwnerAccount.setId(this.lastPostOwnerAccountId);
		lastPostOwnerAccount.setUsername(this.lastPostOwnerAccountUsername);
		lastPostOwnerAccount.setProfileImageUrl(this.lastPostOwnerAccountProfileImageUrl);

		return lastPostOwnerAccount;
	}

	public Thread getLastPostThread()
	{
		Thread thread = new Thread();

		thread.setId(this.lastPostThreadId);
		thread.setTitle(this.lastPostThreadTitle);

		return thread;
	}

	/**
	 * Getters & Setters
	 */

	public Integer getSectionId()
	{
		return sectionId;
	}

	public void setSectionId(Integer sectionId)
	{
		this.sectionId = sectionId;
	}

	public String getSectionTitle()
	{
		return sectionTitle;
	}

	public void setSectionTitle(String sectionTitle)
	{
		this.sectionTitle = sectionTitle;
	}

	public Integer getLastPostId()
	{
		return lastPostId;
	}

	public void setLastPostId(Integer lastPostId)
	{
		this.lastPostId = lastPostId;
	}

	public Timestamp getLastPostCreationDate()
	{
		return lastPostCreationDate;
	}

	public void setLastPostCreationDate(Timestamp lastPostCreationDate)
	{
		this.lastPostCreationDate = lastPostCreationDate;
	}

	public Integer getLastPostOwnerAccountId()
	{
		return lastPostOwnerAccountId;
	}

	public void setLastPostOwnerAccountId(Integer lastPostOwnerAccountId)
	{
		this.lastPostOwnerAccountId = lastPostOwnerAccountId;
	}

	public String getLastPostOwnerAccountUsername()
	{
		return lastPostOwnerAccountUsername;
	}

	public void setLastPostOwnerAccountUsername(String lastPostOwnerAccountUsername)
	{
		this.lastPostOwnerAccountUsername = lastPostOwnerAccountUsername;
	}

	public String getLastPostOwnerAccountProfileImageUrl()
	{
		return lastPostOwnerAccountProfileImageUrl;
	}

	public void setLastPostOwnerAccountProfileImageUrl(String lastPostOwnerAccountProfileImageUrl)
	{
		this.lastPostOwnerAccountProfileImageUrl = lastPostOwnerAccountProfileImageUrl;
	}

	public Integer getLastPostThreadId()
	{
		return lastPostThreadId;
	}

	public void setLastPostThreadId(Integer lastPostThreadId)
	{
		this.lastPostThreadId = lastPostThreadId;
	}

	public String getLastPostThreadTitle()
	{
		return lastPostThreadTitle;
	}

	public void setLastPostThreadTitle(String lastPostThreadTitle)
	{
		this.lastPostThreadTitle = lastPostThreadTitle;
	}

	public Integer getLastPostCheese()
	{
		return lastPostCheese;
	}

	public void setLastPostCheese(Integer lastPostCheese)
	{
		this.lastPostCheese = lastPostCheese;
	}
}
