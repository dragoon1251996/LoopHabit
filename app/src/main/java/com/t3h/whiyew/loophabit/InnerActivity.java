package com.t3h.whiyew.loophabit;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.db.chart.Tools;
import com.db.chart.model.BarSet;
import com.db.chart.model.LineSet;
import com.db.chart.renderer.AxisRenderer;
import com.db.chart.view.HorizontalStackBarChartView;
import com.db.chart.view.LineChartView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by hoadu on 24/05/2017.
 */

public class InnerActivity extends AppCompatActivity implements OnChartValueSelectedListener, TimePickerDialog.OnTimeSetListener {
    private LineChart mChart;
    private LineChartView mChart2;
    int a;
    private final String[] mLabels = {"Jan", "Fev", "Mar", "Apr", "Jun", "May", "Jul", "Aug", "Sep"};

    private final float[][] mValues = {{3.5f, 4.7f, 4.3f, 8f, 6.5f, 9.9f, 7f, 8.3f, 7.0f},
            {4.5f, 2.5f, 2.5f, 9f, 4.5f, 9.5f, 5f, 8.3f, 1.8f}};

    private  HorizontalStackBarChartView mChart3;

    private final String[] mLabels3 = {"0-20", "20-40", "40-60", "60-80", "80-100", "100+"};

    private final float[][] mValues3 =
            {{1.8f, 2f, 2.4f, 2.2f, 3.3f, 3.45f}, {-1.8f, -2.1f, -2.55f, -2.40f, -3.40f, -3.5f},
                    {1.8f, 2.1f, 2.55f, 2.40f, 3.40f, 3.5f},
                    {-1.8f, -2f, -2.4f, -2.2f, -3.3f, -3.45f}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
          setContentView(R.layout.inner_main);
        init();



    }

    private void init() {
        String ten=getIntent().getStringExtra(MainActivity.KEY_NAME);
//        Toolbar toolbar= (Toolbar) findViewById(R.id.innerToolbar);
        //     setSupportActionBar(toolbar);
        getSupportActionBar().show();
        getSupportActionBar().setTitle(ten);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        mChart = (LineChart) findViewById(R.id.chart);
        lineChart();

        mChart2 = (LineChartView) findViewById(R.id.chart1);
        lineCharView();

        mChart3= (HorizontalStackBarChartView) findViewById(R.id.chart2);
        stackBarChart();
    }

