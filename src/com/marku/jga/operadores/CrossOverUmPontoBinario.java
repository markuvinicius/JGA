/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marku.jga.operadores;

import com.marku.jga.model.CromossomoAbstrato;
import com.marku.jga.model.CromossomoBinario;

/**
 *
 * @author Marku
 */
public class CrossOverUmPontoBinario extends CrossOver {

    @Override
    public CromossomoAbstrato[] crossOver(CromossomoAbstrato pai1, CromossomoAbstrato pai2) {
       
        String aux1, aux2 ;	   
        
        CromossomoBinario cPai1 = (CromossomoBinario)pai1;
        CromossomoBinario cPai2 = (CromossomoBinario)pai2;
        
        String gene1 = (String)cPai1.getGene();
        String gene2 = (String)cPai2.getGene();
                
                
        int pontoCorte = (new Double(java.lang.Math.random() * gene1.length() ) ).intValue() ;	   
        
        	
        aux1 = gene1.substring(0,pontoCorte) + gene2.substring(pontoCorte,gene2.length() );
        aux2 = gene2.substring(0,pontoCorte) + gene1.substring(pontoCorte,gene1.length() );
        
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
