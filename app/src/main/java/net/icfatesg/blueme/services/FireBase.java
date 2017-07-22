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
import net.icfatesg.blueme.model.Usuario;

import java.util.ArrayList;

/**
 * Created by minerthal on 12/06/17.
 */

public class FireBase {
    private DatabaseReference mEvento;
    private DatabaseReference mUsuario;
    private DatabaseReference mOficinas;
    private DatabaseReference mOficinasVisitadas;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    public FireBase() {
        this.mAuth =  FirebaseAuth.getInstance();
        this.currentUser = mAuth.getCurrentUser();
        this.mEvento = FirebaseDatabase.getInstance().getReference().child("Evento");
        this.mUsuario = FirebaseDatabase.getInstance().getReference().child("Usuarios").child(currentUser.getUid());
        this.mOficinasVisitadas = mUsuario.child("oficinaVisitadas");
        this.mOficinas = FirebaseDatabase.getInstance().getReference().child("Oficinas");

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

    public void updateUsuarioMAC(Usuario usuario){
        this.mUsuario.setValue(usuario);
    }

    private void keepOnThePhone(){
        this.mEvento.keepSynced(true);
        this.mUsuario.keepSynced(true);
    }

    public void inserePaNois(Usuario usuario){
        this.mUsuario.setValue(usuario);
    }

}
