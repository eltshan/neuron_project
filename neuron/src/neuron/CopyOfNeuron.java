package neuron;

import java.util.ArrayList;
import java.util.Iterator;

public class CopyOfNeuron implements Runnable{
  private int tab;

  private int linkNum;

  private ArrayList<CopyOfNeuron> linkedList;

  private boolean isActive;

  public CopyOfNeuron(int n) {
    tab = n;
    isActive = false;
    linkedList = new ArrayList<CopyOfNeuron>();
  }

  public void linkTo(CopyOfNeuron N) {
    linkNum++;
    linkedList.add(N);
    //N.linkTo(this);
  }

  public int getlinkNum() {
    return linkNum;
  }

  public void beActived() {
    //isActive = true;
    //System.out.println("Neuron " + tab + " has been activated");
    //this.activateAllAdjacentNeuron();
    this.run();
  }
  
  private void activateAllAdjacentNeuron() {
    Iterator<CopyOfNeuron> linkedListIterator = linkedList.iterator();
    ArrayList<Thread> threadLists = new ArrayList<Thread>();
    while (linkedListIterator.hasNext()) {
      CopyOfNeuron adjacentNeuron = linkedListIterator.next();
      if (!adjacentNeuron.isActive)
        threadLists.add(new Thread(adjacentNeuron));

    }
    
    Iterator<Thread> threadIterator = threadLists.iterator();
    while(threadIterator.hasNext()){
      threadIterator.next().start();
    }

  }

  //@Override
  public void run() {
    // TODO Auto-generated method stub
    isActive = true;
    System.out.println("Neuron " + tab + " has been activated");
    this.activateAllAdjacentNeuron();
    
    
  }

}
