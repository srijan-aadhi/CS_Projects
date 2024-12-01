import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class test {
    public static void main(String args[])
    {
        ArrayList arr = new ArrayList();
        arr.add("Hello");
        arr.add("monke");
        arr.remove("monke");
        arr.clear();
        

        Stack<String> games = new Stack<String>();
        games.add("Call of Duty: Ghost");
        games.add("Guitar Hero");
        games.add("Super Monkey Ball");

        Stack<Character> tower = new Stack<Character>();
        tower.add('R'); //adds to the bottom the stack
        tower.add('B');
        tower.add('Y');

        //this is how the stack looks right now

        // |'Y'|
        // |'B'|
        // |'R'|


        tower.empty(); // checks if the stack is empty.
        tower.get(1); //returns the element in the index.
        tower.size();

        Queue<String> bbqline = new LinkedList<String>();

        bbqline.add("Jackson");
        bbqline.add("Tyreke");
        bbqline.add("Susan");

        bbqline.poll(); //takes the first person in line out of the queue
        bbqline.peek(); //looks at the first person in line
        bbqline.size(); //gets the size
        bbqline.contains("C"); //checks if it contains C
        bbqline.toArray(); //converts to an array
        
        LinkedList<Integer> linky = new LinkedList<Integer>();
        linky.add(6);
        linky.add(78);
        linky.add(1);

        System.out.println(linky.get(1));

        Iterator it = linky.iterator();
        while (it.hasNext()) {
            if ((int)it.next() == 78)
            {
                System.out.println("We found 78");
            }
        }
    }
}
