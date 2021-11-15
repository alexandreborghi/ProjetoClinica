
package Controller;

import Model.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PacienteDAO {
    
    //establecer a conexao com o banco
    private final Connection con;
    
    //enviar o comando sql para o banco
    private PreparedStatement cmd;
    
    public PacienteDAO(){
        this.con = Conexao.conectar();
    }
    //
    //inserir
    //
     public int inserir(Paciente obj){
        try {
            String SQL = "insert into tb_paciente "
                       + "(nome,email,telefone,endereco,cidade) values (?,?,?,?,?)";
            
            cmd = con.prepareStatement(SQL);//valida se codigo que digitei esta correto, se tiver erro cai no catch
            //cmd.setInt(1,obj.getId_agendamento());
            cmd.setString(1,obj.getNome());//se estiver correto continua execucao
            cmd.setString(2,obj.getEmail());
            cmd.setString(3,obj.getTelefone());
            cmd.setString(4,obj.getEndereco());
            cmd.setString(5,obj.getCidade());
            
            if (cmd.executeUpdate() > 0){//envia a instrução sql para o banco
                //operação realizada com sucesso
                return 1;   //OK
            }else{
                return -1;  // ERRO
            }
            
        } catch (SQLException e) {
            System.err.println("ERRO: " + e.getMessage());
            return -1;
        }finally{
            Conexao.desconectar(con);
        }
    }
     
     //
    //ATUALIZAR
    //
     public int atualizarPaciente(Paciente obj){
        try {
            String SQL = "update tb_paciente set nome=?, email=?, telefone=?, endereco=?, cidade=?  where id=?";
            
            cmd = con.prepareStatement(SQL);//valida se codigo que digitei esta correto, se tiver erro cai no catch
            //cmd.setInt(1, obj.getId_agendamento());
            cmd.setString(1, obj.getNome());//se estiver correto continua execucao
            cmd.setString(2, obj.getEmail());
            cmd.setString(3, obj.getTelefone());
            cmd.setString(4, obj.getEndereco());
            cmd.setString(5, obj.getCidade());
            cmd.setInt(6,obj.getId());
            
            if (cmd.executeUpdate() > 0){//envia a instrução sql para o banco
                //operação realizada com sucesso
                return 1;   //OK
            }else{
                return -1;  // ERRO
            }
            
        } catch (SQLException e) {
            System.err.println("ERRO: " + e.getMessage());
            return -1;
        }finally{
            Conexao.desconectar(con);
        }
    }
     
     //
    // LISTAR
    // Retorna todos os dados da tabela
    public List<Paciente> listarPaciente(){
        try {
            String SQL = "select * from tb_paciente order by id";
            cmd = con.prepareStatement(SQL);
            
            //executar a consulta
            List<Paciente> lista = new ArrayList<>();
            ResultSet rs = cmd.executeQuery();//aplicacao manda consulta pro banco e o banco retorna resultado
            while (rs.next()){//passa por todos os dados de cada linha
                Paciente fun = new Paciente();
                fun.setId(rs.getInt("id"));
                //fun.setId_agendamento(rs.getInt("id_agendamento"));
                fun.setNome(rs.getString("nome"));
                fun.setEmail(rs.getString("email"));
                fun.setTelefone(rs.getString("telefone"));
                fun.setEndereco(rs.getString("endereco"));
                fun.setCidade(rs.getString("cidade"));
                lista.add(fun);
            }
            return lista;
        } catch (SQLException e) {//nao retorna nada
            System.err.println("ERRO: " + e.getMessage());
            return null;
        }finally{
            Conexao.desconectar(con);
        }
    }
    
    
    //
    // pesquisar por nome
    //pesquisa funcionario por nome
    public List<Paciente> pesquisarPorNomePaciente(String nome){
        try {
            String SQL = "select * from tb_paciente where nome ilike ? order by id";
            cmd = con.prepareStatement(SQL);
            cmd.setString(1,"%"+nome+"%");
            //executar a consulta
            List<Paciente> lista = new ArrayList<>();
            ResultSet rs = cmd.executeQuery();//aplicacao manda consulta pro banco e o banco retorna resultado
            while (rs.next()){//passa por todos os dados de cada linha
                Paciente fun = new Paciente();
                fun.setId(rs.getInt("id"));
                //fun.setId_agendamento(rs.getInt("id_agendamento"));
                fun.setNome(rs.getString("nome"));
                fun.setEmail(rs.getString("email"));
                fun.setTelefone(rs.getString("telefone"));
                fun.setEndereco(rs.getString("endereco"));
                fun.setCidade(rs.getString("cidade"));
                lista.add(fun);
            }
            return lista;
        } catch (SQLException e) {//nao retorna nada
            System.err.println("ERRO: " + e.getMessage());
            return null;
        }finally{
            Conexao.desconectar(con);
        }
    }
     
     
    //
    // pesquisar por id
    //pesquisa funcionario por id
    public Paciente pesquisarPorIdPaciente(String id){
        try {
            String SQL = "select * from tb_paciente where id = ? order by id";
            cmd = con.prepareStatement(SQL);
            cmd.setInt(1, Integer.parseInt(id));
            //executar a consulta
            ResultSet rs = cmd.executeQuery();//aplicacao manda consulta pro banco e o banco retorna resultado
            if (rs.next()){//passa por apenas 1 id
                Paciente fun = new Paciente();
                fun.setId(rs.getInt("id"));
                //fun.setId_agendamento(rs.getInt("id_agendamento"));
                fun.setNome(rs.getString("nome"));
                fun.setEmail(rs.getString("email"));
                fun.setTelefone(rs.getString("telefone"));
                fun.setEndereco(rs.getString("endereco"));
                fun.setCidade(rs.getString("cidade"));
                return fun;
            }
            return null;
        } catch (SQLException e) {//nao retorna nada
            System.err.println("ERRO: " + e.getMessage());
            return null;
        }finally{
            Conexao.desconectar(con);
        }
    }
    
}
