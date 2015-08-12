package com.pundroid.appfinance;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pundroid.appfinance.gui.MenuExpandableList;

/**
 * Created by pumba30 on 11.08.2015.
 */
public class ContentFragment extends Fragment {

    private TextView textContent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);

        textContent = (TextView) view.findViewById(R.id.id_menu);
        textContent.setText(String.valueOf(getArguments().getLong(MenuExpandableList.ID_ITEM_MENU)));
        return view;
    }
}
