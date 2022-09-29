package backend;

import java.sql.Connection;
import java.util.Collection;

public interface Dao<T> {
    Collection<T> getAll(Connection con);
    T getById(Connection con, int id);
    boolean add(Connection con, T t);
    boolean update(Connection con, T t);
    boolean delete(Connection con, T t);
}
