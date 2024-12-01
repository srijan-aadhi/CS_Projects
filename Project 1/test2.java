import java.util.Scanner;
import java.util.stream.Stream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
public class test2 {
    public static void main(String[] args) throws IOException {
        m(4, 1);
    }

    public static void m(int r, int i) {
        if (r > i) {
            System.out.print(r + "");
            m(r - 1, i + 1);
        }
    }
    public static void mid(String lol)
    {

        System.out.println(lol);
    }
}
