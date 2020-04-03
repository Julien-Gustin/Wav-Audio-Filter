import be.uliege.montefiore.oop.audio.*;

public class TwoAllpassFilter extends CompositeFilter
{
  public TwoAllpassFilter(int delay1, double gain1, int delay2,double gain2) throws FilterException
  {
    super(1, 1);



    Filter allPass1 = new AllpassFilter(delay1, gain1),
           allPass2 = new AllpassFilter(delay2, gain2);


    addBlock(allPass1);
    addBlock(allPass2);


    connectInputToBlock(0, allPass1, 0);

    connectBlockToBlock(allPass1, 0, allPass2, 0);

    connectBlockToOutput(allPass2, 0, 0);
  }
}
