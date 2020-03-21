import be.uliege.montefiore.oop.audio.*;

public class GraphNode
{

  private int linko;

  private int compositeInput;
  public int compositeOutput;

  private int inputs;
  private int outputs;

  private int flag;

  public Filter filter;

  public GraphNode[] in;
  private GraphNode[] out;

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
  }

/*
 *When created with this constructor, the graph node is one of the inputs of the composite filter
 *(InOut = 0) or one of the outputs(InOut = 1).
 */
  public GraphNode(int InOut, int compositeNum)
  {
    if(InOut == 0){
      this.compositeInput = compositeNum;
      this.compositeOutput = -1;
      out = new GraphNode[1];
      inputs = 1;
      outputs = 1;
    }
    else{
      this.compositeOutput = compositeNum;
      this.compositeInput = -1;
      inputs = 1;
      outputs = 1;
      in = new GraphNode[1];
    }
  }

  public int nbInputs(){return inputs;}
  public int nbOutputs(){return outputs;}

  public void connectInput(int input, GraphNode node)
  {
    in[input] = node;
  }

  public void connectOutput(int output, GraphNode node)
  {
    linko = output;
    out[output] = node;
  }

  public double getOutput(double[] input) throws FilterException
  {
    if(compositeInput != -1)
      return input[linko];

      if(filter instanceof DelayFilter){
        if(flag == 0){
          flag = 1;
        }
        if(flag ==1){
          flag = 0;
          DelayFilter delay = (DelayFilter) filter;
          return delay.getOutput()[linko];
        }
      }

    double[] inputArray = new double[inputs];
    for(int i = 0; i < inputs; i++){
      inputArray[i] = in[i].getOutput(input);
    }
    if(compositeOutput != -1)
      return inputArray[compositeOutput];

    return filter.computeOneStep(inputArray)[linko];
  }
}
