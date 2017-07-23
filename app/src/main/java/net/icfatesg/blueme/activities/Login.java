package net.icfatesg.blueme.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import net.icfatesg.blueme.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login extends AppCompatActivity {

    @BindView(R.id.buttonConfirmLogin) Button buttonLogin;
    @BindView(R.id.editTextLoginEmail) EditText editTextLoginEmail;
    @BindView(R.id.editTextLoginPassword) EditText editTextLoginPassword;
    private FirebaseAuth mAuth;
    static final String TAG = "LOGIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();

    }

    @OnClick(R.id.buttonConfirmLogin) public void confirmLogin(View view){

        Toast.makeText(getApplicationContext(), "ohoho"+editTextLoginPassword.getText(),Toast.LENGTH_SHORT);
        mAuth.signInWithEmailAndPassword(editTextLoginEmail.getText().toString(), editTextLoginPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            loadMainActivity();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Email ou senha incorreto", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    public void loadMainActivity(){
        Intent myIntent = new Intent(this, MainActivity.class);
        this.startActivity(myIntent);
    }

    @OnClick(R.id.textViewCriarConta) public void criarConta(View view){
        Intent intent = new Intent(this,CadastroActivity.class);
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            loadMainActivity();
        }

    }

}
