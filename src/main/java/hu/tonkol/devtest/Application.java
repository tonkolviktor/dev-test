package hu.tonkol.devtest;

import com.sun.org.apache.xpath.internal.Arg;

/**
 * Created by tonkol on 2016.01.03..
 */
public class Application {

    private Argument argument;

    public static void main(String[] args) {
        try {
            new Application().run(args);
        } catch (BusinessException be) {
            System.out.println(be.getMessage());
            printHelp();
        } catch (Exception e) {
            System.out.println("Unknown error occurred: " + e.getMessage());
            printHelp();
        }
    }

    private static void printHelp() {
        System.out.println("Usage: java -jar <jar-name> <city>");
    }

    public void run(String[] args) {
        this.argument = new Argument(args);
    }
}
