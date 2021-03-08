package com.netsec.taxiplus;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DialogoFecha  extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Calendar calendario = Calendar.getInstance();
        int year = calendario.get(Calendar.YEAR);
        int month = calendario.get(Calendar.MONTH);
        int day =  calendario.get(Calendar.DATE);

        return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener)getActivity(),year,month,day);
    }
}
