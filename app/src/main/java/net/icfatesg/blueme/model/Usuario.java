package net.icfatesg.blueme.model;

/**
 * Created by harlock on 10/11/17.
 */

public class Usuario {
    private String ID;
    private String nome;
    private String CPF;
    private String bluetoothMAC;
    private String email;
    private String senha;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getBluetoothMAC() {
        return bluetoothMAC;
    }

    public void setBluetoothMAC(String bluetoothMAC) {
        this.bluetoothMAC = bluetoothMAC;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
