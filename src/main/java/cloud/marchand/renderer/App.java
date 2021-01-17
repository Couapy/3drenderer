package cloud.marchand.renderer;

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
        object.translate(100, 100, 0);
        espace.addObject(object);

        // Create visualizer
        Window window = new Window(this, espace, camera, FPS_LIMIT);
        window.start();

        int pasX = 5, pasY = 2, pasZ = 3, longueur = 0, longueurParcours = 200;
        boolean sensPositif = true;
        while (isRunning()) {
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

        window.getFrame().dispose();
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
