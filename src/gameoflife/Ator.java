/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameoflife;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.UUID;

/**
 *
 * @author rsautter
 */
public class Ator extends Thread{
    private long nivelDeSono;
    private Tabuleiro t;
    private int stopSignal;
    private String idAtor;
    public Ator(Tabuleiro t, long nivelDeSono){
        this.stopSignal = 0;
        this.t = t;
        this.nivelDeSono = nivelDeSono;
        this.idAtor= UUID.randomUUID().toString();
    }

    public Tabuleiro getT() {
        return t;
    }

    public void setT(Tabuleiro t) {
        this.t = t;
    }

    public void sinalParada(){
        this.stopSignal = 1;
    }
    public String getIdAtor(){
        return this.idAtor;
    }
    public void run(){
        Random random = new Random();
        while(this.stopSignal==0){
            int x = random.nextInt(t.getMatrixSize());
            int y = random.nextInt(t.getMatrixSize());
            t.aplicaRegra(x, y);
            t.displayMatrix();
            try{
                Thread.sleep(this.nivelDeSono);
            } catch(InterruptedException ex) {
                Logger.getLogger(Ator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
