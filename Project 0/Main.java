import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws IOException{
        int index = 0;
        Scanner scan = new Scanner(new File("test1.txt"));
        Scanner scan1 = new Scanner(new File("test1.txt"));
          //creates the 3-d array
        int count = 0;
        
      

        String data = "";

        //counts the number of lines in a list
        while (scan1.hasNextLine() && count < 20)
        {
            scan1.nextLine();
            count++;
        }

        scan1.close();

        String[][] data_combined = new String[count][]; //declare the array with the number of lines
        String[] names = new String[count];
        String[][][] fileContent = new String[count][16][2];
        double [][][] fileNumbers = new double[count][16][2];
        //the idea of this loop is to take all the lines in the file and 
        while(scan.hasNextLine()){
            data = scan.nextLine(); //get one line of data
            String[] data_split = data.split(" "); //splits each line into an array of words
            data_combined[index] = data_split;
            
            index++;
        }

        createArray(fileContent, fileNumbers, data_combined, names);
        scan.close();

        PrintWriter writer = new PrintWriter("pilot_areas.txt");

        // Calculate the area for each pilot and write to file
        for (int i = 0; i < count - 1; i++) {
            double area_turf = calcArea(fileNumbers[i]);
            writer.println(names[i] + "\t" + area_turf); // Write name and area
        }
        
        writer.close();
        


        // for (int i = 0; i < 10; i++)
        // {
        //     for (int j = 0; j < 16; j++)
        //     {
        //         for (int k = 0; k < 2; k++)
        //         {
        //             // System.out.println(fileContent[i][j][k]);
        //         }
        //     }
        // }        
    }

    public static void createArray(String[][][] array, double[][][]fileNums, String[][] comb_data, String[] list_names)
    {   
        int ind = 0;

        while (ind < comb_data.length)
        {
            String[] d_split = comb_data[ind]; //takes each line and stores in array

            list_names[ind] = d_split[0]; //gets the name
            // System.out.println(array.length);
                
            for (int j = 1; j < d_split.length; j++) {
                String[] coordinate = d_split[j].split(","); 
                array[ind][j - 1][0] = coordinate[0]; 
                array[ind][j - 1][1] = coordinate[1]; 


                fileNums[ind][j - 1][0] = Double.parseDouble(coordinate[0]); 
                fileNums[ind][j - 1][1] = Double.parseDouble(coordinate[1]); 
            }
            ind++;
        }
         
            
    }

    public static double calcArea(double[][] array)
    {
        int accumulator = 0;
        for (int j = 0; j < array[0].length-1; j++)
        {
            accumulator += (array[j][0] + array[j+1][0]) * (array[j+1][1] - array[j][1]);
        }
        double area = 0.5 * accumulator;
        if (area < 0)
            area *= -1;
        return area;
    }
}