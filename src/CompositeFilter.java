import be.uliege.montefiore.oop.audio.*;
import java.util.*; // changer en seulement ce qui nous arranges à la fin
import be.uliege.montefiore.oop.audio.*;

public class CompositeFilter implements Filter
{

  private int nbInputs;
  private int nbOutputs;
  private Hashtable<Filter, F> hash; //

  private Filter firstFilter;

  public int nbInputs()
  {
    return nbInputs;
  }
  public int nbOutputs()
  {
    return nbOutputs;
  }

  public CompositeFilter(int inputs, int outputs)
  {
    nbInputs = inputs;
    nbOutputs = outputs;
    hash = new Hashtable<Filter, F>();

    firstFilter = null;
  }

  public double[] computeOneStep(double[] input) throws FilterException
  {
    Filter it = firstFilter;
    double[] output = input;
    while(it != null)
    {
      output = it.computeOneStep(output);
      it = hash.get(it).next;
    }

    return output;
  }

  public void addBlock(Filter f)
  {
    F data = new F();
    hash.put(f, data);

    if(firstFilter == null) firstFilter = f;

  }

  public void connectBlockToBlock(Filter f1, int o1, Filter f2, int i2)
  {
    //if(o1 != i2) ERROR
    F tmp = hash.get(f1);
    tmp.next = f2;
    tmp.o1 = o1;
    tmp.i2 = i2;

  }

  public void connectBlockToOutput(Filter f1, int o1, int o2)
  {
    F tmp = hash.get(f1);
    tmp.o1 = o1;
    tmp.o2 = o2;
  }

  public void connectInputToBlock(int i1, Filter f2, int i2)
  {
    F tmp = hash.get(f2);
    tmp.i1 = i1;
    tmp.i2 = i2;

  }

  public void reset(){

  }
}
