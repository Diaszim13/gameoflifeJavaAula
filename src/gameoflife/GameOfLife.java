package gameoflife;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class GameOfLife {

    public static void main(String[] args){
        int tamanho = 15;
        int nAtores = 30;
        
        Tabuleiro t = new Tabuleiro(tamanho);
        //t.displayMatrix();
        Random r = new Random();
        for (int i=0;i<(int) tamanho*tamanho;i++)
            t.changeMatrix(r.nextInt(tamanho), r.nextInt(tamanho));
        t.changeMatrix(2, 5);
        t.displayMatrix();
        List<Ator> atores = new ArrayList<>();
        for(int i =0;i<nAtores;i++){
            Ator a = new Ator(t,r.nextInt(5, 300));
            atores.add(a);
            a.start();
        }
        // Como temos threads, a entrada continua ativa, basta digitar algo ou clicar enter para finalizar o codigo
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        for(int i =0;i<nAtores;i++)
            atores.get(i).sinalParada();
        try{
            for(int i =0;i<nAtores;i++)
                atores.get(i).join();
        } catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}
