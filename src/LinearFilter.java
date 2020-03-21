import be.uliege.montefiore.oop.audio.*;

public class LinearFilter
{
    public static void main(String[] args)
    {
        try
        {
            // Creates the CompositeFilter object, with one input and one output
            CompositeFilter audioFilter = new CompositeFilter(1, 1);

            // Creates the basic blocks
            Filter delay = new DelayFilter(23);
            Filter multP = new GainFilter(1);
            Filter multN = new GainFilter(1);

            // Adds them to the CompositeFilter
            audioFilter.addBlock(multN);
            audioFilter.addBlock(multP);
            audioFilter.addBlock(delay);

            // Connects the blocks together
             audioFilter.connectInputToBlock(0, multN, 0);
             audioFilter.connectBlockToBlock(multN, 0, delay, 0);
             audioFilter.connectBlockToBlock(delay, 0, multP, 0);
             audioFilter.connectBlockToOutput(multP, 0, 0);


            // Applies the filter
            TestAudioFilter.applyFilter(audioFilter, "Source.wav", "Filtered.wav");

                                                  System.out.println("cc3");
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
