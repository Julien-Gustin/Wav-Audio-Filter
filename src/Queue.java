public class Queue
{
  private Node head;
  private Node tail;

  private int size;

  public Queue()
  {
    size = 0;
    head = null;
    tail = null;
  }

  public void put(Object data)
  {
    Node n = new Node(data);
    size++;
    if(head == null)
      head = tail = n;

    else
    {
      tail.setNext(n);
      tail = n;
    }
  }

  public Object get()
  {
    if(head == null)
      return null;

    Node h = head;
    head = head.getNext();
    size--;

    if(head == null)
      tail = null; // we won't destroy the object

    return h.getData();
  }

  public int size()
  {
    return size;
  }

  public void reset() // garbage collector
  {
    head = null;
    tail = null;
    size = 0;
  }
}
