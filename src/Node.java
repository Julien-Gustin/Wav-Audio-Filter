public class Node
{
  private Node next;
  private Object data;

  public Node(Object data)
  {
    this.data = data;
    next = null;
  }

  public Node(Object data, Node n) // polymorphism
  {
    this.data = data;
    next = n;
  }

  public void setNext(Node newNode)
  {
    next = newNode;
  }

  public Node getNext()
  {
    return next;
  }

  public Object getData()
  {
    return data;
  }

}
