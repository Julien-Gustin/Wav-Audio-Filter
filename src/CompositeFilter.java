import be.uliege.montefiore.oop.audio.*;
import java.util.*; // changer en seulement ce qui nous arranges à la fin
import be.uliege.montefiore.oop.audio.*;

public class CompositeFilter implements Filter
{

  private int nbInputs;
  private int nbOutputs;
  private Hashtable<Filter, F> hash; //

  private Filter filter;

  public int nbInputs(){return nbInputs;}
  public int nbOutputs(){return nbOutputs;}

  public CompositeFilter(int inputs, int outputs)
  {
    nbInputs = inputs;
    nbOutputs = outputs;
    hash = new Hashtable<Filter, F>();

    filter = null;
  }

  public CompositeFilter(int inputs, int outputs, Hashtable<Filter, F> hash, Filter filter)
  {
    nbInputs = inputs;
    nbOutputs = outputs;
    this.hash = hash;
    this.filter = filter;
  }

  public double[] computeOneStep(double[] input) throws FilterException
  {
    double[] outputs = new double [2];
    double[] output = new double [1];
    F tmp = hash.get(filter);
    int check = 0;
    int link = -1;

    if(tmp.input != -1)
    {
      outputs[tmp.in] = input[tmp.input];
      check++;
    }

    if(tmp.prec[0] != null)
    {
      link = tmp.prec[0].linkIn;
      outputs[link] = tmp.prec[0].actuel.computeOneStep(input)[tmp.prec[0].linkOut];
      check++;
    }

    if(tmp.prec[1] != null)
    {
      link = tmp.prec[1].linkIn;
      outputs[link] = tmp.prec[1].actuel.computeOneStep(input)[tmp.prec[1].linkOut];
      check++;
    }

    if(check == 2){
      output = filter.computeOneStep(outputs); // verif output 2 de blockToClock
}
    else if(check == 1 && link == -1)
    {
      output[0] = outputs[tmp.in];
      output = filter.computeOneStep(output);
    }

    else if(check == 1)
    {
      output[0] = outputs[link];
      output = filter.computeOneStep(output);
    }
    return output;
  }

  public void addBlock(Filter f)
  {
    CompositeFilter newCF = new CompositeFilter(f.nbInputs(), f.nbOutputs(), hash, f);
    F data = new F(newCF);
    hash.put(f, data);
  }

  public void connectBlockToBlock(Filter f1, int o1, Filter f2, int i2)
  {
    F tmp = hash.get(f2);

    tmp.prec[i2] = hash.get(f1);
    //if(tmp.prec[i2] != null)
      //ERROR
    tmp.prec[i2].linkOut = o1;
    tmp.prec[i2].linkIn = i2;

  }

  public void connectBlockToOutput(Filter f1, int o1, int o2)
  {
    F tmp = hash.get(f1);

    tmp.output = o2;
    tmp.out = o1;
    filter = f1;
  }

  public void connectInputToBlock(int i1, Filter f2, int i2)
  {
    F tmp = hash.get(f2);

    tmp.input = i1;
    tmp.in = i2;
  }

  public void reset(){

    if(hash.get(filter).prec[0] != null)
      hash.get(filter).prec[0].actuel.reset();

    if(hash.get(filter).prec[1] != null)
      hash.get(filter).prec[1].actuel.reset();

    filter.reset();
  }
}
