package com.example.imagesgallery.ui;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.imagesgallery.R;

/**
 * Created by hyemi on 2017. 4. 24..
 */

public class ProgressBarDialog extends Dialog {

    public ProgressBarDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.layout_progressbar, null);
        setContentView(layout);
        setCancelable(false);
    }
}