    private void stackBarChart() {
        BarSet barSet = new BarSet(mLabels3, mValues3[0]);
        barSet.setColor(Color.parseColor("#90ee7e"));
        mChart3.addData(barSet);

        barSet = new BarSet(mLabels3, mValues3[1]);
        barSet.setColor(Color.parseColor("#2b908f"));
        mChart3.addData(barSet);

        Paint gridPaint = new Paint();
        gridPaint.setColor(Color.parseColor("#e7e7e7"));
        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setAntiAlias(true);
        gridPaint.setStrokeWidth(Tools.fromDpToPx(.7f));

        mChart3.setBarSpacing(Tools.fromDpToPx(10));

        mChart3.setBorderSpacing(0)
                .setStep(1)
                .setGrid(0, 10, gridPaint)
                .setXAxis(false)
                .setYAxis(false)
                .setLabelsFormat(new DecimalFormat("##'M'"))
                .show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.itemmenu2,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()){
           case R.id.edit:


               final Dialog dialog=new Dialog(this,android.R.style.Theme_DeviceDefault_Dialog_NoActionBar_MinWidth);
               LayoutInflater inflater = LayoutInflater.from(this);
               View view=inflater.inflate(R.layout.dialog_habit,null);
               final EditText edtName= (EditText) view.findViewById(R.id.tvName);
               ImageButton imgColor= (ImageButton) view.findViewById(R.id.imgColor);
               EditText edtQue= (EditText) view.findViewById(R.id.tvQuestion);
               Button btnDis= (Button) view.findViewById(R.id.btnDis);
               Button btnSave= (Button) view.findViewById(R.id.btnSave);
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                NameHabit nameHabit=new NameHabit(edtName.getText().toString());
//                data.addJapan(nameHabit);
//                myAdapter=new MyAdapter(getBaseContext(),  data.getAllContacts());
//                listView.setAdapter(myAdapter);
//                myAdapter.notifyDataSetChanged();
//                dialog.dismiss();
//            }
//        });


               final AppCompatSpinner compatSpinner= (AppCompatSpinner) view.findViewById(R.id.spinner);
               final LinearLayout linearLayout= (LinearLayout) view.findViewById(R.id.customRepeat);
               EditText edtTime= (EditText) view.findViewById(R.id.numerator);
               EditText edtDay= (EditText) view.findViewById(R.id.denominator);
               Button btnOK= (Button) view.findViewById(R.id.btnOK);
               final TextView txtDay= (TextView) view.findViewById(R.id.txtDay);
               final TextView edtTimePicker= (TextView) view.findViewById(R.id.edtTime);
               final LinearLayout linearLayout1= (LinearLayout) view.findViewById(R.id.layout_panel);
               final LinearLayout linearLayout2= (LinearLayout) view.findViewById(R.id.layout_panel2);

               linearLayout2.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       final CharSequence[] items = {" Thứ bảy "," Chủ nhật "," Thứ hai "," Thứ ba "," Thứ tư "," Thứ năm "," Thứ sáu "};
                       // arraylist to keep the selected items
                       final ArrayList seletedItems=new ArrayList();

//                        for (int x = 0; x < seletedItems.size(); x++) {
//                            int a= (int) seletedItems.get(x);
//                            items[a].set (x, true);
//                        }
//                        for (int i = 0; i < seletedItems.size(); i++) {
//                            HashMap<String, String> asset = (HashMap<String, String>) seletedItems.get(i);
//                            asset.
//                            if (asset.get(KEY_CODE).equals(scanAsset)){
//                                asset.put(KEY_CHECKED, "Checked");
////                                Log.i("Asset code is ", scanAsset);
//                            }
//                        }
//                        myAdapter.notifyDataSetChanged();

//                        AlertDialog dialog1 = (AlertDialog) dialog;
//                        ListView v = dialog1.getListView();
//                        int i = 0;
//                        while(i < items.length) {
//                            v.setItemChecked(i, true);
//                            i++;
//                        }

                       AlertDialog dialog = new AlertDialog.Builder(InnerActivity.this)

                               .setTitle("Select days")
                               .setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {

                                   @Override
                                   public void onClick(DialogInterface dialog, int indexSelected, boolean isChecked) {
                                       if (isChecked) {
                                           // If the user checked the item, add it to the selected items
                                           seletedItems.add(indexSelected);
                                       } else if (seletedItems.contains(indexSelected)) {
                                           // Else, if the item is already in the array, remove it
                                           seletedItems.remove(Integer.valueOf(indexSelected));
                                       }
                                   }
                               }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialog, int id) {
                                       if(seletedItems.size()==items.length){
                                           txtDay.setText(" Any day of the week ");
                                       }
                                       else{
                                           String b="";
                                           for(int i=0;i<seletedItems.size();i++){
                                               int index= (int) seletedItems.get(i);
                                               b=b+items[index].toString()+",";
//                                            setTitle(seletedItems.get(i).toString()+",");
                                           }
                                           String c=b.substring(0,b.length()-1);
                                           txtDay.setText(c);
                                       }

                                       //  Your code when user clicked on OK
                                       //  You can write the code  to save the selected item here
                                   }
                               }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialog, int id) {

                                       //  Your code when user clicked on Cancel
                                   }
                               }).create();
                       dialog.show();
                   }
               });
               linearLayout1.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       Calendar now = Calendar.getInstance();
                       TimePickerDialog timepickerdialog = TimePickerDialog.newInstance(InnerActivity.this,
                               now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE),true);
                       timepickerdialog.setThemeDark(false); //Dark Theme?
                       timepickerdialog.vibrate(false); //vibrate on choosing time?
                       timepickerdialog.dismissOnPause(false); //dismiss the dialog onPause() called?
