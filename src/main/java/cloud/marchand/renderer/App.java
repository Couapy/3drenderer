package cloud.marchand.renderer;

import cloud.marchand.renderer.models.Espace3D;
import cloud.marchand.renderer.models.Object3D;
import cloud.marchand.renderer.models.Camera;
import cloud.marchand.renderer.models.geometricShapes.Parallelepiped;
import cloud.marchand.renderer.models.math.Vector3D;
import cloud.marchand.renderer.vue.Window;

/**
 * Example application.
 */
public class App {

    /**
     * Limit frame rate for the window.
     * Set to 0 or less to disable limit.
     */
    private static int FPS_LIMIT = 144;
    
    /**
     * Indicate if the thread must run or not
     */
    private boolean running = false;

    /**
     * Main program entry
     * @param args execution arguments
     */
    public static void main(String[] args) {
        new App();
    }

    /**
     * Execute the application
     */
    public App() {
        running = true;
        System.out.println("[INFO][APP] Application started.");

        // Create 3D espace
        Espace3D espace = new Espace3D();
        Camera camera = new Camera(new Vector3D(1000, 1000, 1000), 0, 90, 0);
        Object3D object = new Parallelepiped(300, 150, 200);
        object.translate(100, 100, 0);
        espace.addObject(object);

        // Create visualizer
        Window window = new Window(espace, camera, FPS_LIMIT);
        window.start();

        int pasX = 5, pasY = 2, pasZ = 3, longueur = 0, longueurParcours = 200;
        boolean sensPositif = true;
        while (running) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) { }

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

            // object.rotate(Math.PI/64, Math.PI/48, Math.PI/36);
        }

        window.interrupt();
        System.out.println("[INFO][APP] Application stopped.");
    }

    /**
     * Request to close the application
     */
    public void close() {
        running = false;
    }

}
