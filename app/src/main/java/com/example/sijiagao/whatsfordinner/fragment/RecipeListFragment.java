package com.example.sijiagao.whatsfordinner.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sijiagao.whatsfordinner.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeListFragment extends Fragment {


    public RecipeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view =inflater.inflate(R.layout.fragment_recipe_list, container, false);


        String[] sampleList = {"apple", "ham", "BBQ","Coke"};
        ListView lv;
        lv =(ListView) view.findViewById(R.id.recipe_listview);
        lv.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.recipe_listview_detail, sampleList));

        return view;
    }

}
