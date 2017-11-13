package net.icfatesg.blueme.recyclerlist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import net.icfatesg.blueme.R;
import net.icfatesg.blueme.model.Oficina;
import net.icfatesg.blueme.model.OficinaVisitada;
import net.icfatesg.blueme.recyclerlist.holder.OficinaHolder;

import java.util.List;

/**
 * Created by harlock on 10/11/17.
 */

public class OficinaAdapter extends RecyclerView.Adapter<OficinaHolder> {
    private List<OficinaVisitada> mOficinaList;

    public OficinaAdapter(List mOficinaList) {
            this.mOficinaList = mOficinaList;
    }


    @Override
    public OficinaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OficinaHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_oficina, parent,false));
    }

    @Override
    public void onBindViewHolder(OficinaHolder holder, int position) {

        if (mOficinaList.get(position) instanceof OficinaVisitada){
            OficinaVisitada oficina = mOficinaList.get(position);
            holder.mTextViewNomeEventoOficina.setText(oficina.getNomeOficina());
            holder.mTextViewHorario.setText(oficina.getHoraInicio()+" até "+oficina.getHoraFim());
            holder.mTextViewVisita.setText("Você esteve entre "+oficina.getHoraEntrada()+" até "+oficina.getHoraSaida());
        }else{
            Oficina oficina = mOficinaList.get(position);
            holder.mTextViewNomeEventoOficina.setText(oficina.getNomeOficina());
            holder.mTextViewHorario.setText(oficina.getHoraInicio()+" até "+ oficina.getHoraFim());

        }



    }

    @Override
    public int getItemCount() {
            return this.mOficinaList.size();
    }
}
