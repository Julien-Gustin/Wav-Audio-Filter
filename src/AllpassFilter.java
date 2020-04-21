import be.uliege.montefiore.oop.audio.*;

public class AllpassFilter extends CompositeFilter
{
  public AllpassFilter(int delayDuration, double gain) throws FilterException
  {
    // call the compositefilter constructor
    super(1, 1);

    Filter add1 = new AdditionFilter(),
           add2 = new AdditionFilter(),
           delay = new DelayFilter(delayDuration),
           multP = new GainFilter(gain),
           multN = new GainFilter(-gain);

    addBlock(multN);
    addBlock(multP);
    addBlock(delay);
    addBlock(add1);
    addBlock(add2);

    connectInputToBlock(0, multN, 0);
    connectInputToBlock(0, add1, 0);

    connectBlockToBlock(add1, 0, delay, 0);
    connectBlockToBlock(delay, 0, add2, 0);
    connectBlockToBlock(multN, 0, add2, 1);
    connectBlockToBlock(add2, 0, multP, 0);
    connectBlockToBlock(multP, 0, add1, 1);

    connectBlockToOutput(add2, 0, 0);

  }
}
