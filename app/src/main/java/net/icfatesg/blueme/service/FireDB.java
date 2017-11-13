package net.icfatesg.blueme.service;

import android.provider.ContactsContract;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import net.icfatesg.blueme.model.Evento;
import net.icfatesg.blueme.model.EventoEOficinasVisitadas;
import net.icfatesg.blueme.model.Oficina;
import net.icfatesg.blueme.model.OficinaVisitada;
import net.icfatesg.blueme.model.Usuario;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by harlock on 10/11/17.
 */

public class FireDB {
    private DatabaseReference mDatabase;
    private DatabaseReference mUsuario,mOficina,mOficinaVisitadas,mEvento;
    private String userID;
    public FireDB() {
        this.mDatabase = FirebaseDatabase.getInstance().getReference();
        this.userID = new Autenticacao(null).getIDUser();
        this.mUsuario = mDatabase.child("Usuarios").child(userID);
        this.mOficina = mDatabase.child("Oficinas");
        this.mOficinaVisitadas = mUsuario.child("oficinaVisitadas");
        this.mEvento = mDatabase.child("Evento");
        this.keepOnThePhone();
    }
    // Metodos para pegar dados
    public void getEventos(final OnCompleteEventos callback){
        this.mEvento.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                callback.getEventos(retornaLista(dataSnapshot,Evento.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void getEventosEOficinasVisitadas(final OnCompleteVisitados callback){
        this.mOficinaVisitadas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot snapShotIDEvento) {
                final List<EventoEOficinasVisitadas> aLista = new ArrayList<>();
                for (final DataSnapshot child:snapShotIDEvento.getChildren()) {
                    mEvento.child(child.getKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            EventoEOficinasVisitadas obj = new EventoEOficinasVisitadas();
                            obj.setID(child.getKey());
                            obj.setNomeEvento(dataSnapshot.getValue(Evento.class).getNomeEvento());
                            obj.setOficinaVisitadas(retornaLista(child,OficinaVisitada.class));
                            aLista.add(obj);
                            callback.getEventoEOficinasVisitados(aLista);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void getOficias(Evento evento, final OnCompleteOficinas callback){
        this.mOficina.child(evento.getID()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                callback.getOficinas(retornaLista(dataSnapshot,Oficina.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void getOficinasVisitadas(Evento evento, final OnCompleteOficiasVisitadas callback){
        this.mOficinaVisitadas.child(evento.getID()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                callback.getOficinasVisitadas(retornaLista(dataSnapshot,OficinaVisitada.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void getUsuario(final OnCompleteUsuario callback){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        this.mUsuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Usuario u = new Usuario();
                dataSnapshot.getValue();
                u.setEmail(user.getEmail());
                u.setNome(dataSnapshot.getValue(Usuario.class).getNome());
                u.setSenha(dataSnapshot.getValue(Usuario.class).getSenha());
                u.setCPF(dataSnapshot.getValue(Usuario.class).getCPF());
                u.setBluetoothMAC(dataSnapshot.getValue(Usuario.class).getBluetoothMAC());
                u.setID(dataSnapshot.getValue(Usuario.class).getID());
                callback.getUsuario(u);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void atualizarUsuario(Usuario usuario, OnCompleteUsuario callback){
        this.mUsuario.child("bluetoothMAC").setValue(usuario.getBluetoothMAC());
        this.mUsuario.child("cpf").setValue(usuario.getCPF());
        this.mUsuario.child("nome").setValue(usuario.getNome());
    }



    // Interfaces de retorno
    public interface OnCompleteOficinas{
        void getOficinas(List<Oficina> oficinaList);
    }
    public interface OnCompleteOficiasVisitadas{
        void getOficinasVisitadas(List<OficinaVisitada> oficinaVisitadaList);
    }
    public interface OnCompleteEventos{
        void getEventos(List<Evento> eventoList );
    }
    public interface OnCompleteUsuario{
        void getUsuario(Usuario usuario);
    }
    public interface OnCompleteVisitados{
        void getEventoEOficinasVisitados(List<EventoEOficinasVisitadas> geralList);
    }
    // Mantem Algumas coisas Sincronizadas
    private void keepOnThePhone(){
        this.mEvento.keepSynced(true);
        this.mUsuario.keepSynced(true);
        this.mOficinaVisitadas.keepSynced(true);
    }

    private <T> List<T> retornaLista(DataSnapshot snapshot,Class<T> aClass){
        List<T> lista = new ArrayList<>();
        for (DataSnapshot child:snapshot.getChildren()) {
            lista.add(child.getValue(aClass));
        }
        return lista;
    }

    private <T> List<T> retornaLista(DataSnapshot snapshot,String filtroID,Class<T> aClass){
        List<T> lista = new ArrayList<>();
        for (DataSnapshot child:snapshot.getChildren()) {
            if (filtroID.equals(child.getKey())) lista.add(child.getValue(aClass));
        }
        return lista;
    }
}
