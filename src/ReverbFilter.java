/*
 * INFO0062 - Object-Oriented Programming by Gustin Julien & Raze Felicien
 * Reverb of filter created with a CompositeFilter class
 *
 *
 * @author: Gustin Julien & Raze Felicien
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
            Filter multP = new GainFilter(0.9);
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
            TestAudioFilter.applyFilter(audioFilter, "Source2.wav", "FilteredNew.wav");
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
