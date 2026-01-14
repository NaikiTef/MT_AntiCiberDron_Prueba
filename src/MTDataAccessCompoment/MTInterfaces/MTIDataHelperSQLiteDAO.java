package MTDataAccessCompoment.MTInterfaces;

import java.util.List;
import MTInfrastructureComponent.MTAppException;

public interface MTIDataHelperSQLiteDAO<T> {
    List<T> readAll()            throws MTAppException;
    T       readBy (Integer id)  throws MTAppException;
    boolean create (T entity)    throws MTAppException;
    boolean update (T entity)    throws MTAppException;
    boolean delete (Integer id)  throws MTAppException;
    Integer getMaxReg()          throws MTAppException;
}
