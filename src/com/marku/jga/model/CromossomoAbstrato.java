
package com.marku.jga.model;

import com.marku.jga.functions.Funcao;

/**
 *
 * @author Marku
 */
public abstract class CromossomoAbstrato implements Comparable {
    /*
    Obs: Os atributos locais da classe cromossomo foram declarados como protected para 
    serem acessíveis pelas subclasses.
    */
    
    //abstração para a codificação genética (genótipo) do cromossomo
    protected Object gene;
    
    //tamanho do gene
    protected int tamanhoGene;
    
    //representação do fitness real do indivíduo
    protected double fitness;
    
    //representação do fitness normalzado do indivíduo devido à utilização do método de seleção por Roleta
    protected double fitnessNormalizado;
    
    //abstração da implementação da função fitness.
    protected Funcao funcao;
    
    /**
     * Inicia o cromossomo de tamanho definido com um genótipo aleatório
     * Implementação baseada no algoritmo do Ricardo Linden
     * 
     * @param tamanhoGene   Tamanho absoluto do cromossomo a ser criado (Quantidade de bits)
     */
    protected abstract void iniciaCromossomo(int tamanhoGene);

    /*
        Construtores da classe Cromossomo
    */
    //Construtor padrão. inicia um cromossomo padrão com tamanho 20.
    public CromossomoAbstrato(){
        super();      
    }
    
    /**
     * @param tamanhoGene   Tamanho absoluto do cromossomo a ser criado (Quantidade de bits)
     */   
    public CromossomoAbstrato(int tamanhoGene){       
        super();
        this.tamanhoGene = tamanhoGene;
        this.iniciaCromossomo( tamanhoGene );
    }
    
    /**
     * Inicia um cromossomo com um genótipo pré-definido
     * 
     * @param novoGene   codificação do novo gene a ser criado
     */
    public CromossomoAbstrato( Object novoGene ){
        super();
        this.gene = novoGene;
    }
    
    
    /**
     * Avalia o Fitness do indivíduo
     * A implementação da função de avaliação foi abstraída da classe indivíduo para
     * viabilizar a reutilização de código
     * 
     * @return valor do fitness do indivíduo
     */    
    public double avaliaFitness(){
        this.fitness = funcao.avaliar( this );
        return this.fitness;
    }
       
    
    /* 
        Métodos de acesso (getters and setters)
    */
    
    /**
     * @return the gene
     */
    public Object getGene() {
        return gene;
    }

    /**
     * @param gene the gene to set
     */
    public void setGene(Object gene) {
        this.gene = gene;
    }

    /**
     * @return the fitness
     */
    public double getFitness() {
        return fitness;
    }

    /**
     * @param fitness the fitness to set
     */
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    /**
     * @return the funcao
     */
    public Funcao getFuncao() {
        return funcao;
    }

    /**
     * @param funcao the funcao to set
     */
    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }
    
    /**
     * @return fitnessNormalizado
     */
    public double getFitnessNormalizado() {
        return this.fitnessNormalizado;
    }

    /**
     * @param fitness
     */
    public void setFitnessNormalizado(double fitnessNormalizado) {
        this.fitnessNormalizado = fitnessNormalizado;
    }
    
    
        
}
