/*
 * Copyright (c) 2008, the JUNG Project and the Regents of the University of
 * California.  All rights reserved.
 * 
 * This software is open-source under the BSD license; see either "license.txt"
 * or http://jung.sourceforge.net/license.txt for a description.
 */
package edu.uci.ics.jung.samples;
 
import edu.uci.ics.jung.algorithms.layout.AbstractLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.graph.util.TestGraphs;
import edu.uci.ics.jung.io.PajekNetReader;
import edu.uci.ics.jung.visualization.VisualizationViewer;
 



import edu.uci.ics.jung.visualization.decorators.PickableVertexPaintTransformer;

import org.apache.commons.collections15.FactoryUtils;
 




import org.apache.commons.collections15.Transformer;

import java.awt.Color;
import java.awt.Paint;
import java.io.IOException;
 






import javax.swing.JFrame;
 
/**
 * A class that shows the minimal work necessary to load and visualize a graph.
 */
public class SimpleGraphDraw extends JFrame
{
 
    public static void main(String[] args) throws IOException 
    {
        JFrame jf = new JFrame();
        //Graph g = getGraph();
        Graph g = TestGraphs.getOneComponentGraph();
        VisualizationViewer vv = new VisualizationViewer(new FRLayout(g));
        //VisualizationViewer vv = new VisualizationViewer(new KKLayout(g));
        //VisualizationViewer s = new VisualizationViewer
        //vv.getRenderContext().
        //vv.getRenderContext().setVertexFillPaintTransformer(new PickableVertexPaintTransformer<String>(vv.getPickedVertexState(), 
        //a        Color.red, Color.yellow));
//        Transformer<Integer,Paint> vertexColor = new Transformer<Integer,Paint>() 
//        public Paint transform(Integer i) {
//          if(i == 1) return Color.GREEN;
//          return Color.RED;
//        }
//        Transformer<String,Paint> vertexColor = new Transformer<String,Paint>() {
//
//        //Transformer<Integer,Paint> vertexColor = new Transformer<Integer,Paint>() {
//          public Paint transform(String i) {
//              //if(i == "1" || i == "10" )
//              return Color.GREEN;
//              //return Color.RED;
//          }
//      };
      //vv.getRenderContext().setVertexFillPaintTransformer(vertexColor);

        jf.getContentPane().add(vv);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.pack();
        jf.setVisible(true);
        int sss;
        for(sss = 0; sss < 20; sss++)
        {
          Transformer<String,Paint> vertexColor = new Transformer<String,Paint>() {
            // int ss = sss;
            //Transformer<Integer,Paint> vertexColor = new Transformer<Integer,Paint>() {
              public Paint transform(String i) {
                if(i.charAt(0) == '5')

                  return Color.GREEN;
                  //return Color.RED;
                  return null;
              }
             
          };
          vv.getRenderContext().setVertexFillPaintTransformer(vertexColor);
          jf.repaint();
        }
        }
    /**
     * Generates a graph: in this case, reads it from the file
     * "samples/datasetsgraph/simple.net"
     * @return A sample undirected graph
     */
    public static Graph getGraph() throws IOException 
    {
        PajekNetReader pnr = new PajekNetReader(FactoryUtils.instantiateFactory(Object.class));
        Graph g = new UndirectedSparseGraph();
         //repaint();
        pnr.load("src/main/resources/datasets/simple.net", g);
        return g;
    }
}