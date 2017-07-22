package net.icfatesg.blueme.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.icfatesg.blueme.Holders.EventoHolder;
import net.icfatesg.blueme.R;
import net.icfatesg.blueme.model.Evento;

import java.util.List;

/**
 * Created by harlock on 22/07/17.
 */

public class EventoAdapter extends RecyclerView.Adapter<EventoHolder> {
    private List<Evento> eventos;

    public EventoAdapter(List<Evento> eventos) {
        this.eventos = eventos;
    }

    @Override
    public EventoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card_generic = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_evento, parent,false);
        return new EventoHolder(card_generic);
    }

    @Override
    public void onBindViewHolder(EventoHolder holder, int position) {
        holder.textViewNomeEvento.setText(eventos.get(position).getNomeEvento());
        holder.textViewDescricao.setText(eventos.get(position).getDescricao());
        holder.textViewHoraInicio.setText(eventos.get(position).getHoraInicioEvento());
        holder.textViewHoraFim.setText(eventos.get(position).getHoraFimEvento());
    }

    @Override
    public int getItemCount() {
        return this.eventos.size();
    }
}
