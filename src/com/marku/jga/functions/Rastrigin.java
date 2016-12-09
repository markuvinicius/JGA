/*
 * @class = Rastrigin
 * @file  = Rastrigin.java
 * @superclass = Funcao.java
 * @descrição = Implementaação da classe Função, especilizada para avaliação da função Rastrigin                
 * @author    = Marku Vinícius 
 */
package com.marku.jga.functions;

import com.marku.jga.model.CromossomoAbstrato;

/**
 *
 * @author Marku
 */
public class Rastrigin extends Funcao {

    /**
     * Construtor concreto da classe Rastrigin
     * 
     * @param limiteMin     limite mínimo da função
     * @param limiteMax     limite máximo da função
     * @param tamDimensao   quantidade de bits utilizado para a representação        
     */
    public Rastrigin(double limiteMin, double limiteMax , int tamanhoDimensao) {
        super(limiteMin, limiteMax, tamanhoDimensao);
    }
    
    
    /**
     * Implementação do método de avaliação para a função Rastrigin:
     *  A função Rastrigin possui 2 dimensões, por este motivo a função fitness está preparada para lidar com uma 
     *  codificação que represente as duas variáveis em um string de caracteres
     * 
     * @param gene  gene a ser avaliado;
     * @return valor da avaliação
     */
    @Override
    public double avaliar(CromossomoAbstrato cromossomo) {
        //converte o objeto gene em String
        String gene = (String)cromossomo.getGene();
        //obtém a divisão das duas variáveis 
        int separador = ( gene.length() / 2 ) - 1;
        //obtém o valor real das duas variáveis
        double x = this.converteBooleano(gene , 0  , separador );
        double y = this.converteBooleano(gene , separador+1 , gene.length()-1 );
      
        //normaliza o valor para dentro da faixa permitida    
        x = x * this.fator + this.getDimensao().minBound ;
        y = y * this.fator + this.getDimensao().minBound ;
        
        //calcula e retorna o fitness
        double zx = ( Math.pow( x , 2 ) - ( 10 * Math.cos( Math.PI * 2 * x ) ) + 10 );
        double zy = ( Math.pow( y , 2 ) - ( 10 * Math.cos( Math.PI * 2 * y ) ) + 10 );
        
        return -( zx + zy ) ;    
    }
    
    
    
}
