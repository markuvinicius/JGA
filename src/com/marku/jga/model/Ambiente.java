/*
 * @class = Ambiente
 * @file  = Ambiente.java
 * @descrição = Classe responsável por implementar o ambiente ao qual os indivíduos estão inseridos para adaptação.
                Implementação baseada na abstração proposta por Ricardo Linden (Algoritmos Genéticos - 3º Edição).
                Adaptações e melhorias implementadas por Marku Vinícius para reúso na disciplina de 
                Inteligência Computacional - PPGSI - EACH USP - 2016
 */
package com.marku.jga.model;

import com.marku.jga.functions.Funcao;
import com.marku.jga.operadores.CrossOver;
import com.marku.jga.operadores.Mutacao;
import com.marku.jga.operadores.Selecao;
import com.marku.jga.utils.EstatisticaGA;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.log4j.Logger;

/**
 *  
 * @author Marku
 */
public class Ambiente {
   
   //declaração da classe que será utilizada para os logs em arquivo (local em ../JGA/genetic.log  
   private static final Logger LOGGER = Logger.getLogger( Ambiente.class ); 
   
   /*
    Os atributos internos da classe foram declarados como protected para que sejam acessíveis pelas subclasses.
   */
   
   //representação da população
   protected ArrayList populacao;
   //representação de uma nova população (após a passagem de uma geração e antes da execução do método de população
   protected ArrayList nova_populacao;
   //soma das avaliações da população
   protected double     somaAvaliacoes;
   //soma normalizada das avaliações da população
   protected double     somaAvaliacoesNormalizada;
   //probabiliadde de mutação (definida pelo usuário)
   protected double     chance_mutacao;   
   //número máximo de gerações, tamanho da população e tamanho absoluto do cromossomo (definidos pelo usuário)
   protected int        numero_geracoes,tamanho_populacao,tamanhoCromossomo ;
   //parâmetro que define a ocorrência (ou não) do elitismo
   protected boolean    isElitismo;
   
   /*
    Comportamentos abstraídos do ambiente para favorecer o reuso de código
   */
   
   //método de seleção que será utilizado (selecionável pelo usuário e implementada no pacote com.marku.jga.operadores ): Roleta, Torneio, ...
   protected Selecao        selecao ;
   //método de crossover que será aplicado (selecionável pelo usuário e implementada no pacote com.marku.jga.operadores): 1 ponto, 2 pontos, uniforme, ...
   protected CrossOver      crossOver;
   //método de mutação que será aplicado (selecionável pelo usuário e implementada no pacote com.marku.jga.operadores)
   protected Mutacao        mutacao;
   //função de aptidão que será aplicada (selecionável pelo usuário e implementada no pacote com.marku.jga.functions)
   protected Funcao         funcao;   
      
   
   /****************/
   /* Construtor */
   /****************/

   
   /**
     * Instancia um ambiente com os parâmetros selecionados pelo usuário
     * 
     * @param num_geracoes      Número máximo de gerações
     * @param tam_populacao     Tamanho da populações
     * @param prob_mut          Probabilidade de Mutação
     * @param sel               Implementação do operador de seleção
     * @param cross             Implementação do operador de crossover
     * @param mut               Implementação do operador de mutação
     * @param func              Implementação da função de avaliação
     * @param tamCromoss        Tamanho absoluto do cromossomo (Quantidade de bits)
     */   
   public Ambiente( int  num_geracoes,
                    int  tam_populacao, 
                    double prob_mut, 
                    Selecao sel, 
                    CrossOver cross, 
                    Mutacao mut, 
                    Funcao func , 
                    int tamCromoss ,
                    boolean elitismo ) {
       
   	this.chance_mutacao     = prob_mut;
   	this.numero_geracoes    = num_geracoes;
   	this.tamanho_populacao  = tam_populacao;
        this.selecao            = sel;
        this.crossOver          = cross;
        this.mutacao            = mut;
        this.funcao             = func;
        this.tamanhoCromossomo  = tamCromoss ;        
        this.isElitismo         = elitismo;
   }

   //inicializa uma nova populacão     
   protected void inicializaPopulacao() {
        LOGGER.debug(" ========[Iniciando População]======== ");
        
	int i;
	this.populacao = new ArrayList();                
	
	for( i = 0 ; i < this.tamanho_populacao ; ++i ) {
           
           //cria um novo cromossomo, com o tamanho definido
           CromossomoBinario c = new CromossomoBinario( this.tamanhoCromossomo );
               
           //seta no cromossomo a função de avaliação que será utilizada
           c.setFuncao(funcao);
           //adiciona o cromossomo na população           
	   this.populacao.add( c ); 
           
           LOGGER.debug("[Elemento "+ i +"] - " + c.toString() );           
	}
        
        LOGGER.debug(" ========[Populacao Iniciada]======== ");
   }
   
