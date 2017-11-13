package net.icfatesg.blueme.activitie;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import net.icfatesg.blueme.R;
import net.icfatesg.blueme.model.Usuario;
import net.icfatesg.blueme.service.Autenticacao;
import net.icfatesg.blueme.service.BluetoothService;
import net.icfatesg.blueme.service.FireDB;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MinhaContaActivity extends AppCompatActivity {
    @BindView(R.id.textViewTitulo)
    TextView mTextViewTitulo;
    @BindView(R.id.editTextNomeCompleto)
     EditText mEditTextNomeCompleto;
    @BindView(R.id.ediTextCPF)
     EditText mEdiTextCPF;
    @BindView(R.id.ediTextEnderecoEmail)
     EditText mEdiTextEnderecoEmail;
    @BindView(R.id.ediTextSenha)
     EditText mEdiTextSenha;
    @BindView(R.id.editTextEnderecoMAC)
     EditText mEditTextEnderecoMAC;
    @BindView(R.id.buttonVerSenha)
    ImageButton mButtonVerSenha;
    @BindView(R.id.buttonConfirmarCriarConta)
     Button mButtonConfirmarCriarConta;
    private Intent intent;
    private AlertDialog.Builder builderDialog;
    private String verificar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        ButterKnife.bind(this);
        verificar = new Autenticacao(null).getIDUser();
        builderDialog = new AlertDialog.Builder(this);
        mEditTextEnderecoMAC.setText(new BluetoothService().getBluetoothMacAddress(getApplicationContext()));
        inflarDados();
    }

    @OnClick(R.id.buttonConfirmarCriarConta)
    public void criarConta(View view){
        Usuario usuario = new Usuario();
        usuario.setNome(mEditTextNomeCompleto.getText().toString());
        usuario.setCPF(mEdiTextCPF.getText().toString());
        usuario.setBluetoothMAC(mEditTextEnderecoMAC.getText().toString());
        usuario.setEmail(mEdiTextEnderecoEmail.getText().toString());
        usuario.setSenha(mEdiTextSenha.getText().toString());
        if (verificar != null && verificar != ""){
            new Autenticacao(usuario).updateUsuario();
            inflarDados();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Atualizado com sucesso");
            builder.show();
        }else{
            new Autenticacao(usuario).registrar(new Autenticacao.MensagemAuth() {
                @Override
                public void mensagem(String mensagem) {
                    builderDialog.setMessage(mensagem).show();
                }

                @Override
                public void sucesso(boolean sucesso) {
                    if (sucesso){
                        intent = new Intent(MinhaContaActivity.this,ActivityPrincipal.class);
                        startActivity(intent);
                    }
                }
            });
        }

    }
    private void inflarDados(){

        if (verificar != null && verificar != ""){
            new FireDB().getUsuario(new FireDB.OnCompleteUsuario() {
                @Override
                public void getUsuario(Usuario usuario) {
                    setTitle("Minha conta");
                    mTextViewTitulo.setText("Atualizar meu cadastro");
                    mEditTextNomeCompleto.setText(usuario.getNome());
                    mEdiTextCPF.setText(usuario.getCPF());
                    mEditTextEnderecoMAC.setText(usuario.getBluetoothMAC());
                    mEdiTextEnderecoEmail.setText(usuario.getEmail());
                    mEdiTextEnderecoEmail.setFocusable(false);
                    mEdiTextSenha.setVisibility(View.GONE);
                    mButtonVerSenha.setVisibility(View.GONE);
                    mButtonConfirmarCriarConta.setText("Salvar alterações");
                }
            });

        }
    }
}
