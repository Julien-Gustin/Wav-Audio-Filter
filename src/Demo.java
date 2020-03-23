/*
 * INFO0062 - Object-Oriented Programming by Gustin Julien & Raze Felicien
 * Demo of filter created with a CompositeFilter class
 *
 * This short class refers to an echo class
 *
 * @author: Gustin Julien & Raze Felicien
 */
import be.uliege.montefiore.oop.audio.*;

public class Demo
{
    public static void main(String[] args)
    {
        try
        {
            // Creates the CompositeFilter object, with one input and one output
            CompositeFilter audioFilter = new CompositeFilter(1, 1);

            // Creates the basic blocks
            Filter delay = new DelayFilter(22050);
            Filter mult = new GainFilter(0.6);
            Filter add = new AdditionFilter();

            // Adds them to the CompositeFilter
            audioFilter.addBlock(mult);
            audioFilter.addBlock(delay);
            audioFilter.addBlock(add);

            // Connects the blocks together
            audioFilter.connectInputToBlock(0, add, 0);

            audioFilter.connectBlockToBlock(add, 0, delay, 0);
            audioFilter.connectBlockToBlock(delay, 0, mult, 0);
            audioFilter.connectBlockToBlock(mult, 0, add, 1);

            audioFilter.connectBlockToOutput(add, 0, 0);

            // Applies the filter
            TestAudioFilter.applyFilter(audioFilter, args[0], args[1]);
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
