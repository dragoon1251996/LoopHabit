package com.t3h.whiyew.loophabit;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    public static final String KEY_NAME = "KEY";
    private MyAdapter myAdapter;
    private ArrayList<NameHabit> arrData = new ArrayList<>();
    private ListView listView;
    String color = "#000000";
    TextView edtTimePicker;
    int a;
    private Dialog dialog, dialogColor;
    private TextView txtThu1, txtThu2, txtThu3, txtThu4, txtNgay1, txtNgay2, txtNgay3, txtNgay4;
    Data data;
    private AlarmManager mAlarmManager;

    public void setAlarm(int reminderId, Calendar when) {
        long _alarm = 0;
        Calendar now = Calendar.getInstance();
        if (when.getTimeInMillis() <= now.getTimeInMillis())
            _alarm = when.getTimeInMillis() + (AlarmManager.INTERVAL_DAY);
        else
            _alarm = when.getTimeInMillis();

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
        final int _id = (int) System.currentTimeMillis();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, reminderId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, _alarm, AlarmManager.INTERVAL_DAY * 7, pendingIntent);
    }

    private void startBroadcast() {


        long _alarm = 0;
        Calendar now = Calendar.getInstance();
        Calendar alarm = Calendar.getInstance();
        alarm.set(Calendar.HOUR_OF_DAY, 17);
        alarm.set(Calendar.MINUTE, 2);
        alarm.set(Calendar.SECOND, 0);

        if (alarm.getTimeInMillis() <= now.getTimeInMillis())
            _alarm = alarm.getTimeInMillis() + (AlarmManager.INTERVAL_DAY);
        else
            _alarm = alarm.getTimeInMillis();

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
        final int _id = (int) System.currentTimeMillis();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, _id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, _alarm, AlarmManager.INTERVAL_DAY * 7, pendingIntent);


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new Data(this, null, null, 2);
        ArrayList<NameHabit> arr = new ArrayList<>();


