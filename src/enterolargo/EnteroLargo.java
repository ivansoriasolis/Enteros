/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package enterolargo;

/**
 *
 * @author ivan
 */
public class EnteroLargo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Entero a = new Entero("123456789");
        Entero b = new Entero("123456789");
        
        System.out.println(a.toString());
        System.out.println(b.toString());
                
        System.out.println(a.potencia(40));
    }
    
}
