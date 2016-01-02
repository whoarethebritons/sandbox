/**
 * Created by eden on 6/23/15.
 */
public class recursion {
    public recursion() {
        stairs s = new stairs(4);
        System.out.println(s.ways(4, 2));
    }

    public class stairs {
        int numSteps;
        public stairs(int i) {
            numSteps = i;
        }
        public int stepOrder(int n, int m) {
            if (n <= 1) {
                return n;
            }
            int result = 0;
            for (int i = 1; i <= n && i <= m; i++)
                result += stepOrder(n - i, m);
            return result;
        }
        //k is number of stairs they can take at a time
        //i is number of stairs there are
        public int ways(int i, int k) {
            return stepOrder(i + 1, k);
        }
    }
    public  static void main(String[] args) {
        recursion r = new recursion();
    }
}
