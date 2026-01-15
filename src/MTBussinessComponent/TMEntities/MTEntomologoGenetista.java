package MTBussinessComponent.TMEntities;

import java.util.Random;
import MTDataAccessCompoment.MTDATOs.MTAlimentoTipoDTO;

public class MTEntomologoGenetista extends MTEntomologo {

    public MTEntomologoGenetista(MTAlimentoTipoDTO mtAlimentoTipo) {
        super(mtAlimentoTipo);
    }

    /**
     * REQUERIMIENTO: El entomólogo genetista prepara el alimento inyectando 
     * un genoma aleatoriamente (X, XX, XY).
     */
    public MTAlimento mtPrepararAlimentoG(MTAlimento mtAlimento) {
        // Definición de genomas posibles según la rúbrica
        String[] mtGenomas = {"X", "XX", "XY"};
        Random mtRandom = new Random();
        
        // Selección aleatoria
        String mtGenomaInyectado = mtGenomas[mtRandom.nextInt(mtGenomas.length)];
        
        // Configuración del alimento
        mtAlimento.setMtGenoma(mtGenomaInyectado);
        mtAlimento.setMtEstaPreparado(true);

        // IMPRESIÓN OBLIGATORIA: [ Preparado ]—( tipoAlimento + Genoma )—
        System.out.println("[ Preparado ]—( " + mtAlimento.getMtNombre() + " + " + mtGenomaInyectado + " )—");
        
        return mtAlimento;
    }
}