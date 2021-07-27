package utp.ts.spoilerroom.utilities;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import utp.ts.spoilerroom.dao.factories.H2DAOFactory;

public class RSUtils<T>
{
	public List<T> fetchList(QueryRunner queryRunner, String query, ResultSetHandler<List<T>> beanListHandler, Object... param)
	{
		List<T> tList = null;
		Connection c = H2DAOFactory.getH2Connection();

		try
		{
			tList = queryRunner.query(c, query, beanListHandler, param);
		}
		catch (SQLException throwable)
		{
			throwable.printStackTrace();
		}
		finally
		{
			DbUtils.closeQuietly(c);
		}

		return tList;
	}

	public List<T> fetchList(QueryRunner queryRunner, String query, ResultSetHandler<List<T>> beanListHandler, Object param)
	{
		List<T> tList = null;
		Connection c = H2DAOFactory.getH2Connection();

		try
		{
			tList = queryRunner.query(c, query, beanListHandler, param);
		}
		catch (SQLException throwable)
		{
			throwable.printStackTrace();
		}
		finally
		{
			DbUtils.closeQuietly(c);
		}

		return tList;
	}

	public List<T> fetchList(QueryRunner queryRunner, String query, ResultSetHandler<List<T>> beanListHandler)
	{
		List<T> tList = null;
		Connection c = H2DAOFactory.getH2Connection();

		try
		{
			tList = queryRunner.query(c, query, beanListHandler);
		}
		catch (SQLException throwable)
		{
			throwable.printStackTrace();
		}
		finally
		{
			DbUtils.closeQuietly(c);
		}

		return tList;
	}

	public T fetchSingle(QueryRunner queryRunner, String query, ResultSetHandler<T> beanHandler)
	{
		T t = null;
		Connection c = H2DAOFactory.getH2Connection();

		try
		{
			t = queryRunner.query(c, query, beanHandler);
		}
		catch (SQLException throwable)
		{
			throwable.printStackTrace();
		}
		finally
		{
			DbUtils.closeQuietly(c);
		}

		return t;
	}

	public T fetchSingle(QueryRunner queryRunner, String query, ResultSetHandler<T> beanHandler, Object param)
	{
		T t = null;
		Connection c = H2DAOFactory.getH2Connection();

		try
		{
			t = queryRunner.query(c, query, beanHandler, param);
		}
		catch (SQLException throwable)
		{
			throwable.printStackTrace();
		}
		finally
		{
			DbUtils.closeQuietly(c);
		}

		return t;
	}

	public T insertSingle(QueryRunner queryRunner, String query, ResultSetHandler<T> beanHandler, Object... param)
	{
		T t = null;
		Connection c = H2DAOFactory.getH2Connection();

		try
		{
			t = queryRunner.insert(c, query, beanHandler, param);
		}
		catch (SQLException throwable)
		{
			throwable.printStackTrace();
		}
		finally
		{
			DbUtils.closeQuietly(c);
		}

		return t;
	}

	public T insertSingleQuietly(QueryRunner queryRunner, String query, ResultSetHandler<T> beanHandler, Object... param)
	{
		T t = null;
		Connection c = H2DAOFactory.getH2Connection();

		try
		{
			t = queryRunner.insert(c, query, beanHandler, param);
		}
		catch (SQLException ignored)
		{
		}
		finally
		{
			DbUtils.closeQuietly(c);
		}

		return t;
	}

	public int updateSingle(QueryRunner queryRunner, String query, Object... param)
	{
		int rs = 0;
		Connection c = H2DAOFactory.getH2Connection();

		try
		{
			rs = queryRunner.update(c, query, param);
		}
		catch (SQLException throwable)
		{
			throwable.printStackTrace();
		}
		finally
		{
			DbUtils.closeQuietly(c);
		}

		return rs;
	}

}
