/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marku.jga.operadores;

import com.marku.jga.model.CromossomoBinario;
import java.util.ArrayList;

/**
 *
 * @author Marku
 */
public class Roleta extends Selecao {

   
    
    public Roleta(){
        super();
    }
    
   
    @Override
    public int selecionar(){        
        
        int i;
	double aux=0;
	
	double limite = Math.random() * this.calculaSomaAvaliacoes();	
        
	for(i=0;( (i < this.populacao.size())&&(aux<limite) );++i) {
	   aux+=((CromossomoBinario) populacao.get(i)).getFitness();
	}	
	
        i--;	       
        
	return(i);
    }

    
    
}
