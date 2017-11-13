package net.icfatesg.blueme.model;

/**
 * Created by harlock on 10/11/17.
 */

public class OficinaVisitada extends Oficina {
    private String horaEntrada;
    private String horaSaida;
    private String IDOFICINA;
    private String mac;

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

    public String getIDOFICINA() {
        return IDOFICINA;
    }

    public void setIDOFICINA(String IDOFICINA) {
        this.IDOFICINA = IDOFICINA;
    }
}
