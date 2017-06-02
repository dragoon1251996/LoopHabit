package com.t3h.whiyew.loophabit;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by hoadu on 09/05/2017.
 */

public class MyAdapter extends BaseAdapter implements View.OnClickListener {
    private ArrayList<NameHabit> arrData;
    private Context context;
    private LayoutInflater inflater;
    Data data;
    public MyAdapter(Context context, ArrayList<NameHabit> arrData) {
        this.context = context;
        this.arrData = arrData;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrData.size();
    }

    @Override
    public Object getItem(int i) {
        return arrData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        NameHabit nameHabit=arrData.get(i);
        if(view==null){
            viewHolder=new ViewHolder();
            view=inflater.inflate(R.layout.item_list,viewGroup,false);
            viewHolder.textView= (TextView) view.findViewById(R.id.txtContent);
            viewHolder.img1= (ImageView) view.findViewById(R.id.img1);
            viewHolder.img2= (ImageView) view.findViewById(R.id.img2);
            viewHolder.img3= (ImageView) view.findViewById(R.id.img3);
            viewHolder.img4= (ImageView) view.findViewById(R.id.img4);
            view.setTag(viewHolder);
        }
        else {
         viewHolder= (ViewHolder) view.getTag();

        }
        data = new Data(context, null, null, 2);
        viewHolder.textView.setText(nameHabit.getTenThuocTinh().toString());
        viewHolder.textView.setTextColor(Color.parseColor(data.getAllContacts().get(i).getColor()));
//        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,data.getAllContacts().get(0).getColor()+"",Toast.LENGTH_LONG).show();
//            }
//        });
        viewHolder.img1.setColorFilter(Color.parseColor(data.getAllContacts().get(i).getColor()));
        viewHolder.img2.setColorFilter(Color.parseColor(data.getAllContacts().get(i).getColor()));
        viewHolder.img3.setColorFilter(Color.parseColor(data.getAllContacts().get(i).getColor()));
        viewHolder.img4.setColorFilter(Color.parseColor(data.getAllContacts().get(i).getColor()));

        viewHolder.img1.setOnClickListener(this);
        viewHolder.img2.setOnClickListener(this);
        viewHolder.img3.setOnClickListener(this);
        viewHolder.img4.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img1:
                Toast.makeText(context,"IMG1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.img2:
                Toast.makeText(context,"IMG2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.img3:
                Toast.makeText(context,"IMG3",Toast.LENGTH_SHORT).show();
                break;
            case R.id.img4:
                Toast.makeText(context,"IMG4",Toast.LENGTH_SHORT).show();
                break;

        }
    }

    class ViewHolder{
        TextView textView;
        ImageView img1,img2,img3,img4;
    }
}
