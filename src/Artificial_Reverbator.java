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
      // call the compositefilter constructor
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

          CompositeFilter seq1 = new CompositeFilter(1, 1);
          CompositeFilter seq2 = new CompositeFilter(1, 1);
          CompositeFilter seq3 = new CompositeFilter(1, 1);

          seq1.addBlock(Dpass);

          seq2.addBlock(delay2);
          seq2.addBlock(delay3);
          seq2.addBlock(nested1);

          seq3.addBlock(delay4);
          seq3.addBlock(nested2);

          this.addBlock(delay1);
          this.addBlock(gain1);
          this.addBlock(gain2);
          this.addBlock(gain3);
          this.addBlock(gain4);
          this.addBlock(add1);
          this.addBlock(add2);
          this.addBlock(add3);
          this.addBlock(lowPass);
          this.addBlock(seq1);
          this.addBlock(seq2);
          this.addBlock(seq3);

          //Sequence one
          seq1.connectInputToBlock(0, Dpass, 0);
          seq1.connectBlockToOutput(Dpass, 0, 0);

          //Sequence two
          seq2.connectInputToBlock(0, delay2, 0);
          seq2.connectBlockToBlock(delay2, 0, nested1, 0);
          seq2.connectBlockToBlock(nested1, 0, delay3, 0);
          seq2.connectBlockToOutput(delay3, 0, 0);

          //Sequence three
          seq3.connectInputToBlock(0, delay4, 0);
          seq3.connectBlockToBlock(delay4, 0, nested2, 0);
          seq3.connectBlockToOutput(nested2, 0, 0);


          //Combinaison of sequence => Artificial Reverbator
          this.connectInputToBlock(0, add1, 0);

          //Connect Block together
          this.connectBlockToBlock(add1, 0, seq1, 0);
          this.connectBlockToBlock(seq1, 0, delay1, 0); // the delay wich appear explicitely

          this.connectBlockToBlock(delay1, 0, gain1, 0);
          this.connectBlockToBlock(delay1, 0, seq2, 0);

          this.connectBlockToBlock(seq2, 0, gain2, 0);
          this.connectBlockToBlock(gain2, 0, add2, 0);
          this.connectBlockToBlock(gain1, 0, add2, 1);
          this.connectBlockToBlock(add2, 0, add3, 0);
          this.connectBlockToBlock(seq2, 0, seq3, 0);

          this.connectBlockToBlock(seq3, 0, gain3, 0);
          this.connectBlockToBlock(gain3, 0, add3, 1);
          this.connectBlockToBlock(seq3, 0, lowPass, 0);
          this.connectBlockToBlock(lowPass, 0, gain4, 0);
          this.connectBlockToBlock(gain4, 0, add1, 1);

          //Connect Output
          this.connectBlockToOutput(add3, 0, 0);
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
