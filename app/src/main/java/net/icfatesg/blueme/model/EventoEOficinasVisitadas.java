package net.icfatesg.blueme.model;

import java.util.List;

/**
 * Created by harlock on 12/11/17.
 */

public class EventoEOficinasVisitadas extends Evento {
    private List<OficinaVisitada> oficinaVisitadas;
    public List<OficinaVisitada> getOficinaVisitadas() {
        return oficinaVisitadas;
    }

    public void setOficinaVisitadas(List<OficinaVisitada> oficinaVisitadas) {
        this.oficinaVisitadas = oficinaVisitadas;
    }
}
