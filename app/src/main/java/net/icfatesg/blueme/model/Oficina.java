package net.icfatesg.blueme.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by minerthal on 12/06/17.
 */

public class Oficina implements Parcelable {
    private String ID;
    private String IDEVENTO;
    private String nomeOficina;
    private String palestrante;
    private String local;
    private String horaInicio;
    private String horaFim;

    public Oficina() {
        ID = "";
        IDEVENTO = "";
        nomeOficina = "";
        palestrante = "";
        local = "";
        horaInicio = "";
        horaFim = "";
    }

    public Oficina(String ID, String IDEVENTO, String nomeOficina, String palestrante, String local, String horaInicio, String horaFim) {
        this.ID = ID;
        this.IDEVENTO = IDEVENTO;
        this.nomeOficina = nomeOficina;
        this.palestrante = palestrante;
        this.local = local;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIDEVENTO() {
        return IDEVENTO;
    }

    public void setIDEVENTO(String IDEVENTO) {
        this.IDEVENTO = IDEVENTO;
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

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
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
        dest.writeString(this.ID);
        dest.writeString(this.IDEVENTO);
        dest.writeString(this.nomeOficina);
        dest.writeString(this.palestrante);
        dest.writeString(this.local);
        dest.writeString(this.horaInicio);
        dest.writeString(this.horaFim);
    }

    protected Oficina(Parcel in) {
        this.ID = in.readString();
        this.IDEVENTO = in.readString();
        this.nomeOficina = in.readString();
        this.palestrante = in.readString();
        this.local = in.readString();
        this.horaInicio = in.readString();
        this.horaFim = in.readString();
    }

    public static final Creator<Oficina> CREATOR = new Creator<Oficina>() {
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
