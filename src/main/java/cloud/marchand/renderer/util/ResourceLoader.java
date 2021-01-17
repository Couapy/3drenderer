package cloud.marchand.renderer.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cloud.marchand.renderer.models.Face3D;
import cloud.marchand.renderer.models.Object3D;
import cloud.marchand.renderer.models.math.Vector3D;

/**
 * Utility for loading resources likes assets or objects.
 */
public class ResourceLoader {

	/**
	 * Genereate object from .obj files path.
	 * 
	 * @param filename filename of the resource
	 * @return 3d object loaded from file
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public static Object3D getObject(String filename) throws NumberFormatException, IOException, URISyntaxException {
		URL path = ResourceLoader.class.getClassLoader().getResource(filename);
		File file = new File(path.toURI());
		return getObjectFromFile(file);
	}

	/**
	 * Genereate object from .obj files.
	 * 
	 * @param file object file
	 * @return 3d object loaded from file
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public static Object3D getObjectFromFile(File file) throws NumberFormatException, IOException {
		BufferedReader buffer = new BufferedReader(new FileReader(file));
		String data;
		char firstChar;
		
        List<Vector3D> nodes = new ArrayList<>();
		List<Face3D> faces = new ArrayList<>();

		while ((data = buffer.readLine()) != null) {
			firstChar = data.substring(0, 1).toCharArray()[0];
			if (firstChar == '#') {
				continue;
			}
			else if (firstChar == 'v') {
				String[] coordinates = data.split(" ");
				double x = Double.parseDouble(coordinates[1]);
				double y = Double.parseDouble(coordinates[2]);
				double z = Double.parseDouble(coordinates[3]);
				nodes.add(new Vector3D(x, y, z));
			}
			else if (firstChar == 'f') {
				String[] coordinates = data.split(" ");
				int one = Integer.parseInt(coordinates[1]) - 1;
				int two = Integer.parseInt(coordinates[2]) - 1;
				int three = Integer.parseInt(coordinates[3]) - 1;
				faces.add(new Face3D(nodes.get(one), nodes.get(two), nodes.get(three)));
			}
		}

		buffer.close();
        return new Object3D(
			nodes.toArray(new Vector3D[nodes.size()]),
			faces.toArray(new Face3D[faces.size()]),
			new Vector3D(0, 0, 0)
		);
	}
    
}
