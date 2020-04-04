 /*
 * INFO0062 - Object-Oriented Programming by Gustin Julien & Raze Felicien
 * Reverb of filter created with a CompositeFilter class
 *
 *
 * @author: Gustin Julien & Raze Felicien
 */

import be.uliege.montefiore.oop.audio.*;

public class Artificial_Reverbator extends CompositeFilter
{
    public Artificial_Reverbator() throws FilterException
    {
      super(1, 1);
        try
        {
          final int SAMPLE = 44100;

          Filter Dpass = new TwoAllpassFilter((int)(0.008*SAMPLE), 0.3, (int)(0.012*SAMPLE), 0.3),
                 delay1 = new DelayFilter((int)(0.004*SAMPLE)),
                 delay2 = new DelayFilter((int)(0.017*SAMPLE)),
                 delay3 = new DelayFilter((int)(0.031*SAMPLE)),
                 delay4 = new DelayFilter((int)(0.003*SAMPLE)),
                 gain1 = new GainFilter(0.34),
                 gain2 = new GainFilter(0.14),
                 gain3 = new GainFilter(0.14),
                 gain4 = new GainFilter(0.1),
                 nested1 = new NestedFilter((int)(0.087*SAMPLE), 0.5, (int)(0.062*SAMPLE), 0.25),
                 nested2 = new NestedFilter((int)(0.120*SAMPLE), 0.5, (int)(0.076*SAMPLE), 0.25, (int)(0.030*SAMPLE), 0.25),
                 add1 = new AdditionFilter(),
                 add2 = new AdditionFilter(),
                 add3 = new AdditionFilter(),
                 lowPass = new LowpassFilter((int)(0.002*SAMPLE), 0.7133);

         addBlock(delay1);
         addBlock(delay2);
         addBlock(delay3);
         addBlock(delay4);
         addBlock(gain1);
         addBlock(gain2);
         addBlock(gain3);
         addBlock(gain4);
         addBlock(nested1);
         addBlock(nested2);
         addBlock(add1);
         addBlock(add2);
         addBlock(add3);
         addBlock(lowPass);
         addBlock(Dpass);

         //Connect input
         connectInputToBlock(0, add1, 0);

         //Connect Block together
         connectBlockToBlock(add1, 0, Dpass, 0);
         connectBlockToBlock(Dpass, 0, delay1, 0);
         connectBlockToBlock(delay1, 0, gain1, 0);
         connectBlockToBlock(delay1, 0, delay2, 0);
         connectBlockToBlock(delay2, 0, nested1, 0);
         connectBlockToBlock(nested1, 0, delay3, 0);
         connectBlockToBlock(delay3, 0, gain2, 0);
         connectBlockToBlock(gain2, 0, add2, 0);
         connectBlockToBlock(gain1, 0, add2, 1);
         connectBlockToBlock(add2, 0, add3, 0);
         connectBlockToBlock(delay3, 0, delay4, 0);
         connectBlockToBlock(delay4, 0, nested2, 0);
         connectBlockToBlock(nested2, 0, gain3, 0);
         connectBlockToBlock(gain3, 0, add3, 1);
         connectBlockToBlock(nested2, 0, lowPass, 0);
         connectBlockToBlock(lowPass, 0, gain4, 0);
         connectBlockToBlock(gain4, 0, add1, 1);

         //Connect Output
         connectBlockToOutput(add3, 0, 0);
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
