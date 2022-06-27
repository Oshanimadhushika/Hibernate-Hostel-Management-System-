package lk.ijse.hybernate.dao;

import java.io.IOException;

import java.util.List;

public interface CrudDAO <T,ID> extends SuperDAO{
    List<T> getAll() throws IOException;

    boolean save(T entity) throws IOException;

    boolean update(T entity) throws IOException;

    boolean delete(ID id) throws IOException;

    String generateNewID() throws IOException ;

    T search(String id) throws IOException ;
}
