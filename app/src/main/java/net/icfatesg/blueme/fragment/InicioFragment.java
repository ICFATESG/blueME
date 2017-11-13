package net.icfatesg.blueme.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import net.icfatesg.blueme.R;
import net.icfatesg.blueme.model.Evento;
import net.icfatesg.blueme.model.EventoEOficinasVisitadas;
import net.icfatesg.blueme.model.Oficina;
import net.icfatesg.blueme.model.OficinaVisitada;
import net.icfatesg.blueme.recyclerlist.adapter.OficinaAdapter;
import net.icfatesg.blueme.service.FireDB;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InicioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InicioFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private View view;
    @BindView(R.id.spinnerListarEventosIdos)
    Spinner mSpinner;
    @BindView(R.id.mensagemInicio)
    TextView mMensagemInicio;
    @BindView(R.id.recyclerViewOficinasVisitadas)
    RecyclerView mRecyclerViewOficinasVisitadas;
    private ArrayAdapter<EventoEOficinasVisitadas> mAdapterEvento;
    private FireDB mFireDB;
    private OficinaAdapter mOficinaAdapter;
    public InicioFragment() {
        // Required empty public constructor
    }


    public static InicioFragment newInstance(String param1, String param2) {
        InicioFragment fragment = new InicioFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        mFireDB = new FireDB();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_inicio, container, false);
        ButterKnife.bind(this,view);
        mFireDB = new FireDB();
        mRecyclerViewOficinasVisitadas.setHasFixedSize(true);
        mRecyclerViewOficinasVisitadas.setLayoutManager(new LinearLayoutManager(getContext()));
        loadSpinner();
        return view;
    }

    private void loadSpinner(){
        mFireDB.getEventosEOficinasVisitadas(new FireDB.OnCompleteVisitados() {
            @Override
            public void getEventoEOficinasVisitados(List<EventoEOficinasVisitadas> geralList) {
                mAdapterEvento = new ArrayAdapter<EventoEOficinasVisitadas>(getContext(), R.layout.support_simple_spinner_dropdown_item, geralList);
                mAdapterEvento.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                mSpinner.setAdapter(mAdapterEvento);
                if (geralList.size() == 0){
                    mMensagemInicio.setText("Você ainda não visitou nehum evento");
                }else{
                    mMensagemInicio.setVisibility(View.GONE);
                }
            }
        });
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mOficinaAdapter = new OficinaAdapter(((EventoEOficinasVisitadas)mSpinner.getSelectedItem()).getOficinaVisitadas());
                mRecyclerViewOficinasVisitadas.setAdapter(mOficinaAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        
    }
}
