public class Node {
    public String word; //内容
    public int weight;  //权重
    public Node next;   //下一个节点
    public int num;     //邻接表位置

    public Node() {
        word = null;
        next = null;
        num = weight = 0;
    }

    public Node(String w, int n) {
        word = w;
        weight = 1;
        next = null;
        num = n;
    }
}