/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameoflife;

import java.util.UUID;

/**
 *
 * @author rsautter
 */
public class Acao {
    private Ator a;
    private int x,y;
    private String idAcao;   
    
    public Acao(Ator a, int x, int y){
        this.a = a;
        this.x =x;
        this.y = y;
        this.idAcao= UUID.randomUUID().toString();
        this.aplicaRegra();
    }
    private void aplicaRegra(){
        this.a.getT().aplicaRegra(this.x, this.y);
        this.a.getT().displayMatrix();
    }
}
