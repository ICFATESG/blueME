package net.icfatesg.blueme.Holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import net.icfatesg.blueme.R;

/**
 * Created by harlock on 22/07/17.
 */

public class EventoHolder extends RecyclerView.ViewHolder {
    public TextView textViewDescricao;
    public TextView  textViewNomeEvento;
    public TextView textViewHoraInicio;
    public TextView textViewHoraFim;

    public EventoHolder(View itemView) {
        super(itemView);
        textViewDescricao = (TextView)itemView.findViewById(R.id.textViewDescricao);
        textViewNomeEvento = (TextView)itemView.findViewById(R.id.textViewNomeEvento);
        textViewHoraInicio = (TextView)itemView.findViewById(R.id.textViewHoraInicio);
        textViewHoraFim = (TextView)itemView.findViewById(R.id.textViewHoraFim);
    }
}
