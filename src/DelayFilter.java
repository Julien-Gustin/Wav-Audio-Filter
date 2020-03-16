import be.uliege.montefiore.oop.audio.*;

public class DelayFilter implements Filter
{

  private int delay;
  private Queue sQueue;
  private int compt;

  public int nbInputs(){return 1;}
  public int nbOutputs(){return 1;}


  public DelayFilter(int delay){
    this.delay = delay;
    compt = delay;
    sQueue = new Queue();
  }

  public double[] computeOneStep(double[] input) throws FilterException
  {

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

  public void reset(){
    compt = delay;
    sQueue.reset();
  }
}
