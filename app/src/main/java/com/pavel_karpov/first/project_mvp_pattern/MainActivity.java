package com.pavel_karpov.first.project_mvp_pattern;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements PresenterListener.View {
    public  EditText editFirstName, editLastName, editPosition;
    private Button btnAddNote, btnShowNote;
    TextView textShow;
    private Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editFirstName = findViewById(R.id.edit_firstName);
        editLastName = findViewById(R.id.edit_lastName);
        editPosition = findViewById(R.id.edit_position);
        btnAddNote = findViewById(R.id.btn_add);
        btnShowNote = findViewById(R.id.btn_show);
        textShow = findViewById(R.id.show_data);
        mPresenter = new Presenter(this,getApplicationContext());
        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onButtonAddNoteClick(editFirstName,editLastName,editPosition);
            }
        });
        btnShowNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onButtonShowNoteClick();
            }
        });
    }

    @Override
    public void showText(String message) {
        textShow.setText(message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
