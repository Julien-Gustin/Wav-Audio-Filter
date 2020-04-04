import be.uliege.montefiore.oop.audio.*;
//TODO : remove what is in comment (like OUT) (useless)
/**
* Class allowing a graph representation of the composite filter.
*/
public class GraphNode
{

  private int[] linko;

  private int compositeInput;
  private int compositeOutput;

  private int inputs;

  private int flag;

  private Filter filter;

  private GraphNode[] in;

  private double[] currentOutput;

  private boolean checked; //bool?

  private boolean reset;

  /**
  * GraphNode constructor, create a node by a filter
  *
  * @param filter This is the filter associated of the node
  */
  public GraphNode(Filter filter)
  {
    inputs = filter.nbInputs();
    // outputs = filter.nbOutputs();

    in = new GraphNode[inputs];

    this.filter = filter;

    flag = 0;

    compositeInput = -1;
    compositeOutput = -1;

    linko = new int[filter.nbInputs()];

    currentOutput = null;

    checked = true;
    reset = true;

  }

  /**
   * GraphNode constructor polymorphism, used if the input is the composite input
   * When created with this constructor, the graph node is one of the inputs of the composite filter
   *
   * @param InOut    If InOut = 0, this is the composite Input
   *                 If InOut = 1, this is the composite Output
   * @param compositeNum is this in/output associated
   * @param length number of outputs
   *
   */
  public GraphNode(int InOut, int compositeNum, int length)
  {
    reset = false;
    checked = true;
    currentOutput = null;

    if(InOut == 0)
    {
      this.compositeInput = compositeNum;
      this.compositeOutput = -1;
      inputs = 0;
      currentOutput = null;
      linko = null;
    }
    else
    {
      this.compositeOutput = compositeNum;
      this.compositeInput = -1;
      inputs = 1;
      in = new GraphNode[1];
      linko = new int[length];
    }
  }
  /**
   * Return the numbers of inputs associated of the node
   *
   * @return inputs which should be >= 0
   */
  public int nbInputs(){return inputs;}

  /**
   * Connect the input of the current node with another one
   *
   * @param f1 node corresponding to the filter in input
   * @param o1 the output of f1
   * @param i2 the input
   */
  public void connectInOut(GraphNode f1, int o1, int i2)
  {
    linko[i2] = o1;
    in[i2] = f1;
  }

  /**
   * Get the value of the output TODO finish the "explication"
   *
   * @param input Is the input for the filter
   * @param out The output needed
   *
   * @return currentOutput the output of the current filter needed for the next input
   */
  public double getOutput(double[] input, int out) throws FilterException
  {
    checked = false;
    reset = false;

    if(compositeInput != -1)
    {
      currentOutput = input;
      return currentOutput[0];
    }

    if(currentOutput != null && flag == 0)
      return currentOutput[0];

    if(filter instanceof DelayFilter && flag == 0)
    {
      DelayFilter delay = (DelayFilter) filter;
      currentOutput = delay.viewOutput();
      flag = 1;
      return currentOutput[0]; // bc nbOutputs of a delay filter = 1
    }

    double[] inputArray = new double[inputs];
    for(int i = 0; i < inputs; i++){
      if(in[i].compositeInput != -1)
        inputArray[i] = input[linko[i]];

      else
        inputArray[i] = in[i].getOutput(input, linko[i]);
    }

    if(compositeOutput != -1)
    {
      currentOutput = inputArray;
      return currentOutput[0]; // Always equal to 0 cause that each output is a node
    }

    currentOutput = filter.computeOneStep(inputArray);

    return currentOutput[out];
  }

  public void check(double[] input) throws FilterException
  {
    if(checked) return;
    checked = true;

    for(int i = 0; i < inputs; i++)
      in[i].check(input);

    if(flag == 1)
    {
      this.getOutput(input, 0);
      flag = 0;
    }
  }

  public void resetNode()
  {
    if(reset) return;

    reset = true;

    currentOutput = null;

    for(int i = 0; i < inputs; i++)
      in[i].resetNode();

  }
}
