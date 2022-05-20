public class Node {
    private Edge edge;

    private Node previous;

    private Node next;

    public Node(){

    }

    public Node(Edge edge, Node previous, Node next) {
        this.edge = edge;
        this.previous = previous;
        this.next = next;
    }

    public Edge getEdge(){
        return edge;
    }

    public Node getPrevious(){
        return previous;
    }

    public Node getNext(){
        return next;
    }

    public boolean hasNext(){
        return next!= null;
    }

    public void setNext(Node node){
        this.next = node;
    }

    public void setPrevious(Node node){
        this.previous = node;
    }
}