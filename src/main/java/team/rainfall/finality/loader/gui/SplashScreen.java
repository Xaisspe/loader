package team.rainfall.finality.loader.gui;

import team.rainfall.finality.loader.util.Localization;

import javax.swing.*;
import java.util.Objects;

/**
 * The SplashScreen class represents a splash screen that is displayed when the application starts.
 * It shows an image and provides methods to create and destroy the splash screen.
 * This class is implemented as a singleton.
 *
 * @author RedreamR
 */
public class SplashScreen extends JFrame {

    public static SplashScreen splashScreen = null;

    /**
     * Private constructor to prevent instantiation.
     * Initializes the splash screen with an image and sets its properties.
     *
     * @author RedreamR
     */
    protected SplashScreen() {
        super(Localization.bundle.getString("splash_title"));
        //使用工程内的resources/splash.png文件
        JLabel label = new JLabel(new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/splash.png"))));
        this.add(label);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setSize(800, 400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    /**
     * Creates the splash screen if it does not already exist.
     *
     * @author RedreamR
     */
    public static void create(){
        if(splashScreen == null){
            splashScreen = new SplashScreen();
        }
    }

    /**
     * Destroys the splash screen if it exists.
     *
     * @author RedreamR
     */
    public static void destroy(){
        if(splashScreen != null) {
            splashScreen.dispose();
            splashScreen = null;
        }
    }

}
