package net.icfatesg.blueme.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by minerthal on 12/06/17.
 */

public class Oficina implements Parcelable {
    private String nomeOficina;
    private String palestrante;
    private String horaInicio;
    private String horaFim;

    public Oficina() {

    }

    public Oficina(String nomeOficina, String palestrante, String horaInicio, String horaFim) {
        this.nomeOficina = nomeOficina;
        this.palestrante = palestrante;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }

    public String getNomeOficina() {
        return nomeOficina;
    }

    public void setNomeOficina(String nomeOficina) {
        this.nomeOficina = nomeOficina;
    }

    public String getPalestrante() {
        return palestrante;
    }

    public void setPalestrante(String palestrante) {
        this.palestrante = palestrante;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nomeOficina);
        dest.writeString(this.palestrante);
        dest.writeString(this.horaInicio);
        dest.writeString(this.horaFim);
    }

    protected Oficina(Parcel in) {
        this.nomeOficina = in.readString();
        this.palestrante = in.readString();
        this.horaInicio = in.readString();
        this.horaFim = in.readString();
    }

    public static final Parcelable.Creator<Oficina> CREATOR = new Parcelable.Creator<Oficina>() {
        @Override
        public Oficina createFromParcel(Parcel source) {
            return new Oficina(source);
        }

        @Override
        public Oficina[] newArray(int size) {
            return new Oficina[size];
        }
    };
}
