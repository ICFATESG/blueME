package net.icfatesg.blueme.activitie;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.icfatesg.blueme.R;
import net.icfatesg.blueme.model.Usuario;
import net.icfatesg.blueme.service.Autenticacao;
import net.icfatesg.blueme.service.Utilidade;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecuperarSenhaActivity extends AppCompatActivity {
    @BindView(R.id.editTextRecuperarSenha)
    EditText mEditTextRecuperarSenha;
    AlertDialog.Builder builderDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);
        ButterKnife.bind(this);
        builderDialog = new AlertDialog.Builder(RecuperarSenhaActivity.this);
    }
    @OnClick(R.id.buttonRecuperar)
    public void recuperarSenha(View view){
        Usuario usuario = new Usuario();
        usuario.setEmail(mEditTextRecuperarSenha.getText().toString());
        if (new Utilidade().isValidEmail(usuario.getEmail())){
            builderDialog.setMessage("O campo foi deixado em branco");
            builderDialog.show();
        }else{
            new Autenticacao(usuario).recuperarSenha(new Autenticacao.MensagemAuth() {
                @Override
                public void mensagem(String mensagem) {
                    builderDialog.setMessage(mensagem);
                    builderDialog.show();
                }

                @Override
                public void sucesso(boolean sucesso) {

                }
            });
        }
    }
}
