package cloud.marchand.renderer.vue;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import cloud.marchand.renderer.models.Espace3D;
import cloud.marchand.renderer.App;
import cloud.marchand.renderer.models.Camera;
import cloud.marchand.renderer.vue.controller.FileDropController;
import cloud.marchand.renderer.vue.controller.KeyboardController;
import cloud.marchand.renderer.vue.controller.MouseController;
import cloud.marchand.renderer.vue.controller.MouseMotionController;
import cloud.marchand.renderer.vue.controller.MouseWheelController;
import cloud.marchand.renderer.vue.controller.WindowResizeController;
import cloud.marchand.renderer.vue.overlays.FPSCounter;
import cloud.marchand.renderer.vue.overlays.SkeletonDrawing;
import cloud.marchand.renderer.vue.overlays.Skybox;

/**
 * Graphical reprensatation of the application.
 */
public class Window extends Thread {

    /**
     * Window object.
     */
    private JFrame frame;

    /**
     * Main application.
     */
    private App app;

    /**
     * 3D space to represent.
     */
    private Espace3D espace;

    /**
     * Point of view of the application.
     */
    private Camera camera;

    /**
     * Canvas which inside draw objects.
     */
    private Canvas canvas;

    /**
     * Limit of image refresh rate.
     * If set to 0, there is no limit.
     */
    private int FRAME_TIME = 0;

    /**
     * Create a new window from a point of view.
     * @param app application related to this window
     * @param espace 3D space to represent
     * @param camera point to view to draw the 3D space
     */
    public Window(App app, Espace3D espace, Camera camera) {
        setName("WINDOW");
        this.app = app;
        this.espace = espace;
        this.camera = camera;

        initialize();
    }

    /**
     * Create a new window from a point of view.
     * @param app application related to this window
     * @param espace 3D space to represent
     * @param camera point to view to draw the 3D space
     * @param fps limit of max fps
     */
    public Window(App app, Espace3D espace, Camera camera, int fps) {
        this(app, espace, camera);
        if (fps > 0) {
            FRAME_TIME = 1000 / fps;
        }
    }
    
    /**
     * Initialize the window frame.
     */
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
        this.canvas = new Canvas(espace, camera);
        canvas.addOverlay(new Skybox());
        canvas.addOverlay(new SkeletonDrawing());
        canvas.addOverlay(new FPSCounter());
        frame.add(canvas);
        
        // Add controllers
        frame.addComponentListener(new WindowResizeController(this));
        frame.addKeyListener(new KeyboardController(app));
        frame.addMouseListener(new MouseController());
        frame.addMouseMotionListener(new MouseMotionController(camera));
        frame.addMouseWheelListener(new MouseWheelController(app));
        frame.setTransferHandler(new FileDropController(espace));
    }
    
    /**
     * Thread method. Controls the refresh rate.
     */
    @Override
    public void run() {
        frame.setVisible(true);
        while (app.isRunning()) {
            try {
                Thread.sleep(FRAME_TIME);
            } catch (InterruptedException e) {
            }
            frame.revalidate();
            frame.repaint();
        }
    }

    /**
     * Give the frame.
     * @return the current frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Give the canvas panel.
     * @return the drawing zone
     */
    public Canvas getCanvas() {
        return canvas;
    }

}
