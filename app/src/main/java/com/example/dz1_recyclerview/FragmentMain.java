package com.example.dz1_recyclerview;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class FragmentMain extends Fragment {
    private static final int INITIAL_ELEMENTS_COUNT = 100;
    private static final String ITEMLIST_KEY = "itemList";

    private ArrayList<Integer> itemList;
    private ActivityAccess activity;
    private RecyclerView numbersList;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList(ITEMLIST_KEY, itemList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Добавляем Layout к Main фрагменту
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // создаем список
        numbersList = view.findViewById(R.id.mainList);
        if (itemList == null)
        {
            itemList = new ArrayList<>();
            for (int i = 0; i < INITIAL_ELEMENTS_COUNT; i++) itemList.add(i+1);
        }
        if (savedInstanceState != null) itemList = savedInstanceState.getIntegerArrayList(ITEMLIST_KEY);
        if (numbersList != null) {
            int spanCount;
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                spanCount = getResources().getInteger(R.integer.horizontal_orientation);
            } else spanCount = getResources().getInteger(R.integer.vertical_orientation);
            numbersList.setAdapter(new mainListAdapter(itemList));
            numbersList.setLayoutManager(new GridLayoutManager(view.getContext(), spanCount, RecyclerView.VERTICAL, false));
        }

        // Добавляем кнопку к айтему списка
        Button btnAdd = (Button) view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemList.add(itemList.size()+1);
                numbersList.getAdapter().notifyItemInserted(itemList.size()-1);
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ActivityAccess) {
            activity = (ActivityAccess)context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    class mainListAdapter extends RecyclerView.Adapter<mainListViewHolder> {
        ArrayList<Integer> integers;

        mainListAdapter(ArrayList<Integer> integers) {
            this.integers = integers;
        }

        @NonNull
        @Override
        public mainListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list_item, parent, false);
            return new mainListViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull mainListViewHolder holder, int position) {
            if (integers.get(position) % 2 == 0) {
                holder.digit.setText(String.valueOf(integers.get(position)));
                holder.digit.setTextColor(Color.RED);
            } else {
                holder.digit.setText(String.valueOf(integers.get(position)));
                holder.digit.setTextColor(Color.BLUE);
            }
            holder.digit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView digit = v.findViewById(v.getId());
                    int number = Integer.parseInt((String)digit.getText());
                    ((ActivityAccess) activity).onItemClick(number);
                }
            });

        }

        @Override
        public int getItemCount() {
            return integers.size();
        }


    }

    static class mainListViewHolder extends RecyclerView.ViewHolder {
        TextView digit;

        mainListViewHolder(@NonNull View itemView) {
            super(itemView);
            digit = itemView.findViewById(R.id.digit);
        }
    }



}
