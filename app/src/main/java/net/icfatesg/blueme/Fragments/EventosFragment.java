package net.icfatesg.blueme.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import net.icfatesg.blueme.Adapters.EventoAdapter;
import net.icfatesg.blueme.R;
import net.icfatesg.blueme.activities.OficinasActivity;
import net.icfatesg.blueme.model.Evento;
import net.icfatesg.blueme.services.FireBase;
import net.icfatesg.blueme.services.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventosFragment extends Fragment {


    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView recyclerView;

    public EventosFragment() {
    }


    public static EventosFragment newInstance(String param1, String param2) {
        EventosFragment fragment = new EventosFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_eventos, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        new FireBase().getEventos(new FireBase.CallbackEventos() {
            @Override
            public void getEventos(final List<Evento> eventoList) {
                recyclerView.setAdapter(new EventoAdapter(eventoList));

                recyclerView.addOnItemTouchListener(
                        new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                            @Override public void onItemClick(View view, int position) {
                                Intent intent = new Intent(getActivity(),OficinasActivity.class);
                                intent.putExtra("EVENTO",eventoList.get(position));
                                startActivity(intent);
                            }
                        })
                );
            }
        });
        return view;
    }

}
