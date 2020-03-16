public class GainFilter extends Filter
{

  private double gain;

  public GainFilter(double gain){
    this.gain = gain;
  }

  public double[] computeOneStep(double[] input) throws FilterException{
    int size = input.size;
    double[size] output;
    for(int i = 0; i < size; i++){
      if(input[i] < 0) throw new FilterException("Wrong input");
      output[i] = input[i]*gain;
      if(output[i] < 0) throw new FilterException("Error in output computation");
    }
  }

  public void reset(){

  }

}
