package net.icfatesg.blueme.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import net.icfatesg.blueme.R;
import net.icfatesg.blueme.model.Evento;
import net.icfatesg.blueme.services.FireBase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OficinasVisitadasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class OficinasVisitadasFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //
    private View view;
    private Spinner spinnerEventos;
    private FireBase fireDB;


    public OficinasVisitadasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OficinasVisitadasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OficinasVisitadasFragment newInstance(String param1, String param2) {
        OficinasVisitadasFragment fragment = new OficinasVisitadasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        fireDB = new FireBase(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_oficinas_visitadas, container, false);
        spinnerEventos = (Spinner) view.findViewById(R.id.spinnerEventos);
        updateSpinnerEventos();

        return view;
    }



    public void updateSpinnerEventos(){
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

}
