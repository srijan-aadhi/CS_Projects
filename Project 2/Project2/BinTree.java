
//Srijan Aadhi, sxa220214
import java.lang.Comparable;
import java.util.LinkedList;
import java.util.Queue;

public class BinTree<T extends Comparable<T>> {
    Node<T> value;
    Node<T> root;
    //Constructor
    BinTree()
    {
        this.root = null;
        this.value = null;
    }

    //Overloaded Constructor
    BinTree(Node<T> root)
    {
        this.root = root;
    }

    //Functions    
    
    public Node<T> insert(Node<T> node, Node<T> current)
    {
        if (current == null) //checks if the root node exists
        {
            return node;
        }

        if (current.compareTo(node) < 0) //condition to insert to the left node - compare strings by order of the word
        {
            current.left = insert(node, current.left);
        }

        else if (current.compareTo(node) == 0) //if the node is a duplicate
        {
            return node;
        }

        else //else, it goes to the left node
        {
            current.right = insert(node, current.right);
        }

        return current;
    }

    public void delete(T value) {
        this.root = deleteRecursive(this.root, value);
    }
    
    private Node<T> deleteRecursive(Node<T> current, T value) {
        if (current == null) {
            return null; // Base case: If the tree is empty or node not found
        }
    
        // Find the node to delete
        if (value.compareTo(current.value) < 0) {
            current.left = deleteRecursive(current.left, value);
        } else if (value.compareTo(current.value) > 0) {
            current.right = deleteRecursive(current.right, value);
        } else {
            // Node to delete found
            // Case 1: Node has no children (leaf node)
            if (current.left == null && current.right == null) {
                return null; 
            }
    
            // Case 2: Node has one child
            if (current.left == null) {
                return current.right; 
            } else if (current.right == null) {
                return current.left; 
            }
    
            // case 3: Node has two children
            
            T smallestValue = findMinValue(current.right);
            current.value = smallestValue; 
            current.right = deleteRecursive(current.right, smallestValue);
        }
    
        return current; 
    }
    
    // Helper method to find the minimum value in a subtree
    private T findMinValue(Node<T> root) {
        T minValue = root.value;
        while (root.left != null) {
            root = root.left;
            minValue = root.value;
        }
        return minValue;
    }
    

    public Node<T> search(Node<T> searchItem, Node<T> root) {
        if (root == null) {
            return null; // Tree is empty
        }
    
        Queue<Node<T>> searchQueue = new LinkedList<>();
        searchQueue.add(root); // Initialize the queue with the root node
    
        return searchRecursive(searchItem, searchQueue);
    }
    
    private Node<T> searchRecursive(Node<T> searchItem, Queue<Node<T>> searchQueue) {
        // base case: if the queue is empty, the item is not found
        if (searchQueue.isEmpty()) {
            return null;
        }
    
        // dequeue the front node
        Node<T> current = searchQueue.poll();
    
        // check if the current node matches the search item
        if (current.compareTo(searchItem) == 0) {
            return current; // Found the node
        }
    
        // enqueue left child if it exists
        if (current.left != null) {
            searchQueue.add(current.left);
        }
    
        // enqueue right child if it exists
        if (current.right != null) {
            searchQueue.add(current.right);
        }
    
        return searchRecursive(searchItem, searchQueue);
    }
        

   
}
