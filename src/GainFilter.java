import be.uliege.montefiore.oop.audio.*;

public class GainFilter implements Filter
{

  private double gain;

  public int nbInputs(){return 1;}
  public int nbOutputs(){return 1;}

  public GainFilter(double gain){this.gain = gain;}

  public double[] computeOneStep(double[] input) throws FilterException
  {
    double[] output = new double [1];

    output[0] = input[0]*gain;

    return output;
  }

  public void reset(){
    gain = 0;
  }
}
