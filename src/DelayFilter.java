import be.uliege.montefiore.oop.audio.*;

public class DelayFilter implements Filter
{

  private int delay;

  private int nbInputs;
  private int nbOutputs;
  private int reminder;

  public int nbInputs(){return nbInputs;}
  public int nbOutputs(){return nbOutputs;}


  public DelayFilter(int delay){
    this.delay = delay;
    nbInputs = 0;
    nbOutputs = 0;
    reminder = 0;
  }

  private void SetnbInputs(int nbInputs){this.nbInputs = nbInputs;}
  private void SetnbOutputs(int nbOutputs){this.nbOutputs = nbOutputs;}


  public double[] computeOneStep(double[] input) throws FilterException{
    SetnbInputs(input.length);

    double[] output = new double [nbInputs + delay];
    int i = 0;

    System.out.println(nbInputs);


    SetnbOutputs(delay+nbInputs);
    return output;
  }

  public void reset(){
    delay = 0;
    nbInputs = 0;
    nbOutputs = 0;
  }
}
