package net.icfatesg.blueme.Holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import net.icfatesg.blueme.R;

import org.w3c.dom.Text;

/**
 * Created by harlock on 22/07/17.
 */

public class OficinaHolder extends RecyclerView.ViewHolder {
    public TextView textViewNomeOficina;
    public TextView textViewHorario;
    public TextView textViewPalestrante;
    public TextView textViewLocal;
    public OficinaHolder(View itemView) {
        super(itemView);
        this.textViewNomeOficina = (TextView) itemView.findViewById(R.id.textViewNomeOficina);
        this.textViewHorario = (TextView) itemView.findViewById(R.id.textViewHorario);
        this.textViewPalestrante = (TextView) itemView.findViewById(R.id.textViewPalestrante);
        this.textViewLocal = (TextView) itemView.findViewById(R.id.textViewLocal);
    }
}
