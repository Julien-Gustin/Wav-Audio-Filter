/*
 * INFO0062 - Object-Oriented Programming by Gustin Julien & Raze Felicien
 * Demo of filter created with a CompositeFilter class
 *
 *
 * @author: Gustin Julien & Raze Felicien
 */
import be.uliege.montefiore.oop.audio.*;

public class Test
{
    public static void main(String[] args)
    {
        try
        {
            // Creates the CompositeFilter object, with one input and two outputs
            CompositeFilter audioFilter = new CompositeFilter(1, 2);

            // Creates the basic blocks
            Filter delay = new DelayFilter(22050);
            Filter mult = new GainFilter(0.6);
            Filter add = new AdditionFilter();

            // Adds them to the CompositeFilter
            audioFilter.addBlock(mult);
            audioFilter.addBlock(delay);

            // Connects the blocks together
            audioFilter.connectInputToBlock(0, mult, 0);

            audioFilter.connectBlockToBlock(mult, 0, delay, 0);

            audioFilter.connectBlockToOutput(mult, 0, 0);
            audioFilter.connectBlockToOutput(delay, 0, 1); // L'erreur a lieu ici
            CompositeFilter audioFilterTrue = new CompositeFilter(1, 1);

            audioFilterTrue.addBlock(audioFilter);

            audioFilterTrue.addBlock(add);

            audioFilterTrue.connectInputToBlock(0, audioFilter, 0);
            audioFilterTrue.connectBlockToBlock(audioFilter, 0, add, 0);
            audioFilterTrue.connectBlockToBlock(audioFilter, 1, add, 1);
            audioFilterTrue.connectBlockToOutput(add, 0, 0);

            // Applies the filter
            TestAudioFilter.applyFilter(audioFilterTrue, args[0], args[1]);
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