//                        timepickerdialog.enableSeconds(true); //show seconds?

                       //Handling cancel event
                       timepickerdialog.setOnTimeSetListener(new TimePickerDialog.OnTimeSetListener() {
                           @Override
                           public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                               edtTimePicker.setText(" "+hourOfDay+" : "+minute+" ");
                               if(edtTimePicker.getText().toString()!="Off"){
                                   linearLayout2.setVisibility(View.VISIBLE);
                               }





                           }
                       });
                       timepickerdialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                           @Override
                           public void onCancel(DialogInterface dialogInterface) {
                               Toast.makeText(InnerActivity.this, "Cancel choosing time", Toast.LENGTH_SHORT).show();
                           }
                       });
                       timepickerdialog.show(getFragmentManager(), "Timepickerdialog"); //show time picker dialog
                   }
               });


               imgColor.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       final Dialog dialogColor=new Dialog(InnerActivity.this,android.R.style.Theme_DeviceDefault_Dialog_NoActionBar_MinWidth);
                       LayoutInflater inflater = LayoutInflater.from(InnerActivity.this);
                       View viewColor=inflater.inflate(R.layout.color_selection,null);


                       ImageView img1= (ImageView) viewColor.findViewById(R.id.mau1);
                       ImageView img2= (ImageView) viewColor.findViewById(R.id.mau2);
                       ImageView img3= (ImageView) viewColor.findViewById(R.id.mau3);
                       ImageView img4= (ImageView) viewColor.findViewById(R.id.mau4);
                       ImageView img5= (ImageView) viewColor.findViewById(R.id.mau5);
                       ImageView img6= (ImageView) viewColor.findViewById(R.id.mau6);
                       ImageView img7= (ImageView) viewColor.findViewById(R.id.mau7);
                       ImageView img8= (ImageView) viewColor.findViewById(R.id.mau8);
                       ImageView img9= (ImageView) viewColor.findViewById(R.id.mau9);
                       ImageView img10= (ImageView) viewColor.findViewById(R.id.mau10);
                       ImageView img11= (ImageView) viewColor.findViewById(R.id.mau11);
                       ImageView img12= (ImageView) viewColor.findViewById(R.id.mau12);
                       ImageView img13= (ImageView) viewColor.findViewById(R.id.mau13);
                       switch (a){
                           case 1:
                               img1.setImageResource(R.drawable.check4);
                               break;
                           case 2:
                               img2.setImageResource(R.drawable.check4);
                               break;
                           case 3:
                               img3.setImageResource(R.drawable.check4);
                               break;
                           case 4:
                               img4.setImageResource(R.drawable.check4);
                               break;
                           case 5:
                               img5.setImageResource(R.drawable.check4);
                               break;
                           case 6:
                               img6.setImageResource(R.drawable.check4);
                               break;
                           case 7:
                               img7.setImageResource(R.drawable.check4);
                               break;
                           case 8:
                               img8.setImageResource(R.drawable.check4);
                               break;
                           case 9:
                               img9.setImageResource(R.drawable.check4);
                               break;
                           case 10:
                               img10.setImageResource(R.drawable.check4);
                               break;
                           case 11:
                               img11.setImageResource(R.drawable.check4);
                               break;
                           case 12:
                               img12.setImageResource(R.drawable.check4);
                               break;
                           case 13:
                               img13.setImageResource(R.drawable.check4);
                               break;



                       }

//                        arrImageColor.get(a).setImageResource(R.drawable.check4);
                       img1.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               a=1;
                               edtName.setTextColor(ContextCompat.getColor(InnerActivity.this, R.color.color1));
//                                img1.setImageResource(R.drawable.check4);
                               dialogColor.dismiss();


                           }
                       });
                       img2.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               a=2;
                               edtName.setTextColor(ContextCompat.getColor(InnerActivity.this, R.color.color2));
