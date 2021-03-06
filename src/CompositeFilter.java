import be.uliege.montefiore.oop.audio.*;
import java.util.Hashtable;
import java.util.Enumeration;

public class CompositeFilter implements Filter
{
  // Number of inputs and outputs of the composite filter
  private int nbInputs;
  private int nbOutputs;
  // Hashtable using a given filter as a key, associating it with a GraphNode
  private Hashtable<Filter, GraphNode> hash;
  // Array containing the GraphNodes associated with the filter outputs
  private GraphNode[] outputs;

  /**
  * CompositeFilter constructor, creates a new CompositeFilter given its in/out
  *
  * @param inputs the numbers of inputs of the CompositeFilter
  * @param outputs the numbers of outputs of the CompositeFilter
  */
  public CompositeFilter(int inputs, int outputs)
  {
    nbInputs = inputs;
    nbOutputs = outputs;

    hash = new Hashtable<Filter, GraphNode>();

    this.outputs = new GraphNode[outputs];
    for(int i = 0; i < outputs; i++)
      this.outputs[i] = null;
  }

  /**
  * Method returning the number of inputs of the filter
  */
  public int nbInputs(){return nbInputs;}

  /**
  * Method returning the number of outputs of the filter
  */
  public int nbOutputs(){return nbOutputs;}

  /**
  * Computes the output of the composite filter far a given input
  *
  * @param input the array containing the inputs of the filter
  */
  public double[] computeOneStep(double[] input) throws FilterException
  {
    double[] output = new double[nbOutputs];

    //Gets the output each GraphNode associated to a filter output.
    for(int i = 0; i < nbOutputs; i++)
      output[i] = outputs[i].getOutput(input, i);

    //Gives the inputs to the DelayFilters used in the computation process
    for(int i = 0; i < nbOutputs; i++)
      outputs[i].check(input);

    // Resets the fields currentOutput in the GraphNodes to a null pointer
    for(int i = 0; i < nbOutputs; i++)
      outputs[i].resetNode();

    return output;
  }

  /**
  * Adds a block (filter) to the composite filter
  *
  * @param f This is a filter
  */
  public void addBlock(Filter f)
  {
    GraphNode data = new GraphNode(f);
    hash.put(f, data);
  }

  /**
  * Connects two blocks together in the composite filter
  *
  * @param f1 The first filter
  * @param o1 The output of f1 that needs to be connected with i2
  * @param f2 The second filter
  * @param i2 The input of f2 that needs to be connected with o1
  */
  public void connectBlockToBlock(Filter f1, int o1, Filter f2, int i2) throws FilterException
  {
    if (o1 >= f1.nbOutputs())
      throw new FilterException("Wrong output for f1. ");
    if (i2 >= f2.nbInputs())
      throw new FilterException("Wrong input for f2. ");

    try
    {
      GraphNode node1 = hash.get(f1);
      GraphNode node2 = hash.get(f2);

      node2.connectInOut(node1, o1, i2);
    }
    catch(Exception e)
    {
      throw new FilterException("Filter used has not been added before, in connectBlockToBlock method");
    }
  }

  /**
  * Connects a block to the composite output
  *
  * @param f1 The first filter
  * @param o1 The output of f1 that needs to be connected with o2
  * @param o2 The output of the Composite Filter
  */
  public void connectBlockToOutput(Filter f1, int o1, int o2) throws FilterException
  {
    if (o1 >= f1.nbOutputs())
      throw new FilterException("Wrong output for f1. ");
    if (o2 >= nbOutputs)
      throw new FilterException("Wrong output for the composite filter. ");
    if (outputs[o2] != null)
      throw new FilterException("Output " + o2 + " already taken");

    try
    {
      GraphNode node = hash.get(f1);
      GraphNode out = new GraphNode(1, o2, nbOutputs);
      outputs[o2] = out;

      out.connectInOut(node, o1, 0);
    }
    catch(Exception e)
    {
      throw new FilterException("Filter used to connect to the output has not been added before.");
    }
  }

  /**
  * Connects a block to the composite input
  *
  * @param f2 The filter linked to the input
  * @param i1 The input of the composite filter connected to f2
  * @param i2 The input of f2 that is connected to the input
  */
  public void connectInputToBlock(int i1, Filter f2, int i2) throws FilterException
  {
    if (i1 >= nbInputs)
      throw new FilterException("Wrong output for the composite filter. 2");
    if (i2 >= f2.nbInputs())
      throw new FilterException("Wrong input for f2. ");

    try
    {
      GraphNode node = hash.get(f2);
      GraphNode in = new GraphNode(0, i1, 0);

      node.connectInOut(in, i1, i2);
    }
    catch(Exception e)
    {
      throw new FilterException("Filter used to connect to the input has not been added before.");
    }
  }

  /**
  * Method resetting every filter of the compositefilter
  *
  */
  public void reset(){
    Enumeration<Filter> keys = hash.keys();
    while(keys.hasMoreElements())
    {
        Filter key = keys.nextElement();
        key.reset();
    }
  }
}
