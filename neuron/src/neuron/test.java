package neuron;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.*;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.AggregateLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.VisualizationViewer;

public class test {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public static void main(String args[]) {
    int pointNum = 30;
    JFrame jf;
    VisualizationViewer vv;

    ArrayList<Neuron> neuronList = new ArrayList<Neuron>();
    // Graph graph = TestGraphs.getOneComponentGraph();
    Graph graph = GenerateNeurons.GenerateRandomNeurons(pointNum, 3);

    AggregateLayout<String, Number> clusteringLayout;
    clusteringLayout = new AggregateLayout<String, Number>(new FRLayout<String, Number>(graph));
    Set<Integer> activatedPoints = new HashSet();

    jf = new JFrame();
    vv = new VisualizationViewer(new FRLayout(graph));

    vv.getRenderContext().setVertexLabelTransformer(new Transformer<String, String>() {
      public String transform(String e) {
        return (e);
      }
    });
    // graph.
    Iterator iter = graph.getVertices().iterator();
    System.out.println("number is " + graph.getVertexCount());
    Iterator Edges = graph.getEdges().iterator();
    // Edges.next().
    int map[] = new int[pointNum+1];
    int ii = 1;
    while (iter.hasNext()) {
      String vertext = (String) iter.next();
      
      // Double a = (Double) Edges.next();
      //System.out.println(vertext);
      neuronList.add(new Neuron(Integer.parseInt(vertext), activatedPoints, jf, vv));
      map[Integer.parseInt(vertext)] = ii++;
    }

    while (Edges.hasNext()) {
      Pair<String> endpoints = graph.getEndpoints(Edges.next());
      //System.out.println(vertext);

      neuronList.get(map[Integer.parseInt(endpoints.getFirst())] - 1).linkTo(
              neuronList.get(map[Integer.parseInt(endpoints.getSecond())] - 1));
      neuronList.get(map[Integer.parseInt(endpoints.getSecond())] - 1).linkTo(
              neuronList.get(map[Integer.parseInt(endpoints.getFirst())] - 1));
    }

    jf.getContentPane().add(vv);
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jf.pack();
    jf.setVisible(true);
    // vv.getCenter().
//    for(int i = 0; ;i++)
//    {
        neuronList.get(map[1]-1).beActived();
//    }

    //System.out.println("finishe!");
  }

}
