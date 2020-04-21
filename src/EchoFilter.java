/*
 * INFO0062 - Object-Oriented Programming by Gustin Julien & Raze Felicien
 * Echo filter created with a CompositeFilter class
 *
 * This short class refer to an echo class
 *
 * @author: Gustin Julien & Raze Felicien
 */
import be.uliege.montefiore.oop.audio.*;

public class EchoFilter extends CompositeFilter
{
  public EchoFilter(int delayEcho, double gainEcho) throws FilterException
  {
    super(1, 1);

    try
    {
        // Creates the basic blocks
        Filter delay = new DelayFilter(delayEcho);
        Filter mult = new GainFilter(gainEcho);
        Filter add = new AdditionFilter();

        // Adds them to the CompositeFilter
        addBlock(mult);
        addBlock(delay);
        addBlock(add);

        // Connects the blocks together
        connectInputToBlock(0, add, 0);
        connectBlockToBlock(add, 0, delay, 0);
        connectBlockToBlock(delay, 0, mult, 0);
        connectBlockToBlock(mult, 0, add, 1);

        connectBlockToOutput(add, 0, 0);
    }
    catch(Exception e)
    {
        System.err.println("Error: " + e.getMessage());
    }
  }
}
