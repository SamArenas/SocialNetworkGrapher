import java.util.Objects;

public class Tweet implements Comparable {
    //datos 
    private String mensaje;
    private String diaDelTweet;
    
    //set and get
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getdiaDelTweet() {
        return diaDelTweet;
    }

    public void setdiaDelTweet(String diaDelTweet) {
        this.diaDelTweet = diaDelTweet;
    }
    //constructor
    public Tweet() {
    }
    
    //constructor datos miembro
    public Tweet(String mensaje, String diaDelTweet) {
        this.mensaje = mensaje;
        this.diaDelTweet = diaDelTweet;
    }

    // compareTo
    @Override
    public int compareTo(Object o) {
        return 0;
    }
    
    // toString
    @Override
    public String toString() {
        return "Tweet { Fecha: " +  getdiaDelTweet() + ", Mensaje: " + getMensaje();
    }
    // contiene(palabra)
    public boolean contienePalabra(String palabra){
        return this.getMensaje().contains(palabra);
    }
    
    
}