//                                img2.setImageResource(R.drawable.check4);
                               dialogColor.dismiss();

                           }
                       });
                       img3.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               edtName.setTextColor(ContextCompat.getColor(InnerActivity.this, R.color.color3));

                               a=3;
                               dialogColor.dismiss();
                           }
                       });
                       img4.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               edtName.setTextColor(ContextCompat.getColor(InnerActivity.this, R.color.color4));
                               a=4;
                               dialogColor.dismiss();
                           }
                       });
                       img5.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               edtName.setTextColor(ContextCompat.getColor(InnerActivity.this, R.color.color5));
                               a=5;
                               dialogColor.dismiss();
                           }
                       });
                       img6.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               edtName.setTextColor(ContextCompat.getColor(InnerActivity.this, R.color.color6));
                               a=6;
                               dialogColor.dismiss();
                           }
                       });
                       img7.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               edtName.setTextColor(ContextCompat.getColor(InnerActivity.this, R.color.color7));
                               a=7;
                               dialogColor.dismiss();
                           }
                       });
                       img8.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               edtName.setTextColor(ContextCompat.getColor(InnerActivity.this, R.color.color8));
                               a=8;
                               dialogColor.dismiss();
                           }
                       });
                       img9.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               edtName.setTextColor(ContextCompat.getColor(InnerActivity.this, R.color.color9));
                               a=9;
                               dialogColor.dismiss();
                           }
                       });
                       img10.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               edtName.setTextColor(ContextCompat.getColor(InnerActivity.this, R.color.color10));
                               a=10;
                               dialogColor.dismiss();
                           }
                       });
                       img11.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               edtName.setTextColor(ContextCompat.getColor(InnerActivity.this, R.color.color11));
                               a=11;
                               dialogColor.dismiss();
                           }
                       });
                       img12.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               edtName.setTextColor(ContextCompat.getColor(InnerActivity.this, R.color.color12));
                               a=12;
                               dialogColor.dismiss();
                           }
                       });
                       img13.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               edtName.setTextColor(ContextCompat.getColor(InnerActivity.this, R.color.color13));
                               a=13;
                               dialogColor.dismiss();
                           }
                       });






                       dialogColor.setCancelable(true);
                       dialogColor.setContentView(viewColor);
                       dialogColor.show();
                   }
               });
               compatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                   @Override
                   public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                       String a=compatSpinner.getItemAtPosition(i).toString();
                       if(a.equals("Custom …")){
                           compatSpinner.setVisibility(View.INVISIBLE);
                           linearLayout.setVisibility(View.VISIBLE);
                       }
                       Toast.makeText(InnerActivity.this,a,Toast.LENGTH_SHORT).show();
                   }

                   @Override
                   public void onNothingSelected(AdapterView<?> adapterView) {

                   }
               });
               btnDis.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       dialog.dismiss();
                   }
               });
               dialog.setCancelable(true);
               dialog.setContentView(view);
               dialog.show();



               break;
           default:
               onBackPressed();
               break;


       }

        return super.onOptionsItemSelected(item);
    }

    private void lineCharView() {
        LineSet dataset = new LineSet(mLabels, mValues[0]);
        dataset.setColor(Color.parseColor("#758cbb"))
                .setFill(Color.parseColor("#2d374c"))
                .setDotsColor(Color.parseColor("#758cbb"))
                .setThickness(4)
                .setDashed(new float[] {10f, 10f})
                .beginAt(5);
        mChart2.addData(dataset);

        dataset = new LineSet(mLabels, mValues[0]);
        dataset.setColor(Color.parseColor("#b3b5bb"))
                .setFill(Color.parseColor("#2d374c"))
                .setDotsColor(Color.parseColor("#ffc755"))
                .setThickness(4)
                .endAt(6);
        mChart2.addData(dataset);

        // Chart
        mChart2.setBorderSpacing(Tools.fromDpToPx(15))
                .setAxisBorderValues(0, 20)
                .setYLabels(AxisRenderer.LabelPosition.NONE)
                .setLabelsColor(Color.parseColor("#6a84c3"))
                .setXAxis(false)
                .setYAxis(false);

        mChart2.show();

//        mBaseAction = action;
//        Runnable chartAction = new Runnable() {
//            @Override
//            public void run() {
//
//                mBaseAction.run();
//                mTip.prepare(mChart.getEntriesArea(0).get(3), mValues[0][3]);
//                mChart.showTooltip(mTip, true);
//            }
//        };

//        Animation anim = new Animation().setEasing(new BounceInterpolator()).setEndAction(chartAction);
//
//        mChart.show(anim);
    }

    private void lineChart() {
        mChart.setOnChartValueSelectedListener(this);

        // no description text
        mChart.getDescription().setEnabled(false);

        // enable touch gestures
        mChart.setTouchEnabled(true);

        mChart.setDragDecelerationFrictionCoef(0.9f);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(false);
        mChart.setHighlightPerDragEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);
        mChart.setBackgroundColor(Color.BLACK);

        setData(20, 30);

        mChart.animateX(2500);

    }

    private void setData(int count, float range) {

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
            float mult = range / 2f;
            float val = (float) (Math.random() * mult) + 10;
            yVals1.add(new Entry(i, val));
        }

        ArrayList<Entry> yVals2 = new ArrayList<Entry>();

        for (int i = 0; i < count-1; i++) {
            float mult = range;
            float val = (float) (Math.random() * mult) + 50;
            yVals2.add(new Entry(i, val));
//            if(i == 10) {
//                yVals2.add(new Entry(i, val + 50));
//            }
        }

        ArrayList<Entry> yVals3 = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
            float mult = range;
            float val = (float) (Math.random() * mult) + 50;
            yVals3.add(new Entry(i, val));
        }

        LineDataSet set1, set2, set3;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
