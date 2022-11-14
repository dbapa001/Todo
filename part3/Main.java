import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       // new CLIMenu();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI();
            }
        });
    }

}
