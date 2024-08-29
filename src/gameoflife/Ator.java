/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameoflife;

import java.sql.*;
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

    public long getNivelDeSono() {
        return nivelDeSono;
    }

    public void setNivelDeSono(long nivelDeSono) {
        this.nivelDeSono = nivelDeSono;
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
    public void saveAtor(String idAtor, long nivelDeSono) throws SQLException {
        Connection coon = BDConnection.getConnection();
        Statement st = coon.createStatement();

        if(!st.isClosed())
        {
            String query = "INSERT INTO ator values(?,?)";

            PreparedStatement pst = coon.prepareStatement(query);

            pst.setString(1, idAtor);
            pst.setLong(1, nivelDeSono);

            st.execute(query);

            coon.close();
        }
    }

    public void printAtor(String idAtor) throws SQLException
    {
        Connection coon = BDConnection.getConnection();
        Statement st = coon.createStatement();

        if(!st.isClosed())
        {
            String query = "SELECT * FROM atores where id>?";

            PreparedStatement pst = coon.prepareStatement(query);
            pst.setString(1, idAtor);

            ResultSet res = pst.executeQuery();

            while(res.next())
            {
                System.out.println(res);
            }
            coon.close();

        }
    }
}
