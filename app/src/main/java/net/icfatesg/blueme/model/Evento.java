package net.icfatesg.blueme.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minerthal on 12/06/17.
 */

public class Evento implements Parcelable {
    private String ID;
    private String nomeEvento;
    private String LocalizacaoEvento;
    private String horaInicioEvento;
    private String horaFimEvento;
    private String descricao;

    public Evento() {
    }

    public Evento(String ID, String nomeEvento, String localizacaoEvento, String horaInicioEvento, String horaFimEvento,String descricao) {
        this.ID = ID;
        this.nomeEvento = nomeEvento;
        LocalizacaoEvento = localizacaoEvento;
        this.horaInicioEvento = horaInicioEvento;
        this.horaFimEvento = horaFimEvento;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return nomeEvento+" - "+LocalizacaoEvento;
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

    public String getLocalizacaoEvento() {
        return LocalizacaoEvento;
    }

    public void setLocalizacaoEvento(String localizacaoEvento) {
        LocalizacaoEvento = localizacaoEvento;
    }

    public String getHoraInicioEvento() {
        return horaInicioEvento;
    }

    public void setHoraInicioEvento(String horaInicioEvento) {
        this.horaInicioEvento = horaInicioEvento;
    }

    public String getHoraFimEvento() {
        return horaFimEvento;
    }

    public void setHoraFimEvento(String horaFimEvento) {
        this.horaFimEvento = horaFimEvento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ID);
        dest.writeString(this.nomeEvento);
        dest.writeString(this.LocalizacaoEvento);
        dest.writeString(this.horaInicioEvento);
        dest.writeString(this.horaFimEvento);
        dest.writeString(this.descricao);
    }

    protected Evento(Parcel in) {
        this.ID = in.readString();
        this.nomeEvento = in.readString();
        this.LocalizacaoEvento = in.readString();
        this.horaInicioEvento = in.readString();
        this.horaFimEvento = in.readString();
        this.descricao = in.readString();
    }

    public static final Creator<Evento> CREATOR = new Creator<Evento>() {
        @Override
        public Evento createFromParcel(Parcel source) {
            return new Evento(source);
        }

        @Override
        public Evento[] newArray(int size) {
            return new Evento[size];
        }
    };
}
