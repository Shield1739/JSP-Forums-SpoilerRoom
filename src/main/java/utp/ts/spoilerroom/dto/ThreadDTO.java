package utp.ts.spoilerroom.dto;

import java.sql.Timestamp;
import utp.ts.spoilerroom.models.beans.Account;
import utp.ts.spoilerroom.models.beans.Board;
import utp.ts.spoilerroom.models.beans.Media;
import utp.ts.spoilerroom.models.beans.Post;
import utp.ts.spoilerroom.models.beans.Section;
import utp.ts.spoilerroom.models.beans.Thread;

public class ThreadDTO extends Thread
{
	private Integer sectionId;
	private String sectionTitle;

	private Integer boardId;
	private String boardTitle;

	private Integer mediaId;
	private String mediaTitle;
	private String mediaTypeName;

	private Integer ownerAccountId;
	private String ownerAccountUsername;
	private String ownerAccountProfileImageUrl;

	private Integer lastPostId;
	private Timestamp lastPostCreationDate;

	private Integer lastPostOwnerAccountId;
	private String lastPostOwnerAccountUsername;
	private String lastPostOwnerAccountProfileImageUrl;
	private Integer lastPostCheese;

	public ThreadDTO()
	{
	}

	/**
	 * Beans
	 */

	public Thread getThread()
	{
		Thread thread = new Thread();

		thread.setId(this.getId());
		thread.setCreationDate(this.getCreationDate());
		thread.setClosedDate(this.getClosedDate());
		thread.setTitle(this.getTitle());
		thread.setViewCount(this.getViewCount());
		thread.setPostCount(this.getPostCount());
		thread.setCheese(this.getCheese());

		return thread;
	}

	public Section getSection()
	{
		Section section = new Section();

		section.setId(this.sectionId);
		section.setTitle(this.sectionTitle);

		return section;
	}

	public Board getBoard()
	{
		Board board = new Board();

		board.setId(this.boardId);
		board.setTitle(this.boardTitle);

		return board;
	}

	public Media getMedia()
	{
		Media media = new Media();

		media.setId(this.mediaId);
		media.setTitle(this.mediaTitle);
		media.setMediaTypeName(this.mediaTypeName);

		return media;
	}

	public Account getOwnerAccount()
	{
		Account ownerAccount = new Account();

		ownerAccount.setId(this.ownerAccountId);
		ownerAccount.setUsername(this.ownerAccountUsername);
		ownerAccount.setProfileImageUrl(this.ownerAccountProfileImageUrl);

		return ownerAccount;
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

	public Integer getBoardId()
	{
		return boardId;
	}

	public void setBoardId(Integer boardId)
	{
		this.boardId = boardId;
	}

	public String getBoardTitle()
	{
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle)
	{
		this.boardTitle = boardTitle;
	}

	public Integer getMediaId()
	{
		return mediaId;
	}

	public void setMediaId(Integer mediaId)
	{
		this.mediaId = mediaId;
	}

	public String getMediaTitle()
	{
		return mediaTitle;
	}

	public void setMediaTitle(String mediaTitle)
	{
		this.mediaTitle = mediaTitle;
	}

	public String getMediaTypeName()
	{
		return mediaTypeName;
	}

	public void setMediaTypeName(String mediaTypeName)
	{
		this.mediaTypeName = mediaTypeName;
	}

	public Integer getOwnerAccountId()
	{
		return ownerAccountId;
	}

	public void setOwnerAccountId(Integer ownerAccountId)
	{
		this.ownerAccountId = ownerAccountId;
	}

	public String getOwnerAccountUsername()
	{
		return ownerAccountUsername;
	}

	public void setOwnerAccountUsername(String ownerAccountUsername)
	{
		this.ownerAccountUsername = ownerAccountUsername;
	}

	public String getOwnerAccountProfileImageUrl()
	{
		return ownerAccountProfileImageUrl;
	}

	public void setOwnerAccountProfileImageUrl(String ownerAccountProfileImageUrl)
	{
		this.ownerAccountProfileImageUrl = ownerAccountProfileImageUrl;
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

	public Integer getLastPostCheese()
	{
		return lastPostCheese;
	}

	public void setLastPostCheese(Integer lastPostCheese)
	{
		this.lastPostCheese = lastPostCheese;
	}
}
