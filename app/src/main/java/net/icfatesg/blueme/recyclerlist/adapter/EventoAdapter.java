package net.icfatesg.blueme.recyclerlist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.icfatesg.blueme.R;
import net.icfatesg.blueme.model.Evento;
import net.icfatesg.blueme.recyclerlist.holder.EventoHolder;

import java.util.List;

/**
 * Created by harlock on 10/11/17.
 */

public class EventoAdapter extends RecyclerView.Adapter<EventoHolder>{
    private List<Evento> mEventoList;

    public EventoAdapter(List<Evento> mEventoList) {
        this.mEventoList = mEventoList;
    }

    @Override
    public EventoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EventoHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_evento, parent,false));
    }

    @Override
    public void onBindViewHolder(EventoHolder holder, int position) {
        holder.mTextViewNomeEvento.setText(mEventoList.get(position).getNomeEvento());
        holder.mTextViewDataEvento.setText(mEventoList.get(position).getHoraInicioEvento() +" Até "+mEventoList.get(position).getHoraFimEvento());
        holder.mTextViewLocalEvento.setText(mEventoList.get(position).getLocalizacaoEvento());
    }

    @Override
    public int getItemCount() {
        return this.mEventoList.size();
    }
}
