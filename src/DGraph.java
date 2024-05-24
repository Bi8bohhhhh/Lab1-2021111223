import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import java.io.File;
public class DGraph {
    public DGraph(){
        vertex = 0;
        edge = 0;
    }
    public void addEdge(String word1,String word2){
        int index1 = -1;
        int index2 = -1;

        for(int i = 0;i<vertex;i++){
            if(adj[i].getHead().word.equals(word1)){
                index1 = i;
            }
        }
        if(-1 == index1){
            adj[vertex] = new LinkedList(word1);
            index1 = vertex;
            vertex++;
        }
        for(int i = 0;i<vertex;i++){
            if(adj[i].getHead().word.equals(word2)){
                index2 = i;
            }
        }
        if(-1 == index2){
            adj[vertex] = new LinkedList(word2);
            index2 = vertex;
            vertex++;
        }
        for(Node node =adj[index1].getHead().next;node!=null;node=node.next){
            if(node.word.equals(word2)){
                node.weight++;	//weight表示所有入度和
                return;
            }
        }
        adj[index1].addNode(word2,index2);
    }
    public void Show(){
        for (int i = 0; i < vertex; i++) {
            Node currentNode = adj[i].getHead();
            System.out.print(currentNode.word + ": ");

            // 遍历当前节点的相邻节点
            Node adjacentNode = currentNode.next;
            if (adjacentNode == null) {
                System.out.println("null");
            } else {
                StringBuilder adjacentNodesStr = new StringBuilder();
                while (adjacentNode != null) {
                    adjacentNodesStr.append(adjacentNode.word).append(",");
                    adjacentNode = adjacentNode.next;
                }
                // 去掉最后一个逗号
                adjacentNodesStr.deleteCharAt(adjacentNodesStr.length() - 1);
                System.out.println(adjacentNodesStr);
            }
        }
    }
    public String queryBridgeWords(String word1,String word2){
        int index1 = -1,index2=-1,index3;
        Node node3,node4;
        for(int i=0;i<vertex;i++){
            if(word1.equals(adj[i].getHead().word)){
                index1 = i;
            }
            if(word2.equals(adj[i].getHead().word)){
                index2 = i;
            }
        }
        if(index1==-1 && index2==-1){
            return "No \""+word1+"\" and \""+word2+"\" in the graph!";
        }
        if(index1==-1){
            return "No \""+word1+"\" in the graph!";
        }
        if(index2==-1) {
            return "No \"" + word2 + "\" in the graph!";
        }
        String words = "";  //表示空字符串，引号表明它是字符串
        node3 = adj[index1].getHead().next; //word1的邻接节点
        while(node3!= null){
            index3 = node3.num; //num是当前节点在邻接表中的标号
            node4 = adj[index3].getHead().next;
            while(node4!=null){
                if(node4.word.equals(word2)){
                    words = words+node3.word+",";   //多个桥接词间用,间隔
                    break;
                }
                node4 = node4.next;
            }
            node3 = node3.next;
        }
        if(words.equals("")){
            return "No bridge words from \""+word1+"\" to \""+word2+"\"!";
        }else{
            return "The bridge words from \""+word1 +"\" to \""+word2 +"\" are:"+words;
        }

    }
    public String oneBridgeWord(String word1,String word2){
        int index1 = -1,index2=-1,index3;
        Node node3,node4;
        for(int i=0;i<vertex;i++){
            if(word1.equals(adj[i].getHead().word)){
                index1 = i;
            }
            if(word1.equals(adj[i].getHead().word)){
                index2 = i;
            }
        }
        if(index1==-1||index2==-1){
            return null;
        }
        node3 = adj[index1].getHead().next;
        while(node3!= null){
            index3 = node3.num;
            node4 = adj[index3].getHead().next;
            while(node4!=null){
                if(node4.word.equals(word2)){
                    return node3.word;
                }
                node4 = node4.next;
            }
            node3 = node3.next;
        }
        return null;
    }
    public String generateNewText(String inputText){
        String s = inputText.replaceAll("[\\p{Punct}\\p{Space}]+", " ");
        String[] words = s.trim().split("\\s+");

        for(int i = 0;i<words.length;i++)
        {
            words[i] = words[i].replaceAll("[^(a-zA-Z)]","");
        }
        if(words.length==1 ){
            if (words[0].equals(""))
            {
                return "the input text should contain letter";
            }
            else {
                return "the input text should be longer";
            }
        }
        String text = words[0];
        String word1,word2,word3;
        for(int index=0;index<words.length-1;index++){
            word1= words[index].toLowerCase();
            word2= words[index+1].toLowerCase();
            word3=oneBridgeWord(word1,word2);


            if(word3 == null){
                text = text +" "+word2;
            }else{
                text = text +" "+word3 +" "+word2;
            }
        }
        return text;
    }
    public String randomWalk(){
        int VertexNum = vertex;
        String Reply = new String();
        int MAXEdgeNum = 0;
        for(int i = 0 ;i< VertexNum;i++) {
            if(adj[i].nodeNum > MAXEdgeNum)
                MAXEdgeNum = adj[i].nodeNum;
        }
        Random r = new Random();
        int [][] WalkVisited = new int[VertexNum][MAXEdgeNum];
        for(int i = 0;i<VertexNum;i++) {
            for(int j = 0 ;j < MAXEdgeNum;j++)
                WalkVisited[i][j] = -1;
        }
        int WalkVertex = -1;
        while(WalkVertex < 0)
            WalkVertex = r.nextInt() % VertexNum;
        //System.out.println(WalkVertex);
        StringBuilder WordBuilder = new StringBuilder();
        String firstword = adj[WalkVertex].getHead().word;
        WordBuilder.append(firstword);
        System.out.println(WordBuilder.toString());
        Scanner scanner_walk = new Scanner(System.in);
        System.out.println("请输入1或0来表示继续游走或停止游走");
        int input_walk = scanner_walk.nextInt();
        while(true) {
            if(input_walk != 0 && input_walk != 1) {
                System.out.println("非法输入，请输入1或0");
                input_walk = scanner_walk.nextInt();
                continue;
            }
            if(input_walk == 0) {
                System.out.println("停止游走");
                break;
            }
            int next = -1;
            if(adj[WalkVertex].nodeNum == 0)
                break;
            while(next < 0)
                next = r.nextInt() % adj[WalkVertex].nodeNum;
            Node nextNode = adj[WalkVertex].getHead().next;
            for(int j = 0 ;j < next;j++)
                nextNode = nextNode.next;

            int nextVertex = nextNode.num;
            WordBuilder.append(" "+adj[nextVertex].getHead().word);
            boolean flag = false;
            int EdgeNum = 0;
            for(int j = 0; j < adj[WalkVertex].nodeNum;j++) {
                if (WalkVisited[WalkVertex][j] == -1) {
                    EdgeNum = j;
                    break;
                }
                else if (WalkVisited[WalkVertex][j] == nextVertex) {
                    flag = true;
                    break;
                }
            }

            if(flag)
                break;
            else {
                WalkVisited[WalkVertex][EdgeNum] = nextVertex;
                WalkVertex = nextVertex;
            }
            System.out.println(WordBuilder.toString());
            System.out.println("请输入1或0来表示继续游走或停止游走");
            input_walk = scanner_walk.nextInt();
        }
        //WordBuilder.append(" "+adj[nextVertex].first.Word);
        Reply = WordBuilder.toString();
        return Reply;
    }


