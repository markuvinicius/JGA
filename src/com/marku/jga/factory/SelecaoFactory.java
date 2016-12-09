

package com.marku.jga.factory;

import com.marku.jga.operadores.Roleta;
import com.marku.jga.operadores.RoletaViciada;
import com.marku.jga.operadores.Selecao;

/**
 *
 * @author Marku
 */
public class SelecaoFactory {
    
    private static SelecaoFactory instance;
    
    public static final SelecaoFactory getInstance(){
        if ( instance == null ){
            instance = new SelecaoFactory();
        }
        return instance;
    }
    
    public Selecao getSelecao( int tipo ){
         switch ( tipo ) {
            case 0: return new RoletaViciada();
            case 1: return new Roleta();
            default: return null;
        }
    }

}
