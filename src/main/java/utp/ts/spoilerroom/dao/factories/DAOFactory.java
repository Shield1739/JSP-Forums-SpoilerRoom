package utp.ts.spoilerroom.dao.factories;

import utp.ts.spoilerroom.dao.interfaces.AccountDAO;
import utp.ts.spoilerroom.dao.interfaces.BoardDAO;
import utp.ts.spoilerroom.dao.interfaces.HomeDAO;
import utp.ts.spoilerroom.dao.interfaces.MediaDAO;
import utp.ts.spoilerroom.dao.interfaces.PostDAO;
import utp.ts.spoilerroom.dao.interfaces.SectionDAO;
import utp.ts.spoilerroom.dao.interfaces.ThreadDAO;
import utp.ts.spoilerroom.dao.interfaces.VoteDAO;

public abstract class DAOFactory
{
	public static final int H2 = 1;

	public abstract MediaDAO getMediaDAO();
	public abstract AccountDAO getAccountDAO();
	public abstract SectionDAO getSectionDAO();
	public abstract BoardDAO getBoardDAO();
	public abstract ThreadDAO getThreadDAO();
	public abstract PostDAO getPostDAO();
	public abstract VoteDAO getVoteDAO();
	public abstract HomeDAO getHomeDAO();

	public static DAOFactory getDAOFactory(int type)
	{
		switch(type)
		{
			case H2:
				return new H2DAOFactory();
			default:
				System.out.println("ERROR! DAO FACTORY NULL IMPLEMENTATION");
				return null;
		}
	}
}
