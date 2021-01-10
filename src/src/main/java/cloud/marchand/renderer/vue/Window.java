package cloud.marchand.renderer.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

import cloud.marchand.renderer.interfaces.Observable;
import cloud.marchand.renderer.interfaces.Observer;
import cloud.marchand.renderer.models.Espace3D;
import cloud.marchand.renderer.models.Vision3D;
import cloud.marchand.renderer.vue.controller.WindowResizeController;

public class Window extends Thread implements Observer {

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
        frame.setMinimumSize(new Dimension(480, 480));
        frame.setMaximumSize(new Dimension(2560, 1440));
        frame.setLayout(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.BLACK);

        // Add components to the frame
        this.canvas = new Canvas(espace, vision);
        frame.add(canvas);
        canvas.setBackground(Color.BLACK);

        // Add controllers
        frame.addComponentListener(new WindowResizeController(this));
    }

    @Override
    public void update(Observable observable, String arg) {
        // TODO Auto-generated method stub

    }

    @Override
    public void run() {
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public Canvas getCanvas() {
        return canvas;
    }

}
