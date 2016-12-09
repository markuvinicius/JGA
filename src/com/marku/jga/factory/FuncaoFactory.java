
package com.marku.jga.factory;

import com.marku.jga.functions.Funcao;
import com.marku.jga.functions.MultiModal;
import com.marku.jga.functions.Rastrigin;
import com.marku.jga.functions.UnimodalF1;
import com.marku.jga.functions.UnimodalF2;

/**
 *
 * @author Marku
 */
public class FuncaoFactory {
    
    private static FuncaoFactory instance;
    
    public static FuncaoFactory getInstance(){
        if ( instance == null ){
            instance = new FuncaoFactory();
        }
        
        return instance;
    }
    
    public Funcao getFuncao( int tipo , double minBound , double maxBound , int dimensao){
        switch ( tipo ) {
            case 0: return new Rastrigin( minBound , maxBound , dimensao );
            case 1: return new UnimodalF1(minBound, maxBound, dimensao);
            case 2: return new UnimodalF2(minBound, maxBound, dimensao);
            case 3: return new MultiModal(minBound, maxBound, dimensao);
            default: return null;
        }
            
    }

}
