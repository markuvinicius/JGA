/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marku.jga.operadores;

import com.marku.jga.model.CromossomoBinario;
import com.marku.jga.model.CromossomoAbstrato;


/**
 *
 * @author Marku
 */
public class MutacaoSimples extends Mutacao {

    @Override
    public CromossomoBinario mutacao(CromossomoAbstrato cromossomo, double probabilidadeMutacao) {
        
        CromossomoBinario cromossomoConcreto = (CromossomoBinario)cromossomo;
        String valorGene = (String)cromossomoConcreto.getGene();
                
        int tamanho = valorGene.length();
        
        String aux , inicio , fim ;
        String gene = valorGene;
        
        for( int i = 0 ; i < tamanho ; i++ ){
            
            if ( java.lang.Math.random() < probabilidadeMutacao ) {		   
                aux = valorGene.substring(i,i+1);
                
                logger.debug( " ===>[ Realizou Mutação ]<=== " );
                
                if ( aux.equals( "1" ) ){
                    aux="0";                
                } else{
                    aux="1";                
                }	
                
                inicio = valorGene.substring(0,i);
                fim    = valorGene.substring(i+1,tamanho);		   
                                
                gene = inicio + aux + fim ;                
            }
        }
        
       
        CromossomoBinario c = null;
        try{
            c = new CromossomoBinario( gene );
            c.setFuncao( cromossomo.getFuncao() );
        }catch (Exception e){
            logger.debug( " ===> Exceção ao realizar mutação Simples: " + e.getMessage() );            
        }
        
        return c;
    }
    
}
