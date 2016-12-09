/*
 * @class = Seleção
 * @file  = Seleção.java
 * @descrição = Abstração da função de seleção dos cromossomos para aplicação dos operadores.
                Implementação visa permitir o reuso do algoritmo para otimização de várias funções
 * @author    = Marku Vinícius 
 */
package com.marku.jga.operadores;

import com.marku.jga.model.CromossomoBinario;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author Marku
 */
public abstract class Selecao {
    
    //referência do logger para registro em arquivo texto
    protected static Logger logger = Logger.getLogger( Selecao.class );
    
    //população da qual um individuo será selecionado
    protected ArrayList<CromossomoBinario> populacao;
    
    //soma das avaliações
    protected double somaAvaliacoes;
    
    /**
     * Método abstrato (a ser implementado nas subclasses) que será responsável por selecionad os individuos para aplicação dos operadores
     * 
     * @return retorna o índice do indivíduo selecionado
     */
    public abstract int selecionar();
    
    /**
     * Construtor padrão
     */
    public Selecao(){
        super();
    }
        
    /**
     * função que calcula a soma das avaliações 
     * 
     * @return soma das avaliações
     */
    protected double calculaSomaAvaliacoes() {
	
	this.somaAvaliacoes=0;
        
	for( int i = 0 ; i < populacao.size() ; ++i) {  
            this.somaAvaliacoes+=((CromossomoBinario) populacao.get(i)).getFitness();
	}
        
	return this.somaAvaliacoes ;
    }
    
    /**
     * Seta a população que será considerada na seleção
     * @param populacao 
     */
    public void setPopulacao( ArrayList<CromossomoBinario> populacao ){
        this.populacao = populacao;
    }
    
}
