package com.rm39951.demointent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewUserActivity extends AppCompatActivity {

    @BindView(R.id.etLogin)
    EditText etLogin;

    @BindView(R.id.etSenha)
    EditText etSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.tvNewUser)
    public void createUserClick() {
        
    }
}
