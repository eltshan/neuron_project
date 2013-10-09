package neuron;

import edu.uci.ics.jung.graph.UndirectedGraph;
import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;

public class GenerateNeurons {

  private UndirectedGraph<String, Number> g;

  public GenerateNeurons() {

  }

  public static UndirectedGraph<String, Number> GenerateRandomNeurons(int pointNumber, int avgEdge) {
    UndirectedGraph<String, Number> g = new UndirectedSparseMultigraph<String, Number>();
    //Graph<String,Number> g= new Graph<String, Number>();
    int LastJ = 2;
    int j = LastJ;
    for (int i = 1; i <= pointNumber; i++) {

      int EdgeNumI = (int) ( + Math.random() * avgEdge) + 1;
      //System.out.println(EdgeNumI);
      for (j = LastJ; j <= Math.min(LastJ+EdgeNumI, pointNumber); j++) {
        String i1 = "" + i;
        String i2 = "" + j;
        g.addVertex(i1);
        g.addVertex(i2);
        //System.out.println("? points " + g.getVertexCount());

        g.addEdge(Math.pow(i + 2, j), i1, i2);
        //System.out.println(i1 + " is connected to " + i2);
      }
      //System.out.println(" ");
      LastJ = j;

    }
    //List<String> index = new ArrayList<String>();
    //index.addAll(g.getVertices());
    // and one edge to connect them all
    //for (int i = 0; i < index.size() - 1; i++)
    //  g.addEdge(new Integer(i), index.get(i), index.get(i + 1));
    return g;

  }

}