    public String calcShortestPath(String word1,String word2)
    {
        int[][] path = new int[vertex][vertex];
        boolean[] visited = new boolean[vertex];
        int[] dist = new int[vertex];   //存储起始单词到其它单词的最短距离
        int index1 = -1;
        int index2 = -1;
        String Reply = new String();
        boolean flag = false;   //是否找到word2
        for(int i = 0;i< vertex;i++)
        {
            if(adj[i].getHead().word.equals(word1))
                index1 = i;
            if(adj[i].getHead().word.equals(word2))
                index2 = i;
            dist[i] = MAX;
            visited[i] = false;
            for(int j = 0;j< vertex;j++)
                path[i][j] = -1;
        }

        if(index1 == -1 && index2 == -1)
            return "No \""+word1+"\" and \""+word2+"\" in the graph.";
        if(index1 == -1)
            return "No \""+word1+"\" in the graph.";
        if(index2 == -1)
            return "No \""+word2+"\" in the graph.";
        visited[index1] = true;
        dist[index1] = 0;
        Node Vertex = adj[index1].getHead().next;
        while(Vertex != null)
        {
            dist[Vertex.num] = Vertex.weight;
            path[Vertex.num][0] = index1;
            Vertex = Vertex.next;
        }
        for(int i = 1;i< vertex;i++)
        {
            int MINdist = MAX;
            for(int j = 0;j<vertex;j++)
            {
                if(!visited[j] && dist[j] < MINdist)
                {
                    MINdist = dist[j];
                }
            }
            int[] interVertexs = new int[vertex];
            int interVertexNum = 0;
            for(int j = 0;j<vertex;j++)
            {
                if(!visited[j] && dist[j] == MINdist && MINdist != MAX)
                {
                    interVertexs[interVertexNum] = j;
                    visited[j] = true;
                    interVertexNum ++;
                }
            }
            if(visited[index2])
            {
                flag = true;
                break;
            }
            for(int k = 0; k < vertex;k++)
            {
                if(visited[k])
                {
                    Node NotVisited = adj[k].getHead().next;
                    while(NotVisited != null )
                    {
                        if(!visited[NotVisited.num])
                        {
                            if(dist[NotVisited.num] > dist[k] + NotVisited.weight)
                            {
                                dist[NotVisited.num] = dist[k] + NotVisited.weight;
                                path[NotVisited.num][0] = k;
                                for(int z = 1;z<vertex;z++)
                                {
                                    if(path[NotVisited.num][z] >= 0)
                                        path[NotVisited.num][z] = -1;
                                    else
                                        break;
                                }
                            }
                            else if(dist[NotVisited.num] == dist[k] + NotVisited.weight)
                            {
                                for(int z = 0;z<vertex;z++)
                                {
                                    if(path[NotVisited.num][z] ==-1)
                                    {
                                        path[NotVisited.num][z] = k;
                                        break;
                                    }
                                }
                            }
                        }
                        NotVisited = NotVisited.next;
                    }
                }
            }
        }
        if(flag == false)
        {
            Reply = "不可达";    //不可达
            return Reply;
        }
        Reply = DisplayPath(index1,index2,path);
        String[] WordSplit = Reply.split("@");
        StringBuilder ReplyBuilder = new StringBuilder();

        for(int j = 0 ; j < WordSplit.length ; j ++)
        {
            WordSplit[j] = WordSplit[j] + " " + word2;
            ReplyBuilder.append(WordSplit[j]+"@");
        }
        return ReplyBuilder.toString();
    }

