package cloud.marchand.renderer;

import cloud.marchand.renderer.models.Espace3D;
import cloud.marchand.renderer.models.Object3D;
import cloud.marchand.renderer.models.Vision3D;
import cloud.marchand.renderer.models.geometricShapes.Cube;
import cloud.marchand.renderer.vue.Window;

public class App extends Thread {

    public static void main(String[] args) {
        // Defines the environnement
        Espace3D espace = new Espace3D();
        Vision3D vision = new Vision3D();
        espace.addObject(new Cube(100));

        // Launch the window
        Thread window = new Thread(new Window(espace, vision));
        window.start();

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
        double angle = 1;
        Object3D cube = espace.getObjects().get(0);

        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) { }

            cube.rotateX(angle * 1.0);
            cube.rotateY(angle * 2.0);
            cube.rotateZ(angle * 3.0);

            System.out.print("rotationX=" + cube.getRotationX() + ", rotationY=" + cube.getRotationY() + ", rotationZ=" + cube.getRotationZ() + "\r");
        }
    }

}
