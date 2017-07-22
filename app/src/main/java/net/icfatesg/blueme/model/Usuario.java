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
    private List<OficinaVisitada> oficinaVisitadas;

    public Usuario() {
        this.oficinaVisitadas = new ArrayList<>();
    }

    public Usuario(String ID, String nome, String CPF, String bluetoothMAC, List<OficinaVisitada> oficinaVisitadas) {
        this.ID = ID;
        this.nome = nome;
        this.CPF = CPF;
        this.bluetoothMAC = bluetoothMAC;
        this.oficinaVisitadas = oficinaVisitadas;
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

    public List<OficinaVisitada> getOficinaVisitadas() {
        return oficinaVisitadas;
    }

    public void setOficinaVisitadas(List<OficinaVisitada> oficinaVisitadas) {
        this.oficinaVisitadas = oficinaVisitadas;
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
        dest.writeTypedList(this.oficinaVisitadas);
    }

    protected Usuario(Parcel in) {
        this.ID = in.readString();
        this.nome = in.readString();
        this.CPF = in.readString();
        this.bluetoothMAC = in.readString();
        this.oficinaVisitadas = in.createTypedArrayList(OficinaVisitada.CREATOR);
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
