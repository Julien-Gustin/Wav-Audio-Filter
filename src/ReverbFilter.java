/*
 * INFO0062 - Object-Oriented Programming
 * Example of filter created with a CompositeFilter class
 *
 * This short class demonstrates an example of filter created with the CompositeFilter as
 * described by the statement.
 *
 * The filter here consists of two GainFilter (with real value 0.1) connected to the input which
 * both feeds an AdditionFilter. When applied to a WAV file, this construction produces the same
 * sound as the original WAV file but with a lower volume.
 *
 * Regardless of how you designed your project, your final library MUST compile while using this
 * file as a main program. Compilation errors would mean that:
 * -classes described in the statement (i.e., AdditionFilter, GainFilter, DelayFilter and
 *  CompositeFilter) aren't correctly named,
 * -the interface of the CompositeFilter class isn't correctly named or does not use the same
 *  parameters as described in the statement.
 * However, the way you handle exceptions (if used) is entirely free.
 *
 * @author: J.-F. Grailet (ULiege)
 */

import be.uliege.montefiore.oop.audio.*;

public class ReverbFilter
{
    public static void main(String[] args)
    {
        try
        {
            // Creates the CompositeFilter object, with one input and one output
            CompositeFilter audioFilter = new CompositeFilter(1, 1);

            // Creates the basic blocks
            Filter delay = new DelayFilter(1323);
            Filter multP = new GainFilter(0.5);
            Filter multN = new GainFilter(-0.5);
            Filter add1 = new AdditionFilter();
            Filter add2 = new AdditionFilter();

            // Adds them to the CompositeFilter
            audioFilter.addBlock(multN);
            audioFilter.addBlock(multP);
            audioFilter.addBlock(delay);
            audioFilter.addBlock(add1);
            audioFilter.addBlock(add2);

            // Connects the blocks together
             audioFilter.connectInputToBlock(0, multN, 0);
             audioFilter.connectInputToBlock(0, add1, 0);
             audioFilter.connectBlockToBlock(add1, 0, delay, 0);
             audioFilter.connectBlockToBlock(delay, 0, add2, 0);
             audioFilter.connectBlockToBlock(multN, 0, add2, 1);
             audioFilter.connectBlockToBlock(add2, 0, multP, 0);
             audioFilter.connectBlockToBlock(multP, 0, add1, 1);
             audioFilter.connectBlockToOutput(add2, 0, 0);

            // Applies the filter
            TestAudioFilter.applyFilter(audioFilter, "Source2.wav", "Filtered.wav");
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