    public String DisplayPath(int start,int end,int[][] path)
    {
        if (start == end)
            return adj[start].getHead().word;
        StringBuilder builder = new StringBuilder();
        for(int i = 0 ; i < vertex ; i ++)
        {
            if(path[end][i] != -1)
            {
                StringBuilder Wordbuilder = new StringBuilder();
                String MidString = DisplayPath(start,path[end][i],path);
                String[] PathWords = MidString.split("@");
                for(int j = 0 ;j < PathWords.length;j++)
                {
                    if(path[end][i] != start)
                        PathWords[j] = PathWords[j] +" " +  adj[path[end][i]].getHead().word;

                    Wordbuilder.append(PathWords[j] + "@");
                }
                builder.append(Wordbuilder.toString());
            }
        }
        return builder.toString();
    }

    private int vertex;//节点数
    private int edge;//边数
    private LinkedList[] adj = new LinkedList[MAX];
    private int version = 1;

    public static final int MAX = 32767;

}

class Node{
    public String word; //内容
    public int weight;  //权重
    public Node next;   //下一个节点
    public int num;     //邻接表位置
    public boolean painted;
    public Node(){
        word = null;
        next = null;
        num = weight = 0;
    }
    public Node(String w,int n){
        word = w;
        weight = 1;
        next = null;
        num = n;
    }
}

class LinkedList{
    private Node head = null;
    private Node tail = null;
    public int nodeNum;
    public LinkedList(){
        nodeNum = -1;
    }
    public LinkedList(String w){
        nodeNum = 0;
        Node newNode = new Node(w,-1);
        head = newNode;
        tail = newNode;
    }
    public boolean isEmpty(){

        return head==null;
    }
    public void addNode(String w,int n){
        Node newNode = new Node(w,n);
        if(isEmpty()){
            head = tail = newNode;
        }else{
            tail.next = newNode;
            tail = newNode;
        }
        nodeNum++;
    }
    public Node getHead(){
        return head;
    }
    public Node getTail(){
        return tail;
    }
}