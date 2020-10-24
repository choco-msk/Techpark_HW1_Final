package com.example.dz1_recyclerview;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dz1_recyclerview.R;


public class FragmentClose extends Fragment {
    private static final String DIGIT_KEY = "digit";

    TextView digit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Добавляем layout к фрагменту
        return inflater.inflate(R.layout.fragment_close, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String text = "No";
        Bundle arguments = getArguments();
        if (arguments != null) {
            text = arguments.getString(DIGIT_KEY);
        }
        TextView digit = ((TextView)view.findViewById(R.id.digit));
        digit.setText(text);

        assert text != null;
        int num = Integer.parseInt(text);
        if (num % 2 == 0) digit.setTextColor(Color.RED);
        else digit.setTextColor(Color.BLUE);


    }

    static FragmentClose newInstance(int param) {
        FragmentClose fragment = new FragmentClose();
        Bundle bundle = new Bundle();
        bundle.putString(DIGIT_KEY, String.valueOf(param));
        fragment.setArguments(bundle);
        return fragment;
    }
}
