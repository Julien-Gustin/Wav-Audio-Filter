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
            Filter myFilter = new GainFilter(0.3);
        //    Filter myFilter = new DummyFilter(44100 * 3);

        //Filter add = new AddFilter();
        //Filter delay = new DelayFilter(10^4);
        //Filter gain = new GainFilter(0.6);
        //Filter myFilter = new CompositeFilter(1,1);
        //myFilter.addBlock(add);
        //myFilter.addBlock(delay);
        //myFilter.addBlock(gain);
        //myFilter.connectInputToBlock(,add,);
        //myFilter.connectBlockToOutput(add,,);
        //myFilter.connectBlockToBlock(add, , delay, );
        //myFilter.connectBlockToBlock(delay, , gain, );
        //myFilter.connectBlockToBlock(gain, , add, );

            TestAudioFilter.applyFilter(myFilter, "Source.wav", "Filtered.wav");
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
}
