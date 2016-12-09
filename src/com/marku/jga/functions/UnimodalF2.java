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
public class UnimodalF2 extends Funcao{
    
    private final double CONSTANTE = 0.5;
    private final double EXPOENTE  = 2.0;
    
    public UnimodalF2(double limiteMin, double limiteMax, int tamDimensao) {
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
            //acumula o valor da soma 
            retorno += Math.pow( Math.abs( val + CONSTANTE ) , EXPOENTE ) ;            
        }
        
        return retorno;        
    }
    

}
