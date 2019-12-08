package com.pavel_karpov.first.project_mvp_pattern;

import android.content.Context;
import android.widget.EditText;

public class Presenter implements PresenterListener.Presenter {
    private PresenterListener.View mView;
    private PresenterListener.Model model;
    private String message;
    public Presenter(PresenterListener.View view, Context context){
        mView = view;
        model = new Model(context);
    }
    @Override
    public void onButtonAddNoteClick(EditText first, EditText last, EditText position) {
        model.loadMessage(first.getText().toString(),last.getText().toString(),position.getText().toString());
    }

    @Override
    public void onButtonShowNoteClick() {
     message = model.readMessage();
     mView.showText(message);
    }

    @Override
    public void onDestroy() {

    }
}
