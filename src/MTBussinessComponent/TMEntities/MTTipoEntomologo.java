package MTBussinessComponent.TMEntities;

import MTDataAccessCompoment.MTDATOs.MTAlimentoTipoDTO;
import java.util.logging.Logger;

public abstract class MTTipoEntomologo {
    private static final Logger LOGGER = Logger.getLogger(MTTipoEntomologo.class.getName());
    protected MTTipoEntomologo(MTAlimentoTipoDTO alimentoTipo){
        LOGGER.info("El entomólogo está preparando el alimento tipo: " + alimentoTipo.getNombre());
    }
}
