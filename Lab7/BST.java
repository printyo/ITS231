package Lab7;

public class BST<T extends Comparable<T>> extends BT<T> {
    /** Create an empty binary tree */
    public BST() {

    }

    /** Create a binary tree from an object */
    public BST(T object) {
        root.element = object;
    }

    /** Create a binary tree from an array of objects */
    public BST(T[] objects) {
        for (int i = 0; i < objects.length; i++)
            insert(objects[i]);
    }

    // ----------------------------------------------
    /**
     * Insert newdata into the binary search tree
     */
    public void insert(T newdata) {
        // Ex1: Complete this program
        if (root == null) {
            // Create a new root]
            root = new BTNode<T>(newdata);
        } else {
            // Locate the parent node
            BTNode<T> parent = null;
            BTNode<T> current = root;
            // insert your code
            while (current != null) {
                if (newdata.compareTo(current.element) <= 0) { // if new data is < current
                    parent = current;
                    current = current.left;
                } else { // if new data is > current
                    parent = current;
                    current = current.right;
                }
            }// current is now null
            if (newdata.compareTo(parent.element) <= 0) { // if new data is < parent
                parent.left = new BTNode<T>(newdata);
            } else { // if newdata is > parent
                parent.right = new BTNode<T>(newdata);
            }
        }

        size++;
    }

    // ----------------------------------------------
    /**
     * Delete data from the binary search tree
     */
    // Ex2. Complete This Program
    public T delete(T item) {
        // Locate the node to be deleted and also locate its parent node
        BTNode<T> parent = null;
        BTNode<T> current = root;

        boolean currentIsLeftChild = true;

        while (current != null) {
            // insert your code
            if (item.compareTo(current.element) == 0) { //if you found the element you want to delete
                break;
            } else if (item.compareTo(current.element) < 0) { // if item < current
                parent = current;
                current = current.left;
                currentIsLeftChild = true;
            } else { // if item > current
                parent = current;
                current = current.right;
                currentIsLeftChild = false;
            }
        }

        // Case 0: item is not in the tree
        if (current == null) {
            return null;
        }

        T temp = current.element;
        // Case 1: current is the leaf

        if (current.left == null && current.right == null) {
            // insert your code
            if (parent == null) {
                root = null; // only 1 node so remove everything
            } else {
                if (currentIsLeftChild) { // check if item is on the left or right
                    parent.left = null; 
                } else { // on the right
                    parent.right = null;
                }
            }
        }

        // Case 2: If the deleted node has one child
        // Case 2.1: if its child node is on the right
        if ((current.left == null)) { // If only has one right child or no children.
            if (currentIsLeftChild) { //left of the parent
                // insert your code
                if (parent == null) {// want to remove the root
                    root = current.right;
                } else {
                    parent.left = current.right;
                }

            } else {//right of the parent
                // insert your code
                if (parent == null) {
                    root = current.right;
                } else {
                    parent.right = current.right;
                }
            }
        }
        // Case 2.2: If its child node is on the left
        else if ((current.right == null)) { // Only one left child
            if (currentIsLeftChild) {//left of parent
                // insert your code
                if (parent == null) {//if u want to delete root
                    root = current.left;
                } else {
                    parent.left = current.left;
                }
            } else {//right of parent
                // insert your code
                if (parent == null) {
                    root = current.left;
                } else {
                    parent.right = current.left;
                }
            }
        } else { // Case 3: Have both children
            BTNode<T> maxleft = current.left;
            BTNode<T> maxleftParent = current;
            while (maxleft.right != null) { //find max left
                maxleftParent = maxleft;
                maxleft = maxleft.right;
            }
            current.element = maxleft.element; //replace current with maxleft and delete last maxleft
            if (maxleft.left == null && maxleft.right == null) { // Case 3.1 if maxleft is a leaf, then ..
                // insert your code
                maxleftParent.right = null;
            } else if (maxleft.left != null) { // Case 3.2 if maxleft has a left child, then .
                // insert your code
                maxleftParent.right = maxleft.left;
            } else if (maxleftParent == current) { // Case 3.3 if maxleft is leftchild of current, then ..
                // insert your code
                if (maxleft.left == null) {
                    current.left = null;
                } else {
                    current.left = maxleft.left;
                }
            }
        }
        size--;
        return temp;
    }
    // ---------------------------------------------------------

    // Search for the data returns true if it finds the data or otherwise false
    public boolean search(T data) {
        // Ex 3: Complete this section.
        BTNode<T> temp = root;
        if (data.compareTo(temp.element) == 0) {
            return true;
        }
        while (temp != null) {
            if (data.compareTo(temp.element) == 0) {
                return true;
            } else if (data.compareTo(temp.element) < 0) {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
        // replace the following line with your code
        return false;
    }

    // ---------------------------------------------------------

    BTNode<T> findSmallest() {
        return findSmallest(root);
    }

    // ----------------------------------------------
    BTNode<T> findSmallest(BTNode<T> start) {
        // Ex 4: Complete this section.
        if (size == 0) { // empty tree
            return null;
        } else {
            while (start.left != null) {// has a left child
                start = start.left;
            }
            return start;
            // replace the following line with your code
            // return null;
        }
    }

    // ----------------------------------------------
    BTNode<T> findLargest() {
        return findLargest(root);
    }

    // ----------------------------------------------
    BTNode<T> findLargest(BTNode<T> start) {
        // Ex 5: Complete this section.
        if (size == 0) { // empty tree
            return null;
        } else {
            while (start.right != null) {// has a left child
                start = start.right;
            }
            return start;
            // replace the following line with your code
            // return null;
        }
    }

    /** Get the number of nodes in the tree */
    public int getSize() {
        return size;
    }

    /** Returns the root of the tree */
    public BTNode<T> getRoot() {
        return root;
    }

    /** Returns a path from the root leading to the specified element */
    public java.util.ArrayList<BTNode<T>> path(T e) {
        java.util.ArrayList<BTNode<T>> list = new java.util.ArrayList<BTNode<T>>();
        BTNode<T> current = root; // Start from the root

        while (current != null) {
            list.add(current); // Add the node to the list
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else
                break;
        }

        return list; // Return an array of nodes
    }

}