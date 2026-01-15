package MTBussinessComponent.TMEntities;

import MTDataAccessCompoment.MTDATOs.MTHormigaDTO;
import MTBussinessComponent.TMInterfaces.MTFactoryBL;


public class MTHLarva {
    private final MTFactoryBL<MTHormigaDTO> factory;

    public MTHLarva(MTFactoryBL<MTHormigaDTO> factory) {
        this.factory = factory;
    }


    public MTHormigaDTO getLarva(int id) {
        try {
            return factory.getBy(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public java.util.List<MTHormigaDTO> getLarvas() {
        try {
            return factory.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object comer(String comida) {
        if (comida.equals("carne")) {
            return new MTHSoldado();
        }
        return this;
    }

    @Override
    public String toString() {
        return "MTHLarva {}";
    }

}
