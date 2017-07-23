package net.icfatesg.blueme.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import net.icfatesg.blueme.Adapters.VisitadoAdapter;
import net.icfatesg.blueme.R;
import net.icfatesg.blueme.model.Evento;
import net.icfatesg.blueme.model.OficinaVisitada;
import net.icfatesg.blueme.services.FireBase;

import java.util.ArrayList;
import java.util.List;

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
    private List<Evento> eventos;
    private ArrayList<OficinaVisitada> oficinaVisitadas;



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
        fireDB = new FireBase();
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
                eventos = new ArrayList<Evento>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    eventos.add(child.getValue(Evento.class));
                }
                ArrayAdapter<Evento> adapter = new ArrayAdapter<Evento>(getContext(), R.layout.support_simple_spinner_dropdown_item, eventos);
                adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinnerEventos.setAdapter(adapter);
//                listenerSpinner();
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
        try {
        fireDB.getmOficinasVisitadas().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    oficinaVisitadas = new ArrayList<OficinaVisitada>();
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        oficinaVisitadas.add(child.getValue(OficinaVisitada.class));
                    }
                        VisitadoAdapter adapter = new VisitadoAdapter(oficinaVisitadas);
                        recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        }catch (Exception e ){
            Log.d("ERRO",e.getMessage());
        }

    }
    private void listenerSpinner(){
        spinnerEventos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List<OficinaVisitada> ofv = new ArrayList<OficinaVisitada>();
                for (OficinaVisitada of: oficinaVisitadas) {
                    if(eventos.get(position).getID().equals(of.getIDEVENTO())){
                        ofv.add(of);
                    }
                }
                recyclerView.setAdapter(new VisitadoAdapter(ofv));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
