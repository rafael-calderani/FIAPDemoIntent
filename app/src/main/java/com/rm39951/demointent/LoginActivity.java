package com.rm39951.demointent;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.etLogin)
    EditText etLogin;

    @BindView(R.id.etSenha)
    EditText etSenha;

    static final int REQUEST_NEW_USER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        requestSmsPermission();
    }

    @OnClick(R.id.tvNewUser)
    public void createUserClick() {
        Intent i = new Intent(this, NewUserActivity.class);
        startActivityForResult(i, REQUEST_NEW_USER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case REQUEST_NEW_USER:
                etLogin.setText(data.getStringExtra("UserName"));
                etSenha.setText(data.getStringExtra("Senha"));
                sendBroadcast(data);
                break;
            default:
                Toast.makeText(this, "Um erro ocorreu ao criar o usuário, favor contatar o José!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void requestSmsPermission() {
        String permission = Manifest.permission.RECEIVE_SMS;
        int grant = ContextCompat.checkSelfPermission(this, permission);
        if ( grant != PackageManager.PERMISSION_GRANTED) {
            String[] permission_list = new String[1];
            permission_list[0] = permission;
            ActivityCompat.requestPermissions(this, permission_list, 1);
        }
    }
}