package net.icfatesg.blueme.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minerthal on 12/06/17.
 */

public class Usuario implements Parcelable {
    private String ID;
    private String nome;
    private String CPF;
    private String bluetoothMAC;
    private String email;
    private String senha;

    public Usuario() {
        ID = "";
        nome = "";
        CPF = "";
        bluetoothMAC = "";
        email = "";
        senha = "";
    }


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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ID);
        dest.writeString(this.nome);
        dest.writeString(this.CPF);
        dest.writeString(this.bluetoothMAC);
        dest.writeString(this.email);
        dest.writeString(this.senha);
    }

    protected Usuario(Parcel in) {
        this.ID = in.readString();
        this.nome = in.readString();
        this.CPF = in.readString();
        this.bluetoothMAC = in.readString();
        this.email = in.readString();
        this.senha = in.readString();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel source) {
            return new Usuario(source);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };
}
