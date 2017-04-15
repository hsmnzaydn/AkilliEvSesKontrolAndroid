package net.serkanozaydin.evkontrolapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HatirlaticiActivity extends AppCompatActivity {
    Button btnTarih,btnSaat,BtnKaydet;
    EditText EdtGorev;
    int yil,ay,gun;
    int saat,dakika;
    static final int DIALOG_ID=0;
    static final int DIALOG =1;
    FirebaseDatabase db;


    //Hüseyin Serkan Özaydin

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hatirlatici);
        showDialogClickOnButtonClick();
        showTimePickerDialog();
        EdtGorev= (EditText) findViewById(R.id.EDTGorev);
        BtnKaydet= (Button) findViewById(R.id.BTNkaydet);
        Calendar calendar=Calendar.getInstance();
        db=FirebaseDatabase.getInstance();

        yil=calendar.get(Calendar.YEAR);
        ay=calendar.get(Calendar.MONTH);
        gun=calendar.get(Calendar.DAY_OF_MONTH);
        saat=calendar.get(Calendar.AM_PM);
        dakika=calendar.get(Calendar.MINUTE);


        final Hatirlatici hatirla=new Hatirlatici(ay+"",gun+"",yil+"",saat+"",dakika+"",EdtGorev.getText().toString());

        BtnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference dbRef = db.getReference("Gorevler");
                String key = dbRef.push().getKey();
                DatabaseReference dbRefKeyli = db.getReference("Gorevler/"+key);

                dbRefKeyli.setValue(hatirla);
                EdtGorev.setText("");
            }
        });




    }

    public void showTimePickerDialog(){

        btnSaat= (Button) findViewById(R.id.BtnSaat);

        btnSaat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG);
            }
        });


    }
    @Override
    protected Dialog onCreateDialog(int id){
        if(id==DIALOG_ID) {
            return new DatePickerDialog(this, dpickerListner, yil, ay, gun);
        }
        if(id== DIALOG){
            return new TimePickerDialog(this,kTimePickerListener,saat,dakika,false);
        }
        return null;
    }


    protected TimePickerDialog.OnTimeSetListener kTimePickerListener=
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    saat=hourOfDay;
                    dakika=minute;
                    Toast.makeText(getApplicationContext(),saat+" "+dakika,Toast.LENGTH_SHORT).show();
                }
            };


    public void showDialogClickOnButtonClick(){
    btnTarih= (Button) findViewById(R.id.BtnTarih);//Datepicker açmak için kullanacağım

        btnTarih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Bilgi","Datepicker'a tıklandı");
                showDialog(DIALOG_ID);
            }
        });
    }



    private DatePickerDialog.OnDateSetListener dpickerListner =new DatePickerDialog.OnDateSetListener() {//Tıklama eventınında olacakları buraya yazdık
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

          yil=year;
            ay=month;
            gun=dayOfMonth;
            Toast.makeText(getApplicationContext(),"Tarih ayarlandı şimdi saati seçebilirsiniz",Toast.LENGTH_SHORT).show();
        }
    };
}
