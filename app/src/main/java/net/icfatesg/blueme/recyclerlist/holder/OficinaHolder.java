package net.icfatesg.blueme.recyclerlist.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import net.icfatesg.blueme.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by harlock on 10/11/17.
 */

public class OficinaHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.textViewNomeEventoOficina)
    public TextView mTextViewNomeEventoOficina;
    @BindView(R.id.textViewHorario)
    public TextView mTextViewHorario;
    @BindView(R.id.textViewOrientador)
    public TextView mTextViewOrientador;
    @BindView(R.id.textViewVisita)
    public TextView mTextViewVisita;
    @BindView(R.id.textViewDescricao)
    public TextView mTextViewDescricao;



    public OficinaHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);

    }
}
