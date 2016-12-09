

package com.marku.jga.functions;

import com.marku.jga.model.CromossomoAbstrato;
import com.marku.jga.model.CromossomoReal;
import java.util.ArrayList;

/**
 *
 * @author Marku
 */
public class UnimodalF1 extends Funcao {
    
    private final double EXPOENTE = 2.0;

    public UnimodalF1(double limiteMin, double limiteMax, int tamDimensao) {
        super(limiteMin, limiteMax, tamDimensao);
        
        //fator = é igual a um para números reais visto que estamos utilizando todo o alfabeto
        this.fator  = 1;
        //calcula o tamanho do espaço de busca
        this.tamanhoDimensao = this.dimensao.maxBound - this.dimensao.minBound; 
    }

    /**
     * Contrutor padrão da função
     * 
     * @param limiteMin   limite mínimo de operação da função
     * @param limiteMax   limite máximo de operação da função 
     * @param tamDimensao parametro não utilizado
     */
    
    

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
            retorno += Math.pow( val , EXPOENTE );
        }
        
        return retorno;        
    }

}
