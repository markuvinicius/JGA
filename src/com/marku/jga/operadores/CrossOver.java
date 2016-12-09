/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marku.jga.operadores;

import com.marku.jga.model.CromossomoAbstrato;
import org.apache.log4j.Logger;

/**
 *
 * @author Marku
 */
public abstract class CrossOver {
    
    protected static Logger logger = Logger.getLogger( CrossOver.class );
    
    public abstract CromossomoAbstrato[] crossOver( CromossomoAbstrato pai1 , CromossomoAbstrato pai2 );
    
}
