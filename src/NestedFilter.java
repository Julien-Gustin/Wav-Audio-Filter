import be.uliege.montefiore.oop.audio.*;

public class NestedFilter extends CompositeFilter
{
  public NestedFilter(int delayNested, double gainNested, int delayPass, double gainPass) throws FilterException
  {
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

  public NestedFilter(int delayNested, double gainNested, int delayPass1, double gainPass1, int delayPass2, double gainPass2) throws FilterException
  {
    super(1, 1);



    Filter allPass1 = new AllpassFilter(delayPass1, gainPass1),
           allPass2 = new AllpassFilter(delayPass2, gainPass2),
           add1 = new AdditionFilter(),
           add2 = new AdditionFilter(),
           gainP = new GainFilter(gainNested),
           gainN = new GainFilter(-gainNested),
           delay = new DelayFilter(delayNested);


    addBlock(allPass1);
    addBlock(allPass2);
    addBlock(gainP);
    addBlock(gainN);
    addBlock(add1);
    addBlock(add2);
    addBlock(delay);

    connectInputToBlock(0, add1, 0);
    connectInputToBlock(0, gainN, 0);

    connectBlockToBlock(add1, 0, allPass1, 0);
    connectBlockToBlock(allPass1, 0, allPass2, 0);
    connectBlockToBlock(allPass2, 0, delay, 0);
    connectBlockToBlock(delay, 0, add2, 1);
    connectBlockToBlock(gainN, 0, add2, 0);
    connectBlockToBlock(add2, 0, gainP, 0);
    connectBlockToBlock(gainP, 0, add1, 1);

    connectBlockToOutput(add2, 0, 0);
  }
}
