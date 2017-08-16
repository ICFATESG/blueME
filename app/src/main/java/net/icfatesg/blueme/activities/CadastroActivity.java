package net.icfatesg.blueme.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import net.icfatesg.blueme.R;
import net.icfatesg.blueme.model.Usuario;
import net.icfatesg.blueme.services.BluetoothService;
import net.icfatesg.blueme.services.FireBase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CadastroActivity extends AppCompatActivity {
    @BindView(R.id.editTextNomeCompleto)
    EditText nomeCompleto;
    @BindView(R.id.editTextEnderecoDeEmail)
    EditText enderecoDeEmail;
    @BindView(R.id.editTextSenha)
    EditText senha;
    @BindView(R.id.buttonConfirmar)
    Button confirmar;
    @BindView(R.id.buttonVerSenha)
    ImageButton versenha;
    @BindView(R.id.editTextEnderecoMAC)
    EditText enderecomac;
    @BindView(R.id.editTextCPF)
    EditText cpf;
    private FirebaseAuth mAuth;
    private boolean olhoAberto = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        enderecomac.setText(new BluetoothService().getBluetoothMacAddress(getApplicationContext()));

    }

    @OnClick(R.id.buttonVerSenha) public void mostrarSenha(View view){
        if(olhoAberto){
            versenha.setImageResource(R.drawable.eyeclose);
            senha.setTransformationMethod(new PasswordTransformationMethod());
            olhoAberto = false;
        }else{
            versenha.setImageResource(R.drawable.eyeopen);
            senha.setTransformationMethod(null);
            olhoAberto = true;
        }

    }

    @OnClick(R.id.buttonConfirmar) public void confirmarCadastro(View view){
        Usuario u = new Usuario();
        u.setEmail(enderecoDeEmail.getText().toString());
        u.setSenha(senha.getText().toString());
        u.setNome(nomeCompleto.getText().toString());
        u.setCPF(cpf.getText().toString());
        u.setBluetoothMAC(enderecomac.getText().toString());
        mAuth.createUserWithEmailAndPassword(u.getEmail(),u.getSenha());
            mAuth.signInWithEmailAndPassword(u.getEmail(),u.getSenha());
            FirebaseUser user = mAuth.getCurrentUser();
            new FireBase().updateUsuario(u);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Usuário criado com sucesso!")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(CadastroActivity.this,Login.class);
                            startActivity(intent);
                            finish();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        };


}
