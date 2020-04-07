/*
 * INFO0062 - Object-Oriented Programming by Gustin Julien & Raze Felicien
 * Demo of filter created with a CompositeFilter class
 *
 * This short class refer to an echo class
 *
 * @author: Gustin Julien & Raze Felicien
 */
import be.uliege.montefiore.oop.audio.*;

public class Demo
{
    public static void main(String[] args)
    {
      try
      {
        if(args.length < 2 || args.length  > 3 )
        {
          throw new IllegalArgumentException("Wrong inputs, read REAME.MD");
        }
        // Creates the CompositeFilter object, with one input and one output
        if(args.length == 3 && args[0].equals("Reverb"))
        {
          Artificial_Reverbator reverb = new Artificial_Reverbator();
          TestAudioFilter.applyFilter(reverb, args[1], args[2]);
        }

        else
        {
        EchoFilter echoFilter = new EchoFilter(22050, 0.4);
        TestAudioFilter.applyFilter(echoFilter, args[0], args[1]);
        }
      }
      catch(Exception e)
      {
          System.err.println("Error: " + e.getMessage());
      }
    }
}
