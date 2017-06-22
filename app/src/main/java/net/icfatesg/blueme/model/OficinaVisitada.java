package net.icfatesg.blueme.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by minerthal on 12/06/17.
 */

public class OficinaVisitada implements Parcelable {
    private String ID;
    private String nomeEvento;
    private String mac;
    private String horaEntrada;
    private String horaSaida;
    private String IDEVENTO;
    private String IDOFICINA;
    private String nomeOFICINA;

    public OficinaVisitada() {
    }

    public OficinaVisitada(String ID) {
        this.ID = ID;
    }

    public OficinaVisitada(String ID, String nomeEvento, String mac, String horaEntrada, String horaSaida, String IDEVENTO, String IDOFICINA, String nomeOFICINA) {
        this.ID = ID;
        this.nomeEvento = nomeEvento;
        this.mac = mac;
        this.horaEntrada = horaEntrada;
        this.horaSaida = horaSaida;
        this.IDEVENTO = IDEVENTO;
        this.IDOFICINA = IDOFICINA;
        this.nomeOFICINA = nomeOFICINA;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(String horaSaida) {
        this.horaSaida = horaSaida;
    }

    public String getIDEVENTO() {
        return IDEVENTO;
    }

    public void setIDEVENTO(String IDEVENTO) {
        this.IDEVENTO = IDEVENTO;
    }

    public String getIDOFICINA() {
        return IDOFICINA;
    }

    public void setIDOFICINA(String IDOFICINA) {
        this.IDOFICINA = IDOFICINA;
    }

    public String getNomeOFICINA() {
        return nomeOFICINA;
    }

    public void setNomeOFICINA(String nomeOFICINA) {
        this.nomeOFICINA = nomeOFICINA;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ID);
        dest.writeString(this.nomeEvento);
        dest.writeString(this.mac);
        dest.writeString(this.horaEntrada);
        dest.writeString(this.horaSaida);
        dest.writeString(this.IDEVENTO);
        dest.writeString(this.IDOFICINA);
        dest.writeString(this.nomeOFICINA);
    }

    protected OficinaVisitada(Parcel in) {
        this.ID = in.readString();
        this.nomeEvento = in.readString();
        this.mac = in.readString();
        this.horaEntrada = in.readString();
        this.horaSaida = in.readString();
        this.IDEVENTO = in.readString();
        this.IDOFICINA = in.readString();
        this.nomeOFICINA = in.readString();
    }

    public static final Creator<OficinaVisitada> CREATOR = new Creator<OficinaVisitada>() {
        @Override
        public OficinaVisitada createFromParcel(Parcel source) {
            return new OficinaVisitada(source);
        }

        @Override
        public OficinaVisitada[] newArray(int size) {
            return new OficinaVisitada[size];
        }
    };
}
