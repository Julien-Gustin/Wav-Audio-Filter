import be.uliege.montefiore.oop.audio.*;

public class DelayFilter implements Filter
{

  private int delay;
  private Queue sQueue;

  public int nbInputs(){return 1;}
  public int nbOutputs(){return 1;}


  public DelayFilter(int delay){
    this.delay = delay;
    sQueue = new Queue();
  }

  public double[] computeOneStep(double[] input) throws FilterException
  {

    double[] output = new double [1];
    sQueue.put(Double.valueOf(input[0]));
    if(delay > 0)
    {
      output[0] = 1;
      delay--;
      return output;
    }

    output[0] = (Double)sQueue.get();


    return output;
  }

  public void reset(){
    delay = 0;
    sQueue.reset();
    // sQueue = null;

  }
}
