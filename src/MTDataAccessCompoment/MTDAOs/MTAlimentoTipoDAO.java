package MTDataAccessCompoment.MTDAOs;

import MTDataAccessCompoment.MTDATOs.MTAlimentoTipoDTO;
import MTDataAccessCompoment.MTHelpers.MTDataHelperSQLiteDAO;
import MTInfrastructureComponent.MTAppDataException;

public class MTAlimentoTipoDAO extends MTDataHelperSQLiteDAO<MTAlimentoTipoDTO> {
    public MTAlimentoTipoDAO() throws MTAppDataException {
        super(MTAlimentoTipoDTO.class, "MTAlimentoTipo", "IdAlimentoTipo");
    }
}
