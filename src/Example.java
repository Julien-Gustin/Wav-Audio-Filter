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
            //Filter myFilter = new GainFilter(0.2);
            Filter myFilter = new DelayFilter(1);

            TestAudioFilter.applyFilter(myFilter, "Source.wav", "Filtered.wav");
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
}
