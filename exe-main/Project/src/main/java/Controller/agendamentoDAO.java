/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import Model.agendamento;

import java.sql.SQLException;


public class agendamentoDAO {
    
    //establecer a conexao com o banco
    private final Connection con;
    
    //enviar o comando sql para o banco
    private PreparedStatement cmd;
    
    public agendamentoDAO(){
        this.con = Conexao.conectar();
    }
    //
    //inserir
    //
     public int inserir(agendamento obj){
        try {
            String SQL = "insert into tb_agendamento "
                       + "(id_paciente,nome,data,cpf,horario,status) values (?,?,?,?,?,?)";
            
            cmd = con.prepareStatement(SQL);//valida se codigo que digitei esta correto, se tiver erro cai no catch
            cmd.setInt(1,obj.getId_paciente());
            cmd.setString(2,obj.getNome());//se estiver correto continua execucao
            cmd.setString(3,obj.getData());
            cmd.setString(4,obj.getCpf());
            cmd.setString(5,obj.getHorario());
            cmd.setInt(6,obj.getStatus());
            
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
     public int atualizaragendamento(agendamento obj){
        try {
            String SQL = "update tb_agendamento set id_paciente=?, nome=?, data=?, cpf=?, horario=?, status=?  where id=?";
            
            cmd = con.prepareStatement(SQL);//valida se codigo que digitei esta correto, se tiver erro cai no catch
            cmd.setInt(1, obj.getId_paciente());
            cmd.setString(2, obj.getNome());//se estiver correto continua execucao
            cmd.setString(3, obj.getData());
            cmd.setString(4, obj.getCpf());
            cmd.setString(5, obj.getHorario());
            cmd.setInt(6, obj.getStatus());
            cmd.setInt(7,obj.getId());
            
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
    public List<agendamento> listaragendamento(){
        try {
            String SQL = "select * from tb_agendamento order by id";
            cmd = con.prepareStatement(SQL);
            
            //executar a consulta
            List<agendamento> lista = new ArrayList<>();
            ResultSet rs = cmd.executeQuery();//aplicacao manda consulta pro banco e o banco retorna resultado
            while (rs.next()){//passa por todos os dados de cada linha
                agendamento fun = new agendamento();
                fun.setId(rs.getInt("id"));
                fun.setId_paciente(rs.getInt("id_paciente"));
                fun.setNome(rs.getString("nome"));
                fun.setData(rs.getString("data"));
                fun.setCpf(rs.getString("cpf"));
                fun.setHorario(rs.getString("horario"));
                fun.setStatus(rs.getInt("status"));
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
    //pesquisa agendamento por nome
    public List<agendamento> pesquisarPorNomeagendamento(String nome){
        try {
            String SQL = "select * from tb_agendamento where nome ilike ? order by id";
            cmd = con.prepareStatement(SQL);
            cmd.setString(1,"%"+nome+"%");
            //executar a consulta
            List<agendamento> lista = new ArrayList<>();
            ResultSet rs = cmd.executeQuery();//aplicacao manda consulta pro banco e o banco retorna resultado
            while (rs.next()){//passa por todos os dados de cada linha
                agendamento fun = new agendamento();
                fun.setId(rs.getInt("id"));
                fun.setId_paciente(rs.getInt("id_paciente"));
                fun.setNome(rs.getString("nome"));
                fun.setData(rs.getString("data"));
                fun.setCpf(rs.getString("cpf"));
                fun.setHorario(rs.getString("horario"));
                fun.setStatus(rs.getInt("status"));
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
    //pesquisa agendamento por id
    public agendamento pesquisarPorIdagendamento(String id){
        try {
            String SQL = "select * from tb_agendamento where id = ? order by id";
            cmd = con.prepareStatement(SQL);
            cmd.setInt(1, Integer.parseInt(id));
            //executar a consulta
            ResultSet rs = cmd.executeQuery();//aplicacao manda consulta pro banco e o banco retorna resultado
            if (rs.next()){//passa por apenas 1 id
                agendamento fun = new agendamento();
                fun.setId(rs.getInt("id"));
                fun.setId_paciente(rs.getInt("id_paciente"));
                fun.setNome(rs.getString("nome"));
                fun.setData(rs.getString("data"));
                fun.setCpf(rs.getString("cpf"));
                fun.setHorario(rs.getString("horario"));
                fun.setStatus(rs.getInt("status"));
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
