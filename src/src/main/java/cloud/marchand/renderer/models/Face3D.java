package cloud.marchand.renderer.models;

import cloud.marchand.renderer.models.math.Vector3D;

public class Face3D {
    
    private Vector3D[] nodes;

    public Face3D(Vector3D[] nodes) {
        this.nodes = nodes;
    }

    public Vector3D[] getNodes() {
        return nodes;
    }

    public void setNodes(Vector3D[] nodes) {
        this.nodes = nodes;
    }

}
