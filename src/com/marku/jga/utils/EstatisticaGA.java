/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marku.jga.utils;

import com.marku.jga.model.Ambiente;
import com.marku.jga.model.CromossomoAbstrato;
import com.marku.jga.model.CromossomoBinario;
import java.util.ArrayList;

/**
 *
 * @author Marku
 * @name classe responsável por gerar estatísticas do algoritmo genetico
 */
public class EstatisticaGA {
    private ArrayList<Ponto> pontos;   
          
    public EstatisticaGA(){
        pontos = new ArrayList<Ponto>();
    }        
    
    public ArrayList<Ponto> getEstatistica(){
        return pontos;
    }
    
    public void clearEstatistica(){
        this.pontos.clear();
    }
    
    private double getMelhorFitness(ArrayList populacao){        
        //Como o array já está ordenado de forma descresente do fitness, o indice do melhor elemento é sempre 0;
        CromossomoAbstrato c = (CromossomoAbstrato)populacao.get(0);
        return c.getFitness();


    }
    
    private double getPiorFitness(ArrayList populacao){
        
        //Como o array já está ordenado de forma descresente do fitness, o indice do pior elemento é o último;
        CromossomoAbstrato c = (CromossomoAbstrato)populacao.get( populacao.size()-1 );
        
        return c.getFitness();

    }
    
    private double getDesvio( ArrayList populacao , double media ){
        double retorno = 0;
        for( int i = 0 ; i < populacao.size() ; i++ ){
            CromossomoAbstrato cr = (CromossomoAbstrato)populacao.get(i);
            retorno += Math.pow( cr.getFitness() - media , 2 );
        }
        
        retorno = retorno / ( populacao.size() - 1 );
        retorno = Math.sqrt( retorno );
        return -retorno;
    }
    
    public void addEstatistica( Ambiente ambiente ){        
        
        ArrayList cr = (ArrayList)ambiente.getPopulacao();               
        
        double mediaFitness             = ambiente.getSomaAvaliacoes() / ambiente.getPopulacao().size() ;
        double melhorFitness            = getMelhorFitness( ambiente.getPopulacao() );
        double desvio                   = getDesvio( ambiente.getPopulacao() , mediaFitness );
                
        Ponto ponto = new Ponto( mediaFitness , melhorFitness , desvio );
        this.pontos.add(ponto);
    }
    
    public class Ponto{
        public double mediaFitness;
        public double melhorFitness;
        public double desvioPadrao;
        
        public Ponto( double media , double melhor , double desvio ){            
            this.mediaFitness = media;
            this.melhorFitness = melhor;
            this.desvioPadrao = desvio;
        }
    }
    
}
