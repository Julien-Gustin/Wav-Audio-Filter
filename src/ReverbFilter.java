/*
 * INFO0062 - Object-Oriented Programming by Gustin Julien & Raze Felicien
 * Reverb of filter created with a CompositeFilter class
 *
 *
 * @author: Gustin Julien & Raze Felicien
 */

import be.uliege.montefiore.oop.audio.*;

public class ReverbFilter //TODO : make a beautyfullest reverb
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

           CompositeFilter audioFilter2 = new CompositeFilter(1, 1);

           // Creates the basic blocks
           Filter delay2 = new DelayFilter(1323);
           Filter multP2 = new GainFilter(0.5);
           Filter multN2 = new GainFilter(-0.5);
           Filter add12 = new AdditionFilter();
           Filter add22 = new AdditionFilter();

           // Adds them to the CompositeFilter
           audioFilter2.addBlock(multN2);
           audioFilter2.addBlock(multP2);
           audioFilter2.addBlock(delay2);
           audioFilter2.addBlock(add12);
           audioFilter2.addBlock(add22);

         // Connects the blocks together
          audioFilter2.connectInputToBlock(0, multN2, 0);
          audioFilter2.connectInputToBlock(0, add12, 0);

          audioFilter2.connectBlockToBlock(add12, 0, delay2, 0);
          audioFilter2.connectBlockToBlock(delay2, 0, add22, 0);
          audioFilter2.connectBlockToBlock(multN2, 0, add22, 1);
          audioFilter2.connectBlockToBlock(add22, 0, multP2, 0);
          audioFilter2.connectBlockToBlock(multP2, 0, add12, 1);

          audioFilter2.connectBlockToOutput(add22, 0, 0);

          CompositeFilter audioFilter3 = new CompositeFilter(1, 1);

          // Creates the basic blocks
          Filter delay3 = new DelayFilter(1323);
          Filter multP3 = new GainFilter(0.5);
          Filter multN3 = new GainFilter(-0.5);
          Filter add13 = new AdditionFilter();
          Filter add23 = new AdditionFilter();

          // Adds them to the CompositeFilter
          audioFilter3.addBlock(multN3);
          audioFilter3.addBlock(multP3);
          audioFilter3.addBlock(delay3);
          audioFilter3.addBlock(add13);
          audioFilter3.addBlock(add23);

        // Connects the blocks together
         audioFilter3.connectInputToBlock(0, multN3, 0);
         audioFilter3.connectInputToBlock(0, add13, 0);

         audioFilter3.connectBlockToBlock(add13, 0, delay3, 0);
         audioFilter3.connectBlockToBlock(delay3, 0, add23, 0);
         audioFilter3.connectBlockToBlock(multN3, 0, add23, 1);
         audioFilter3.connectBlockToBlock(add23, 0, multP3, 0);
         audioFilter3.connectBlockToBlock(multP3, 0, add13, 1);

         audioFilter3.connectBlockToOutput(add23, 0, 0);

           CompositeFilter Reverb = new CompositeFilter(1, 1);

           Reverb.addBlock(audioFilter);
           Reverb.addBlock(audioFilter2);
           Reverb.addBlock(audioFilter3);

           Reverb.connectInputToBlock(0, audioFilter, 0);

           Reverb.connectBlockToBlock(audioFilter, 0, audioFilter2, 0);
           Reverb.connectBlockToBlock(audioFilter2, 0, audioFilter3, 0);

           Reverb.connectBlockToOutput(audioFilter3, 0, 0);

          // Applies the filter
          TestAudioFilter.applyFilter(Reverb, args[0], args[1]);
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
