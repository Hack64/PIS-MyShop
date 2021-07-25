package View;

import javax.swing.*;
import java.time.LocalDate;

public class MainClass {

    public static void main(String[] args) {

        System.out.println(LocalDate.now());

        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
            // handle exception
        }
        catch (ClassNotFoundException e) {
            // handle exception
        }
        catch (InstantiationException e) {
            // handle exception
        }
        catch (IllegalAccessException e) {
            // handle exception
        }

        new AppFrame();
    }
}
