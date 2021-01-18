package cloud.marchand.renderer;

import java.awt.Dimension;
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
     * 3D space.
     */
    private Espace3D espace;

    /**
     * Point of view.
     */
    private Camera camera;

    /**
     * Graphical interface.
     */
    private Window window;

    /**
     * Main program entry
     * 
     * @param args execution arguments
     */
    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    /**
     * Execute the application
     */
    public App() {
        // Initialize the application
        configureSettings();
        running = true;

        // Create 3D espace
        espace = new Espace3D();
        camera = new Camera(new Vector3D(1000, 1000, 1000), 90, 90, 90);
        Object3D object;
        try {
            object = ResourceLoader.getObject("objects/ship.obj");
            object.scale(0.1d);
        } catch (Exception e) {
            object = new Parallelepiped(300, 150, 200);
            e.printStackTrace();
        }
        espace.addObject(object);

        // Create visualizer
        window = new Window(this, espace, camera, FPS_LIMIT);
    }

    /**
     * Configure the settings.
     */
    private void configureSettings() {
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
     * Run the application.
     */
    private void run() {
        window.start();
        window.getFrame().setSize(new Dimension(1440, 960));
        
        while (isRunning()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            handleKeyboard();
            espace.getObjects().get(0).rotate(Math.PI/1024, Math.PI/1024, Math.PI/1024);
        }

        window.getFrame().dispose();
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
     * Handle the pressured keys.
     */
    public void handleKeyboard() {
        if (isPressed("forward")) {
            camera.translate(10, 0, 0);
        }
        if (isPressed("backward")) {
            camera.translate(-10, 0, 0);
        }
        if (isPressed("left")) {
            camera.translate(0, 10, 0);
        }
        if (isPressed("right")) {
            camera.translate(0, -10, 0);
        }
        if (isPressed("up")) {
            camera.translate(0, 0, 10);
        }
        if (isPressed("down")) {
            camera.translate(0, 0, -10);
        }
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
