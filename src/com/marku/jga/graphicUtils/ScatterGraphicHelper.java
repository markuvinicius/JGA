/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marku.jga.graphicUtils;


import com.marku.jga.utils.EstatisticaGA;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Marku
 */
public class ScatterGraphicHelper extends GraphicHelper {
    
    private static ScatterGraphicHelper instance;
    
    public static ScatterGraphicHelper getInstance(){
        if ( instance == null ){
            instance = new ScatterGraphicHelper();            
        }
        
        return instance;
    }
  
    @Override
    public void drawGraphic() {
        
        if ( this.dataset == null ){
            throw new UnsupportedOperationException("Dataset inválido."); 
        }else{
            XYSeriesCollection xDataSet = (XYSeriesCollection)this.dataset;
            
            /*
            this.chart =  ChartFactory.createScatterPlot(
                                                            "Evolução",                 // chart title
                                                            "Gerações",                 // domain axis label
                                                            "Fitness",                  // range axis label
                                                            xDataSet,                   // data
                                                            PlotOrientation.VERTICAL,   // orientation
                                                            true,                       // include legend
                                                            true,                       // tooltips
                                                            true                        // urls
                                                        );
            */
            
            this.chart =  ChartFactory.createXYLineChart(
                                                            "Evolução",                 // chart title
                                                            "Gerações",                 // domain axis label
                                                            "Fitness",                  // range axis label
                                                            xDataSet,                   // data
                                                            PlotOrientation.VERTICAL,   // orientation
                                                            true,                       // include legend
                                                            true,                       // tooltips
                                                            true                        // urls
                                                        );
            
            
            ChartPanel chartPanel = new ChartPanel(chart);
            this.configuraTela(chartPanel);                        
        }        
    }
    
    private void configuraTela( ChartPanel chart ){
        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(this.componente);       
        this.componente.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(chart)
                .addContainerGap(5, Short.MAX_VALUE))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(chart)
                .addContainerGap(5, Short.MAX_VALUE))
        ); 
        
        this.componente.updateUI();
        
    }

    @Override
    public void createData( EstatisticaGA estatistica ) {
        
        XYSeriesCollection data = new XYSeriesCollection();        
        
        XYSeries med  = new XYSeries( "Média Fitness" );
        XYSeries mel  = new XYSeries( "Melhor Fitness" );
        XYSeries desv = new XYSeries( "Desvio" );
        
        for ( int i = 0 ; i < estatistica.getEstatistica().size() ; i++ ){
            //adiciona série com o número da geração e a média do Fitness
            med.add( i , estatistica.getEstatistica().get(i).mediaFitness  );
            //adiciona série com o número da geração e o melhor fitness
            mel.add( i , estatistica.getEstatistica().get(i).melhorFitness );
            //adiciona série com o número da geração e o desvio padrão
            desv.add( i , estatistica.getEstatistica().get(i).desvioPadrao );
        }
            
        data.addSeries(med);
        data.addSeries(mel);
        data.addSeries(desv);
        
        this.dataset = data;
    }
    
}
