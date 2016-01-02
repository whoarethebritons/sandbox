import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by eden on 7/19/15.
 */
public class heap {
    class Key implements Comparable{
        int key;
        public Key(int i) {
            key = i;
        }

        @Override
        public int compareTo(Object o) {
            //a negative integer, zero, or a positive integer as this object is
            // less than, equal to, or greater than the specified object.
            Key key1 = (Key) o;
            if(key < key1.key) {
                return -1;
            }
            else if(key > key1.key) {
                return 1;
            }
            else {
                return 0;
            }
        }
    }
    PriorityQueue<Key> priorityQueue;
    int k;
    Scanner scanner = new Scanner(System.in);
    public heap(int n) {
        k = n;
        priorityQueue = new PriorityQueue(k);
        kthLargest();
    }

    public void kthLargest() {
        int count = 0;
        while(true) {
            //input for things
            int j = scanner.nextInt();
            Key i = new Key(j);
            if(count < k-1) {
                priorityQueue.add(i);
                count++;
            }
            else {
                if(count == k-1) {
                    priorityQueue.add(i);
                }
                if(j > (priorityQueue.peek().key)) {
                    //priorityQueue.poll();
                    //priorityQueue.
                    priorityQueue.add(i);
                }
                System.out.println("kth largest " + priorityQueue.poll().key);
                count++;
            }
        }
    }

    public static void main(String[] args) {
        heap h = new heap(3);
    }
}

