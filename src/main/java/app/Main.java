package app;

import javax.swing.SwingUtilities;
import view.LoginFrame;

/**
 * Titik masuk aplikasi: selalu mulai dari LoginFrame.
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }
}
