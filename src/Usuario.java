
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Usuario{
    private String Nombre;
    private ArrayList<Tweet> Tweets;
    private LinkedHashMap<String, Integer> relaciones;

    public Usuario(String nombre, ArrayList<Tweet> tweets, LinkedHashMap<String, Integer> relaciones) {
        Nombre = nombre.substring(0,nombre.length()-4);
        Tweets = tweets;
        this.relaciones = relaciones;
    }

    public String getNombre() {
        return Nombre;
    }

    public ArrayList<Tweet> getTweets() {
        return Tweets;
    }

    public HashMap<String, Integer> getRelaciones() {
        return relaciones;
    }

    public void agregaTweet(Tweet tweet){
        Tweets.add(tweet);
    }

    public void imprimeTweets (){
        System.out.println("\nPara el usuario: " + this.getNombre() + "\n");
        for(Tweet tweet: this.getTweets()){
            System.out.println(tweet.toString());
        }
    }

    public void insertaRelacion(String contacto){
        if (!this.getRelaciones().containsKey(contacto)) {
            this.getRelaciones().put(contacto, 1);
        }
        //si token empieza con @ y si esta en la lista, se agrega +1 a su valor
        else if (this.getRelaciones().containsKey(contacto)){
            int valor = this.getRelaciones().get(contacto);
            valor++;
            this.getRelaciones().put(contacto, valor);
        }
    }

    public void calculaRelaciones(){
        int numRelaciones = 0;
        for(int numero : this.getRelaciones().values()){
            numRelaciones+=numero;
        }
        System.out.println("El total de relaciones para el usuario "+ this.getNombre() + " son: " + numRelaciones);
    }

    public void imprimeRelaciones(){
        System.out.println("\nTodas las relaciones para el usuario " + this.getNombre() +  " son: \n" + this.getRelaciones().toString());
    }
}
