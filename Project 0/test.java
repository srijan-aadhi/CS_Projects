import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class test {
    public static void main(String[] args) throws FileNotFoundException
    {
        File file = new File("test1.txt");
        Scanner scan = new Scanner(file);

        while (scan.hasNextLine())
        {
            String data = scan.nextLine();
        }

        while (scan.hasNextLine())
        {
            int x = 0;
            System.out.println(x + 1);
        }
        

        scan.close();
    }
}
