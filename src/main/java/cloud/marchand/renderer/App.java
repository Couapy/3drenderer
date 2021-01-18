package cloud.marchand.renderer;

import java.awt.event.KeyEvent;
import java.util.HashMap;

import cloud.marchand.renderer.models.Camera;
import cloud.marchand.renderer.models.Espace3D;
import cloud.marchand.renderer.models.Object3D;
import cloud.marchand.renderer.models.geometricShapes.Parallelepiped;
import cloud.marchand.renderer.models.math.Vector3D;
import cloud.marchand.renderer.util.ResourceLoader;
import cloud.marchand.renderer.vue.Window;

/**
 * Example application.
 */
public class App {

    /**
     * Limit frame rate for the window. Set to 0 or less to disable limit.
     */
    private static int FPS_LIMIT = 500;

    /**
     * Indicate if the thread must run or not
     */
    private boolean running = false;

    /**
     * Defines the settings of the application.
     */
    private HashMap<String, Integer> settings = new HashMap<>();

    /**
     * Indicate which key is pressed.
     */
    private HashMap<Integer, Boolean> keyPressed = new HashMap<>();

    /**
     * Main program entry
     * 
     * @param args execution arguments
     */
    public static void main(String[] args) {
        new App();
    }

    /**
     * Execute the application
     */
    public App() {
        configure();
        running = true;

        // Create 3D espace
        Espace3D espace = new Espace3D();
        Camera camera = new Camera(new Vector3D(1000, 1000, 1000), 0, 90, 0);
        Object3D object;
        try {
            object = ResourceLoader.getObject("objects/ship.obj");
            object.scale(100d);
        } catch (Exception e) {
            object = new Parallelepiped(300, 150, 200);
            e.printStackTrace();
        }
        espace.addObject(object);


        // Create visualizer
        Window window = new Window(this, espace, camera, FPS_LIMIT);
        window.start();
        
        int pasX = 5, pasY = 2, pasZ = 3, longueur = 0, longueurParcours = 100;
        boolean sensPositif = true;
        while (isRunning()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }

            if (sensPositif) {
                object.translate(pasX, pasY, pasZ);
                longueur++;
                if (longueur == longueurParcours) {
                    sensPositif = false;
                }
            }
            else {
                object.translate(-pasX, -pasY, -pasZ);
                longueur--;
                if (longueur == 0) {
                    sensPositif = true;
                }
            }
            object.rotate(Math.PI/128, Math.PI/196, Math.PI/256);

            if (isPressed("forward")) {
                object.translate(10, 0, 0);
            }
            if (isPressed("backward")) {
                object.translate(-10, 0, 0);
            }
            if (isPressed("left")) {
                object.translate(0, 10, 0);
            }
            if (isPressed("right")) {
                object.translate(0, -10, 0);
            }
            if (isPressed("up")) {
                object.translate(0, 0, 10);
            }
            if (isPressed("down")) {
                object.translate(0, 0, -10);
            }

            if (isPressed("rotateX")) {
                object.rotate(Math.PI/32, 0, 0);
            }
            if (isPressed("rotateY")) {
                object.rotate(0, Math.PI/32, 0);
            }
            if (isPressed("rotateZ")) {
                object.rotate(0, 0, Math.PI/32);
            }

        }

        window.getFrame().dispose();
    }

    /**
     * Configure the settings.
     */
    private void configure() {
        settings.put("forward", KeyEvent.VK_Z);
        settings.put("backward", KeyEvent.VK_S);
        settings.put("left", KeyEvent.VK_Q);
        settings.put("right", KeyEvent.VK_D);
        settings.put("up", KeyEvent.VK_SHIFT);
        settings.put("down", KeyEvent.VK_SPACE);
        settings.put("rotateX", KeyEvent.VK_LEFT);
        settings.put("rotateY", KeyEvent.VK_DOWN);
        settings.put("rotateZ", KeyEvent.VK_RIGHT);

        for (Integer key: settings.values()) {
            keyPressed.put(key, false);
        }
    }

    /**
     * Indicate if the key is pressed.
     * @param key key code
     * @return true if the key is pressed, false if not
     */
    private boolean isPressed(String key) {
        return keyPressed.get(settings.get(key));
    }

    /**
     * Defines if a key is pressed
     * @param key key code
     * @param pressed true if the key is pressed, false if not
     */
    public void setPressed(Integer key, Boolean pressed) {
        keyPressed.put(key, pressed);
    }

    /**
     * Request to close the application
     */
    public void close() {
        running = false;
    }

    /**
     * Indicate if the application is running
     * @return true if the application is running
     */
	public boolean isRunning() {
		return running;
	}

}
