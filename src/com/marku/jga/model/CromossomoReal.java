

package com.marku.jga.model;

import java.util.ArrayList;

/**
 *
 * @author Marku
 */
public class CromossomoReal extends CromossomoAbstrato{

    public CromossomoReal(){
        super();   
        this.gene = new ArrayList();
    }
    
    
   /**
     * Inicia um cromossomo com um genótipo pré-definido
     * 
     * @param novoGene   codificação do novo gene a ser criado
     */
    public CromossomoReal( ArrayList novoGene ){
        super();
        this.gene = novoGene;
        this.tamanhoGene = novoGene.size();
    }

    CromossomoReal(int tamanhoCromossomo) {
        super();
        this.iniciaCromossomo( tamanhoCromossomo );
    }
    
    @Override
    public void iniciaCromossomo(int tamanhoGene) {                                        
        //gera números aleatórios dentro do espaço de busca para cada posição do gene
        for( int i = 0 ; i < tamanhoGene ; ++i ) {
            ((ArrayList)this.gene).add( this.funcao.getTamanhoDimensao() * Math.random() ) ;           
        }
        
        this.tamanhoGene = tamanhoGene;
    }
    
    /**
     * 
     * @param o objeto Cromossomo que será usado como critério de comparação
     * @return retorna 0 se o fitness for igual. 1 se for maior e -1 se for menor, baseado no fitness real
     */
    @Override
    public int compareTo(Object o) {
        int retorno=-1;
        CromossomoAbstrato aux=(CromossomoAbstrato) o;
        if ( this.fitnessNormalizado <  aux.fitnessNormalizado ) {retorno=1;}
        if ( this.fitnessNormalizado == aux.fitnessNormalizado ) {retorno=0;}
        return(retorno);
    }
    
    @Override
    public String toString(){        
        String retorno = "Fitness: " + this.getFitness() + " - Fitness Normalizado: " + this.getFitnessNormalizado() ;
        
        return retorno;
        
        
    }
    
    

}
