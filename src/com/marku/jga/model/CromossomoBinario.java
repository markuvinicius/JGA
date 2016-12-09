/*
 * @class = CromossomoBinario
 * @file  = CromossomoBinario.java
 * @descrição = Classe responsável por implementar o indivíduo no GA (CromossomoBinario). Implementação baseada
                na abstração proposta por Ricardo Linden (Algoritmos Genéticos - 3º Edição).
                Adaptações e melhorias implementadas por Marku Vinícius para reúso na disciplina de 
                Inteligência Computacional - PPGSI - EACH USP - 2016
 */
package com.marku.jga.model;

/**
 *
 * @author Marku
 */
public class CromossomoBinario extends CromossomoAbstrato {
                
    /*
        Construtores da classe CromossomoBinario
    */
    
    //Construtor padrão. inicia um cromossomo padrão com tamanho 20.
    public CromossomoBinario(){
        super();
        this.iniciaCromossomo(20);
    }
    
    /**
     * @param tamanhoGene   Tamanho absoluto do cromossomo a ser criado (Quantidade de bits)
     */   
    public CromossomoBinario(int tamanhoGene){       
        super();
        this.tamanhoGene = tamanhoGene;
        this.iniciaCromossomo( tamanhoGene );
    }
    
    /**
     * Inicia um cromossomo com um genótipo pré-definido
     * 
     * @param novoGene   codificação do novo gene a ser criado
     */
    public CromossomoBinario( Object novoGene ){
        super();
        this.gene = novoGene;
    }
    /**
     * Inicia o cromossomo de tamanho definido com um genótipo aleatório
     * Implementação baseada no algoritmo do Ricardo Linden
     * 
     * @param tamanhoGene   Tamanho absoluto do cromossomo a ser criado (Quantidade de bits)
     */
    
    @Override
    public void iniciaCromossomo(int tamanhoGene){
        int i;
        this.gene = new String();
        
        for( i = 0 ; i < tamanhoGene ; ++i ) {
           if ( Math.random() < 0.5 ) {
              this.gene = this.gene + "0";
           } else {
              this.gene = this.gene + "1";
           }
        }
    }
    
    
       
    
    public String toString(){
        //converte o genótipo de Object para String
        String geneFormatado = (String)this.gene;
        //obtém a divisão das duas variáveis 
        int separador = ( geneFormatado.length() / 2 )-1;
        //obtém o valor real das duas variáveis
        double x = this.funcao.converteBooleano( geneFormatado , 0  , separador );
        double y = this.funcao.converteBooleano( geneFormatado , separador + 1 , geneFormatado.length()-1 );
        
        //normaliza o valor para dentro da faixa permitida    
        double a = x * this.funcao.getFator() + this.funcao.getDimensao().minBound ;
        double b = y * this.funcao.getFator() + this.funcao.getDimensao().minBound ;
        
        /*
        String out = "|Genótipo| " + geneFormatado + "|" +
                       "|Fenótipo| " + a + " - " + b + "|" +
                       "|Decimal: x = " + x + " - y = " + y + "|" +
                       "|Fitness|" + this.fitness + 
                       "|F. Normalizado:" + this.fitnessNormalizado;
        */
        String out = "|" + this.gene + "|" + this.fitness + "|" + this.fitnessNormalizado;
        
        return out;
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
        
    
}
