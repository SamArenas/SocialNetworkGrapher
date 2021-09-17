import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class TweeterGrafo {
    private ArrayList<Usuario> usuarios;

    //constructor
    public TweeterGrafo(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    //getters
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    //metodo para dibujar red
    public void dibujaRed() throws IOException {

        //crear una grafica simple
        DefaultDirectedGraph<String, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);

        //iterar por todos los usuarios de la lista de usuarios
        for(Usuario usuario: this.getUsuarios()){

            //crear un iterator que itera por la lista de relaciones de cada usuario
            Iterator iteratorKey = usuario.getRelaciones().keySet().iterator();
            int i = 0;

            //crear un vértice con el nombre del usuario
            g.addVertex(usuario.getNombre());

            //iterar hasta 20 relaciones en la lista de relaciones del usuario
            while(iteratorKey.hasNext()&& i<20) {

                //inicializar un String con el nombre de la siguiente relación en la lista
                String targetVertex = iteratorKey.next().toString();

                //intentar conectar los dos vertices
                try {
                    g.addEdge(usuario.getNombre(), targetVertex);
                }

                //si no hay un vértice con el nombre de la relación, crear un vértice y continuar con la iteración
                catch (IllegalArgumentException e) {
                    g.addVertex(targetVertex);
                }
                i++;
            }
        }
        //llamar método para crear grafica
        crearImagen(g);
    }

    //método para crear un .png de output
    private void crearImagen(DefaultDirectedGraph<String, DefaultEdge> grafica) throws IOException {

        //crear un nuevo archivo para guardar la imagen
        File imgFile = new File("src/graph.png");
        imgFile.createNewFile();

        System.out.println("Creando JGraph Adapter...");
        JGraphXAdapter<String, DefaultEdge> graphAdapter = new JGraphXAdapter<String, DefaultEdge>(grafica);

        System.out.println("Calculando Layout...");
        mxIGraphLayout layout = new mxFastOrganicLayout(graphAdapter);

        System.out.println("Ejecutando layout...");
        layout.execute(graphAdapter.getDefaultParent());

        System.out.println("Renderizando Imagen...");
        BufferedImage image = mxCellRenderer.createBufferedImage(graphAdapter, null, 1, Color.WHITE, false, null);
        imgFile = new File("src/graph.png");

        System.out.println("Creando archivo de imagen...");
        ImageIO.write(image, "PNG", imgFile);

        System.out.println("Imagen creado exitosamente!");
    }
}
