package net.icfatesg.blueme.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.icfatesg.blueme.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MinhaContaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MinhaContaFragment extends Fragment {

    private View view;
    public MinhaContaFragment() {
        // Required empty public constructor
    }


    public static MinhaContaFragment newInstance(String param1, String param2) {
        MinhaContaFragment fragment = new MinhaContaFragment();
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
        view = inflater.inflate(R.layout.fragment_minha_conta, container, false);

        return view;
    }

}
