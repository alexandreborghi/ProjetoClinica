
package Controller;

import Controller.Conexao;
import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UsuarioDAO {
    
    private Connection con;
    private PreparedStatement cmd;
    
    public UsuarioDAO(){
        this.con = Conexao.conectar();
    }
    
    public boolean login(Usuario u){
        try{
            
            String SQL = "select * from tb_usuario where "
                    + "usuario=? and senha=md5(?)";
            
            cmd = con.prepareStatement(SQL);
            cmd.setString(1, u.getUsuario());
            cmd.setString(2, u.getSenha());
            
            
            ResultSet rs = cmd.executeQuery();
            if(rs.next()){
                return true;
            }else{
                return false;
            }
           /* if(cmd.executeUpdate() > 0){
                return true;
            }else{
                return false;
            }*/
            }catch(Exception e){
                System.err.println("ERRO: " + e.getMessage());
                return false;
            }
        
    }
}
