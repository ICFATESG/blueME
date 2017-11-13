package net.icfatesg.blueme.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.icfatesg.blueme.R;
import net.icfatesg.blueme.activitie.ActivityPrincipal;
import net.icfatesg.blueme.model.Evento;
import net.icfatesg.blueme.recyclerlist.adapter.EventoAdapter;
import net.icfatesg.blueme.recyclerlist.holder.EventoHolder;
import net.icfatesg.blueme.service.FireDB;
import net.icfatesg.blueme.service.RecyclerItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EventoFragment extends Fragment {

    private View view;
    @BindView(R.id.recyclerViewEventos)
    RecyclerView mRecyclerViewEventos;
    private EventoAdapter mEventoAdapter;
    public EventoFragment() {
        // Required empty public constructor
    }

    public static EventoFragment newInstance(String param1, String param2) {
        EventoFragment fragment = new EventoFragment();
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
        view = inflater.inflate(R.layout.fragment_evento, container, false);
        ButterKnife.bind(this,view);
        mRecyclerViewEventos.setHasFixedSize(true);
        mRecyclerViewEventos.setLayoutManager(new LinearLayoutManager(getContext()));
        new FireDB().getEventos(new FireDB.OnCompleteEventos() {
            @Override
            public void getEventos(final List<Evento> eventoList) {
                mEventoAdapter = new EventoAdapter(eventoList);
                mRecyclerViewEventos.setAdapter(mEventoAdapter);

                mRecyclerViewEventos.addOnItemTouchListener(
                        new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                            @Override public void onItemClick(View view, int position) {
                                ((ActivityPrincipal)getActivity()).loadDetalhesEvento(eventoList.get(position));
//                                ActivityPrincipal.mActivityPrincipal.loadDetalhesEvento(eventoList.get(position));
                            }
                        })
                );
            }
        });


        return view;
    }

}
