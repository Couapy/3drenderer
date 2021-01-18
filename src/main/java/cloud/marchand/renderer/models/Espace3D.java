package cloud.marchand.renderer.models;

import java.util.ArrayList;
import java.util.List;

import cloud.marchand.renderer.models.math.Vector3D;

/**
 * Represent a 3D space.
 */
public class Espace3D {

    /**
     * List of objects belong to the space.
     */
    private List<Object3D> objects = new ArrayList<>();

    /**
     * The light direction;
     */
    private Vector3D lightDirection;

    /**
     * Instanciate an empty 3D space.
     */
    public Espace3D() {
        lightDirection = new Vector3D(0d, 0d, -1d);
    }

    /**
     * Instanciate a 3D space with objects.
     * @param objects list of 3D objects
     */
    public Espace3D(List<Object3D> objects) {
        this();
        this.objects = objects;
    }

    /**
     * Give the objects of the 3D space.
     * @return a list of 3D objects
     */
    public List<Object3D> getObjects() {
        return objects;
    }

    /**
     * Defines the objects of the 3D space.
     * @param objects a list of 3D objects
     */
    public void setObjects(List<Object3D> objects) {
        this.objects = objects;
    }

    /**
     * Add a 3D object to the space.
     * @param object 3D object
     */
    public void addObject(Object3D object) {
        objects.add(object);
    }
    
    /**
     * Defines the light direction
     * @param vector light direction
     */
    public void setLightDirection(Vector3D vector) {
        lightDirection = vector;
    }

    /**
     * Give the light direction
     * @return the light direction
     */
    public Vector3D getLightDirection() {
        return lightDirection;
    }
}
