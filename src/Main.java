import java.io.*;
import java.util.*;

public class Main {

    public static ArrayList<Tweet> crearTweets(File corpus, String name) throws IOException {
        File[] listOfFiles = corpus.listFiles();
        assert listOfFiles != null;
        ArrayList<Tweet> tweetsUsuario = new ArrayList<>();

        for(File file : listOfFiles){
            if(file.getName().equals(name)) {
                Scanner scanner = new Scanner(file);

                String line = scanner.nextLine();

                while (scanner.hasNext()) {
                    if (line.contains(file.getName().substring(0, file.getName().length() - 4))) {
                        StringTokenizer tokenizer = new StringTokenizer(line);
                        if (tokenizer.countTokens() > 3) {
                            tokenizer.nextToken();

                            String mensaje = "";

                            String fecha = (tokenizer.nextToken() + " " + tokenizer.nextToken());

                            while (tokenizer.hasMoreTokens()) {
                                mensaje += " " + tokenizer.nextToken();
                            }
                            line = scanner.nextLine();
                            tweetsUsuario.add(new Tweet(mensaje, fecha));
                        } else {
                            line = scanner.nextLine();
                        }
                    } else {
                        line = scanner.nextLine();
                    }
                }
            }

        }
        return tweetsUsuario;
    }

    public static ArrayList<Usuario> relaciones(File corpus) throws IOException {

        //crear un variable folder con todos los tweets y convertirlo en lista con todos los archivos
        File[] listOfFiles = corpus.listFiles();
        ArrayList<Usuario> Todos = new ArrayList<>();

        //crear file reader para el file del usuario
        //loop para iterar todos los files
        assert listOfFiles != null;
        for (File file : listOfFiles) {
            if (file.isFile()) {
                //inicializar numero de relaciones
                int relaciones = 0;

                //crear file reader
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = reader.readLine();

                //hashmap con todos los usuarios y sus relaciones
                LinkedHashMap<String, Integer> personas = new LinkedHashMap<>();

                //contar numero total de relaciones and it works
                while (line != null) {
                    //checa si la linea contiene @ y cuenta la cantidad total de @
                    if (line.contains("@")) {
                        // dividir linea en tokens
                        StringTokenizer tokenizer = new StringTokenizer(line);

                        while (tokenizer.hasMoreTokens()) {
                            String contacto = tokenizer.nextToken();
                            //si token empieza con @ y no esta en el Map, se agrega con un valor de 1
                            if (!personas.containsKey(contacto) && contacto.startsWith("@")) {
                                personas.put(contacto, 1);
                                ++relaciones;
                            }
                            //si token empieza con @ y si esta en la lista, se agrega +1 a su valor
                            else if (personas.containsKey(contacto) && contacto.startsWith("@")) {
                                int valor = personas.get(contacto);
                                valor++;
                                personas.put(contacto, valor);
                                ++relaciones;
                            }
                        }
                    }
                    //actualizar el lector para que lea la siguente linea
                    line = reader.readLine();
                }
                //pasamos el archivo actual y su nombre para crear un Array list con todos los tweets de ese usuario
                ArrayList<Tweet> tweetsUsuario = crearTweets(corpus,file.getName());

                //creamos un nuevo usuario con el nombre del archivo actual, su archivo de tweets y las relaciones que tiene
                Todos.add(new Usuario(file.getName(),tweetsUsuario,personas));
            }
        }
        return Todos;
    }

    public static void main(String[] args) throws IOException{
        //crear archivo del corpus
        File corpus = new File("Tweets");

        //pasar el corpus al metodo relaciones para crear todas la relaciones y lista de tweets de todos los usuarios
        ArrayList<Usuario> Twitter = relaciones(corpus);

        //iterar por la lista de Twitter
        for(Usuario usuario: Twitter){
            //usuario.calculaRelaciones();
            //usuario.imprimeRelaciones();
            System.out.println(usuario.getNombre());
        }

        //crear la grafica
        //TweeterGrafo tweeterGrafo = new TweeterGrafo(Twitter);
        //tweeterGrafo.dibujaRed();

        Iterator<Usuario> iterator = Twitter.iterator();
        iterator.next().imprimeTweets();

    }
}