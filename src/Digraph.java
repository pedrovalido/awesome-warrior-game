public class Digraph {

    /**
     * class to be used just to iterate the graph's edge
     * there's no need to implement the remove node method
     */
    public Node head;
    public Node tail;

    public int counter;

    public Digraph(){
       // counter=0;
    }

    public void addNode(Node node){
        counter++;
        if(head==null && tail==null){
            head = node;
            tail = node;
            return;
        }
        if(counter==1){
            tail = node;
            head.setNext(node);
            tail.setPrevious(head);
            return;
        }
        tail.setNext(node);
        node.setPrevious(tail);
        tail = node;
    }

    public int getSize(){
        return counter;
    }

    public Node getFirst(){
        return head;
    }
    
}
