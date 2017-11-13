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

public class EventoHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.textViewNomeEvento)
    public TextView mTextViewNomeEvento;
    @BindView(R.id.textViewDataEvento)
    public TextView mTextViewDataEvento;
    @BindView(R.id.textViewLocalEvento)
    public TextView mTextViewLocalEvento;
    public EventoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
