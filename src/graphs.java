

import com.sun.corba.se.impl.orbutil.graph.Graph;
import com.sun.corba.se.impl.orbutil.graph.GraphImpl;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by eden on 6/21/15.
 */
public class graphs {
    public class Node {
        String myName;
        ArrayList<String> myFriends;// = new ArrayList<String>();
        public Node(String n) {
            myName = n;
        }
    }

        /*
        Input:
4
A:B,C,D
B:A,D,E
C:E,B
A
         */

        Scanner scanInput = new Scanner(System.in);
        String startVal;
        HashMap<String, Node> graph;

    ArrayList<String> printed = new ArrayList<String>();


        public graphs() {
            System.out.println("prompt:");
            int loop = scanInput.nextInt();
            scanInput.nextLine();
            graph = new HashMap<String, Node>();
            String temp;
            Node tempNode;
            for(int i=0; i<loop-1; i++) {
                temp = scanInput.nextLine();
                String[] name = temp.split(":");
                if(!graph.containsKey(name[0])) {
                    tempNode = new Node(name[0]);
                } else {
                    tempNode = graph.get(name[0]);
                }
                if(name.length != 1) {
                    tempNode.myFriends = new ArrayList<String>();
                    tempNode.myFriends.addAll(Arrays.asList(name[1].split(",")));
                }
                graph.put(name[0], tempNode);
                for (String s: tempNode.myFriends) {
                    graph.putIfAbsent(s, new Node(s));
                }

            }
            startVal = scanInput.nextLine();
            graph.putIfAbsent(startVal, new Node(startVal));
            levels(graph.get(startVal).myFriends);


    }
    public void levels(ArrayList<String> pass) {
        if(pass==null) { return; }
        if(pass.containsAll(graph.get(startVal).myFriends) && graph.get(startVal).myFriends.containsAll(pass)) {
            System.out.println("I'm at the first level");
            for(String s:pass) {
                if(!printed.contains(s) && !s.equals(startVal)) {
                    System.out.print(s);
                    printed.add(s);
                }
            }
            System.out.println();
            for(String s:pass) {
                levels(graph.get(s).myFriends);
                //add in thing to keep track of level so that levels print right
            }
        }
        else {
            for(String s:pass) {
                if(!printed.contains(s)  && !s.equals(startVal)) {
                    System.out.print(s);
                    printed.add(s);
                }
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        graphs g = new graphs();
    }
}
