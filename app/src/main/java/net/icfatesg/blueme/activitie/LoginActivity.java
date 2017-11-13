package net.icfatesg.blueme.activitie;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.icfatesg.blueme.R;
import net.icfatesg.blueme.model.Usuario;
import net.icfatesg.blueme.service.Autenticacao;
import net.icfatesg.blueme.service.BluetoothService;
import net.icfatesg.blueme.service.Utilidade;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.editTextEmailLogin)
    EditText mEditTextEmailLogin;
    @BindView(R.id.editTextSenhaLogin) EditText mEditTextSenhaLogin;
    @BindView(R.id.buttonEntrar)
    Button mButtonEntrar;
    @BindView(R.id.textViewRecuperarSenha)
    TextView mTextViewRecuperarSenha;
    @BindView(R.id.textViewCriarConta)
    TextView  mTextViewCriarConta;
    private Intent intent;
    private AlertDialog.Builder builderDialog;
    private BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService(new Intent(this, BluetoothService.class));
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        builderDialog = new AlertDialog.Builder(LoginActivity.this);
        setTitle(R.string.title_activity_login);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mBluetoothAdapter.enable();
    }

    @OnClick(R.id.buttonEntrar)
    public void entrar(View view) {
        Usuario usuario = new Usuario();
        usuario.setEmail(mEditTextEmailLogin.getText().toString());
        usuario.setSenha(mEditTextSenhaLogin.getText().toString());
        if (!usuario.getEmail().equals("") || !usuario.getSenha().equals("") && new Utilidade().isValidEmail(usuario.getEmail())){
            new Autenticacao(usuario).entrar(new Autenticacao.MensagemAuth() {
                @Override
                public void mensagem(String mensagem) {
                    builderDialog.setMessage(mensagem);
                    builderDialog.show();
                }

                @Override
                public void sucesso(boolean sucesso) {
                    if (sucesso){
                        intent = new Intent(LoginActivity.this,ActivityPrincipal.class);
                        startActivity(intent);
                    }
                }
            });
        }else{
            builderDialog.setMessage("Algum dos campos ficou em branco");
            builderDialog.show();
        }

    }
    @OnClick(R.id.textViewRecuperarSenha)
    public void recuperarSenha(View view){
        intent = new Intent(this, RecuperarSenhaActivity.class);
        startActivity(intent);

    }
    @OnClick(R.id.textViewCriarConta)
    public void criarConta(View view){
        intent  = new Intent(this, MinhaContaActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (new Autenticacao(null).usuarioLogado()){
            intent = new Intent(this,ActivityPrincipal.class);
            startActivity(intent);
        }
        startService(new Intent(this, BluetoothService.class));
    }

    @Override
    protected void onPause() {
        super.onPause();
        startService(new Intent(this, BluetoothService.class));
    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }
}
