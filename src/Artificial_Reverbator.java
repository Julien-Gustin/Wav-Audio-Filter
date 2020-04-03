/*
 * INFO0062 - Object-Oriented Programming by Gustin Julien & Raze Felicien
 * Reverb of filter created with a CompositeFilter class
 *
 *
 * @author: Gustin Julien & Raze Felicien
 */

import be.uliege.montefiore.oop.audio.*;

public class Artificial_Reverbator
{
    public static void main(String[] args)
    {
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

          CompositeFilter reverb = new CompositeFilter(1, 1);

          reverb.addBlock(delay1);
          reverb.addBlock(delay2);
          reverb.addBlock(delay3);
          reverb.addBlock(delay4);
          reverb.addBlock(gain1);
          reverb.addBlock(gain2);
          reverb.addBlock(gain3);
          reverb.addBlock(gain4);
          reverb.addBlock(nested1);
          reverb.addBlock(nested2);
          reverb.addBlock(add1);
          reverb.addBlock(add2);
          reverb.addBlock(add3);
          reverb.addBlock(lowPass);
          reverb.addBlock(Dpass);

          reverb.connectInputToBlock(0, add1, 0);

          reverb.connectBlockToBlock(add1, 0, Dpass, 0);
          reverb.connectBlockToBlock(Dpass, 0, delay1, 0);
          reverb.connectBlockToBlock(delay1, 0, gain1, 0);
          reverb.connectBlockToBlock(delay1, 0, delay2, 0);
          reverb.connectBlockToBlock(delay2, 0, nested1, 0);
          reverb.connectBlockToBlock(nested1, 0, delay3, 0);
          reverb.connectBlockToBlock(delay3, 0, gain2, 0);
          reverb.connectBlockToBlock(gain2, 0, add2, 0);
          reverb.connectBlockToBlock(gain1, 0, add2, 1);
          reverb.connectBlockToBlock(add2, 0, add3, 0);
          reverb.connectBlockToBlock(delay3, 0, delay4, 0);
          reverb.connectBlockToBlock(delay4, 0, nested2, 0);
          reverb.connectBlockToBlock(nested2, 0, gain3, 0);
          reverb.connectBlockToBlock(gain3, 0, add3, 1);
          reverb.connectBlockToBlock(nested2, 0, lowPass, 0);
          reverb.connectBlockToBlock(lowPass, 0, gain4, 0);
          reverb.connectBlockToBlock(gain4, 0, add1, 1);

          reverb.connectBlockToOutput(add3, 0, 0);

          TestAudioFilter.applyFilter(reverb, args[0], args[1]);
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