//        if(arr.size()>=1) {
//            for(int i=0;i<arr.size();i++){
//            Calendar calendar = Calendar.getInstance();
//            calendar.set(Calendar.HOUR_OF_DAY, 14);
//            calendar.set(Calendar.MINUTE, 37);
//            calendar.set(Calendar.SECOND, 0);
//                Toast.makeText(this, calendar.getTimeInMillis()+"",Toast.LENGTH_LONG).show();
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, i, alarm, 0);
//            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
//                    calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY,
//                    pendingIntent);}
//        }
        //   initData();
        listView = (ListView) findViewById(R.id.lv);
        myAdapter = new MyAdapter(this, data.getAllContacts());
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, InnerActivity.class);
                String name = data.getAllContacts().get(i).getTenThuocTinh();
                intent.putExtra(KEY_NAME, name);
                startActivity(intent);
            }
        });
        myAdapter.notifyDataSetChanged();
        txtThu1 = (TextView) findViewById(R.id.txtThu1);
        txtThu2 = (TextView) findViewById(R.id.txtThu2);
        txtThu3 = (TextView) findViewById(R.id.txtThu3);
        txtThu4 = (TextView) findViewById(R.id.txtThu4);
        txtNgay1 = (TextView) findViewById(R.id.txtDay1);
        txtNgay2 = (TextView) findViewById(R.id.txtDay2);
        txtNgay3 = (TextView) findViewById(R.id.txtDay3);
        txtNgay4 = (TextView) findViewById(R.id.txtDay4);


        Calendar calenda = Calendar.getInstance();
        int a = calenda.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        int b = calenda.get(Calendar.DAY_OF_WEEK);

        txtNgay4.setText(a + "");
        txtNgay3.setText(a - 1 + "");
        txtNgay2.setText(a - 2 + "");
        txtNgay1.setText(a - 3 + "");
        txtThu4.setText("TH" + b);
        txtThu3.setText("TH" + (b - 1));
        txtThu2.setText("TH" + (b - 2));
        txtThu1.setText("TH" + (b - 3));
        if (b == 8 || b == 1) {
            txtThu4.setText("CN");
        }
        if ((b - 1) == 8 || (b - 1) == 1) {
            txtThu3.setText("CN");
        }
        if ((b - 2) == 8 || (b - 2) == 1) {
            txtThu2.setText("CN");
        }
        if ((b - 3) == 8 || (b - 3) == 1) {
            txtThu1.setText("CN");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.itemmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    String ngay;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                dialog = new Dialog(this, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar_MinWidth);
                LayoutInflater inflater = LayoutInflater.from(this);
                View view = inflater.inflate(R.layout.dialog_habit, null);
                final EditText edtName = (EditText) view.findViewById(R.id.tvName);
                ImageButton imgColor = (ImageButton) view.findViewById(R.id.imgColor);
                EditText edtQue = (EditText) view.findViewById(R.id.tvQuestion);
                Button btnDis = (Button) view.findViewById(R.id.btnDis);
                Button btnSave = (Button) view.findViewById(R.id.btnSave);
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (edtTimePicker.getText().toString().equals((":")) == false) {
                            String[] time = edtTimePicker.getText().toString().split(":");
                            NameHabit nameHabit = new NameHabit(color, edtName.getText().toString(), new Time(time[0], time[1]));
                            data.addJapan(nameHabit);
                            Calendar alarm1 = Calendar.getInstance();
                            alarm1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
                            alarm1.set(Calendar.MINUTE, Integer.parseInt(time[1]));
                            alarm1.set(Calendar.SECOND, 0);
                            myAdapter = new MyAdapter(getBaseContext(), data.getAllContacts());
                            listView.setAdapter(myAdapter);
                            myAdapter.notifyDataSetChanged();
                            setAlarm(data.getContactsCount(), alarm1);
                            dialog.dismiss();
                        } else {
                            Toast.makeText(getBaseContext(), "Time????", Toast.LENGTH_LONG).show();
                        }
                    }
                });


                final AppCompatSpinner compatSpinner = (AppCompatSpinner) view.findViewById(R.id.spinner);
                final LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.customRepeat);
                EditText edtTime = (EditText) view.findViewById(R.id.numerator);
                EditText edtDay = (EditText) view.findViewById(R.id.denominator);
                Button btnOK = (Button) view.findViewById(R.id.btnOK);
                final TextView txtDay = (TextView) view.findViewById(R.id.txtDay);
                edtTimePicker = (TextView) view.findViewById(R.id.edtTime);
                LinearLayout linearLayout1 = (LinearLayout) view.findViewById(R.id.layout_panel);
                final LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.layout_panel2);

                linearLayout2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final CharSequence[] items = {" Thứ bảy ", " Chủ nhật ", " Thứ hai ", " Thứ ba ", " Thứ tư ", " Thứ năm ", " Thứ sáu "};
                        final ArrayList seletedItems = new ArrayList();

                        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)

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
                                        if (seletedItems.size() == items.length) {
                                            txtDay.setText(" Any day of the week ");
                                            ngay = " Any day of the week ";
                                        } else {
                                            String b = "";
                                            for (int i = 0; i < seletedItems.size(); i++) {
                                                int index = (int) seletedItems.get(i);
                                                b = b + items[index].toString() + ",";
//                                            setTitle(seletedItems.get(i).toString()+",");
                                            }
                                            String c = b.substring(0, b.length() - 1);
                                            txtDay.setText(c);
                                            ngay = c;
                                        }

                                    }
                                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {

                                    }
                                }).create();
                        dialog.show();
                    }
                });
                linearLayout1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar now = Calendar.getInstance();
                        TimePickerDialog timepickerdialog = TimePickerDialog.newInstance(MainActivity.this,
                                now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), true);
                        timepickerdialog.setThemeDark(false); //Dark Theme?
                        timepickerdialog.vibrate(false); //vibrate on choosing time?
                        timepickerdialog.dismissOnPause(false); //dismiss the dialog onPause() called?
                        timepickerdialog.setOnTimeSetListener(new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                                edtTimePicker.setText(hourOfDay + ":" + minute);
                                if (edtTimePicker.getText().toString() != "Off") {
                                    linearLayout2.setVisibility(View.VISIBLE);
                                }
                            }
                        });
                        timepickerdialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialogInterface) {
                                Toast.makeText(MainActivity.this, "Cancel choosing time", Toast.LENGTH_SHORT).show();
                            }
                        });
                        timepickerdialog.show(getFragmentManager(), "Timepickerdialog"); //show time picker dialog
                    }
                });


                imgColor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogColor = new Dialog(MainActivity.this, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar_MinWidth);
                        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                        View viewColor = inflater.inflate(R.layout.color_selection, null);
                        ImageView img1 = (ImageView) viewColor.findViewById(R.id.mau1);
                        ImageView img2 = (ImageView) viewColor.findViewById(R.id.mau2);
                        ImageView img3 = (ImageView) viewColor.findViewById(R.id.mau3);
                        ImageView img4 = (ImageView) viewColor.findViewById(R.id.mau4);
                        ImageView img5 = (ImageView) viewColor.findViewById(R.id.mau5);
                        ImageView img6 = (ImageView) viewColor.findViewById(R.id.mau6);
                        ImageView img7 = (ImageView) viewColor.findViewById(R.id.mau7);
                        ImageView img8 = (ImageView) viewColor.findViewById(R.id.mau8);
                        ImageView img9 = (ImageView) viewColor.findViewById(R.id.mau9);
                        ImageView img10 = (ImageView) viewColor.findViewById(R.id.mau10);
                        ImageView img11 = (ImageView) viewColor.findViewById(R.id.mau11);
                        ImageView img12 = (ImageView) viewColor.findViewById(R.id.mau12);
                        ImageView img13 = (ImageView) viewColor.findViewById(R.id.mau13);
                        switch (a) {
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
                        img1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                a = 1;
                                edtName.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.color1));
                                color = "#d50000";
                                dialogColor.dismiss();
                            }
                        });
                        img2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                a = 2;
                                edtName.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.color2));
                                color = "#f44336";
                                dialogColor.dismiss();
                            }
                        });
                        img3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                edtName.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.color3));
                                color = "#ff3d00";
                                a = 3;
                                dialogColor.dismiss();
                            }
                        });
                        img4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                edtName.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.color4));
                                a = 4;
                                color = "#64dd17";
                                dialogColor.dismiss();
                            }
                        });
                        img5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                edtName.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.color5));
                                a = 5;
                                color = "#0d47a1";
                                dialogColor.dismiss();
                            }
                        });
                        img6.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                edtName.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.color6));
                                a = 6;
                                color = "#00695c";
                                dialogColor.dismiss();
                            }
                        });
                        img7.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                edtName.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.color7));
                                a = 7;
                                color = "#004d40";
                                dialogColor.dismiss();
                            }
                        });
                        img8.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                edtName.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.color8));
                                a = 8;
                                color = "#558b2f";
                                dialogColor.dismiss();
                            }
                        });
                        img9.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                edtName.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.color9));
                                a = 9;
                                color = "#311b92";
                                dialogColor.dismiss();
                            }
                        });
                        img10.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                edtName.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.color10));
                                a = 10;
                                color = "#9c27b0";
                                dialogColor.dismiss();
                            }
                        });
                        img11.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                edtName.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.color11));
                                a = 11;
                                color = "#f44336";
                                dialogColor.dismiss();
                            }
                        });
                        img12.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                edtName.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.color12));
                                a = 12;
                                color = "#000";
                                dialogColor.dismiss();
                            }
                        });
                        img13.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                edtName.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.color13));
                                a = 13;
                                color = "#ccc";
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
                        String a = compatSpinner.getItemAtPosition(i).toString();
                        if (a.equals("Custom …")) {
                            compatSpinner.setVisibility(View.INVISIBLE);
                            linearLayout.setVisibility(View.VISIBLE);
                        }
                        Toast.makeText(MainActivity.this, a, Toast.LENGTH_SHORT).show();
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
            case R.id.show_archived:
                break;
            case R.id.night_mode:
                Toast.makeText(getBaseContext(), "asd", Toast.LENGTH_LONG).show();
                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.er);
                relativeLayout.setVisibility(View.VISIBLE);
                item.setChecked(false);
                // mBold = false;
                break;
            case R.id.setting:
                Toast.makeText(MainActivity.this, "More2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about:
                Intent intent = new Intent(this, About.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {

    }


}
