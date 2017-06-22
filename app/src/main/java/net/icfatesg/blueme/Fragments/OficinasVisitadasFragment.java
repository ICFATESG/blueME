package net.icfatesg.blueme.Fragments;


import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import net.icfatesg.blueme.Adapters.VisitadosAdapter;
import net.icfatesg.blueme.R;
import net.icfatesg.blueme.model.Evento;
import net.icfatesg.blueme.model.OficinaVisitada;
import net.icfatesg.blueme.services.FireBase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OficinasVisitadasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class OficinasVisitadasFragment extends Fragment {
    //
    private View view;
    private Spinner spinnerEventos;
    private FireBase fireDB;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;



    public OficinasVisitadasFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static OficinasVisitadasFragment newInstance(String param1, String param2) {
        OficinasVisitadasFragment fragment = new OficinasVisitadasFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fireDB = new FireBase(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_oficinas_visitadas, container, false);
        spinnerEventos = (Spinner) view.findViewById(R.id.spinnerEventos);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        updateSpinnerEventos();
        updateRecyclerView();

        return view;
    }



    private void updateSpinnerEventos(){
        fireDB.getmEvento().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Evento> eventos = new ArrayList<Evento>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    eventos.add(child.getValue(Evento.class));
                }
                ArrayAdapter<Evento> adapter = new ArrayAdapter<Evento>(getContext(), R.layout.support_simple_spinner_dropdown_item, eventos);
                adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinnerEventos.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void updateRecyclerView(){
        //


        //
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setLayoutManager(mLayoutManager);

        fireDB.getmOficinasVisitadas().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<OficinaVisitada> oficinaVisitadas = new ArrayList<OficinaVisitada>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    oficinaVisitadas.add(child.getValue(OficinaVisitada.class));
                    int o = 1
;                }
                VisitadosAdapter adapter = new VisitadosAdapter(oficinaVisitadas);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
