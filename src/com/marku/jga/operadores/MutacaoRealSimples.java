/*
 * @class = Cromossomo
                           * @file  = Cromossomo.java
                           * @description =
                           * @author = Marku Vinícius da Silva
                          To change this license header, choose License Headers in Project Properties.
 * @class = Cromossomo
                           * @file  = Cromossomo.java
                           * @description =
                           * @author = Marku Vinícius da Silva
                          To change this template file, choose Tools | Templates
 * @class = Cromossomo
                           * @file  = Cromossomo.java
                           * @description =
                           * @author = Marku Vinícius da Silva
                          and open the template in the editor.
 */

package com.marku.jga.operadores;

import com.marku.jga.model.CromossomoAbstrato;
import com.marku.jga.model.CromossomoBinario;
import static com.marku.jga.operadores.Mutacao.logger;
import java.util.ArrayList;

/**
 *
 * @author Marku
 */
public class MutacaoRealSimples extends Mutacao {

    /**
     * Mutação simples: Altera o valor de uma posição do gene do cromossomo de acordo com a probabilidade de mutação
     * @param cromossomo                cromossomo que será mutado
     * @param probabilidadeMutacao      probabilidade de mutação
     * @return                          cromossomo mutado.
     */
    @Override
    public CromossomoAbstrato mutacao(CromossomoAbstrato cromossomo, double probabilidadeMutacao) {
        
        ArrayList gene = (ArrayList)cromossomo.getGene();
                              
        for( int i = 0 ; i < gene.size() ; i++ ){
            
            if ( java.lang.Math.random() < probabilidadeMutacao ) {		   
                
                logger.debug( " ===>[ Realizou Mutação ]<=== " );
                
                gene.set( i , Math.random() * cromossomo.getFuncao().getTamanhoDimensao() );
                                
            }
        }
        
        return cromossomo;
    }

}
