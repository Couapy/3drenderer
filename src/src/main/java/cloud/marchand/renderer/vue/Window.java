package cloud.marchand.renderer.vue;

import javax.swing.JFrame;

import cloud.marchand.renderer.models.Espace3D;
import cloud.marchand.renderer.models.Vision3D;

public class Window extends JFrame implements Runnable {

    private static final long serialVersionUID = -1350613803096338434L;
    private Espace3D espace;
    private Vision3D vision;
    private Canvas canvas;

    public Window(Espace3D espace, Vision3D vision) {
        this.espace = espace;
        this.vision = vision;
        initialize();
	}

	private void initialize() {
        // Configurate the window
        setSize(500, 500);
        setLayout(null);
        setResizable(false);
        setTitle("Cube Renderer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add components to the frame
        this.canvas = new Canvas(espace, vision);
        add(canvas);
        canvas.setBounds(0, 0, 500, 500);
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        setVisible(true);

    }

}
