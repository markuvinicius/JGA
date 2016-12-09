/*
 * @class = RoletaViciada
 * @file  = RoletaViciada.java
 * @superclass = Selecao.java
 * @descrição = Implementaação da classe seleção, especilizada na seleção nos casos em que os fitness podem ser negativos
 * @author    = Marku Vinícius 
 */
package com.marku.jga.operadores;

import com.marku.jga.model.CromossomoAbstrato;
import com.marku.jga.model.CromossomoBinario;


/**
 *
 * @author Marku
 */
public class RoletaViciada extends Selecao {

    /**
     * Implementação concreta do método de seleção
     * @return indice do individuo selecionado na população
     * @author  Marku Vinícius
     */
    @Override
    public int selecionar() { 
        int i;
	double aux = 0;
	
	double limite = Math.random() * this.calculaSomaAvaliacoes();	
        
        //itera a população e seleciona o individuo conforme o fitness Normalizado
	for( i = 0 ; ( ( i < this.populacao.size() ) && ( aux < limite ) ) ;++i ) {
           CromossomoAbstrato cr = (CromossomoAbstrato)populacao.get(i);
	   aux+= cr.getFitnessNormalizado();
	}	
	
        i--;	               
	return i;
    }
    
      
    /**
     * Método sobrescrito para calcular a soma das avaliações normalizadas
     * @return valor da soma das avaliações normalizadas
     */
    @Override
    protected final double calculaSomaAvaliacoes() {
	int i;
	this.somaAvaliacoes = 0;        
        
	for(i=0;i<populacao.size();++i) {
		this.somaAvaliacoes+=((CromossomoAbstrato) populacao.get(i)).getFitnessNormalizado();
	}
                
	return this.somaAvaliacoes ;
   }
    
    
}
