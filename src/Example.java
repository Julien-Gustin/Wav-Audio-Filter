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
            //Filter myFilter = new DelayFilter(44100*10);
            Filter mult1 = new GainFilter(0.5);
            Filter mult2 = new GainFilter(0.5);
            Filter mult3 = new GainFilter(0.1);

            CompositeFilter audioFilter = new CompositeFilter(1, 1);
            audioFilter.addBlock(mult1);
            audioFilter.addBlock(mult2);
            audioFilter.addBlock(mult3);

            audioFilter.connectInputToBlock(0, mult1, 0);
            audioFilter.connectBlockToBlock(mult1, 0, mult2, 0);
            audioFilter.connectBlockToOutput(mult2, 0, 0);

            audioFilter.connectInputToBlock(0, mult2, 0);
            audioFilter.connectBlockToBlock(mult2, 0, mult3, 0);
            audioFilter.connectBlockToOutput(mult3, 0, 0);


            TestAudioFilter.applyFilter(audioFilter, "Source.wav", "Filtered3.wav");
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
}
