import be.uliege.montefiore.oop.audio.*;

public class AdditionFilter implements Filter
{

  public int nbInputs(){return 2;}
  public int nbOutputs(){return 1;}

  public AdditionFilter(){}

  public double[] computeOneStep(double[] input) throws FilterException
  {
    if (input.length != 2)
      throw new FilterException ("Wrong input length. ");

    double[] output = new double[1];
    output[0] = input[1]+input[0];
    return output;
  }

  public void reset(){}
}
