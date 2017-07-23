package net.icfatesg.blueme.Fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import net.icfatesg.blueme.R;
import net.icfatesg.blueme.model.Usuario;
import net.icfatesg.blueme.services.FireBase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MinhaContaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MinhaContaFragment extends Fragment {
    private EditText editTextNomeCompletoMinhaConta;
    private EditText editTextCPFMinhaConta;
    private EditText editTextEnderecoDeEmailMinhaConta;
    private EditText editTextEnderecoMACMinhaConta;
    private Button buttonConfirmarMinhaConta;

    public MinhaContaFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
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
        View view = inflater.inflate(R.layout.fragment_minha_conta, container, false);
        //
        this.editTextNomeCompletoMinhaConta = (EditText)view.findViewById(R.id.editTextNomeCompletoMinhaConta);
        this.editTextCPFMinhaConta = (EditText)view.findViewById(R.id.editTextCPFMinhaConta);
        this.editTextEnderecoDeEmailMinhaConta = (EditText)view.findViewById(R.id.editTextEnderecoDeEmailMinhaConta);
        this.editTextEnderecoMACMinhaConta = (EditText)view.findViewById(R.id.editTextEnderecoMACMinhaConta);
        this.buttonConfirmarMinhaConta = (Button) view.findViewById(R.id.buttonConfirmarMinhaConta);
        // Seta os valores para edição
        new FireBase().getmUsuario().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                editTextNomeCompletoMinhaConta.setText(dataSnapshot.getValue(Usuario.class).getNome());
                editTextCPFMinhaConta.setText(dataSnapshot.getValue(Usuario.class).getCPF());
                editTextEnderecoDeEmailMinhaConta.setText(dataSnapshot.getValue(Usuario.class).getEmail());
                editTextEnderecoMACMinhaConta.setText(dataSnapshot.getValue(Usuario.class).getBluetoothMAC());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        buttonConfirmarMinhaConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Usuario u = new Usuario();
                u.setEmail(editTextEnderecoDeEmailMinhaConta.getText().toString());
                u.setNome(editTextNomeCompletoMinhaConta.getText().toString());
                u.setCPF(editTextCPFMinhaConta.getText().toString());
                u.setBluetoothMAC(editTextEnderecoMACMinhaConta.getText().toString());

                new AlertDialog.Builder(getActivity())
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Concluir edição")
                        .setMessage("Salvar alterações?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new FireBase().getmUsuario().setValue(u);
                            }

                        })
                        .setNegativeButton("Não", null)
                        .show();

            }
        });


        return  view;
    }

}
