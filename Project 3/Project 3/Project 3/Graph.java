/*Defines a generic Graph class that extends HashMap. 
    Supports storing keys with lists of vertices. 
    Includes methods for searching vertices, either globally across the entire graph or within a specific key's list. 
    Uses compareTo for vertex matching. 
    Returns the found vertex or null if not found. */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class Graph<K, T extends Vertex> extends HashMap<K, ArrayList<T>>{
    Graph()
    {

    }
    
    public T search(T v)
    {
        //generic search - looks for element in entire hashmap
        for (Map.Entry<K,ArrayList<T>> s: this.entrySet())
        {
            ArrayList<T> array = s.getValue();
            for (T obj: array)
            {
                if (obj.compareTo(v) == 0)
                {
                    return obj;
                }
            }
        }
        return null;
    }

    public T search(K key, T v)
    {
        //specific search - looks for an element in a specified key
        ArrayList<T> array = this.get(key);
        for (T obj: array)
        {
            if (obj.compareTo(v) == 0)
            {
                return obj;
            }
        }
        return null;
    }
    
}
