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
public class Entero {
    private byte[] valor;
    
    public Entero(String n){
        char[] digitos = n.toCharArray();
        int longitud = digitos.length;
        //los dígitos se cargan en el arreglo en orden inverso para que luego se pueda operar
        //de forma más natural unidades, decenas, centenas......
        this.valor = new byte[longitud];
        for (int i = 0;i<longitud;i++)
            this.valor[longitud-1-i]= (byte)Character.getNumericValue(digitos[i]);
    }
    public byte getDigit(int n){
        //devuelve el digito n-simo, los digitos se deben pedir empezando del 1 para el primer digito
        return valor[n];
    }
    public void setDigit(int n, byte v){
        //establece el valor del dígito n-simo
        this.valor[n]= v;
    }
    public int getLong(){
        return valor.length;
    }
    public void addDigit(byte v){
        byte[] valorAnterior = this.valor;
        this.valor = new byte[valorAnterior.length+1];
        System.arraycopy(valorAnterior, 0, this.valor, 0, this.valor.length);
        this.valor[this.valor.length]=v;
            
    }
    public Entero sumar(Entero sumando){
        Entero sumando1 = this;
        Entero sumando2 = sumando;
        byte digito1, digito2, acarreo, digitoResultado;
        acarreo = 0;
        int longResultado = Math.max(sumando1.getLong(), sumando2.getLong());
        String resultado = "";
        for (int n=0; n<longResultado;n++){
            if (n>= sumando1.getLong())
                digito1 = 0;
            else
                digito1 = sumando1.getDigit(n);
            if (n>= sumando2.getLong())
                digito2 = 0;
            else
                digito2 = sumando2.getDigit(n);
            digitoResultado = (byte)(digito1 + digito2 + acarreo);
            acarreo = (byte)(digitoResultado /10);
            digitoResultado = (byte) (digitoResultado %10);
            resultado = digitoResultado + resultado;    
        }
        if (acarreo!=0)
            resultado = acarreo+resultado;
        return new Entero(resultado);
    }
    public Entero desplazarN(int n){
        String numero;
        numero = this.toString();
        for (int i=1;i<=n;i++)
            numero = numero + "0";
        return new Entero(numero);
    }
    public Entero multiplicar(Entero multiplicador){
        int tam1 = this.getLong();
        int tam2 = multiplicador.getLong();
        String resultado;
        byte acarreo = 0,digitoResultado;
        Entero[] resParcial;
        resParcial= new Entero[tam1];
        Entero resMulti;
                
        for (int i=0;i<tam1;i++){
            resultado = "";
            acarreo = 0;
            for(int j=0;j<tam2;j++){
                digitoResultado = (byte)(this.getDigit(i)* multiplicador.getDigit(j)+acarreo);   
                acarreo = (byte)(digitoResultado/10);
                digitoResultado = (byte)(digitoResultado%10);
                resultado = digitoResultado+resultado;
            }
            if (acarreo!=0)
                resultado = acarreo+resultado;
            resParcial[i]=new Entero(resultado).desplazarN(i);
        }
        resMulti = resParcial[0];
        for(int i=1;i<resParcial.length;i++){
            resMulti=resMulti.sumar(resParcial[i]);
        }
        return resMulti;
    }
 
    public Entero potencia(int n){
        Entero resPotencia=this;
        if (n==0)
            resPotencia= new Entero("1");
        if (n==1)
            return this;
        for (int i = 1;i<n;i++)
            resPotencia=resPotencia.multiplicar(this);
        return resPotencia;
    }
            
    @Override
    public String toString(){
        String digitos = "";
        for(int i = valor.length-1;i>=0;i--)
            digitos = digitos + valor[i];
        return digitos;
    }
}
