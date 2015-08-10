package com.pundroid.appfinance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by pumba30 on 10.08.2015.
 */
public class ContentFragment extends Fragment {
    private long id = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        TextView idMenu = (TextView) view.findViewById(R.id.id_menu);
        idMenu.setText(String.valueOf(getArguments().getLong(MainActivity.ID_MENU_ITEM)));

        return view;
    }
}
