package net.icfatesg.blueme.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.icfatesg.blueme.Holders.OficinaHolder;
import net.icfatesg.blueme.R;
import net.icfatesg.blueme.model.Oficina;

import java.util.List;

/**
 * Created by harlock on 22/07/17.
 */

public class OficinaAdapter extends RecyclerView.Adapter<OficinaHolder>{
    private List<Oficina> oficinas;

    public OficinaAdapter(List<Oficina> oficinas) {
        this.oficinas = oficinas;
    }


    @Override
    public OficinaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card_generic = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_content_oficina, parent,false);
        return new OficinaHolder(card_generic);
    }

    @Override
    public void onBindViewHolder(OficinaHolder holder, int position) {
        holder.textViewNomeOficina.setText(oficinas.get(position).getNomeOficina());
        holder.textViewHorario.setText(oficinas.get(position).getHoraInicio()+" - "+oficinas.get(position).getHoraFim());
        holder.textViewPalestrante.setText("Palestrante :"+oficinas.get(position).getPalestrante());
        holder.textViewLocal.setText(oficinas.get(position).getLocal());
    }

    @Override
    public int getItemCount() {
        return this.oficinas.size();
    }
}
