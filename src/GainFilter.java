import be.uliege.montefiore.oop.audio.*;

public class GainFilter implements Filter
{

  private double gain;

  private int nbInputs;
  private int nbOutputs;

  public int nbInputs(){return nbInputs;}
  public int nbOutputs(){return nbOutputs;}


  public GainFilter(double gain){
    this.gain = gain;
    nbInputs = 0;
    nbOutputs = 0;
  }

  private void SetnbInputs(int nbInputs){this.nbInputs = nbInputs;}
  private void SetnbOutputs(int nbOutputs){this.nbOutputs = nbOutputs;}


  public double[] computeOneStep(double[] input) throws FilterException{
    SetnbInputs(input.length);

    double[] output = new double [nbInputs];
    for(int i = 0; i < nbInputs; i++){
      //if(input[i] < 0) throw new FilterException("Wrong input");
      output[i] = input[i]*gain;
      //if(output[i] < 0) throw new FilterException("Error in output computation");
    }

    SetnbOutputs(nbInputs);
    return output;
  }

  public void reset(){
    gain = 0;
    nbInputs = 0;
    nbOutputs = 0;
  }
}
