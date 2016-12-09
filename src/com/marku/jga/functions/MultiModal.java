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

package com.marku.jga.functions;

import com.marku.jga.model.CromossomoAbstrato;
import com.marku.jga.model.CromossomoReal;
import java.util.ArrayList;

/**
 *
 * @author Marku
 */
public class MultiModal extends Funcao {
    
    public MultiModal(double limiteMin, double limiteMax, int tamDimensao) {
        super(limiteMin, limiteMax, tamDimensao);
        
        //fator = é igual a um para números reais visto que estamos utilizando todo o alfabeto
        this.fator  = 1;
        //calcula o tamanho do espaço de busca
        this.tamanhoDimensao = this.dimensao.maxBound - this.dimensao.minBound; 
    }

    @Override
    public double avaliar(CromossomoAbstrato cromossomo) {
        double retorno = 0;
        CromossomoReal cr = (CromossomoReal)cromossomo;
        ArrayList gene = (ArrayList)cr.getGene();
        
        for ( int i = 0 ; i < gene.size() ; i++ ){
            //obtém o valor real da posição no gene
            double val = (double)gene.get(i);
            //normaliza o valor real do cromossomo para dentro do espaço de busca
            val = val * this.fator + this.getDimensao().minBound ;
            //calcula o valor do seno do módulo do valor armazenado no array
            double sen;
            sen = Math.sin( Math.sqrt( Math.abs( val ) ) );
            //calcula o valor do fit do valor e acumula.
            retorno += -(val*sen);
            
        }
        
        return retorno;   
    }

}
