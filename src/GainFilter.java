import be.uliege.montefiore.oop.audio.*;

public class GainFilter implements Filter
{

  private double gain;

  public int nbInputs(){return 1;}
  public int nbOutputs(){return 1;}

  /**
   * Constructor. gain is expressed as a multiplicator
   *
   * @param gain
   */
  public GainFilter(double gain){this.gain = gain;}

  /**
   * Computes one step of the filter, i.e.:
   * creates output array which contain input[0] * gain
   *
   * @param input
   *
   * @return the piece of sample gained
   */
  public double[] computeOneStep(double[] input) throws FilterException
  {

    if (input.length != 1)
      throw new FilterException ("Wrong input length. ");

    double[] output = new double [1];

    output[0] = input[0]*gain;

    return output;
  }

  public void reset(){
  }
}
