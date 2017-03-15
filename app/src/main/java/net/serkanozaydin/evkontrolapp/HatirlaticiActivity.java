package net.serkanozaydin.evkontrolapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class HatirlaticiActivity extends AppCompatActivity {
    Button btnTarih;
    int yil,ay,gun;
    static final int DIALOG_ID=0;

    //Hüseyin Serkan Özaydin

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hatirlatici);
        showDialogClickOnButtonClick();
        Calendar calendar=Calendar.getInstance();

        yil=calendar.get(Calendar.YEAR);
        ay=calendar.get(Calendar.MONTH);
        gun=calendar.get(Calendar.DAY_OF_MONTH);


    }


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

    @Override
    protected Dialog onCreateDialog(int id){
     if(id==DIALOG_ID) {
         return new DatePickerDialog(this, dpickerListner, yil, ay, gun);
     }
     return null;
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
