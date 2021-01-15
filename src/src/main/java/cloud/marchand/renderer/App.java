package cloud.marchand.renderer;


import cloud.marchand.renderer.models.Espace3D;
import cloud.marchand.renderer.models.Vision3D;
import cloud.marchand.renderer.models.geometricShapes.Cube;
import cloud.marchand.renderer.models.math.Vector3D;
import cloud.marchand.renderer.vue.Window;

public class App extends Thread {

    public static void main(String[] args) {
        new App();
    }

    private boolean running = false;

    public App() {
        running = true;
        this.start();
    }

    public void run() {
        System.out.println("[INFO][APP] Application started.");
        // Create 3D espace
        Espace3D espace = new Espace3D();
        Vision3D vision = new Vision3D(new Vector3D(1000, 1000, 1000), 0, 90, 0);
        Cube cube = new Cube(300);
        cube.translate(100, 100, 0);
        espace.addObject(cube);

        // Create visualizer
        Window window = new Window(espace, vision);
        window.start();

        int pasX = 1, pasY = 2, pasZ = 3, longueur = 0, longueurParcours = 200;
        boolean sensPositif = true;
        while (running) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) { }

            if (sensPositif) {
                cube.translate(pasX, pasY, pasZ);
                longueur++;
                if (longueur == longueurParcours) {
                    sensPositif = false;
                }
            }
            else {
                cube.translate(-pasX, -pasY, -pasZ);
                longueur--;
                if (longueur == 0) {
                    sensPositif = true;
                }
            }

            // cube.rotate(Math.PI/64, Math.PI/48, Math.PI/36);
        }
        System.out.println("[INFO][APP] Application stopped.");
    }

    public void close() {
        running = false;
    }

}
