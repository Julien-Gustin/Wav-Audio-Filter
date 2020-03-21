import be.uliege.montefiore.oop.audio.*;
import java.util.*; // changer en seulement ce qui nous arranges à la fin

public class CompositeFilter implements Filter
{

  private int nbInputs;
  private int nbOutputs;
  private Hashtable<Filter, GraphNode> hash; //

  private GraphNode[] outputs;

  public int nbInputs(){return nbInputs;}
  public int nbOutputs(){return nbOutputs;}

  public CompositeFilter(int inputs, int outputs)
  {
    nbInputs = inputs;
    nbOutputs = outputs;
    hash = new Hashtable<Filter, GraphNode>();

    this.outputs = new GraphNode[outputs];
    for(int i = 0; i < outputs; i++){
      this.outputs[i] = null;
    }
  }

  public double[] computeOneStep(double[] input) throws FilterException
  {
    double[] output = new double[nbOutputs];

    for(int i = 0; i < nbOutputs; i++)
      output[i] = outputs[i].getOutput(input);

    return output;
  }

  public void addBlock(Filter f)
  {
    GraphNode data = new GraphNode(f);
    hash.put(f, data);
  }

  public void connectBlockToBlock(Filter f1, int o1, Filter f2, int i2) throws FilterException
  {
    if (o1 >= f1.nbOutputs())
      throw new FilterException("Wrong output for f1. ");
    if (i2 >= f2.nbInputs())
      throw new FilterException("Wrong input for f2. ");

    GraphNode node1 = hash.get(f1);
    GraphNode node2 = hash.get(f2);

    node1.connectOutput(o1, node2);
    node2.connectInput(i2, node1);

  }

  public void connectBlockToOutput(Filter f1, int o1, int o2) throws FilterException
  {
    if (o1 >= f1.nbOutputs())
      throw new FilterException("Wrong output for f1. ");
    if (o2 >= this.nbInputs())
      throw new FilterException("Wrong input for the composite filter. ");

    if (outputs[o2] != null) // fefe doit verif ceci
      throw new FilterException("Output already taken. ");

    GraphNode node = hash.get(f1);
    GraphNode out = new GraphNode(1, o2);
    outputs[o2] = out;
    node.connectOutput(o1, out);
    out.connectInput(o2, node);

  }

  public void connectInputToBlock(int i1, Filter f2, int i2) throws FilterException
  {
    if (i1 >= nbInputs)
      throw new FilterException("Wrong output for the composite filter. ");
    if (i2 >= f2.nbInputs())
      throw new FilterException("Wrong input for f2. ");

    GraphNode node = hash.get(f2);
    GraphNode in = new GraphNode(0, i1);
    in.connectOutput(i1, node);
    node.connectInput(i2, in);
  }

  public void reset(){
    Enumeration<Filter> keys = hash.keys();
    while(keys.hasMoreElements())
    {
        Filter key = keys.nextElement();
        key.reset();
    }
  }
}
