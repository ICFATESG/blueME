package net.icfatesg.blueme.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.icfatesg.blueme.Holders.VistadoHolder;
import net.icfatesg.blueme.R;
import net.icfatesg.blueme.model.OficinaVisitada;

import java.util.List;

/**
 * Created by minerthal on 12/06/17.
 */

public class VisitadoAdapter extends RecyclerView.Adapter<VistadoHolder>{

    private List<OficinaVisitada> oficinaVisitadas;

    public VisitadoAdapter(List<OficinaVisitada> oficinaVisitadas) {
        this.oficinaVisitadas = oficinaVisitadas;
    }

    @Override
    public VistadoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card_generic = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_content_visitados, parent,false);
        return new VistadoHolder(card_generic);
    }

    @Override
    public void onBindViewHolder(VistadoHolder holder, int position) {
        holder.updateUI(oficinaVisitadas.get(position));
    }

    @Override
    public int getItemCount() {
        return oficinaVisitadas.size();
    }
}
