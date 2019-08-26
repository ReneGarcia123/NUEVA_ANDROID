package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText e_mail;
    EditText pass;
    Button btn_entrar;
    TextView estado;

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e_mail=(EditText)findViewById(R.id.txtemail);
        pass=(EditText)findViewById(R.id.txtpass);
        btn_entrar=(Button)findViewById(R.id.btningresar);
        estado=(TextView)findViewById(R.id.textView2);

        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Registrar_Usuario();
            }
        });

        mAuth=FirebaseAuth.getInstance();
        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Registrar_Usuario();
            }
        });

        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser usuario=firebaseAuth.getCurrentUser();
                if (usuario!=null){
                    String e_mail=usuario.getEmail();
                    estado.setText("Ingresando como:"+e_mail);
                }
                else   {
                    estado.setText("Usuario no loquegado");
                }
            }
        };
    }

    protected void Registrar_Usuario(){
        String E_MAIL=e_mail.getText().toString().trim();
        String PASSWORD=pass.getText().toString().trim();

        if (TextUtils.isEmpty(E_MAIL)){
            Toast.makeText(this,"Ingrese un e-mail",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(PASSWORD)){
            Toast.makeText(this,"Ingrese su contraseña",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public  void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public  void onStop(){
        super.onStop();
        if (mAuthListener!=null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
