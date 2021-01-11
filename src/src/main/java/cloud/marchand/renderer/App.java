package cloud.marchand.renderer;

import java.util.Iterator;

import cloud.marchand.renderer.models.Espace3D;
import cloud.marchand.renderer.models.Object3D;
import cloud.marchand.renderer.models.Point3D;
import cloud.marchand.renderer.models.Vision3D;
import cloud.marchand.renderer.models.geometricShapes.Cube;
import cloud.marchand.renderer.vue.Window;

public class App extends Thread {

    public static void main(String[] args) {
        // Defines the environnement
        Espace3D espace = new Espace3D();
        Vision3D vision = new Vision3D();
        Cube cube = new Cube(300);
        cube.translate(100, 100, 100);
        espace.addObject(cube);

        // Launch the window
        Window window = new Window(espace, vision);
        window.run();

        // Start the application
        new App(espace, vision);
    }

    private Espace3D espace;
    private Vision3D vision;

    public App(Espace3D espace, Vision3D vision) {
        System.out.println("[INFO][APP] Application started.");
        this.espace = espace;
        this.vision = vision;
        this.start();
    }

    public void run() {
        Object3D cube = espace.getObjects().get(0);

        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) { }

            cube.rotateZ(Math.PI/64);
            System.out.print("\r");
        }
    }

}
