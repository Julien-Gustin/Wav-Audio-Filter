import be.uliege.montefiore.oop.audio.*;

public class F
{
  public int linkIn;
  public int linkOut;

  public F[] prec;
  public CompositeFilter actuel;

  public int input; // input = {0, 1} if not => no input
  public int output; // output = {0, 1} if not => no output

  public int in; // reserved for the input
  public int out; // reserved for the outpt


  public F(CompositeFilter cf)
  {
    prec = new F [2];
    actuel = cf;
    prec[0] = null;
    prec[1] = null;
    input = -1;
    output = -1;
    in = -1;
    out = -1;
    linkIn = -1;
    linkOut = -1;
  }
}
