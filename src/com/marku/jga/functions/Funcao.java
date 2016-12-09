/*
 * @class = Funcao
 * @file  = Função.java
 * @descrição = Abstração da função de avaliacão de aptidão (fitness) dos cromossomos para 
                permitir o reuso do algoritmo para otimização de várias funções
 * @author    = Marku Vinícius 
 */
package com.marku.jga.functions;

import com.marku.jga.model.CromossomoAbstrato;

/**
 *
 * @author Marku
 */

public abstract class Funcao {
    
    //subclasse que representa os limites (máximo e mínimo) da função a ser otimizada
    protected DimensaoFuncao dimensao;
    //fator de ajuste para normalização dentro da faixa do problema.
    protected double fator;
    //tamanho (qtd de bits) utilizado para cada variável do problema.
    protected double tamanhoDimensao ;
    //fator de ajuste para normalização do fitness (para uso da roleta)
    protected double fatorPositivador;
    
    /**
     * Método abstrato a ser implementado nas subclasses
     * 
     * @param gene  gene a ser avaliado (via subclasses)
     * @return      valor da avaliação
     */
    //public abstract double avaliar( String gene );  
    public abstract double avaliar( CromossomoAbstrato cromossomo );  
    
    /**
     * Subclasse para abstração do tamanho do espaço de busca da função
     * 
     * @author Marku Vinícius
     */     
    public class DimensaoFuncao
    {
        public double minBound, maxBound;

        private DimensaoFuncao(double min, double max)
        {
            minBound    =   min;
            maxBound    =   max;
        }
    }
       
    /**
     * Construtor totalmente qualificado para a função
     * 
     * @param limiteMin     limite mínimo da função
     * @param limiteMax     limite máximo da função
     * @param tamDimensao   quantidade de bits utilizado para a representação     
     */
    protected Funcao( double limiteMin , double limiteMax , int tamDimensao ){
        super();
        //cria a faixa de limite para a dimensão
        dimensao = new DimensaoFuncao(limiteMin, limiteMax);
               
        //seta o tamanho da dimensão de cada variável
        this.tamanhoDimensao = tamDimensao ;
        
        //calcula o fator de ajuste para a faixa limite permitido
        this.fator  = ( ( limiteMax - limiteMin ) / ( Math.pow( 2 , this.tamanhoDimensao ) - 1 ) );        
        //calcula o tamanho do espaço de busca
        this.tamanhoDimensao = this.dimensao.maxBound - this.dimensao.minBound;   
        
        this.fatorPositivador = Math.abs( this.dimensao.maxBound ) / ( this.dimensao.maxBound - this.dimensao.minBound );
    }   
    
    /**
     * Função do tipo protected (acessível para as subclasses) que converte um número binário em real
     * 
     * @param gene      genótipo a ser avaliado
     * @param inicio    posição de início do corte no gene
     * @param fim       posição de fim do corte no gene
     * @return          valor real representado 
     * @author          Ricardo Linden
     */    
    public float converteBooleano(String gene , int inicio , int fim ) {
        int i;
        float aux = 0;
        
        String s = gene;
        
        for( i = inicio ; i <= fim ; ++i ) {
             aux *= 2;
             
             if (s.substring(i,i+1).equals("1")) {
                aux+=1;
             }
        }
        return(aux);
    }
    
    /*
        Métodos de acesso (getters and setters)
    */
    
    
    public double getFatorPositivador(){
        return this.fatorPositivador;
    }
    
    public DimensaoFuncao getDimensao(){
        return this.dimensao;
    }
    
    public double getFator(){
        return this.fator;
    }
    
    public double getTamanhoDimensao(){
        return this.tamanhoDimensao;
    }
    
    
}
