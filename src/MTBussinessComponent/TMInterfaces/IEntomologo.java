package MTBussinessComponent.TMInterfaces;

import java.util.List;
import MTBussinessComponent.TMEntities.MTAlimento;
import MTBussinessComponent.TMEntities.MTHormiga; 

public interface IEntomologo {

    public List<MTHormiga> mtEntAntNest();

    public List<MTAlimento> mtEntAntFood();

    /**
     * Realiza la acción de alimentar a una hormiga específica.
     * @return La hormiga con sus cambios aplicados (mutación o muerte).
     */
    public MTHormiga mtAlimentarAnt(MTHormiga mtHormiga, MTAlimento mtAlimento);
}