   //calcula a soma das avaliações reais e normalizadas da população
   protected double calculaSomaAvaliacoes() {	
        //zera as variáveis de soma
        this.setSomaAvaliacoes(0) ;
        this.setSomaAvaliacoesNormalizada(0);
        
        //itera a população acumulando as avaliações
	for ( int i = 0 ; i < populacao.size() ; ++i ) {
            //soma do fitness "puro" (conforme calculado pela função de avaliação)
            this.setSomaAvaliacoes(this.getSomaAvaliacoes() + ((CromossomoAbstrato) populacao.get(i)).getFitness());            
	}
	return(this.getSomaAvaliacoes());
   }
   
   //loga em arquivo o genótipo, fitnesse e fitness normalizado de toda a população
   /** Grava log em arquivo (caso configurado) da soma das avaliações, soma normalizada, fitness médio e fit. médio normalizado
    * 
    */
   protected void logarElementos(){                 
        //itera toda a população para colocar os valores em log (arquivo genetic.log)
	for(int i = 0 ; i < this.getPopulacao().size() ; ++i ) {
                //Obtem o elemento
		CromossomoAbstrato aux=(CromossomoAbstrato)this.getPopulacao().get(i);
                //loga os dados do elemento
                LOGGER.debug( aux.toString() );
	}        
        LOGGER.info("  -----> A soma das avaliações é              : " + this.getSomaAvaliacoes() );
        LOGGER.info("  -----> A soma das avaliações normalizadas é : " + this.getSomaAvaliacoesNormalizada() );
        LOGGER.info("  -----> O Fitness médio  é                   : " + this.getSomaAvaliacoes() / this.getPopulacao().size() );
        LOGGER.info("  -----> O Fitness médio (normalizado) é      : " + this.getSomaAvaliacoesNormalizada() / this.getPopulacao().size() );
   }
   
   //avalia o fitness (real e normalizado) de toda a população
   protected void avaliaTodos() {
	double somaAvaliacoes = 0;
       
        LOGGER.debug(" ========[Avaliando Todos os Elementos]======== ");
        
        //itera toda a população avaliando os elementos.
	for(int i = 0 ; i < this.getPopulacao().size() ; ++i ) {
                //Obtem o elemento
		CromossomoAbstrato aux=(CromossomoAbstrato)this.getPopulacao().get(i);
                //chama função de avaliação
		somaAvaliacoes += aux.avaliaFitness();          
	}
        
	
        //calcula as somas das avaliacões (real e normalizada)
        this.setSomaAvaliacoes( somaAvaliacoes );
        this.setSomaAvaliacoesNormalizada( this.normalizaFitnessPopulacao() );
        
        //ordena a população de acordo com o fitness        
        Collections.sort( this.getPopulacao() );
        
        //apresenta detalhes da população em arquivo de log        	
        this.logarElementos();
   }
   
   
   protected void Novageracao() {
	this.setNovaPopulacao( new ArrayList() );
        CromossomoAbstrato pai1 , pai2 , filho;
	
        LOGGER.debug(" =====[Calculando Nova Geração]===== " );	
        
        //seta a população a ser utilizada na implementação concreta da classe de seleção
        this.selecao.setPopulacao( this.getPopulacao() );
                
        //alteração para rodar os operadores genéticos um número de vezes igual ao tamanho da população
	for( int i = 0 ;  i < this.getPopulacao().size() ; ++i ){                
                
                //seleciona os pais
		pai1 = (CromossomoAbstrato) getPopulacao().get( this.getSelecao().selecionar() );
		pai2 = (CromossomoAbstrato) getPopulacao().get( this.getSelecao().selecionar() );		
                
                //loga no arquivo quais foram os pais selecionados
                LOGGER.debug("[Pai1 Selecionado]: " + pai1.toString() );	
                LOGGER.debug("[Pai2 Selecionado]: " + pai2.toString() );
                
                //realiza o crossover (gerando 2 filhos novos com o material genético dos dois pais selecionados)
                CromossomoAbstrato[] filhos = (CromossomoAbstrato[])crossOver.crossOver(pai1, pai2);	       
                
                //realiza a mutação nos 2 filhos gerados
		filhos[0] = (CromossomoAbstrato)mutacao.mutacao(filhos[0], chance_mutacao );
                filhos[1] = (CromossomoAbstrato)mutacao.mutacao(filhos[1], chance_mutacao );
                
                //adiciona os dois filhos gerados na nova população
                this.getNovaPopulacao().add( filhos[0] );
                this.getNovaPopulacao().add( filhos[1] );               
	}
   }
  
   
   //módulo de populacao, com controle de elitismo
    protected void moduloPopulacao() {    
        
        int complemento = 0;
        ArrayList aux = new ArrayList();
        
        
        if ( this.isElitismo ){
            //obtém o melhor indivíduo da população atual
            CromossomoAbstrato c = (CromossomoAbstrato)this.getPopulacao().get( this.getElite() );
            
            LOGGER.debug("[Elitismo] - " + c.toString() );
                   
            //adiciona apenas o melhor elemento da população atual na nova população
            aux.add( c );            
            //controle do tamanho da população
            complemento = 1;
        }
                
        //copia todos os individuos para a nova populacao
        for ( int i = 0 ; i < this.getPopulacao().size() - complemento ; i++ ) {
            aux.add( getNovaPopulacao().get(i) );
        }
        
        //atualiza a variável interna
        this.getPopulacao().clear();
        this.getPopulacao().addAll(aux);
    }

