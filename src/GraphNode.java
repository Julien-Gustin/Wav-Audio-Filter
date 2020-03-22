import be.uliege.montefiore.oop.audio.*;

public class GraphNode
{

  private int linko;

  private int compositeInput;
  private int compositeOutput;

  private int inputs;
  private int outputs;

  private int flag;

  private Filter filter;

  private GraphNode[] in;
  private GraphNode[] out; // delete?

  private double[] currentOutput;

  private int check;

  private int tmp;

  /**
  * GraphNode constructor, create a node by a filter
  *
  * @param filter This is the filter associated of the node
  */
  public GraphNode(Filter filter)
  {
    inputs = filter.nbInputs();
    outputs = filter.nbOutputs();

    in = new GraphNode[inputs];
    out = new GraphNode[outputs];

    this.filter = filter;

    flag = 0;

    compositeInput = -1;
    compositeOutput = -1;

    linko = -1;

    currentOutput = null;
    check = 0;

    tmp = 0;
  }

  /**
   * GraphNode constructor polymorphism, used if the input is the composite input
   * When created with this constructor, the graph node is one of the inputs of the composite filter
   *
   * @param InOut    If InOut = 0, this is the composite Input
   *                 If InOut = 1, this is the composite Output
   * @param compositeNum is this in/output associated
   *
   */
  public GraphNode(int InOut, int compositeNum)
  {
    if(InOut == 0){
      this.compositeInput = compositeNum;
      this.compositeOutput = -1;
      out = new GraphNode[1];
      inputs = 0;
      outputs = 1;
      currentOutput = null;
      check = 0;
    }
    else{
      this.compositeOutput = compositeNum;
      this.compositeInput = -1;
      inputs = 1;
      outputs = 1;
      in = new GraphNode[1];
      currentOutput = null;
      check = 0;
    }
  }
  /**
   * Return the numbers of inputs associated of the node
   *
   * @return inputs which should be >= 0
   */
  public int nbInputs(){return inputs;}

  /**
   * Return the numbers of outputs associated of the node
   *
   * @return outputs which should be >= 0
   */
  public int nbOutputs(){return outputs;}

  /**
   * Connect the input of the current node with another one
   *
   * @param input
   * @param node
   */
  public void connectInput(int input, GraphNode node)
  {
    in[input] = node;
  }

  /**
   * Connect the input of the current node with another one
   *
   * @param input
   * @param node
   */
  public void connectOutput(int output, GraphNode node)
  {
    linko = output;
    out[output] = node;
  }

  /**
   * Get the value of the output TODO finish the "explication"
   *
   * @param input Is the input for the filter
   *
   * @return currentOutput the output of the current filter needed for the next input
   */
  public double getOutput(double[] input) throws FilterException
  {
    check = 1;

    if(compositeInput != -1)
      return input[linko];
    //
    // if(currentOutput != null)
    //   return currentOutput[linko];
    //
    // if (filter instanceof DelayFilter && flag == 0){
    //   DelayFilter delay = (DelayFilter) filter;
    //   currentOutput = delay.viewOutput();
    //   flag = 1;
    //   return currentOutput[linko];
    // }

    if(filter instanceof DelayFilter && check == 1){
      check = 0;
      return tmp;
    }

    double[] inputArray = new double[inputs];
    for(int i = 0; i < inputs; i++){
      inputArray[i] = in[i].getOutput(input);
    }
    if(compositeOutput != -1){
      check = 0;
      return inputArray[compositeOutput];
    }

    currentOutput = filter.computeOneStep(inputArray);
    check = 0;
    return currentOutput[linko];
  }


//   public void check(double[] input) throws FilterException
//   {
//     if(checked == 1) return;
//     for(int i = 0; i < inputs; i++){
//       in[i].check(input);
//     }
//     if(flag == 1){
//       this.getOutput(input);
//       flag = 0;
//     }
//     checked = 1;
//   }
//
//   public void resetNode()
//   {
//     if(currentOutput == null) return;
//     for(int i = 0; i < inputs; i++){
//       in[i].resetNode();
//     }
//     currentOutput = null;
//   }
}
