import be.uliege.montefiore.oop.audio.*;

public class DelayFilter implements Filter
{

  private int delay;
  private Queue sQueue; // sQueue is a queue which contains the 'delay' sample before the current one of the input
  private int compt; //compt take the value of delay and decrease at each calli
  private int needsInput;

  public int nbInputs(){return 1;}
  public int nbOutputs(){return 1;}

  /**
   * Constructor. delay is expressed in samples.
   *
   * @param delay should be > 0
   */
  public DelayFilter(int delay)
  {
    this.delay = delay;
    compt = delay;
    sQueue = new Queue();
    needsInput = 0;
  }

  /**
   * Computes one step of the filter, i.e.:
   * creates output array which if delay is smaller than 0, contain 0
   * else the output[0] take the value of the head of the queue which is the 'smaller'th before the current one of the input
   *
   * @param input is the sample
   */
  public double[] computeOneStep(double[] input) throws FilterException
  {
    if(needsInput == 1)
      throw new FilterException ("No previous input.");

    if(delay <= 0)
      throw new FilterException("Delay should be strictly positive. ");

    if (input.length != 1)
      throw new FilterException ("Wrong input length. ");

    double[] output = new double [1];
    sQueue.put(Double.valueOf(input[0]));

    if(compt > 0)
    {
      output[0] = 0;
      compt--;
    }
    else
      output[0] = (Double)sQueue.get();

    return output;
  }

  // TODO: document
  public double[] viewOutput() throws FilterException
  {

    if(needsInput == 1)
      throw new FilterException ("No previous input.");

    if(delay <= 0)
      throw new FilterException("Delay should be strictly positive. ");

    double[] output = new double [1];

    if(compt > 0)
    {
      output[0] = 0;
    }
    else
      output[0] = (Double)sQueue.view();

    return output;
  }

  /**
  * Reset the filter
  */
  public void reset()
  {
    compt = delay;
    sQueue.reset();
  }
}
