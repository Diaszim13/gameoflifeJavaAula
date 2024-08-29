/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameoflife;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
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

    public void saveAcao(int idAcao, int x, int y) throws SQLException {
        Connection coon = BDConnection.getConnection();
        Statement st = coon.createStatement();
        if(!st.isClosed())
        {
            String query = "INSERT INTO acao values ()";
            PreparedStatement pst = coon.prepareStatement(query);
            pst.setInt(1, idAcao);
            pst.setInt(2, x);
            pst.setInt(3, y);
            st.execute(query);

            coon.close();
        }
    }

    public void printAcao(int idAcao) throws SQLException
    {
        Connection coon = BDConnection.getConnection();
        Statement st = coon.createStatement();

        if(!st.isClosed())
        {
            String query = "SELECT * from where id>?";
            PreparedStatement pst = coon.prepareStatement(query);

            pst.setInt(1, idAcao);

            ResultSet res = pst.executeQuery();

            while(res.next())
            {
                System.out.println(res.getString("idAcao"));
            }
        }
    }

}
