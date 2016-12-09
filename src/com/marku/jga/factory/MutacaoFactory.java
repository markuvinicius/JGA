
package com.marku.jga.factory;

import com.marku.jga.operadores.Mutacao;
import com.marku.jga.operadores.MutacaoRealSimples;
import com.marku.jga.operadores.MutacaoSimples;

/**
 *
 * @author Marku
 */
public class MutacaoFactory {
    
    private static MutacaoFactory instance;
    
    public static final MutacaoFactory getInstance(){
        if (instance == null){
            instance = new MutacaoFactory();
        }
        return instance;
    }
    
    public Mutacao getMutacao( int tipo ){
        switch ( tipo ) {
            case 0: return new MutacaoSimples();
            case 1: return new MutacaoRealSimples();
            default: return null;
        }
    } 

}
