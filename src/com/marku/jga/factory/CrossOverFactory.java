

package com.marku.jga.factory;


import com.marku.jga.operadores.CrossOver;
import com.marku.jga.operadores.CrossOverDoisPontosBinario;
import com.marku.jga.operadores.CrossOverUmPontoBinario;
import com.marku.jga.operadores.CrossOverUmPontoReal;

/**
 *
 * @author Marku
 */
public class CrossOverFactory {

    private static CrossOverFactory instance;
    
    public static CrossOverFactory getInstance(){
        if ( instance == null ){
            instance = new CrossOverFactory();
        }
        
        return instance;
    }
    
    public CrossOver getCrossOver( int tipo){
        switch ( tipo ) {
            case 0: return new CrossOverUmPontoBinario();
            case 1: return new CrossOverDoisPontosBinario();
            case 2: return new CrossOverUmPontoReal();
            default: return null;
        }
            
    }
    
}
