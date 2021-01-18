package cloud.marchand.renderer.vue.controller;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import cloud.marchand.renderer.App;

public class MouseWheelController implements MouseWheelListener {

    private Object application;

    public MouseWheelController(App app) {
        this.application = app;
	}

	@Override
    public void mouseWheelMoved(MouseWheelEvent e) {
    }
    
}
