package utp.ts.spoilerroom.dao.factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import utp.ts.spoilerroom.dao.implementations.h2.H2AccountDAO;
import utp.ts.spoilerroom.dao.implementations.h2.H2BoardDAO;
import utp.ts.spoilerroom.dao.implementations.h2.H2HomeDAO;
import utp.ts.spoilerroom.dao.implementations.h2.H2MediaDAO;
import utp.ts.spoilerroom.dao.implementations.h2.H2PostDAO;
import utp.ts.spoilerroom.dao.implementations.h2.H2SectionDAO;
import utp.ts.spoilerroom.dao.implementations.h2.H2ThreadDAO;
import utp.ts.spoilerroom.dao.implementations.h2.H2VoteDAO;
import utp.ts.spoilerroom.dao.interfaces.AccountDAO;
import utp.ts.spoilerroom.dao.interfaces.BoardDAO;
import utp.ts.spoilerroom.dao.interfaces.HomeDAO;
import utp.ts.spoilerroom.dao.interfaces.MediaDAO;
import utp.ts.spoilerroom.dao.interfaces.PostDAO;
import utp.ts.spoilerroom.dao.interfaces.SectionDAO;
import utp.ts.spoilerroom.dao.interfaces.ThreadDAO;
import utp.ts.spoilerroom.dao.interfaces.VoteDAO;

public class H2DAOFactory extends DAOFactory
{
	public static Connection getH2Connection()
	{
		//TODO better connection
		Connection conn;
		String url = "jdbc:h2:C:\\Users\\Luisf\\IdeaProjects\\UTP\\SEM5\\PROG2\\SemestralV3\\H2Database\\SpoilerRoom;DATABASE_TO_UPPER=FALSE";
		String user = "sa";
		String ps = "sa";
		try
		{
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection(url, user, ps);

			return conn;
		}
		catch (SQLException | ClassNotFoundException e)
		{
			System.out.println("H2 DB CONNECTION ERROR");
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public SectionDAO getSectionDAO()
	{
		return new H2SectionDAO();
	}

	@Override
	public AccountDAO getAccountDAO()
	{
		return new H2AccountDAO();
	}

	@Override
	public MediaDAO getMediaDAO()
	{
		return new H2MediaDAO();
	}

	@Override
	public BoardDAO getBoardDAO()
	{
		return new H2BoardDAO();
	}

	@Override
	public ThreadDAO getThreadDAO()
	{
		return new H2ThreadDAO();
	}

	@Override
	public PostDAO getPostDAO()
	{
		return new H2PostDAO();
	}

	@Override
	public VoteDAO getVoteDAO()
	{
		return new H2VoteDAO();
	}

	@Override
	public HomeDAO getHomeDAO()
	{
		return new H2HomeDAO();
	}
}
