package MTBussinessComponent.TMEntities;

import MTDataAccessCompoment.MTDATOs.MTVWHormigaDTO;
import MTBussinessComponent.TMInterfaces.MTFactoryBL;
import MTDataAccessCompoment.MTDAOs.MTHormigaDAO;

public abstract class MTHormiga {
    protected MTFactoryBL<MTVWHormigaDTO> mtFactory;
    public MTVWHormigaDTO mtData = null;


}
