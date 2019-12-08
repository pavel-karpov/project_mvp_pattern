package com.pavel_karpov.first.project_mvp_pattern;

import android.widget.EditText;

public interface PresenterListener {
     interface View{
        void showText(String message);
    }
    interface Presenter{
         void onButtonAddNoteClick(EditText first, EditText last, EditText position);
         void onButtonShowNoteClick();
         void onDestroy();
    }
    interface Model{
         void loadMessage(String first, String last, String position);
         String readMessage();
    }
}