    /**
     * 
     * @return retorna o índice do primeiro elemento da população. Como a lista está ordenada pelo fitness (do maior para o menor).
     */    
    private int determinaMelhor() {
        LOGGER.debug(" ======[ Determinando Melhor Elemento]=====");
        
        //Collections.sort(this.populacao);
        return 0;                
   }
    
   protected int getElite(){             
	int ind_melhor = 0;        
        CromossomoAbstrato aux;        
	        
	double aval_melhor  = ((CromossomoAbstrato)this.getPopulacao().get(0)).getFitnessNormalizado();        
        
	for( int i = 1 ; i < this.getPopulacao().size() ; ++i ) {
		aux = (CromossomoAbstrato)this.getPopulacao().get(i);		
                
		if ( aux.getFitnessNormalizado() > aval_melhor ) {
		   aval_melhor = aux.getFitnessNormalizado();
		   ind_melhor  = i;
		}                                
	}
        
        //retorna os índices do melhor fitness e melhor fitness normalizado
        int retorno = ind_melhor ;
 	return retorno;    
   } 
    
   //lógica de execução do algoritmo genético 
   public EstatisticaGA otimizar() {
        //Limpa as estatísticas de execução anteriores para uma nova otimização
        EstatisticaGA estatistica = new EstatisticaGA();
        
	//inicializa a população
	this.inicializaPopulacao();
	
        //percorre o número máximo de gerações conforme configurado.
        //testar implementação para detecção de convergência e parar a execução do algoritmo quando a convergência for alcançada.
	for (int i = 0 ; i < this.numero_geracoes ; ++i ) {	
                
                LOGGER.debug(" ======[ Geração " + i + " ]=====");
		
                //avalia todos os indivíduos da população
		this.avaliaTodos();
                
                //registra estatística de execução
                estatistica.addEstatistica( this );                
                
                //calcula nova geração
		this.Novageracao();
                
                //executa controle de população 
		this.moduloPopulacao();
		
	}
        
        this.avaliaTodos();
        
        //obtém o melhor indivíduo
        int melhor = this.determinaMelhor();        
        
        System.out.println( " ======> O melhor elemento é: " + (CromossomoAbstrato)this.getPopulacao().get(melhor) );
        LOGGER.info(" ======> O melhor elemento é: " + (CromossomoAbstrato)this.getPopulacao().get(melhor));	
        
        
        //retorna as estísticas de execução
        return estatistica;
   } 
       
    /**
     * 
     * @return normaliza o fitness de todos os elementos (para evitar fitness negativos na roleta) e retorna a soma dos fitness normalizados
     */
    protected double normalizaFitnessPopulacao(){
        
        double fitNormalizado = 0 ;
        
        for ( int i = 0 ; i < this.getPopulacao().size() ; i ++ ){
            //Normaliza o fitness da população calculando um fitness relativo baseado na representatividade de cada individuo em relação à população
            CromossomoAbstrato c = (CromossomoAbstrato)this.getPopulacao().get(i);
            double fit = 1 / ( c.getFitness() / this.getSomaAvaliacoes() ) ;
            c.setFitnessNormalizado( fit );
            
            fitNormalizado += fit;
        }
        
        return fitNormalizado;
        
    }
    
    /*
        Métodos de acesso (getters and setters)
    */
    
    
    /**
     * 
     * @param soma 
     */
    
    public void setSomaAvaliacoesNormalizada(double soma){
        this.somaAvaliacoesNormalizada = soma;
    }
    
    /**
     * 
     * @return soma das avaliações normalizadas
     */
    public double getSomaAvaliacoesNormalizada(){
        return this.somaAvaliacoesNormalizada;
    }
    
    /**
     * 
     * @return população
     */
    public ArrayList<CromossomoBinario> getPopulacao(){
        return this.populacao;
    }
        
    /**
     * @return the somaAvaliacoes
     */
    public double getSomaAvaliacoes() {
        return somaAvaliacoes;
    }

    /**
     * @param somaAvaliacoes the somaAvaliacoes to set
     */
    public void setSomaAvaliacoes(double somaAvaliacoes) {
        this.somaAvaliacoes = somaAvaliacoes;
    }
    
    /**
     * 
     * @return definição da utilização do elitismo
     */
    public boolean getIsElitismo(){
        return this.isElitismo;
    }
    
    public void setIsElitismo( boolean elitismo ){
        this.isElitismo = elitismo;
    }
    
    public ArrayList getNovaPopulacao(){
        return this.nova_populacao;
    }
    
    public void setNovaPopulacao( ArrayList novaPopulacao ){
        this.nova_populacao = novaPopulacao;
    }
    
    public Selecao getSelecao(){
        return this.selecao;
    }
    
    public Mutacao getMutacao(){
        return this.mutacao;
    }
    
}
