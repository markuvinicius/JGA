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
import com.marku.jga.model.CromossomoReal;
import static com.marku.jga.operadores.CrossOver.logger;
import java.util.ArrayList;

/**
 *
 * @author Marku
 */
public class CrossOverDoisPontosReal extends CrossOver {

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
        //define os dois pontos de corte na lista               
        int pontoCorte1 = ( new Double( Math.random() * ( gene1.size() -1 ) ) ).intValue();
        int pontoCorte2 = ( new Double( Math.random() * ( gene1.size() - ( pontoCorte1 + 1 ) ) ) ).intValue();
        pontoCorte2 = pontoCorte2 + pontoCorte1;
        
        
        //monta o gene do primeiro filho
        //aux1  = pai1.getGene().toString().substring( 0 , pontoCorte1);
        //aux1 += pai2.getGene().toString().substring( pontoCorte1 , pontoCorte2 );
        //aux1 += pai1.getGene().toString().substring( pontoCorte2 , pai1.getGene().toString().length() );
        
        //monta o gene do segundo filho
        //aux2  = pai2.getGene().toString().substring( 0 , pontoCorte1);
        //aux2 += pai1.getGene().toString().substring( pontoCorte1 , pontoCorte2 );
        //aux2 += pai2.getGene().toString().substring( pontoCorte2 , pai2.getGene().toString().length() );
        
        for ( int i = 0 ; i < gene1.size() ; i++ ){
            if ( i < pontoCorte1 ){ 
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
