package cloud.marchand.renderer.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent a 3D space.
 */
public class Espace3D {

    /**
     * List of objects belong to the space.
     */
    private List<Object3D> objects = new ArrayList<>();

    /**
     * Instanciate an empty 3D space.
     */
    public Espace3D() {
    }

    /**
     * Instanciate a 3D space with objects.
     * @param objects list of 3D objects
     */
    public Espace3D(List<Object3D> objects) {
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
    
}
