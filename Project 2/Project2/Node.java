//Srijan Aadhi, sxa220214
public class Node<T extends Comparable<T> /*ensures that T is a comparable class e.g: Integer */> implements Comparable<Node<T>>{
    T value;
    Node<T> left;
    Node<T> right;
    
    Node()
    {
        this.value = null;
    }

    Node(T value)
    {
        this.value = value;
    }

    @Override
    public int compareTo(Node<T> node) {
        // returns -1, 0 or 1, depending on whether the value is alphabetically less, more or the same
        return node.value.compareTo(this.value);
    }

    @Override
    public String toString()
    {
        return "" + this.value;
    }
}
