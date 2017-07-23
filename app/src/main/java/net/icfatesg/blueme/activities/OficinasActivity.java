package net.icfatesg.blueme.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import net.icfatesg.blueme.Adapters.OficinaAdapter;
import net.icfatesg.blueme.R;
import net.icfatesg.blueme.model.Evento;
import net.icfatesg.blueme.model.Oficina;
import net.icfatesg.blueme.services.FireBase;

import java.util.ArrayList;
import java.util.List;

public class OficinasActivity extends AppCompatActivity {

    private Evento evento;
    private FireBase fireBase;
    private RecyclerView recyclerView;
    private TextView nomeEvento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        evento = getIntent().getParcelableExtra("EVENTO");
        try {
            setContentView(R.layout.activity_oficinas);
            nomeEvento = (TextView) findViewById(R.id.textViewEventoNomeB);
            fireBase = new FireBase();

            nomeEvento.setText(evento.getNomeEvento());
            recyclerView = (RecyclerView) findViewById(R.id.OficinasRecycler);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            // Seta as oficinas no recycler view
            fireBase.getmOficinas().child(evento.getID()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    List<Oficina> oficinas = new ArrayList<Oficina>();
                    for (DataSnapshot child: dataSnapshot.getChildren()){
                        oficinas.add(child.getValue(Oficina.class));
                    }
                    recyclerView.setAdapter(new OficinaAdapter(oficinas));
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }catch (Exception e){
            Log.d("ERRO",e.getMessage());
        }

    }
}
