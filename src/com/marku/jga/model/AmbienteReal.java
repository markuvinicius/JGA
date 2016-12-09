
package com.marku.jga.model;

import com.marku.jga.functions.Funcao;
import com.marku.jga.operadores.CrossOver;
import com.marku.jga.operadores.Mutacao;
import com.marku.jga.operadores.Selecao;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.log4j.Logger;

/**
 *
 * @author Marku
 */
public class AmbienteReal extends Ambiente{
    
    //declaração da classe que será utilizada para os logs em arquivo (local em ../JGA/genetic.log  
   private static final Logger LOGGER = Logger.getLogger( AmbienteReal.class ); 
   
   /*
    Os atributos internos da classe foram declarados como protected para que sejam acessíveis pelas subclasses.
   */
   
   //representação da população
   protected ArrayList<CromossomoReal> populacao;
   //representação de uma nova população (após a passagem de uma geração e antes da execução do método de população
   protected ArrayList<CromossomoReal> nova_populacao;
   
   public AmbienteReal(
                            int num_geracoes, 
                            int tam_populacao, 
                            double prob_mut, 
                            Selecao sel, 
                            CrossOver cross, 
                            Mutacao mut, 
                            Funcao func, 
                            int tamCromoss ,
                            boolean elitismo
                        ) {
        super(num_geracoes, tam_populacao, prob_mut, sel, cross, mut, func, tamCromoss , elitismo);
    }
   
   //inicializa uma nova populacão     
   @Override
   protected void inicializaPopulacao() {
        LOGGER.debug(" ========[Iniciando População]======== ");
        
	int i;
	this.populacao = new ArrayList();                
	
	for( i = 0 ; i < this.tamanho_populacao ; ++i ) {
           
           //cria um novo cromossomo sem gene definido
           CromossomoReal c = new CromossomoReal();
           //seta no cromossomo a função de avaliação que será utilizada, junto com as dimensões da mesma
           c.setFuncao(funcao);
           //inicia o cromossomo com valores aleatórios para cada posição do gene
           c.iniciaCromossomo( this.tamanhoCromossomo );
           //adiciona o cromossomo criado na população
	   this.populacao.add( c ); 
           
           LOGGER.debug("[Elemento "+ i +"] - " + c.toString() );           
	}
        
        LOGGER.debug(" ========[Populacao Iniciada]======== ");
   }
   
    /**
     * 
     * @return população
     */
    @Override
    public ArrayList getPopulacao(){
        return this.populacao;
    }
    
    @Override
    public ArrayList getNovaPopulacao(){
        return this.nova_populacao;
    }
    
    @Override
    public void setNovaPopulacao( ArrayList novaPopulacao ){
        this.nova_populacao = novaPopulacao;
    }
    
    @Override
    public Selecao getSelecao(){
        return this.selecao;
    }
    

}
