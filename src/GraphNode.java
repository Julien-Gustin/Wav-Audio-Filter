import be.uliege.montefiore.oop.audio.*;

public class GraphNode
{

  private int compositeInput;
  private int compositeOutput;

  private int inputs;
  private int outputs;

  private Filter filter;

  private GraphNode[] in;
  private GraphNode[] out;

  public GraphNode(Filter filter)
  {
    inputs = filter.nbInputs();
    outputs = filter.nbOutputs();

    in = new GraphNode[inputs];
    out = new GraphNode[outputs];

    this.filter = filter;

    compositeInput = -1;
    compositeOutput = -1;
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
    }
    else{
      this.compositeOutput = compositeNum;
      this.compositeInput = -1;
      inputs = 1;
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
    out[output] = node;
  }

  public double[] getNodeOutput(double[] input) throws FilterException
  {
    //If the node is one of the inputs of the composite filter, we return the corresponding input
    if(compositeInput != -1){
      double[] ret = new double[1];
      ret[0] = input[compositeInput];
      return ret;
    }

    double[] tempIn = new double[inputs];
    double[] temp = new double[inputs];
    int index = 0;

    for (int i = 0; i < inputs; i++){
      temp = in[i].getNodeOutput(input);
      for(int j = 0; j < in[i].nbOutputs(); j++){
        tempIn[index]=temp[j];
        index++;
      }
    }
    if(compositeOutput != -1)
      return tempIn;
    return filter.computeOneStep(tempIn);
  }
}
