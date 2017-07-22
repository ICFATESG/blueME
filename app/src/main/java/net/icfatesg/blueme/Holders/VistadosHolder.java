package net.icfatesg.blueme.Holders;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import net.icfatesg.blueme.R;
import net.icfatesg.blueme.model.Evento;
import net.icfatesg.blueme.model.OficinaVisitada;
import net.icfatesg.blueme.services.FireBase;

import java.util.ArrayList;

/**
 * Created by minerthal on 12/06/17.
 */

public class VistadosHolder extends RecyclerView.ViewHolder  {
    private TextView evento;
    private TextView oficina;
    private TextView status;
    private FireBase fireDB;

    public VistadosHolder(View itemView) {
        super(itemView);
        evento = (TextView) itemView.findViewById(R.id.textViewEventoNome);
        oficina = (TextView) itemView.findViewById(R.id.textViewEventoOfinica);
        status =(TextView)  itemView.findViewById(R.id.textViewEventoStatus);
        fireDB = new FireBase();
    }

    public void updateUI(OficinaVisitada visitada){
                evento.setText(visitada.getNomeEvento());
                oficina.setText(visitada.getNomeOFICINA());
                if(visitada.getHoraEntrada().equals("")){
                    status.setTextColor(Color.parseColor("#3b1212"));
                    status.setText("Não visitada");
                }else {
                    status.setText("Visitada");
                }

    }
}
