import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by eden on 6/22/15.
 */
public class weightedGraph {
    public class Node{
        String city;
        int weight =0;
        boolean discovered=false;
        Node prev=null;
        ArrayList<Edge> myEdges;
        public Node(String i) {
            city = i;
        }
    }
    public class Edge{
        Node start;
        Node end;
        int weight;
        public Edge(Node s, Node e, int w) {
            start = s;
            end = e;
            weight = w;
            //System.out.println(toString());
        }
        public String toString() {
            return "Start: " + start.city + " End: " + end.city + " Weight: " + weight;
        }
    }
    Random rand = new Random();
    HashMap<String, Node> wgraph;
    public weightedGraph() {
        wgraph = new HashMap<String, Node>();
        String[] cities = {"LA", "New York", "Boston", "Dallas", "Salt Lake City"};
        ArrayList<Edge> q = new ArrayList<Edge>();
        ArrayList<Edge> q1 = new ArrayList<Edge>();
        ArrayList<Edge> q2 = new ArrayList<Edge>();
        ArrayList<Edge> q3 = new ArrayList<Edge>();
        ArrayList<Edge> q4 = new ArrayList<Edge>();

        for(int i =0; i < cities.length; i++) {
            Node m = new Node(cities[i]);
            wgraph.putIfAbsent(cities[i], m);
        }
        Node s = wgraph.get(cities[0]);
        q.add(new Edge(s, wgraph.get(cities[1]), rand.nextInt(300)));
        q.add(new Edge(s, wgraph.get(cities[4]), rand.nextInt(300)));
        s.myEdges = q;
        for(Edge e: s.myEdges) {
            System.out.println(e);
        }
        Node n = wgraph.get(cities[1]);
        q1.add(new Edge(n, wgraph.get(cities[2]), rand.nextInt(300)));
        q1.add(new Edge(n, wgraph.get(cities[3]), rand.nextInt(300)));
        q1.add(new Edge(n, wgraph.get(cities[4]), rand.nextInt(300)));
        n.myEdges = q1;

//        q.clear();
        Node n1 = wgraph.get(cities[2]);
        q2.add(new Edge(n1, wgraph.get(cities[0]), rand.nextInt(300)));
        q2.add(new Edge(n1, wgraph.get(cities[4]), rand.nextInt(300)));
        n1.myEdges = q2;
        //q.clear();
        Node n2 = wgraph.get(cities[3]);
        q3.add(new Edge(n2, wgraph.get(cities[0]), rand.nextInt(300)));
        q3.add(new Edge(n2, wgraph.get(cities[4]), rand.nextInt(300)));
        n2.myEdges = q3;
        //q3.clear();
        Node n3 = wgraph.get(cities[4]);
        q4.add(new Edge(n3, wgraph.get(cities[3]), rand.nextInt(300)));
        n3.myEdges = q4;
        //q.clear();
        breadthFirst(wgraph.get("LA"));
        System.out.println("\nand now depth\n");
        depthFirsth(wgraph.get("LA"));
    }

    public void Dijkstras(String startCity) {

    }
    /*
    Boston 5 LA,Dallas
    Boston 3 NewYork,LA
     */
    public void breadthFirst(Node u) {
        LinkedBlockingQueue<Node> queue = new LinkedBlockingQueue<Node>();
        queue.add(u);
        u.discovered = true;
        System.out.println(u.city);
        while(queue.peek() != null) {
            Node w = queue.poll();
            System.out.println("City: " + w.city);
            for(Edge s: w.myEdges) {
                //System.out.println(s);
                Node v = s.end;
                //System.out.println(v.city);
                if (!v.discovered) {
                    //weight is equal to the previous weight plus the weight of the edge
                    v.weight = w.weight + s.weight;
                    //System.out.println(v.weight);
                    v.prev = w;
                    v.discovered = true;
                    queue.offer(v);
                }

            }
            Object[] test = queue.toArray();
            for(int i = 0; i < test.length; i++) {
                System.out.println(((Node)test[i]).city);
            }
            System.out.println("that's the q");
        }
    }
    public void depthFirsth(Node u) {
        for (Node v : wgraph.values()) {
            v.discovered = false;
        }
        Stack<Node> stack = new Stack<Node>();
        stack.push(u);
        u.discovered = true;
        System.out.println(u.city);
        while(!stack.empty()) {
            Node w = stack.pop();
            System.out.println("City: " + w.city);
            for(Edge s: w.myEdges) {
                //System.out.println(s);
                Node v = s.end;
                //System.out.println(v.city);
                if (!v.discovered) {
                    //weight is equal to the previous weight plus the weight of the edge
                    v.weight = w.weight + s.weight;
                    //System.out.println(v.weight);
                    v.prev = w;
                    v.discovered = true;
                    stack.push(v);
                }

            }
            Object[] test = stack.toArray();
            for(int i = 0; i < test.length; i++) {
                System.out.println(((Node)test[i]).city);
            }
            System.out.println("that's the stack");
        }

    }
    public boolean eulerCircuit() {
        ArrayList<String> circuit = new ArrayList<String>();

        return false;
    }
    public boolean eulerPath() {

        return false;
    }

    public static void main(String[] args) {
        weightedGraph g = new weightedGraph();

    }
}
