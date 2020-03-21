import be.uliege.montefiore.oop.audio.*;

public class DelayFilter implements Filter
{

  private int delay;
  private Queue sQueue;
  private int compt;
  private int needsInput;

  public int nbInputs(){return 1;}
  public int nbOutputs(){return 1;}

  /*
   * Constructor. delay is expressed in samples.
   *
   * delay should be > 0
   * compt take the value of delay
   * sQueue is a queue which contains the 'delay' sample before the current one of the input
   */
  public DelayFilter(int delay){
    this.delay = delay;
    compt = delay;
    sQueue = new Queue();
    needsInput = 0;
  }

  /*
   * Computes one step of the filter, i.e.:
   * creates output array which if delay is smaller than 0, contain 0
   * else the output[0] take the value of the head of the queue which is the 'smaller'th before the current one of the input
   */
  public double[] computeOneStep(double[] input) throws FilterException
  {
    if(needsInput==1)
      throw new FilterException ("No previous input.");

    if(delay < 0)
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

  public double[] getOutput() throws FilterException
  {
    if(needsInput==1)
      throw new FilterException ("No previous input.");

    if(delay < 0)
      throw new FilterException("Delay should be strictly positive. ");

    double[] output = new double [1];

    if(compt > 0)
    {
      output[0] = 0;
      compt--;
    }
    else
      output[0] = (Double)sQueue.get();

    needsInput = 1;
    return output;
  }

  public void giveInput() throws FilterException
  {
    if(needsInput==0)
      throw new FilterException ("No need of new input.");

    if (input.length != 1)
      throw new FilterException ("Wrong input length. ");

    sQueue.put(Double.valueOf(input[0]));
    needsInput = 0;
  }

  public void reset(){
    compt = delay;
    sQueue.reset();
  }
}
