/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marku.jga.graphicUtils;


import com.marku.jga.utils.EstatisticaGA;
import javax.swing.JComponent;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.AbstractDataset;

/**
 *
 * @author Marku
 */
public abstract class GraphicHelper {
    
    protected JComponent componente;
    protected AbstractDataset dataset;
    protected JFreeChart chart;                
        
    public void setComponente( JComponent compo ){
        this.componente = compo;
    }
    
    public abstract void drawGraphic();
    
    public abstract void createData( EstatisticaGA estatistica );
    
}
