package com.pundroid.appfinance.adapters;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pundroid.appfinance.R;
import com.pundroid.appfinance.databases.DbAdapter;
import com.pundroid.appfinance.enums.OperationType;
import com.pundroid.appfinance.objects.AppGlobalContext;
import com.pundroid.appfinance.objects.ImageUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by pumba30 on 13.08.2015.
 */
public class OperationsAdapter extends CursorAdapter {

    private AppGlobalContext globalContext;

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
    private SimpleDateFormat timeFormatter = new SimpleDateFormat("kk:mm");

    private Calendar calendar = Calendar.getInstance();
    private LayoutInflater inflater;

    public OperationsAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
        this.globalContext = (AppGlobalContext) context.getApplicationContext();
        inflater = LayoutInflater.from(context);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = inflater.inflate(R.layout.listview_row_operation, parent, false);

        ViewHolder holder = new ViewHolder();
        holder.source = (TextView) view.findViewById(R.id.txt_oper_source);
        holder.date = (TextView) view.findViewById(R.id.txt_oper_date);
        holder.image = (ImageView) view.findViewById(R.id.img_source);
        holder.time = (TextView) view.findViewById(R.id.txt_oper_time);
        holder.amount = (TextView) view.findViewById(R.id.txt_oper_amount);
        holder.type = (TextView) view.findViewById(R.id.txt_oper_type);
        holder.currency = (TextView) view.findViewById(R.id.txt_oper_currency);

        view.setTag(holder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();

        holder.amount.setText(cursor.getString(cursor.getColumnIndex(DbAdapter.FIELD_AMOUNT)));
        holder.source.setText(cursor.getString(cursor.getColumnIndex(DbAdapter.FIELD_SOURCE)));
        holder.type.setText(" - (" + cursor.getString(cursor.getColumnIndex(DbAdapter.FIELD_TYPE)) + ")");
        holder.currency.setText(cursor.getString(cursor.getColumnIndex(DbAdapter.FIELD_CURRENCY)));

        String imagePath = null;
        try {
            imagePath = globalContext
                    .getIconsFolder() + "/" + cursor
                    .getString(cursor.getColumnIndex(DbAdapter.FIELD_SOURCE_ID)) + ".png";
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            holder.image.setImageBitmap(ImageUtils.getSizedBitmap(imagePath, AppGlobalContext.IMAGE_WIDTH, AppGlobalContext.IMAGE_HEIGHT));
        } catch (Exception e) {
            e.printStackTrace();
        }


        long dateTime = cursor.getLong(cursor.getColumnIndex(DbAdapter.FIELD_OPERATION_DATETIME));
        calendar.setTimeInMillis(dateTime);

        holder.date.setText(dateFormatter.format(calendar.getTime()) + ", ");
        holder.time.setText(timeFormatter.format(calendar.getTime()));

        if (cursor.getInt(cursor.getColumnIndex(DbAdapter.FIELD_TYPE_ID)) == Integer.valueOf(OperationType.INCOME.getId())) {
            holder.type.setTextColor(Color.GREEN);
            //holder.type.setText(R.string.income);
        } else {
            holder.type.setTextColor(Color.RED);
            //holder.type.setText(R.string.spending);
        }
    }

    static class ViewHolder {
        public TextView date;
        public ImageView image;
        public TextView time;
        public TextView amount;
        public TextView source;
        public TextView type;
        public TextView currency;
    }
}
