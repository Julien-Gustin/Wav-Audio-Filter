import be.uliege.montefiore.oop.audio.*;

public class NestedFilter extends CompositeFilter
{
  public NestedFilter(int delayNested, double gainNested, int delayPass, double gainPass) throws FilterException
  {
    // call the compositefilter constructor
    super(1, 1);

    Filter allPass = new AllpassFilter(delayPass, gainPass),
           add1 = new AdditionFilter(),
           add2 = new AdditionFilter(),
           gainP = new GainFilter(gainNested),
           gainN = new GainFilter(-gainNested),
           delay = new DelayFilter(delayNested);


    addBlock(allPass);
    addBlock(gainP);
    addBlock(gainN);
    addBlock(add1);
    addBlock(add2);
    addBlock(delay);

    connectInputToBlock(0, add1, 0);
    connectInputToBlock(0, gainN, 0);

    connectBlockToBlock(add1, 0, allPass, 0);
    connectBlockToBlock(allPass, 0, delay, 0);
    connectBlockToBlock(delay, 0, add2, 1);
    connectBlockToBlock(gainN, 0, add2, 0);
    connectBlockToBlock(add2, 0, gainP, 0);
    connectBlockToBlock(gainP, 0, add1, 1);

    connectBlockToOutput(add2, 0, 0);
  }

  //polymorphism, double allpass filter in the nested
  public NestedFilter(int delayNested, double gainNested, int delayPass1, double gainPass1, int delayPass2, double gainPass2) throws FilterException
  {
    // call the compositefilter constructor
    super(1, 1);



    Filter dAllPass = new TwoAllpassFilter(delayPass1, gainPass1, delayPass2, gainPass2),
           add1 = new AdditionFilter(),
           add2 = new AdditionFilter(),
           gainP = new GainFilter(gainNested),
           gainN = new GainFilter(-gainNested),
           delay = new DelayFilter(delayNested);

    addBlock(dAllPass);
    addBlock(gainP);
    addBlock(gainN);
    addBlock(add1);
    addBlock(add2);
    addBlock(delay);

    connectInputToBlock(0, add1, 0);
    connectInputToBlock(0, gainN, 0);

    connectBlockToBlock(add1, 0, dAllPass, 0);
    connectBlockToBlock(dAllPass, 0, delay, 0);
    connectBlockToBlock(delay, 0, add2, 1);
    connectBlockToBlock(gainN, 0, add2, 0);
    connectBlockToBlock(add2, 0, gainP, 0);
    connectBlockToBlock(gainP, 0, add1, 1);

    connectBlockToOutput(add2, 0, 0);
  }
}
