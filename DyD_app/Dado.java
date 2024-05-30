package dyd_clase_construccion;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Random;
/**
 *
 * @author Metatron
 */
public class Dado {
    
    private int valorDado;

    public int getValorDado() { //el tamaño del dado, de 4 caras, 6 caras...
        return valorDado;
    }

    public void setValorDado(int valorDado) {
        this.valorDado = valorDado;
    }


    public static class Valor{
               
        public static int d4 = 4;
        public static int d6 = 6;
        public static int d8 = 8;
        public static int d10 = 10;
        public static int d12 = 12;
        public static int d20 = 20;
        public static int d100 = 100;  
    }
    
    public int lanzarDado(){ //acto de lanzar dado
        int res;
        Random rand = new Random();
        res = rand.nextInt(this.valorDado)+1;
        return res;
    }
    
}
