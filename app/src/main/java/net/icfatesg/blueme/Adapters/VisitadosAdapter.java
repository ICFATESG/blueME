package net.icfatesg.blueme.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.icfatesg.blueme.Holders.VistadosHolder;
import net.icfatesg.blueme.R;
import net.icfatesg.blueme.model.OficinaVisitada;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by minerthal on 12/06/17.
 */

public class VisitadosAdapter extends RecyclerView.Adapter<VistadosHolder>{

    private List<OficinaVisitada> oficinaVisitadas;

    public VisitadosAdapter(List<OficinaVisitada> oficinaVisitadas) {
        this.oficinaVisitadas = oficinaVisitadas;
    }

    @Override
    public VistadosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card_generic = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_content_visitados, parent,false);
        return new VistadosHolder(card_generic);
    }

    @Override
    public void onBindViewHolder(VistadosHolder holder, int position) {
        holder.updateUI(oficinaVisitadas.get(position));
    }

    @Override
    public int getItemCount() {
        return oficinaVisitadas.size();
    }
}
