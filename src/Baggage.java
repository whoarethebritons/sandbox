import java.lang.reflect.Array;

/**
 * Created by eden on 6/17/15.
 */
public class Baggage {
    public class Bin {
        String bin;
        public Bin(String s) {
            bin = s;
        }
        public String toString() {
            return bin;
        }
    }

    String[] bins;
    int turn, mn, turns, zero, newzero, mzero, mfirst;
    public Baggage (int n) {
        bins = new String[n * 4];
        System.out.println(n*4);
        mzero = n * 2 - 2;
        newzero = mzero;
        zero = n * 2 - 2;
        mfirst = n*2;
        turn = 0;
        mn = n;
        double mturns = (double) n;
        turns = (int) Math.ceil(mturns/2);
        System.out.println("you have " + turns + " turns");
        for (int i = 0; i < bins.length; i++) {
            bins[i]= i + " blank";
            if(i >= bins.length/2) {
                if ((i % 2) == 0) {
                    bins[i] = i + " B";
                } else {
                    bins[i] = i + " A";
                }
            }
        }
        for(String b: bins) {
            System.out.print(" " + b + " ");
        }
    }
    public int swapping(int empty, int index) {
        System.out.println();
        System.out.println("swapping " + bins[empty] + " with " + bins[index]);

        System.out.println("swapping " + bins[empty+1] + " with " + bins[index+1]);
        System.out.println("before move:");
        for(String b: bins) {
            System.out.print(" " + b + " ");
        }
        System.out.println();
        bins[empty] = bins[index];
        bins[empty +1] = bins[index +1];
        bins[index] = "blank";
        bins[index +1] = "blank";
        turn++;
        System.out.println("after " + turn +" move(s)");
        for(String b: bins) {
            System.out.print(" " + b + " ");
        }
        //the empty index
        return index;
    }


    public void moving(int first, int last, int empty) {
        System.out.println();
        System.out.println("first: " + first + " last: " + last + " empty: " + empty + " zero: " + zero);
        //if we have two to the left and are still on the rearranging part
        if(empty == zero && turn <= turns) {
            System.out.println("\nAB swap");

            int m = 2 * (last - first) - 1;
            if(first != mfirst) {
                m = (2 * (last - first) - 1) + first;
            }
            empty = swapping(empty, m);


        }
        if(turn< turns) {
            System.out.println("\nBA swap");
            empty = swapping(empty, first+2);
            zero = zero + 4;
        }

        if(((first+4) < (last-4)) && turn<turns) {
            moving(first + 4, last - 4, empty);
        }
        if(turn>=turns && turn<= mn) {
            System.out.println("\nAA or BB swaps");
            empty = swapping(empty, first - 1);
            empty = swapping(empty, last - 1);
        }
    }




    public static void main(String[] args) {
        Baggage b = new Baggage(5);
        b.moving(10,19,8);
        Baggage c= new Baggage(6);
        c.moving(12,23,10);
    }
}
