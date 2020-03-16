import be.uliege.montefiore.oop.audio.*;

public class GainFilter implements Filter
{

  private double gain;

  public int nbInputs(){return 1;}
  public int nbOutputs(){return 1;}

  /*
   * Constructor. gain is expressed as a multiplicator
   *
   * gain should be > 0
   */
  public GainFilter(double gain){this.gain = gain;}

  /*
   * Computes one step of the filter, i.e.:
   * creates output array which contain input[0] * gain
   */
  public double[] computeOneStep(double[] input) throws FilterException
  {
    if(gain < 0)
      throw new FilterException("Gain should be strictly positive");

    double[] output = new double [1];

    output[0] = input[0]*gain;

    return output;
  }

  public void reset(){
  }
}
