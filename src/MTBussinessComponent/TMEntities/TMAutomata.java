package MTBussinessComponent.TMEntities;

import java.util.Scanner;

public class TMAutomata {
    static final Integer ER = -10;
    static final Integer OK = 100;
    static final String  ALFABETO ="a,b,c, "; 
    static final Integer[][] mt={//  a    b     c    , esp
                                    {1   ,ER   ,ER   ,ER   ,ER   ,ER}, // q0
                                    {ER  ,2    ,ER   ,ER   ,ER   ,OK}, // q1 *
                                    {ER  ,2    ,3    ,ER   ,ER   ,OK}, // q2 *
                                    {ER  ,ER   ,ER   ,ER   ,ER   ,ER}, // q3 *
                                };

    @SuppressWarnings("resource")
    private int getIndexAlfabeto(String caracter){
        Scanner scAlfa = new Scanner(ALFABETO).useDelimiter(",");
        for (int i = 0; scAlfa.hasNext(); i++) 
            if( caracter.equals(scAlfa.next()))
                return i;
        return ER;
    }

    public boolean checkTipoArsenal(String arsenal) {
        int q = 0;

    for (int i = 0; i < arsenal.length(); i++) {
        int indexAlfa = getIndexAlfabeto(arsenal.charAt(i) + "");

        if (indexAlfa == ER || mt[q][indexAlfa].equals(ER))
            return false;

        q = mt[q][indexAlfa];
    }

    int indexEsp = getIndexAlfabeto(" ");
    return mt[q][indexEsp].equals(OK);
    }
}
