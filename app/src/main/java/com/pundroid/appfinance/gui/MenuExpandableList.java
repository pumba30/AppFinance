package com.pundroid.appfinance.gui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ExpandableListView;

import com.pundroid.appfinance.OperationFragment;
import com.pundroid.appfinance.R;
import com.pundroid.appfinance.adapters.MyExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pumba30 on 10.08.2015.
 */
public class MenuExpandableList {

    private static final String TAG = MenuExpandableList.class.getSimpleName();
    public static final String ID_ITEM_MENU = "id_item_menu";
    public static final String OPERATION_TYPE = "com.pundroid.appfinance.operationType";

    private Activity context;
    private DrawerLayout drawerLayout;

    private MyExpandableListAdapter adapter;
    private ExpandableListView listView;

    private List<String> listMenuGroup; //входят родительские пункты меню
    private HashMap<String, List<String>> mapChildItemMenu; //дочерние пункты меню


    public MenuExpandableList(final Activity context) {
        this.context = context;
        listView = (ExpandableListView) context.findViewById(R.id.left_menu);
        drawerLayout = (DrawerLayout) context.findViewById(R.id.drawer_layout);

        fillMenu();
        adapter = new MyExpandableListAdapter(context, listMenuGroup, mapChildItemMenu);
        listView.setAdapter(adapter);

        //нажатие на родительское меню
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        //развернуть родительский пункт
        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });

        //свернуть родительское меню
        listView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                Log.d(TAG, "onChildClick");
                OperationFragment fragment = new OperationFragment();

                Bundle args = new Bundle();
                args.putInt(OPERATION_TYPE, childPosition);
                fragment.setArguments(args);

                android.app.FragmentManager fragmentManager = context.getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

                drawerLayout.closeDrawer(Gravity.LEFT);
                return true;
            }
        });


    }


    private void fillMenu() {
        listMenuGroup = new ArrayList<>();
        mapChildItemMenu = new HashMap<>();

        listMenuGroup.add(context.getResources().getString(R.string.menu_header_1));
        listMenuGroup.add(context.getResources().getString(R.string.menu_header_2));
        listMenuGroup.add(context.getResources().getString(R.string.menu_header_3));

        //создаем список пунктов в родительском меню
        List<String> menu1 = new ArrayList<>();
        for (String item : context.getResources().getStringArray(R.array.menu_one)) {
            menu1.add(item);
        }
        List<String> menu2 = new ArrayList<>();
        for (String item : context.getResources().getStringArray(R.array.menu_two)) {
            menu2.add(item);
        }
        List<String> menu3 = new ArrayList<>();
        for (String item : context.getResources().getStringArray(R.array.menu_three)) {
            menu3.add(item);
        }

        //добавляем в каждое родительское меню список пунктов меню
        mapChildItemMenu.put(listMenuGroup.get(0), menu1);
        mapChildItemMenu.put(listMenuGroup.get(1), menu2);
        mapChildItemMenu.put(listMenuGroup.get(2), menu3);

    }


}
