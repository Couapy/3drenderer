package cloud.marchand.renderer.vue;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import cloud.marchand.renderer.models.Espace3D;
import cloud.marchand.renderer.models.Vision3D;
import cloud.marchand.renderer.vue.controller.WindowResizeController;
import cloud.marchand.renderer.vue.overlays.FPSCounter;
import cloud.marchand.renderer.vue.overlays.SkeletonDrawing;
import cloud.marchand.renderer.vue.overlays.Skybox;

public class Window extends Thread {

    private JFrame frame;
    private Espace3D espace;
    private Vision3D vision;
    private Canvas canvas;

    public Window(Espace3D espace, Vision3D vision) {
        setName("WINDOW");
        this.espace = espace;
        this.vision = vision;
        initialize();
    }

    private void initialize() {
        // Configurate the window
        this.frame = new JFrame();
        frame.setTitle("3D Renderer");
        frame.setSize(900, 900);
        frame.setMinimumSize(new Dimension(480, 480));
        frame.setMaximumSize(new Dimension(2560, 1440));
        frame.setLayout(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.BLACK);

        // Add components to the frame
        this.canvas = new Canvas(espace, vision);
        canvas.addOverlay(new Skybox());
        canvas.addOverlay(new SkeletonDrawing());
        canvas.addOverlay(new FPSCounter());
        frame.add(canvas);

        // Add controllers
        frame.addComponentListener(new WindowResizeController(this));
    }

    @Override
    public void run() {
        System.out.println("[INFO][WINDOW] Window started.");
        frame.setVisible(true);
        while (true) {
            // try {
            //     Thread.sleep(10);
            // } catch (InterruptedException e) {
            // }
            frame.revalidate();
            frame.repaint();
        }
    }

    public void update() {
        frame.revalidate();
        frame.repaint();
    }

    public JFrame getFrame() {
        return frame;
    }

    public Canvas getCanvas() {
        return canvas;
    }

}
