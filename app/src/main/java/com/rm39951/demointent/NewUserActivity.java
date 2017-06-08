package com.rm39951.demointent;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewUserActivity extends AppCompatActivity {

    @BindView(R.id.etUserName)
    EditText etUserName;

    @BindView(R.id.etNome)
    EditText etNome;

    @BindView(R.id.etSenha)
    EditText etSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        ButterKnife.bind(this);

        if (savedInstanceState != null) {}
    }

    @OnClick(R.id.btnCreateUser)
    public void createUser() {
        Intent i = new Intent();
        i.putExtra("UserName", etUserName.getText().toString());
        i.putExtra("Nome", etNome.getText().toString());
        i.putExtra("Senha", etSenha.getText().toString());

        //setResult(RESULT_CANCELED, i);
        setResult(RESULT_OK, i);
        finish();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);

        super.onBackPressed();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
