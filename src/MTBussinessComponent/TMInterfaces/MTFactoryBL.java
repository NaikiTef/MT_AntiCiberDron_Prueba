package MTBussinessComponent.TMInterfaces;

import java.util.List;
import MTDataAccessCompoment.MTInterfaces.MTIDataHelperSQLiteDAO;
import MTInfrastructureComponent.MTAppException;
import MTInfrastructureComponent.MTAppDataException;


public class MTFactoryBL<T> {
    private final MTIDataHelperSQLiteDAO<T> oDAO;

    public MTFactoryBL(Class<? extends MTIDataHelperSQLiteDAO<T>> classDAO) throws MTInfrastructureComponent.MTAppDataException {
        try {
            this.oDAO = classDAO.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new MTInfrastructureComponent.MTAppDataException(e, getClass(), "MTFactoryBL(...)");
        }
    }


 
        public List<T> getAll() throws MTAppException {
            return oDAO.readAll();
        }

    public T getBy(Integer id) throws MTAppException {
        return oDAO.readBy(id);
    }

    public boolean add(T oT) throws MTAppException {
        return oDAO.create(oT);
    }

    public boolean upd(T oT) throws MTAppException {
        return oDAO.update(oT);
    }

    public boolean del(Integer id) throws MTAppException {
        return oDAO.delete(id);
    }
}
