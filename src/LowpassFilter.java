import be.uliege.montefiore.oop.audio.*;

public class LowpassFilter extends CompositeFilter
{
  public LowpassFilter(int delayDuration, double gain) throws FilterException
  {
    // call the compositefilter constructor
    super(1, 1);

    Filter add = new AdditionFilter(),
           delay = new DelayFilter(delayDuration),
           mult = new GainFilter(gain),
           multI = new GainFilter(1-gain);

    addBlock(multI);
    addBlock(mult);
    addBlock(delay);
    addBlock(add);


    connectInputToBlock(0, multI, 0);

    connectBlockToBlock(multI, 0, add, 0);
    connectBlockToBlock(mult, 0, add, 1);
    connectBlockToBlock(add, 0, delay, 0);
    connectBlockToBlock(delay, 0, mult, 0);

    connectBlockToOutput(add, 0, 0);
  }
}
