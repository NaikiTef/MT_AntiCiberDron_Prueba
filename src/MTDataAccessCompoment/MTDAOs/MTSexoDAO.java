package MTDataAccessCompoment.MTDAOs;

import MTDataAccessCompoment.MTDATOs.MTSexoDTO;
import MTDataAccessCompoment.MTHelpers.MTDataHelperSQLiteDAO;
import MTInfrastructureComponent.MTAppDataException;

public class MTSexoDAO extends MTDataHelperSQLiteDAO<MTSexoDTO> {
    public MTSexoDAO() throws MTAppDataException {
        super(MTSexoDTO.class, "MTSexo", "IdSexo");
    }
}
