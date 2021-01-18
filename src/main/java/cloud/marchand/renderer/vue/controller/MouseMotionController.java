package cloud.marchand.renderer.vue.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import cloud.marchand.renderer.models.Camera;

public class MouseMotionController implements MouseMotionListener {

    private Camera camera;

    public MouseMotionController(Camera camera) {
        this.camera = camera;
	}

	@Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
}
