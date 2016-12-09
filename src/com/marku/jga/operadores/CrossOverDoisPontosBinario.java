
package com.marku.jga.operadores;

import com.marku.jga.model.CromossomoAbstrato;
import com.marku.jga.model.CromossomoBinario;

/**
 *
 * @author Marku
 */
public class CrossOverDoisPontosBinario extends CrossOver {

    @Override
    public CromossomoAbstrato[] crossOver(CromossomoAbstrato pai1, CromossomoAbstrato pai2) {
        
        String aux1 , aux2 ;
        CromossomoBinario returno = null;
        
        int pontoCorte1 = ( new Double( Math.random() * ( pai1.getGene().toString().length() -1 ) ) ).intValue();
        int pontoCorte2 = ( new Double( Math.random() * ( pai1.getGene().toString().length() - ( pontoCorte1 + 1 ) ) ) ).intValue();
        pontoCorte2 = pontoCorte2 + pontoCorte1;
        
        //monta o gene do primeiro filho
        aux1  = pai1.getGene().toString().substring( 0 , pontoCorte1);
        aux1 += pai2.getGene().toString().substring( pontoCorte1 , pontoCorte2 );
        aux1 += pai1.getGene().toString().substring( pontoCorte2 , pai1.getGene().toString().length() );
        
        //monta o gene do segundo filho
        aux2  = pai2.getGene().toString().substring( 0 , pontoCorte1);
        aux2 += pai1.getGene().toString().substring( pontoCorte1 , pontoCorte2 );
        aux2 += pai2.getGene().toString().substring( pontoCorte2 , pai2.getGene().toString().length() );
        
        CromossomoBinario filho1 = null;
        CromossomoBinario filho2 = null;
        
        try {
           filho1 = new CromossomoBinario( aux1 );
           filho2 = new CromossomoBinario( aux2 ); 
           
           filho1.setFuncao( pai1.getFuncao() );
           filho2.setFuncao( pai2.getFuncao() );
        } catch (Exception e) {
            logger.debug("======> Exceção no CrossOver1Ponto: " + e.getMessage() );            
        }	   	   
        
        CromossomoBinario[] retorno = new CromossomoBinario[ 2 ];
        retorno[0] = filho1 ;
        retorno[1] = filho2 ;
        
        return retorno ;                
    }

}
