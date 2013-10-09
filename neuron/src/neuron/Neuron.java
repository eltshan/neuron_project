package neuron;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.samples.SimpleGraphDraw;
import edu.uci.ics.jung.visualization.VisualizationViewer;

public class Neuron implements Runnable {

  private int tab;

  // private Collection<Integer> a ;
  // private Map<int>;
  private int linkNum;

  private JFrame jf;

  private VisualizationViewer vv;

  // private Collection<int>;
  private Set<Integer> activatedPoints;

  private ArrayList<Neuron> linkedList;

  private boolean isActive;

  public Neuron(int i, Set<Integer> a, JFrame jff, VisualizationViewer vvv) {
    tab = i;
    isActive = false;
    activatedPoints = a;
    linkedList = new ArrayList<Neuron>();
    jf = jff;
    vv = vvv;
  }

  public void linkTo(Neuron N) {
    linkNum++;
    linkedList.add(N);
    // String a = " ";
    // a.equals(anObject)
    // N.linkTo(this);
  }

  public int getlinkNum() {
    return linkNum;
  }

  public int gettab() {
    return tab;
  }

  public void beActived() {

    this.run();

  }

  private void activateAllAdjacentNeuron() {
    Iterator<Neuron> linkedListIterator = linkedList.iterator();
    ArrayList<Thread> threadLists = new ArrayList<Thread>();
    int threadTag = 0;

    while (linkedListIterator.hasNext()) {
      Neuron adjacentNeuron = linkedListIterator.next();
      if (!adjacentNeuron.isActive) {
        //System.out.println(threadTag++ + " is goint to start to activate "
        //        + adjacentNeuron.gettab());
        threadLists.add(new Thread(adjacentNeuron));

      }

    }

    Iterator<Thread> threadIterator = threadLists.iterator();
    while (threadIterator.hasNext()) {
      // threadIterator.next().start();
      threadIterator.next().start();
    }

  }

  // @Override
  public void run() {
    // TODO Auto-generated method stub
    if (!isActive) {
      isActive = true;
      activatedPoints.add(this.gettab());
      System.out.println("Neuron " + tab + " has been activated");
      synchronized (this) {
        this.upDateGraph();
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }

      }
      // TODO Auto-generated catch block
      this.activateAllAdjacentNeuron();

      return;
    }
    return;

  }

  public void upDateGraph() {
    Transformer<String, Paint> vertexColor = new Transformer<String, Paint>() {

      public Paint transform(String i) {
        if (activatedPoints.contains(Integer.parseInt(i)))
          return Color.GREEN;
        return Color.RED;
      }
    };

    vv.getRenderContext().setVertexFillPaintTransformer(vertexColor);
    jf.repaint();
  }

}
