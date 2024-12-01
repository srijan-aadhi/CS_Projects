//Srijan Aadhi, sxa220214
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException
    {
        Scanner scan = new Scanner(System.in);

        //gathering the file names from the user
        System.out.println("Please provide the name of the database file.");
        String databaseFile = scan.nextLine();

        System.out.println("Please provide the name of the batch commands file.");
        String commandsFile = scan.nextLine();
        scan.close();

        //read file and extract its contents
        Scanner dataScanner = new Scanner(new File(databaseFile));
        Scanner commandScanner = new Scanner(new File(commandsFile));

        ArrayList<String> data = new ArrayList<String>();
        ArrayList<String> commands = new ArrayList<String>();

        //add data to arraylist
        while (dataScanner.hasNextLine())
        {
            data.add(dataScanner.nextLine());
        }

        //add data to arraylist
        while (commandScanner.hasNextLine())
        {
            commands.add(commandScanner.nextLine());
        }

        dataScanner.close();
        commandScanner.close();

        
        BinTree<Game> BST = new BinTree<Game>();
        
        for (int i = 0; i < data.size(); i++)
        {
            String[] parts = data.get(i).split(", ");
            String name = parts[0];
            int score = Integer.parseInt(parts[1]);
            String init = parts[2];
            int plays = Integer.parseInt(parts[3]);
            Game obj = new Game(name, score, init, plays);
            Node<Game> objNode = new Node<Game>(obj);

            BST.root = BST.insert(objNode, BST.root);
        }


        writeTreeToFile(BST, "cidercade (code result).dat");

    }

    private static void writeTreeToFile(BinTree<Game> tree, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        if (tree.root == null) {
            writer.write("The tree is empty.");
        } else {
            Queue<Node<Game>> queue = new LinkedList<>();
            queue.add(tree.root);

            while (!queue.isEmpty()) {
                Node<Game> current = queue.poll();
                writer.write(current.value.name + ", " +
                             current.value.highScore + ", " +
                             current.value.initials + ", " +
                             current.value.plays + ", " +
                             "$" + String.format("%.2f", current.value.plays * 0.25) + "\n");

                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
        }

        writer.close();
        System.out.println("Tree data written to file: " + fileName);
    }

    //ascending
    public static ArrayList<String> ascending(ArrayList<String> array, ArrayList<String> newArray) {
        if (array.isEmpty()) {
            return newArray; // Base case: if the original list is empty, return the sorted list
        }
    
        // Find the index of the minimum element
        int minIndex = 0;
        String lowest = array.get(0);
        for (int i = 1; i < array.size(); i++) {
            if (array.get(i).compareTo(lowest) < 0) {
                lowest = array.get(i);
                minIndex = i;
            }
        }
    
        // Add the minimum element to the new array and remove it from the original array
        newArray.add(lowest);
        array.remove(minIndex);
    
        // Recursively call the method to continue sorting the remaining elements
        return ascending(array, newArray);
    }

    //descending
    public static ArrayList<String> descending(ArrayList<String> array, ArrayList<String> newArray) {
        if (array.isEmpty()) {
            return newArray; // Base case: if the original list is empty, return the sorted list
        }
    
        // Find the index of the maximum element
        int maxIndex = 0;
        String highest = array.get(0);
        for (int i = 1; i < array.size(); i++) {
            if (array.get(i).compareTo(highest) > 0) {
                highest = array.get(i);
                maxIndex = i;
            }
        }
    
        // Add the maximum element to the new array and remove it from the original array
        newArray.add(highest);
        array.remove(maxIndex);
    
        // Recursively call the method to continue sorting the remaining elements
        return descending(array, newArray);
    }
    
    
}
