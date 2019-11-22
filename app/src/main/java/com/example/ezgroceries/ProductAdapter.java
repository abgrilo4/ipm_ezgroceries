package com.example.ezgroceries;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
    ArrayList<Object> list;
    public static final int PRODUCT = 0;
    public static final int HEADER = 1;
    private LayoutInflater inflater;

    public ProductAdapter(Context context, ArrayList<Object> list){
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public Object removeItem(int position){
       return list.remove(position);
    }

    @Override
    public int getItemViewType(int position) {
        if(list.get(position) instanceof Product)
            return PRODUCT;
        else
            return HEADER;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            switch (getItemViewType(position)){
                case PRODUCT:
                    convertView = inflater.inflate(R.layout.rowlayout, null);

                    break;
                case HEADER:
                    convertView = inflater.inflate(R.layout.header,null);
                    break;
            }
        }
        switch (getItemViewType(position)){
            case PRODUCT:
                TextView name = (TextView) convertView.findViewById(R.id.rowName);
                TextView number = (TextView) convertView.findViewById(R.id.rowNumber);
                TextView price = (TextView) convertView.findViewById(R.id.rowPrice);
                name.setText(((Product)list.get(position)).getName());
                number.setText(String.valueOf(((Product)list.get(position)).getNumber()));
                price.setText(Double.toString(((Product)list.get(position)).getPrice()) + "€");
                break;
            case HEADER:
                TextView nome = (TextView) convertView.findViewById(R.id.headerName);
                TextView quantidade = (TextView) convertView.findViewById(R.id.headerNumber);
                TextView preco = (TextView) convertView.findViewById(R.id.headerPrice);
                nome.setText("Produto");
                quantidade.setText("Quantidade");
                preco.setText("Preço");
                break;
        }

        if(getItemViewType(position) == PRODUCT){
            CheckBox checkBox = convertView.findViewById(R.id.checkBox2);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    ((Product)list.get(position)).setCheckbox();
                }
            });
        }

        return convertView;
    }

}
