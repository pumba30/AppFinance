package com.pundroid.appfinance;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pundroid.appfinance.enums.OperationType;
import com.pundroid.appfinance.gui.MenuExpandableList;
import com.pundroid.appfinance.objects.AppGlobalContext;
import com.pundroid.appfinance.objects.Operation;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by pumba30 on 11.08.2015.
 */
public class OperationFragment extends Fragment {
    public static final String TAG = OperationFragment.class.getSimpleName();
    private TextView textContent;
    private OperationType operationType;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_operations, container, false);

        textContent = (TextView) view.findViewById(R.id.id_menu);

        switch (getArguments().getInt(MenuExpandableList.OPERATION_TYPE)) {
            case 0:
                this.operationType = OperationType.INCOME;
                break;
            case 1:
                this.operationType = OperationType.SPENDING;
                break;
        }

        Log.d(TAG, "operation type "
                + String.valueOf(getArguments().getInt(MenuExpandableList.OPERATION_TYPE)));

        try {
            fillTextContext();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return view;
    }

    private void fillTextContext() throws SQLException {
        List<Operation> operationList = AppGlobalContext
                .getInstanceDbAdapter().getOperations(operationType);

        for (Operation operation : operationList) {
            textContent.setText(textContent.getText() + operation.getAmount().toString() + "\n");
        }
    }
}
