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
    if(input.length != 1)
      System.out.println("yo");

    double[] output = new double [1];
    sQueue.put(Double.valueOf(input[0]));
    if(delay > 0)
    {
      output[0] = 0;
      delay--;
    }
    else
      output[0] = (Double)sQueue.get();

    return output;
  }

  public void reset(){
    sQueue.reset();
    sQueue = null;
  }
}
