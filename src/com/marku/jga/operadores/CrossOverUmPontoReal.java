
package com.marku.jga.operadores;

import com.marku.jga.model.CromossomoAbstrato;
import com.marku.jga.model.CromossomoReal;
import java.util.ArrayList;

/**
 *
 * @author Marku
 */
public class CrossOverUmPontoReal extends CrossOver {

    @Override
    public CromossomoAbstrato[] crossOver(CromossomoAbstrato pai1, CromossomoAbstrato pai2) {
        //declara as variaveis temporarias para armazenar os novos genes
        ArrayList aux1 = new ArrayList();
        ArrayList aux2 = new ArrayList();
        //parse dos parâmetros para cromossomos do tipo Real
        CromossomoReal cPai1 = (CromossomoReal)pai1;
        CromossomoReal cPai2 = (CromossomoReal)pai2;
        //obtém os genótipos dos 2 pais envolvidos no crossover
        ArrayList gene1 = (ArrayList)cPai1.getGene();
        ArrayList gene2 = (ArrayList)cPai2.getGene();
        //define o ponto de corte no array
        int pontoCorte = (new Double(java.lang.Math.random() * gene1.size() ) ).intValue() ;
        
        for ( int i = 0 ; i < gene1.size() ; i++ ){
            if ( i < pontoCorte ){ 
                aux1.add( gene1.get( i ) );
                aux2.add( gene2.get( i ) );
            }else{
                aux1.add( gene2.get( i ) );
                aux2.add( gene1.get( i ) );
            }
        }
           
        //declara as referências dos cromossomos de saída
        CromossomoReal filho1 = null;
        CromossomoReal filho2 = null;
        
        try {
           filho1 = new CromossomoReal( aux1 );
           filho2 = new CromossomoReal( aux2 ); 
           
           filho1.setFuncao( pai1.getFuncao() );
           filho2.setFuncao( pai2.getFuncao() );
        } catch (Exception e) {
            logger.debug("======> Exceção no CrossOver1Ponto: " + e.getMessage() );            
        }	   	   
        
        CromossomoReal[] retorno = new CromossomoReal[ 2 ];
        retorno[0] = filho1 ;
        retorno[1] = filho2 ;
        
        return retorno ;                
    }

}
