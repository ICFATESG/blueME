package net.icfatesg.blueme.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.icfatesg.blueme.R;
import net.icfatesg.blueme.model.Evento;
import net.icfatesg.blueme.model.Oficina;
import net.icfatesg.blueme.recyclerlist.adapter.OficinaAdapter;
import net.icfatesg.blueme.service.FireDB;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VerDetalhesEventoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VerDetalhesEventoFragment extends Fragment {
    private static final String ARG_PARAM2 = "Evento";

    private Evento mEvento;
    private View view;
    @BindView(R.id.textViewTituloEvento)
    TextView mTextViewTituloEvento;
    @BindView(R.id.recyclerViewListarOficinas)
    RecyclerView mRecyclerView;

    public VerDetalhesEventoFragment() {
        // Required empty public constructor
    }

    public static VerDetalhesEventoFragment newInstance(Evento evento) {
        VerDetalhesEventoFragment fragment = new VerDetalhesEventoFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM2, evento);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mEvento = getArguments().getParcelable(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ver_detalhes_evento, container, false);
        ButterKnife.bind(this,view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mTextViewTituloEvento.setText(mEvento.getNomeEvento());
        new FireDB().getOficias(mEvento, new FireDB.OnCompleteOficinas() {
            @Override
            public void getOficinas(List<Oficina> oficinaList) {
                mRecyclerView.setAdapter(new OficinaAdapter(oficinaList));
            }
        });
        return view;
    }

}
