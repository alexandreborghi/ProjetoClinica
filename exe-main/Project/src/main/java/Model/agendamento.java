/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Alexandre
 */
public class agendamento {
    
    private int id;
    private int id_paciente;
    private String nome;
    private String data;
    private String cpf;
    private String horario;
    private int status;
    
    public agendamento(){
    
    }

    public agendamento(int id, int id_paciente, String nome, String data, String cpf, String horario, int status) {
        this.id = id;
        this.id_paciente = id_paciente;
        this.nome = nome;
        this.data = data;
        this.cpf = cpf;
        this.horario = horario;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.nome;
    }
    
    
}
