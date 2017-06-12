package net.icfatesg.blueme.services;

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
    private DatabaseReference mOficina;
    private DatabaseReference mUsuario;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    private Context context;

    public FireBase() {
    }

    public FireBase(Context context) {
        this.mAuth =  FirebaseAuth.getInstance();
        this.currentUser = mAuth.getCurrentUser();
        this.mEvento = FirebaseDatabase.getInstance().getReference().child("Evento");
        this.mUsuario = FirebaseDatabase.getInstance().getReference().child("Usuarios");
        this.context = context;

    }


    public void saveParticipacao(OficinaVisitada oficinaVisitada){
        if (oficinaVisitada.getID() != null || oficinaVisitada.getID() != ""){
            String id = mUsuario.getKey();
            this.mUsuario.child(currentUser.getUid()).child(id).setValue(oficinaVisitada);
        }else{
            this.mUsuario.child(currentUser.getUid()).child(oficinaVisitada.getID()).setValue(oficinaVisitada);
        }

    }


    public DatabaseReference getmEvento() {
        return mEvento;
    }

    public DatabaseReference getmOficina() {
        return mOficina;
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
        this.mOficina.keepSynced(true);
    }
}
