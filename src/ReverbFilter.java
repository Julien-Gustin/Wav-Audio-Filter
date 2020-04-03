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
          AllpassFilter pass1 = new AllpassFilter(1323, 0.5);
          AllpassFilter pass2 = new AllpassFilter(1323, 0.5);
          AllpassFilter pass3 = new AllpassFilter(1323, 0.5);

          CompositeFilter reverb = new CompositeFilter(1, 1);
          reverb.addBlock(pass1);
          reverb.addBlock(pass2);
          reverb.addBlock(pass3);

          reverb.connectInputToBlock(0, pass1, 0);
          reverb.connectBlockToBlock(pass1, 0, pass2, 0);
          reverb.connectBlockToBlock(pass2, 0, pass3, 0);

          reverb.connectBlockToOutput(pass3, 0, 0);

          TestAudioFilter.applyFilter(reverb, args[0], args[1]);
        }
        catch(Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
