package net.icfatesg.blueme.services;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import net.icfatesg.blueme.model.Evento;
import net.icfatesg.blueme.model.Oficina;
import net.icfatesg.blueme.model.OficinaVisitada;

import java.util.ArrayList;

/**
 * Created by minerthal on 12/06/17.
 */

public class FireBase {
    private DatabaseReference mEvento;
    private DatabaseReference mUsuario;
    private DatabaseReference mOficinas;
    private DatabaseReference mOficinasVisitadas;
    //remove
    private BluetoothAdapter mBluetoothAdapter;
    //
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    private Context context;

    public FireBase() {
    }

    public FireBase(Context context) {
        this.mAuth =  FirebaseAuth.getInstance();
        this.currentUser = mAuth.getCurrentUser();
        this.mEvento = FirebaseDatabase.getInstance().getReference().child("Evento");
        this.mUsuario = FirebaseDatabase.getInstance().getReference().child("Usuarios").child(currentUser.getUid());
        this.mOficinasVisitadas = mUsuario.child("OficinaVisitada");
        this.mOficinas = FirebaseDatabase.getInstance().getReference().child("Oficinas");
        this.context = context;

//        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//
//        mBluetoothAdapter.enable();
//        for (int i = 0; i < 10; i++) {
//            OficinaVisitada ofv = new OficinaVisitada(getmOficinasVisitadas().push().getKey());
//            ofv.setHoraEntrada("09:42:17");
//            ofv.setHoraSaida("10:01:12");
//            ofv.setIDOFICINA("-KmRw9GucLTDkiSaEVzL");
//            ofv.setMac(mBluetoothAdapter.getAddress());
//            ofv.setNomeEvento("1º Forúm Tecnologio - FATESG");
//            ofv.setNomeOFICINA("Aprendendo Python");
//            mOficinasVisitadas.child(ofv.getID()).setValue(ofv);
//
//        }

    }


    public void saveParticipacao(OficinaVisitada oficinaVisitada){
        if (oficinaVisitada.getID() != null || oficinaVisitada.getID() != ""){
            String id = mUsuario.getKey();
            this.mUsuario.child(currentUser.getUid()).child(id).setValue(oficinaVisitada);
        }else{
            this.mUsuario.child(currentUser.getUid()).child(oficinaVisitada.getID()).setValue(oficinaVisitada);
        }
    }

    public DatabaseReference getmOficinas() {

        return mOficinas;
    }

    public DatabaseReference getmOficinasVisitadas() {

        return mOficinasVisitadas;
    }

    public DatabaseReference getmEvento() {
        return mEvento;
    }

    public DatabaseReference getmUsuario() {
        return mUsuario;
    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public FirebaseUser getCurrentUser() {
        return currentUser;
    }

    public Context getContext() {
        return context;
    }
    private void keepOnThePhone(){
        this.mEvento.keepSynced(true);
        this.mUsuario.keepSynced(true);
    }
}
