package cloud.marchand.renderer.vue.controller;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.TransferHandler;

import cloud.marchand.renderer.models.Espace3D;
import cloud.marchand.renderer.models.Object3D;
import cloud.marchand.renderer.util.ResourceLoader;

public class FileDropController extends TransferHandler {

    /**
     * Usefull for the serialisation.
     */
    private static final long serialVersionUID = 1949529325553614313L;

    private Espace3D espace;

    private Object transferData;

    public FileDropController(Espace3D espace) {
        this.espace = espace;
    }

    @Override
    public boolean canImport(TransferHandler.TransferSupport support) {
        for (DataFlavor flavor : support.getDataFlavors()) {
            if (flavor.isFlavorJavaFileListType()) {
                return true;
            }
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean importData(TransferHandler.TransferSupport support) {
        if (!this.canImport(support))
            return false;

        List<File> files;
        try {
            transferData = support.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
            files = (List<File>) transferData;
        } catch (UnsupportedFlavorException | IOException ex) {
            return false;
        }

        for (File file : files) {
            try {
                Object3D object = ResourceLoader.getObjectFromFile(file);
                espace.addObject(object);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
    
    
}
