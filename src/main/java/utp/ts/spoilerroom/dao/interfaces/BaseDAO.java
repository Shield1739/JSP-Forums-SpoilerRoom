package utp.ts.spoilerroom.dao.interfaces;

import java.util.List;

public interface BaseDAO<T>
{
	List<T> fetchAll();
	T fetchById(int id);

	T insert(T t);
	void update(T t);
	void delete(T t);
}
