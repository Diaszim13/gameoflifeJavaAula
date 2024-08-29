package gameoflife;

import java.sql.*;

public class Tabuleiro {
    private int[][] matrix;
    private int matrixSize;
    public Tabuleiro(int matrixSize){
        /*
        * Cria uma matriz de tamanho matrixSizeXmatrixSize
        */
        this.matrixSize = matrixSize;
        this.matrix = new int[matrixSize][matrixSize];
        for(int i=0;i<this.matrixSize;i++){
            for(int j=0;j<this.matrixSize;j++){
                  //Inicializa com zeros o estado inicial
                  this.matrix[i][j] = 0;
            }
        }
    }
    public void changeMatrix(int i, int j){
        if (this.matrix[i][j]== 0)
            this.matrix[i][j] = this.matrix[i][j]+1; 
        else
            this.matrix[i][j] = this.matrix[i][j]-1;
    }
    public void aplicaRegra(int i, int j){
        // Aplicando as regras
        int vizinhosVivos = this.contarVizinhosVivos(i,j);
        if (matrix[i][j] == 1) { // Célula viva
            if (vizinhosVivos < 2 || vizinhosVivos > 3)
                this.matrix[i][j]= this.matrix[i][j]-1; // Morte
        }
        else { // Célula morta
            if (vizinhosVivos == 3)
                this.matrix[i][j] = this.matrix[i][j]+1; // Nascimento
        }
    }
    private int contarVizinhosVivos(int x, int y){
        int count = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && i < this.matrixSize && j >= 0 && j < this.matrixSize && !(i == x && j == y)) {
                    count += matrix[i][j];
                }
            }
        }
        return count;
    }
    
    public int getMatrixSize(){
        return this.matrixSize;
    }
    public int[][] getMatrix(){
        return this.matrix;
    }
    public void displayMatrix(){
        /*
        * poderia receber como entrada o JPanel e atualizar,
        * mas para simplificar ele vai apenas mostrar a matriz no terminal
        */
        
        // apaga a tela
        System.out.flush();  
        for(int i=-1;i<this.matrixSize;i++)
            System.out.print("---");
        System.out.print("-\n");
        for(int i=0;i<this.matrixSize;i++){
            System.out.print("|");
            for(int j=0;j<this.matrixSize;j++){
                switch (this.matrix[i][j]) {
                    case 0:
                        System.out.print("   ");
                        break;
                    case 1:
                        System.out.print(" # ");
                        break;
                    default:
                        System.out.print("Tem algo de errado aqui!");
                        break;
                }
            }
            System.out.print("|\n");
        }
        for(int i=-1;i<this.matrixSize;i++)
            System.out.print("---");
        System.out.print("-\n");
    }

    public void saveTabuleiro(int idTabuleiro, int matrixSize) throws SQLException {
        Connection coon = BDConnection.getConnection();
        Statement st = coon.createStatement();

        if (!st.isClosed()) {
            Tabuleiro tab = new Tabuleiro(matrixSize);
            String query = "insert into Tabuleiro values(?,?)";
            PreparedStatement pst = coon.prepareStatement(query);

            pst.setInt(1, idTabuleiro);
            pst.setInt(2, matrixSize);
            st.execute(query);

            coon.close();
        }
    }
     //todo continuar fazendo isso pras outras tabelas
    public void printTabuleiro(int idTabuleiro) throws SQLException {
        Connection coon = BDConnection.getConnection();

        Statement st = coon.createStatement();
        if(!st.isClosed()) {
            String query = "SELECT * FROM Tabuleiro where id>?;";

            PreparedStatement pst = coon.prepareStatement(query);

            pst.setInt(1, idTabuleiro);

            ResultSet res = pst.executeQuery();

            while (res.next())
            {
                System.out.println(res.getString("idTabuleiro"));
            }
        }

    }
}
