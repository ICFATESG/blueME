package net.icfatesg.blueme.model;

/**
 * Created by harlock on 10/11/17.
 */

public class Oficina {
    private String ID;
    private String IDEVENTO;
    private String nomeOficina;
    private String palestrante;
    private String local;
    private String horaInicio;
    private String horaFim;

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
}
