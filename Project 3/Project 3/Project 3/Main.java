/*PSEUDOCODE:
 * Reads map file to build a graph of planets and connections. 
 * Extracts pilots and routes from routes file. 
 * Calculates route weights, marking invalid ones as zero. 
 * Sorts pilots by weights, using alphabetical order for ties. 
 * Writes sorted results (pilots, weights, validity) to output file.
 */

import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner userInput = new Scanner(System.in);

        System.out.println("Provide the name of the map file");
        String mapFile = userInput.nextLine();
        System.out.println("Provide the name of the routes file");
        String routesFile = userInput.nextLine();
        userInput.close();

        Scanner mapScanner = new Scanner(new File(mapFile));
        ArrayList<String> mapArray = new ArrayList<>();

        while (mapScanner.hasNextLine()) {
            mapArray.add(mapScanner.nextLine());
        }

        mapScanner.close();
        // map initialized as a set of strings

        Graph<String, Vertex> graph = new Graph<>();
        for (String s : mapArray) {
            // creating temporary variables in order to store our data in the graph
            // variables change after each iteration
            String[] line_split = s.split(" ");
            String planetName = line_split[0];

            ArrayList<Vertex> connections = new ArrayList<>();
            // initialize the array of edges
            for (int i = 1; i < line_split.length; i++) {
                String[] c = line_split[i].split(",");
                String v = c[0];
                int w = Integer.parseInt(c[1]);
                Vertex v1 = new Vertex(v, w);
                connections.add(v1);
            }
            // add them to the graph
            graph.put(planetName, connections);
        }
        /* at this point, all planets should have their own set of connected edges,
        with respective weights to traverse through the graph
        */
        Scanner routeScanner = new Scanner(new File(routesFile));
        ArrayList<String> routesArray = new ArrayList<>();
        ArrayList<String> pilots = new ArrayList<>();
        ArrayList<Integer> weights = new ArrayList<>();
        while (routeScanner.hasNextLine()) {
            String line = routeScanner.nextLine();
            String[] split = line.split(" ", 2);
            pilots.add(split[0]);
            routesArray.add(split[1]);
        }
        FileWriter writer = new FileWriter("patrols.txt");
        for (int i = 0; i < routesArray.size(); i++) {
            int weight = 0;

            String[] split = routesArray.get(i).split(" ");
            Vertex v1 = new Vertex();
            for (int j = 0; j < split.length - 1; j++) {
                v1 = graph.search(split[j], new Vertex(split[j + 1]));

                if (v1 == null) {
                    weight = 0;

                    break;
                } else {
                    weight += v1.getWeight();
                }
            }
            // write to file
            weights.add(weight);
        }
        // now arrange it in order and then send it back to the file

        // using quicksort
        int[] arr = weights.stream().mapToInt(Integer::intValue).toArray();
        String[] pilotsArray = pilots.toArray(new String[0]);
        int n = arr.length;
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0, n - 1});

        while (!stack.isEmpty()) {
            int[] bounds = stack.pop();
            int low = bounds[0];
            int high = bounds[1];

            if (low < high) {
                int pivot = arr[high];
                String pivotPilot = pilotsArray[high];
                int i = low - 1;

                // Partition logic
                for (int j = low; j < high; j++) {
                    if (arr[j] < pivot || (arr[j] == pivot && pilotsArray[j].compareTo(pivotPilot) < 0)) {
                        i++;
                        // Swap arr
                        int temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;

                        // Swap pilotsArray
                        String tempS = pilotsArray[i];
                        pilotsArray[i] = pilotsArray[j];
                        pilotsArray[j] = tempS;
                    }
                }

                // Place the pivot in the correct position
                int temp = arr[i + 1];
                arr[i + 1] = arr[high];
                arr[high] = temp;

                String tempS = pilotsArray[i + 1];
                pilotsArray[i + 1] = pilotsArray[high];
                pilotsArray[high] = tempS;

                int pi = i + 1;

                // Push left and right subarray bounds onto the stack
                stack.push(new int[]{low, pi - 1});
                stack.push(new int[]{pi + 1, high});
            }
        }

        // Write the sorted result to the file
        for (int i = 0; i < pilotsArray.length; i++) {
            if (arr[i] == 0)
                writer.write(pilotsArray[i] + " " + arr[i] + " invalid\n");
            else
                writer.write(pilotsArray[i] + " " + arr[i] + " valid\n");
        }

        writer.close();
        routeScanner.close();
    }
}
