package cloud.marchand.renderer.interfaces;

/**
 * Une interface qui implémente l'interface Observer
 */
public interface Observer {

    /**
     * Met à jour l'observateur
     * @param observable l'objet qui est à l'origine de la mise à jour
     * @param arg argument qui indique la mise à jour
     */
	void update(Observable observable, String arg);
    
}
