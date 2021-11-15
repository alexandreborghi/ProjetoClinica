
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;


public class Conexao {
     //PARÂMETROS
    private static final String HOST = "localhost:5432";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "123456";
    private static final String DATABASE = "PROJETO";
    
    //STRING DE CONEXÃO
    private static final String URL =
        "jdbc:postgresql://" + HOST + "/" + DATABASE;
    
    //
    // CONECTAR
    // Estabelece uma nova conexão com o SGBD
    public static Connection conectar(){
        try{
            //retornar uma conexão
            return DriverManager.getConnection(
                URL, 
                USUARIO, 
                SENHA
            );
        }catch(Exception erro){
            //exibir a mensagem de erro
            System.err.println("ERRO: " + erro.getMessage());
            return null;
        }
    }
    
    //
    // DESCONECTAR
    // Finaliza a conexão com o SGBD
    public static void desconectar(Connection con){
        try{
            //se a conexão está ativa
            if ( con != null){
                con.close();
            }
        }catch(Exception erro){
            System.err.println("ERRO: " + erro.getMessage());
        }
    }
}
