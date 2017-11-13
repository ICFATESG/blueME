package net.icfatesg.blueme.service;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import net.icfatesg.blueme.model.Usuario;

/**
 * Created by harlock on 10/11/17.
 */

public class Autenticacao {
    private Usuario mUsuario;
    private FirebaseAuth mAuth;

    public Autenticacao(Usuario usuario) {
        this.mUsuario = usuario;
        mAuth = FirebaseAuth.getInstance();
    }
    public void entrar(final MensagemAuth callback){
        this.mAuth.signInWithEmailAndPassword(mUsuario.getEmail(),mUsuario.getSenha())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            callback.mensagem("Bem vindo");
                            callback.sucesso(true);
                        }else{
                            Log.w("FATAL", "Login:failure", task.getException());
                            callback.sucesso(false);
                            callback.mensagem("Email ou senha incorreto");
                        }
                    }
                });

    }
    public void registrar(final MensagemAuth callback){
        this.mAuth.createUserWithEmailAndPassword(mUsuario.getEmail(),mUsuario.getSenha())
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    mUsuario.setID(getIDUser());
                    new FireDB().atualizarUsuario(mUsuario, new FireDB.OnCompleteUsuario() {
                        @Override
                        public void getUsuario(Usuario usuario) {

                        }
                    });
                    callback.mensagem("Bem vindo "+mUsuario.getNome());
                    callback.sucesso(true);
                }else{
                    callback.mensagem("Algo deu errado durante o cadastro");
                    callback.sucesso(false);
                }
            }
        });

    }
    public void recuperarSenha(final MensagemAuth callback){
        this.mAuth.sendPasswordResetEmail(mUsuario.getEmail()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    callback.mensagem("Link para recuperação Enviada para o email:"+mUsuario.getEmail());
                    callback.sucesso(true);
                }else{
                    callback.mensagem("O email informado não foi encontrado na nossa base");
                    callback.sucesso(false);
                }
            }
        });
    }
    public boolean usuarioLogado(){
        FirebaseUser user = this.mAuth.getCurrentUser();
        if (user!=null){
            return true;
        }else {
            return false;
        }

    }
    public String getIDUser(){
        if (mAuth.getCurrentUser()!=null){
            return mAuth.getCurrentUser().getUid();
        }else{
            return null;
        }

    }
    public void updateUsuario(){
        new FireDB().atualizarUsuario(mUsuario, new FireDB.OnCompleteUsuario() {
            @Override
            public void getUsuario(Usuario usuario) {

            }
        });
    }

    public void deslogar() {
        mAuth.signOut();
    }

    public interface MensagemAuth{
        void mensagem(String mensagem);
        void sucesso(boolean sucesso);
    }

}
