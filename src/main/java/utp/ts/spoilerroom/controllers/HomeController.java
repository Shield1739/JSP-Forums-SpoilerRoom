package utp.ts.spoilerroom.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import utp.ts.spoilerroom.dao.factories.DAOFactory;
import utp.ts.spoilerroom.dao.factories.H2DAOFactory;
import utp.ts.spoilerroom.dao.interfaces.AccountDAO;
import utp.ts.spoilerroom.dao.interfaces.HomeDAO;
import utp.ts.spoilerroom.dao.interfaces.MediaDAO;
import utp.ts.spoilerroom.dao.interfaces.ThreadDAO;
import utp.ts.spoilerroom.dto.AccountDTO;
import utp.ts.spoilerroom.dto.HomeDTO;
import utp.ts.spoilerroom.dto.MediaDTO;
import utp.ts.spoilerroom.dto.ThreadDTO;
import utp.ts.spoilerroom.models.AccountModel;
import utp.ts.spoilerroom.models.HomeModel;
import utp.ts.spoilerroom.models.ThreadModel;
import utp.ts.spoilerroom.models.entities.AccountEntity;
import utp.ts.spoilerroom.models.entities.MediaEntity;
import utp.ts.spoilerroom.models.entities.PostEntity;
import utp.ts.spoilerroom.models.entities.ThreadEntity;

public class HomeController
{
	private final HomeModel homeModel;

	public HomeController()
	{
		List<MediaDTO> streamingMediaDTOList = mediaDAO().fetchAllStreamingMedia();
		List<MediaEntity> streamingMediaEntityList = new ArrayList<>();

		for (MediaDTO mediaDTO : streamingMediaDTOList)
		{
			streamingMediaEntityList.add(new MediaEntity(mediaDTO.getMedia()));
		}

		List<ThreadDTO> mostActiveThreadDTOList = threadDAO().fetchMostActiveThreads();
		List<ThreadModel> mostActiveThreadModelList = new ArrayList<>();

		for (ThreadDTO threadDTO : mostActiveThreadDTOList)
		{
			ThreadEntity threadEntity = new ThreadEntity(
				threadDTO.getThread(), threadDTO.getOwnerAccount(),
				threadDTO.getBoard(), null);

			PostEntity lastPostEntity = new PostEntity(
				threadDTO.getLastPost(), threadDTO.getThread(),
				threadDTO.getOwnerAccount(), null);

			mostActiveThreadModelList.add(new ThreadModel(threadEntity, lastPostEntity, null, null));
		}

		List<ThreadDTO> latestThreadDTOList = threadDAO().fetchLatestThreads();
		List<ThreadModel> latestThreadModelList = new ArrayList<>();

		for (ThreadDTO threadDTO : latestThreadDTOList)
		{
			ThreadEntity threadEntity = new ThreadEntity(
				threadDTO.getThread(), threadDTO.getOwnerAccount(),
				threadDTO.getBoard(), null);

			PostEntity lastPostEntity = new PostEntity(
				threadDTO.getLastPost(), threadDTO.getThread(),
				threadDTO.getOwnerAccount(), null);

			latestThreadModelList.add(new ThreadModel(threadEntity, lastPostEntity, null,null));
		}

		List<ThreadDTO> announcementsThreadDTOList = threadDAO().fetchLatestAnnouncements();
		List<ThreadModel> announcementsThreadModelList = new ArrayList<>();

		for (ThreadDTO threadDTO : announcementsThreadDTOList)
		{
			ThreadEntity threadEntity = new ThreadEntity(
				threadDTO.getThread(), threadDTO.getOwnerAccount(),
				threadDTO.getBoard(), null);

			announcementsThreadModelList.add(new ThreadModel(threadEntity, null, null, null));
		}

		AccountDTO accountDTO = accountDAO().fetchTopCheeser();
		HomeDTO homeDTO = homeDAO().fetch();

		this.homeModel = new HomeModel(
			streamingMediaEntityList, mostActiveThreadModelList, latestThreadModelList, announcementsThreadModelList,
			new AccountModel(new AccountEntity(accountDTO.getAccount())), homeDTO.getMemberCount(), homeDTO.getThreadCount(), homeDTO.getPostCount());
	}

	/**
	 * DAO
	 */

	private static MediaDAO mediaDAO()
	{
		return Objects.requireNonNull(H2DAOFactory.getDAOFactory(DAOFactory.H2)).getMediaDAO();
	}

	private static ThreadDAO threadDAO()
	{
		return Objects.requireNonNull(H2DAOFactory.getDAOFactory(DAOFactory.H2)).getThreadDAO();
	}

	private static AccountDAO accountDAO()
	{
		return Objects.requireNonNull(H2DAOFactory.getDAOFactory(DAOFactory.H2)).getAccountDAO();
	}

	private static HomeDAO homeDAO()
	{
		return Objects.requireNonNull(H2DAOFactory.getDAOFactory(DAOFactory.H2)).getHomeDAO();
	}

	/**
	 * Getters
	 */

	public HomeModel getHomeModel()
	{
		return homeModel;
	}
}
