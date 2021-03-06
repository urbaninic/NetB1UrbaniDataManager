/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urbanidatamanagernetb1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

/**
 *
 * @author MC
 */
public class JSONWriter {
    
    public static final String JSON_FILE="libreria.json";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        // Es.2 (1)
        ArrayList<Libro> libri = new ArrayList();
        
        // array statico
        // Libro libri[] = new Libro[2];
        
        // ad arraylist libri aggiungo un nuovo libro
        libri.add(new Libro());
        
        libri.get(0).setGenere("fantasy");
        libri.get(0).setTitolo("Lo Hobbit");
        libri.get(0).setAutore("J. R. R. Tolkien");
        libri.get(0).setPrezzo(9.9f);
        
        // Vecchi metodi set      
        // libri[0].setGenere("fantasy");
        // libri[0].setTitolo("Lo Hobbit");
        // libri[0].setAutore("J. R. R. Tolkien");
        // libri[0].setPrezzo(9.9f);
        
        
        //istanza nuovo libro
        libri.add(new Libro());
        
        libri.get(1).setGenere("fantasy");
        libri.get(1).setTitolo("Il signore degli anelli");
        libri.get(1).setAutore("J. R. R. Tolkien");
        libri.get(1).setPrezzo(30.00f);
        
        // Vecchi metodi set        
        // libri[1].setGenere("fantasy");
        // libri[1].setTitolo("Il signore degli anelli");
        // libri[1].setAutore("J. R. R. Tolkien");
        // libri[1].setPrezzo(30.00f);
    
    
        JsonObjectBuilder rootObject = Json.createObjectBuilder();
        JsonObjectBuilder booksObject = Json.createObjectBuilder();
        JsonArrayBuilder bookArray = Json.createArrayBuilder();
        
        // a destra dei due punti c'?? la fonte, a SX il tipo e il nome ("assegnazione")
        for (Libro libro : libri){
            // fasi:
            // - Creo contenitore "libro".
            // - Creo dei campi all'interno del nuovo "libro".
            // - Con .build chiudo
            // (creo oggetti JSON da Java posseduto)
            JsonObjectBuilder bookObject =Json.createObjectBuilder();
            bookObject.add("genere", libro.getGenere());
            bookObject.add("titolo", libro.getTitolo());
            bookObject.add("autore", libro.getAutore());
            bookObject.add("prezzo", libro.getPrezzo());
            bookArray.add(bookObject.build());           
        }
        
        booksObject.add("Libri", bookArray.build());
        rootObject.add("Libreria", booksObject.build());
        
        OutputStream output = new FileOutputStream(JSON_FILE);
        
        JsonWriter jsonWriter = Json.createWriter(output);
        
        jsonWriter.writeObject(rootObject.build());
        
        jsonWriter.close();
        
        output.close();
             
    }
    
}
