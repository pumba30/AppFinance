package com.pundroid.appfinance;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.pundroid.appfinance.adapters.OperationsAdapter;
import com.pundroid.appfinance.enums.OperationType;
import com.pundroid.appfinance.gui.MenuExpandableList;
import com.pundroid.appfinance.objects.AppGlobalContext;

import java.sql.SQLException;

/**
 * Created by pumba30 on 11.08.2015.
 */
public class OperationFragment extends android.app.ListFragment {
    public static final String TAG = OperationFragment.class.getSimpleName();

    private OperationType operationType;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int type = getArguments().getInt(MenuExpandableList.OPERATION_TYPE);

        switch (type) {
            case 0:
                this.operationType = OperationType.ALL;
                Log.d(TAG, "operation type " + String.valueOf(type));
                break;
            case 1:
                this.operationType = OperationType.INCOME;
                Log.d(TAG, "operation type " + String.valueOf(type));
                break;
            case 2:
                this.operationType = OperationType.SPENDING;
                Log.d(TAG, "operation type " + String.valueOf(type));
                break;

        }


        OperationsAdapter operationsAdapter = null;
        try {
            operationsAdapter = new OperationsAdapter(getActivity(),
                    AppGlobalContext.getInstanceDbAdapter().getOperations(operationType), false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        setListAdapter(operationsAdapter);
        AppGlobalContext.getInstanceDbAdapter().closeDatabase();

    }


}