//            set2 = (LineDataSet) mChart.getData().getDataSetByIndex(1);
//            set3 = (LineDataSet) mChart.getData().getDataSetByIndex(2);
            set1.setValues(yVals1);
//            set2.setValues(yVals2);
//            set3.setValues(yVals3);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(yVals1, "DataSet 1");

            set1.setAxisDependency(YAxis.AxisDependency.RIGHT);
            set1.setColor(Color.WHITE);
            set1.setCircleColor(Color.RED);
            set1.setLineWidth(2f);
            set1.setCircleRadius(4f);
            set1.setFillAlpha(65);
            set1.setFillColor(Color.WHITE);
            set1.setDrawVerticalHighlightIndicator(true);

            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setDrawCircleHole(false);
            //set1.setFillFormatter(new MyFillFormatter(0f));
            //set1.setDrawHorizontalHighlightIndicator(false);
            //set1.setVisible(false);
            //set1.setCircleHoleColor(Color.WHITE);

            // create a dataset and give it a type
//            set2 = new LineDataSet(yVals2, "DataSet 2");
//            set2.setAxisDependency(YAxis.AxisDependency.RIGHT);
//            set2.setColor(Color.RED);
//            set2.setCircleColor(Color.WHITE);
//            set2.setLineWidth(2f);
//            set2.setCircleRadius(3f);
//            set2.setFillAlpha(65);
//            set2.setFillColor(Color.RED);
//            set2.setDrawCircleHole(false);
//            set2.setHighLightColor(Color.rgb(244, 117, 117));
            //set2.setFillFormatter(new MyFillFormatter(900f));

//            set3 = new LineDataSet(yVals3, "DataSet 3");
//            set3.setAxisDependency(YAxis.AxisDependency.RIGHT);
//            set3.setColor(Color.YELLOW);
//            set3.setCircleColor(Color.WHITE);
//            set3.setLineWidth(2f);
//            set3.setCircleRadius(3f);
//            set3.setFillAlpha(65);
//            set3.setFillColor(ColorTemplate.colorWithAlpha(Color.YELLOW, 200));
//            set3.setDrawCircleHole(false);
//            set3.setHighLightColor(Color.rgb(244, 117, 117));

            // create a data object with the datasets
//            LineData data = new LineData(set1, set2, set3);
            LineData data = new LineData(set1);
            data.setValueTextColor(Color.WHITE);
            data.setValueTextSize(9f);

            // set data
            mChart.setData(data);
        }
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {

    }
}