package utp.ts.spoilerroom.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import utp.ts.spoilerroom.dao.factories.DAOFactory;
import utp.ts.spoilerroom.dao.factories.H2DAOFactory;
import utp.ts.spoilerroom.dao.interfaces.PostDAO;
import utp.ts.spoilerroom.dao.interfaces.ThreadDAO;
import utp.ts.spoilerroom.dao.interfaces.VoteDAO;
import utp.ts.spoilerroom.dto.PostDTO;
import utp.ts.spoilerroom.dto.ThreadDTO;
import utp.ts.spoilerroom.dto.VoteDTO;
import utp.ts.spoilerroom.models.ThreadModel;
import utp.ts.spoilerroom.models.beans.Post;
import utp.ts.spoilerroom.models.entities.MediaEntity;
import utp.ts.spoilerroom.models.entities.PostEntity;
import utp.ts.spoilerroom.models.entities.ThreadEntity;

public class ThreadController
{
	private final ThreadModel threadModel;

	public ThreadController(int threadId, int rowNumber, Integer accountId)
	{
		ThreadDTO threadDTO = threadDAO().fetchById(threadId);

		if (threadDTO == null)
		{
			this.threadModel = null;
			return;
		}

		ThreadEntity threadEntity = new ThreadEntity(
			threadDTO.getThread(), threadDTO.getOwnerAccount(), threadDTO.getBoard(), threadDTO.getSection());

		PostEntity lastPostEntity = new PostEntity(
			threadDTO.getLastPost(), null, threadDTO.getLastPostOwnerAccount(), null);

		List<PostDTO> postDTOList;

		if (accountId == null)
		{
			postDTOList = postDAO().fetchAllPostsWhereThreadIdBetweenRow(threadId, rowNumber);
		}
		else
		{
			postDTOList = postDAO().fetchAllPostsWhereThreadIdBetweenRowWithAccountVote(threadId, rowNumber, accountId);
		}

		List<PostEntity> postEntityList = new ArrayList<>();

		for (PostDTO postDTO : postDTOList)
		{
			postEntityList.add(new PostEntity(
				postDTO.getPost(), postDTO.getThread(),
				postDTO.getOwnerAccount(), postDTO.getLastEditorAccount(), postDTO.getVote()));
		}

		this.threadModel = new ThreadModel(threadEntity, lastPostEntity, new MediaEntity(threadDTO.getMedia()), postEntityList);

		threadDAO().updateThreadViewCountWhereId(threadId);
	}

	public static void postReply(int threadId, int parentId, int accountId, String postContent)
	{
		PostDTO postDTO = new PostDTO();
		postDTO.setThreadId(threadId);
		postDTO.setParentId(parentId);
		postDTO.setOwnerAccountId(accountId);
		postDTO.setCreationDate(new Timestamp(System.currentTimeMillis()));
		postDTO.setContent(postContent);

		postDAO().insert(postDTO);
	}

	public static void editPost(int postId, int editorAccountId, String postContent)
	{
		PostDTO postDTO = new PostDTO();
		postDTO.setId(postId);
		postDTO.setLastEditorAccountId(editorAccountId);
		postDTO.setLastEditDate(new Timestamp(System.currentTimeMillis()));
		postDTO.setContent(postContent);

		postDAO().update(postDTO);
	}

	public static void manageVote(int postId, int accountId, int voteType)
	{
		VoteDTO voteDTO = new VoteDTO();
		voteDTO.setPostId(postId);
		voteDTO.setAccountId(accountId);
		voteDTO.setVoteType(voteType);
		voteDTO.setCreationDate(new Timestamp(System.currentTimeMillis()));

		if (voteType == 0)
		{
			voteDAO().delete(voteDTO);
		}
		else
		{
			voteDAO().insert(voteDTO);
		}
	}

	public static void deleteThread(int threadId)
	{
		ThreadDTO threadDTO = new ThreadDTO();
		threadDTO.setId(threadId);

		threadDAO().delete(threadDTO);
	}

	public static void deletePost(int postId, int parentId)
	{
		PostDTO postDTO = new PostDTO();
		postDTO.setId(postId);
		postDTO.setParentId(parentId);

		postDAO().delete(postDTO);
	}

	/**
	 * DAO
	 */

	private static ThreadDAO threadDAO()
	{
		return Objects.requireNonNull(H2DAOFactory.getDAOFactory(DAOFactory.H2)).getThreadDAO();
	}

	private static PostDAO postDAO()
	{
		return Objects.requireNonNull(H2DAOFactory.getDAOFactory(DAOFactory.H2)).getPostDAO();
	}

	private static VoteDAO voteDAO()
	{
		return Objects.requireNonNull(H2DAOFactory.getDAOFactory(DAOFactory.H2)).getVoteDAO();
	}

	/**
	 * Getters
	 */

	public ThreadModel getThreadModel()
	{
		return threadModel;
	}
}
