package cloud.marchand.renderer.models;

import java.util.ArrayList;
import java.util.List;

import cloud.marchand.renderer.interfaces.Observable;

public class Espace3D extends Observable {

    private List<Object3D> objects = new ArrayList<>();
    private List<Player> players = new ArrayList<>();

    public Espace3D() {
    }

    public Espace3D(List<Object3D> objects, List<Player> players) {
        this.objects = objects;
        this.players = players;
    }
    public List<Object3D> getObjects() {
        return objects;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setObjects(List<Object3D> objects) {
        this.objects = objects;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void addObject(Object3D object) {
        objects.add(object);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
    
}
