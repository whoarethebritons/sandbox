import java.util.Comparator;

/**
 * Created by eden on 6/22/15.
 */
public class trees {
    public class Vertex {
        Comparable mKey;
        String mData;
        Vertex mParent, mLeft, mRight;
        public Vertex(Comparable key, String data) {
            mKey = key;
            mData = data;
        }
    }
    Vertex mRoot;
    public Vertex recursiveSearching(Vertex x, Comparable key) {
        //O(h), takes as long as height of tree
        if(x == null || x.mKey == key) {
            return x;
        }
        else if(x.mKey.compareTo(key) < 0) {
            recursiveSearching(x.mLeft, key);
        }
        else {
            recursiveSearching(x.mRight, key);
        }
        return null;
    }
    public Vertex searching(Comparable key, String data) {
        Vertex toInsert = new Vertex(key,data);
        Vertex test = mRoot;
        while(test!=null && test.mKey != key) {
            int compareVal = test.mKey.compareTo(toInsert.mKey);
            //less than test
            if (compareVal < 0) {
                test = test.mLeft;
            } else {
                test = test.mRight;
            }
        }
        return test;
    }


    public void add(Comparable key, String data) {
        Vertex insert = new Vertex(key, data);
        boolean placed = false;

        Vertex parent = null;
        Vertex v = mRoot;
        if(mRoot == null) {
            mRoot = insert;
        }
        else {
            while (!placed) {
                parent = v;
                int compareVal = v.mKey.compareTo(insert.mKey);
                if (compareVal > 0) {
                    if(v.mLeft==null) {
                        v.mLeft = insert;
                        insert.mParent = v;
                        placed=true;
                    }
                    v = v.mLeft;
                } else {
                    if(v.mRight==null) {
                        v.mRight = insert;
                        insert.mParent = v;
                        placed=true;
                    }
                    v = v.mRight;
                }
            }
            insert.mParent = parent;
        }

    }
    public void swap(Vertex u, Vertex v) {
        if(u.mParent == null) {
            mRoot = v;
        }
        //I am left child
        else if(u == u.mParent.mLeft) {
            u.mParent.mLeft = v;
            System.out.println(u.mParent.mLeft == v);
        }
        else {
            u.mParent.mRight = v;
            System.out.println(u.mParent.mRight == v);
        }
        if(v != null) {
            v.mParent = u.mParent;
        }
    }
    public void delete(Vertex toDelete) {
        //Vertex toDelete = new Vertex(key,data);
        if(toDelete.mLeft == null && toDelete.mRight == null) {
            //replace it with null
            swap(toDelete, null);
        }
        else if(toDelete.mRight == null) {
            //move the left subtree up
            swap(toDelete, toDelete.mLeft);
        }
        else {
            //find the smallest... of the biggest
            Vertex v = findMinimum(toDelete.mRight);
            System.out.println("my min is " + v.mKey);
            //if the smallest is not the root of the left subtree
            if(v.mParent != toDelete) {
                //move the smallest one's right tree to where the smallest was
                //the smallest one will not have a left tree
                swap(v, v.mRight);
                v.mRight = toDelete.mRight;
                v.mRight.mParent = v;

                System.out.println(v.mKey + " " + v.mRight.mKey + " " + v.mRight.mParent.mKey);
            }
            //replace the Vertex with the smallest of the biggest
            swap(toDelete, v);
            v.mLeft = toDelete.mLeft;
            v.mLeft.mParent = v;
            System.out.println(v.mKey + " " + v.mLeft.mKey + " " + v.mLeft.mParent.mKey);
        }
        System.out.println("deleted!");
    }
    public Vertex findMinimum(Vertex v) {
        if(v == null) {return null;}
        if(v.mLeft == null) { return v; }
        return findMinimum(v.mLeft);
    }

    public void inOrder(Vertex x) {
        //theta(n)
        //sorted
        if(x != null) {
            inOrder(x.mLeft);
            System.out.println(x.mKey + " " + x.mData);
            inOrder(x.mRight);
        }
    }
    public void preOrder(Vertex x) {
        //a preorder tree walk prints the root before the values in either subtree,
        if(x!=null) {
            System.out.println(x.mKey + " " + x.mData);
            preOrder(x.mLeft);
            preOrder(x.mRight);
        }
    }
    public void postOrder(Vertex x) {
        //a postorder tree walk prints the root after the values in its subtrees.
        if(x!=null) {
            postOrder(x.mLeft);
            postOrder(x.mRight);
            System.out.println(x.mKey + " " + x.mData);
        }
    }

    public static void main(String[] args) {
        trees binaryTree = new trees();
        binaryTree.add(6, "a");
        binaryTree.add(4, "b");
        binaryTree.add(8, "c");
        binaryTree.add(3, "d");
        binaryTree.add(7, "e");
        binaryTree.add(5, "f");
        binaryTree.add(10, "g");
        binaryTree.inOrder(binaryTree.mRoot);
        binaryTree.postOrder(binaryTree.mRoot);
        binaryTree.preOrder(binaryTree.mRoot);
        //binaryTree.delete(binaryTree.mRoot);
        binaryTree.inOrder(binaryTree.mRoot);
        trees bt2 = new trees();
        bt2.add(8, "e");
        bt2.add(7, "b");
        bt2.add(10, "c");
        bt2.add(11, "d");
        bt2.inOrder(bt2.mRoot);
        //bt2.minTreePath(bt2.mRoot);
        System.out.println("sub tree");
        System.out.println(binaryTree.isSubTree(binaryTree.mRoot, bt2.mRoot));
    }

    /*public static int minTreePath(Vertex t)
    {
        // INSERT YOUR CODE HERE
        //shortest path would be not moving
        System.out.println("hello");
        if(t.mLeft == null || t.mRight == null) { return 1; }
        return DFS(t,"hi");
    }
    // METHOD SIGNATURE ENDS
    public static int DFS(Vertex t, String s) {
        System.out.println(s);
        System.out.println(t.mData);
        int leftSide = DFS(t.mLeft, "left");
        int rightSide = DFS(t.mRight,"right");
        //if(t.left == null || t.right == null) {}
        boolean side1,side2;
        if( !(side1 && side2) || (side1||side2) ) {

        }
        return Math.min(leftSide, rightSide);

    }*/


    public boolean equals(Vertex v1, Vertex v2) {
        if(v1 == v2) {return true;} //they are equal
        if(v1 == null || v2 == null) { return false;} //only one of them can be null at this point
        //finally we can check to see if they are actually equal
        if(v1.mKey != v2.mKey) { return false; }
        //it has to equal on both left subtree && right subtree
        return equals(v1.mLeft, v2.mLeft) && equals(v2.mRight, v2.mRight);
    }
    public boolean isSubTree(Vertex v1, Vertex v2) {
        if(v2 == null) return true; //a null node would be a subtree
        if(v1 == null) return false; //if the tree we are finding subtrees in is null and the second vertex is not null, not a subtree

        //checks if subtree of root || checks if subtree on left || checks if subtree on right
        return equals(v1,v2) || isSubTree(v1.mLeft, v2) || isSubTree(v1.mRight, v2);
    }


































}
