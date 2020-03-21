/*
 * INFO0062 - Object-Oriented Programming
 * Project basis
 *
 * Example code to filter a WAV file using audio.jar. The filter to apply has to be implemented by
 * the students first.
 */

// Imports all at once: TestAudioFilter, Filter, FilterException
import be.uliege.montefiore.oop.audio.*;

public class Example
{
    public static void main(String[] args)
    {
        try
        {
            // TODO: instanciate myFilter
            Filter mult2 = new DelayFilter(44100*5);
            Filter mult1 = new GainFilter(0.1);
            //Filter mult2 = new GainFilter(0.5);
            Filter mult3 = new GainFilter(0.5);

            Filter add = new AdditionFilter();

            CompositeFilter audioFilter = new CompositeFilter(1, 1);
//             audioFilter.addBlock(mult1);
//             audioFilter.addBlock(mult2);
//             audioFilter.addBlock(mult3);
//             audioFilter.addBlock(myFilter);
//
//             audioFilter.connectInputToBlock(0, mult1, 0);
//             audioFilter.connectBlockToBlock(mult1, 0, mult2, 0);
// ;
//
//             audioFilter.connectBlockToBlock(mult2, 0, mult3, 0);
//
//             audioFilter.connectBlockToBlock(mult3, 0, myFilter, 0);
//             audioFilter.connectBlockToOutput(myFilter, 0, 0);

            audioFilter.addBlock(mult1);
            audioFilter.addBlock(mult2);
            audioFilter.addBlock(add);

            audioFilter.connectInputToBlock(0, mult1, 0);
            audioFilter.connectInputToBlock(0, mult2, 0);
            audioFilter.connectBlockToBlock(mult1, 0, add, 0);
            audioFilter.connectBlockToBlock(mult2, 0, add, 1);
            audioFilter.connectBlockToOutput(add, 0, 0);




            TestAudioFilter.applyFilter(audioFilter, "Source2.wav", "Filtered.wav");
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
}
