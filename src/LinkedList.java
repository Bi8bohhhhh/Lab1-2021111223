public class LinkedList {
    private Node head = null;
    private Node tail = null;
    public int nodeNum;

    public LinkedList() {
        nodeNum = -1;
    }

    public LinkedList(String w) {
        nodeNum = 0;
        Node newNode = new Node(w, -1);
        head = newNode;
        tail = newNode;
    }

    public boolean isEmpty() {

        return head == null;
    }

    public void addNode(String w, int n) {
        Node newNode = new Node(w, n);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        nodeNum++;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }
}
