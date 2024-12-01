import java.util.Scanner;
import java.util.stream.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Math;
import java.util.Arrays;

public class Main{
    public static void main(String[] args) throws FileNotFoundException {
        //Scanner objects
        String regFileName = "regular.txt";
        String preferredFileName = "preferred.txt";
        String purchases = "purchases.txt";
        Scanner scanRegular = new Scanner(new File(regFileName));
        Scanner scanPreferred = new Scanner(new File(preferredFileName));
        Scanner scanPurchases = new Scanner(new File(purchases));

        int index = 0;
        int countRegular = 0;
        int countPreferred = 0;

        countRegular = verify(regFileName, preferredFileName, countRegular, countPreferred)[0]; 
        countPreferred = verify(regFileName, preferredFileName, countRegular, countPreferred)[1];    
        
        
        //create the array
        //----------------------------
        //FIX - CREATE PREFERRED CUSTOMER ARRAY AFTER CHECKING ORDERS
        //----------------------------
        Customer[] Customers = new Customer[countRegular+countPreferred];

        //initialize the regCustomers array
        while(scanRegular.hasNextLine())
        {
            //NOTE: NO NEED TO CHECK FOR INVALID VALUES FOR REGULAR.TXT
            String[] line_split = scanRegular.nextLine().split(" ");
            
            Customers[index] = new Customer(line_split[1], line_split[2], line_split[0], Float.parseFloat(line_split[3]));
            index++;
        }


        //***** USE THIS AT THE END OF THE takeOrder FUNCTION***********
        //initialize the preferred Array using polymorphism

        int i = countRegular;
        while (scanPreferred.hasNextLine())
        {
            //NOTE: NO NEED TO CHECK FOR INVALID DATA FOR PREFERRED.TXT
            String[] line_split = scanPreferred.nextLine().split(" ");
            int cashValue = Integer.parseInt(line_split[3]);
             //does not execute if countPreferred is 0, meaning no lines in preferred.txt
            if (cashValue > 200)
            {   
                Customers[i] = new Platinum(line_split[1], line_split[2], line_split[0], Integer.parseInt(line_split[3]));
                i++;
            }
            else{
                Customers[i] = new Gold(line_split[1], line_split[2], line_split[0], Float.parseFloat(line_split[3]));
                i++;
            }
            
        }

        scanRegular.close();
        scanPreferred.close();

        //arrays have been initialized

        //two dimensional array to determine differences in size
        double[][] cupDetails = {
            {2.0, 4.5, 12.0}, //{Radius, Height, Ounces}
            {2.25, 5.75, 20.0},
            {2.75, 7, 32.0}
        };

        index = 0;
        //take the orders (call the function).
        Customer[][] arrey = takeOrder(Customers, index, scanPurchases, cupDetails);

        //write to the file
        for (int j = 0; j < arrey.length; j++)
        {
            for (int k = 0; k < arrey[j].length; k++)
            {
                // System.out.println(arrey[j][k].getAmountSpent());
            }
        }


        Customer[] regularArray = arrey[0];
        Customer[] preferredArray = arrey[1];

        // Write regular customers to a file
        try (PrintWriter regularWriter = new PrintWriter("customer.dat")) {
            for (Customer customer : regularArray) {
                if (customer != null) { // Check for null to avoid NullPointerException
                    regularWriter.println(customer.getID() + " " + customer.getFirstName() + " " + customer.getLastName() + " " + customer.getAmountSpent());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error writing to regular_customers.txt: " + e.getMessage());
        }

        // Write preferred customers to a different file
        try (PrintWriter preferredWriter = new PrintWriter("preferred.dat")) {
            for (Customer customer : preferredArray) {
                if (customer != null) { // Check for null to avoid NullPointerException
                    preferredWriter.println(customer.getID() + " " + customer.getFirstName() + " " + customer.getLastName() + " " + customer.getAmountSpent());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error writing to preferred_customers.txt: " + e.getMessage());
        }

        scanPurchases.close();

    }


    //verifies the file's existence and returns the count of each file
    public static int[] verify(String regularFile, String preferredFile, int countReg, int countPref)
    {
        
        try(Stream<String> regStream = Files.lines(Paths.get(regularFile));
        Stream<String> preferredStream = Files.lines(Paths.get(preferredFile));)
        {
            countReg = (int) regStream.count();
            countPref = (int) preferredStream.count();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        int[] counts = {countReg, countPref};
        return counts;
    }


    public static Customer[][] takeOrder(Customer[] array, int ind, Scanner scan, double[][] cupDeets)
    {
        if (scan.hasNextLine()){
            String[] line_split = scan.nextLine().split(" ");
            
            //listing constant drink prices here
            final double PRICE_SODA = 0.2;
            final double PRICE_TEA = 0.12;
            final double PRICE_FRUITPUNCH = 0.15;
            String drinkType = line_split[2];

            ind = -1;
            double drinkRate = 0.0;
            double drinkPrice = 0.0;
            boolean match = true;

            double decorationPrice = 0.0;

            //check which customer made the purchase in the line we are reading
            
            /*
            --------------------------------
            UNFINISHED - INPUT VALIDATION FOR PURCHASES
            Check for: 
            Incorrect number of inputs (has to be = 5)
            No match on the customer ID
            Incorrect value for size (has to be either S, M, or L)
            Incorrect value for drink type (has to be soda, tea or fruit punch)
            Garbage characters - data type that is not supposed to be in a certain position
            --------------------------------
            */

            //sees if any of the numbers are invalid inputs
            try{
                Integer.parseInt(line_split[0]);
                Double.parseDouble(line_split[3]);
                Integer.parseInt(line_split[4]);
            }
            catch(NumberFormatException e)
            {
                //ignore the line if there are invalid inputs
                takeOrder(array, ind, scan, cupDeets);
            }


            //check drink type and size
            switch(drinkType)
            {
                case "soda":
                    drinkRate = PRICE_SODA;
                    break;
                case "tea":
                    drinkRate = PRICE_TEA;
                    break;
                case "punch":
                    drinkRate = PRICE_FRUITPUNCH;
                    break;
                default:
                    match = false;
            }

            if (!(line_split[1].equals("S")) && !(line_split[1].equals("M")) && !(line_split[1].equals("L")))
            {
                match = false;
            }

            if (line_split.length != 5 || !match)
            {
                takeOrder(array, ind, scan, cupDeets);
            }


            if (line_split[1] == "S")
            {
                double area = 2 * Math.PI * cupDeets[0][0] * cupDeets[0][1]; //cup deets[0][0] is small size radius, cup deets[0][1] is small size height
                drinkPrice = drinkRate * cupDeets[0][2]; //[0][2] is the number of oz in small drink
                decorationPrice = area * Double.parseDouble(line_split[3]); //line_split[3] refers to the price per square inch of decoration
            }
            else if (line_split[1] == "M")
            {
                double area = 2 * Math.PI * cupDeets[1][0] * cupDeets[1][1]; 
                drinkPrice = drinkRate * cupDeets[1][2]; 
                decorationPrice = area * Double.parseDouble(line_split[3]);   
            }
            else if (line_split[1] == "L")
            {
                double area = 2 * Math.PI * cupDeets[2][0] * cupDeets[2][1]; 
                drinkPrice = drinkRate * cupDeets[2][2]; 
                decorationPrice = area * Double.parseDouble(line_split[3]);   
            }

            
            for (int i = 0; i < array.length; i++)
            {
                if (array[i].getID().equals(line_split[0]))
                {
                    ind = i;
                }
            }
            
            
            //check if discount or bonus bucks apply to the customer
            if (ind == -1)
            {
                takeOrder(array, ind, scan, cupDeets);
            }
            else
            {
                float price = 0.0f;
                if (array[ind].getAmountSpent() < 50)
                {
                    price = array[ind].getAmountSpent() + (((float) decorationPrice + (float) drinkPrice) * Integer.parseInt(line_split[4])); //adds up the prices to the existing customer expenditure and multiplies the result by the quantity ordered
                    System.out.println("ID: " + array[ind].getID() + " Price: " + price);
                    array[ind] = new Customer(array[ind].getFirstName(), array[ind].getLastName(), array[ind].getID(), price); //update the expenditure into the array
                }
        
                if (array[ind].getAmountSpent() > 50 && array[ind].getAmountSpent() <= 200)
                {
                    
                    price = (((float) decorationPrice + (float) drinkPrice) * Integer.parseInt(line_split[4])); //adds up the prices to the existing customer expenditure and multiplies the result by the quantity ordered
                    // System.out.println("ID: " + array[ind].getID() + " Price: " + price);
                    Gold temp = new Gold(array[ind].getFirstName(), array[ind].getLastName(), array[ind].getID(), (float) price); //update the expenditure into the array
                    temp.setDiscount(price);
                    price = price * (float) temp.getDiscount();
                    array[ind] = new Gold(array[ind].getFirstName(), array[ind].getLastName(), array[ind].getID(), (float) price);
                    
                }
        
                if (array[ind].getAmountSpent() > 200)
                {
                    price = array[ind].getAmountSpent() + (((float) decorationPrice + (float) drinkPrice) * Integer.parseInt(line_split[4])); //adds up the prices to the existing customer expenditure and multiplies the result by the quantity ordered
                    
                    Platinum temp = new Platinum(null, null, null, price);
                    temp.setBonusBucks(price);
                    array[ind] = new Platinum(array[ind].getFirstName(), array[ind].getLastName(), array[ind].getID(), price - temp.getBonusBucks()); //update the expenditure into the array
                    //System.out.println("ID: " + array[ind].getID() + " Price: " + (price - temp.getBonusBucks()));
                }
            }
            
            

            takeOrder(array, ind, scan, cupDeets);
        }
        
        //iterate through customer array and sort out the regular and preferred customers remaining after all the purchases
        int countPreferred = 0;
        int countRegular = 0;
        for (int i = 0; i < array.length; i++)
        {
            if (array[i].getClass().equals(Gold.class) || array[i].getClass().equals(Platinum.class))
            {
                countPreferred++;
            }
            else{
                countRegular++;
            }
        }
        
        Customer[] regularArray = new Customer[countRegular];
        Customer[] preferredArray = new Customer[countPreferred];
        
        for (int i = 0, j = 0, k = 0; i < array.length; i++)
        {
            if (array[i].getClass().equals(Gold.class) || array[i].getClass().equals(Platinum.class))
            {
                preferredArray[j] = array[i];
                j++;
            }
            else if (array[i].getClass().equals(Customer.class)){
                regularArray[k] = array[i];
                k++;
            }
        }

        Customer[][] finalArrayCustomers = {
            regularArray,
            preferredArray
        };

        return finalArrayCustomers;
    }


    
    public static Customer promote(Customer c, double decorPrice, double drinkCost, String[] line)
    {
        
        return c;
    }
}