package net.serkanozaydin.evkontrolapp;




import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AlarmKontrol extends Activity {
Button AlarmKur,AlarmKaldir,AlarmDurdur,AlarmKapat;
   CheckBox pazartesi,sali,carsamba,persembe,cuma,cumartesi,pazar;
    TextView pazartesiAlarm,saliAlarm,carsambaAlarm,persembeAlarm,cumaAlarm,cumartesiAlarm,pazarAlarm;
    TimePicker time;
    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference alarm_db=db.getReference("Alarm");
    DatabaseReference komut=db.getReference("Komut");
    public String kuruluAlarmlar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_kontrol);
        AlarmKapat= (Button) findViewById(R.id.AlarmKapat);
        AlarmDurdur= (Button) findViewById(R.id.AlarmDurdur);
        AlarmKaldir= (Button) findViewById(R.id.AlarmKaldir);
        pazartesi= (CheckBox) findViewById(R.id.Pazartesi);
        sali= (CheckBox) findViewById(R.id.Sali);
        carsamba= (CheckBox) findViewById(R.id.carsamba);
        persembe= (CheckBox) findViewById(R.id.persembe);
        cuma= (CheckBox) findViewById(R.id.cuma);
        cumartesi= (CheckBox) findViewById(R.id.cumartesi);
        pazar= (CheckBox) findViewById(R.id.pazar);

        time= (TimePicker) findViewById(R.id.timepicker);
        AlarmKur= (Button) findViewById(R.id.AlarmKur);

        pazarAlarm= (TextView) findViewById(R.id.pazarAlarm);
        pazartesiAlarm= (TextView) findViewById(R.id.pazartesiAlarm);
        saliAlarm= (TextView) findViewById(R.id.saliAlarm);
        carsambaAlarm= (TextView) findViewById(R.id.carsambaAlarm);
        persembeAlarm= (TextView) findViewById(R.id.persembeAlarm);
        cumaAlarm= (TextView) findViewById(R.id.cumaAlarm);
        cumartesiAlarm= (TextView) findViewById(R.id.cumartesiAlarm);



        AlarmKapat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                komut.setValue("alarm kapat");
                alarm_db.child("Sunday").setValue("bos");
                alarm_db.child("Monday").setValue("bos");
                alarm_db.child("Tuesday").setValue("bos");
                alarm_db.child("Wednesday").setValue("bos");
                alarm_db.child("Friday").setValue("bos");
                alarm_db.child("Saturday").setValue("bos");
                alarm_db.child("Thursday").setValue("bos");

                Toast.makeText(getApplicationContext(),"Tüm alamlar kapatıldı",Toast.LENGTH_SHORT).show();

            }
        });
        AlarmDurdur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                komut.setValue("alarm durdur");
            }
        });

        AlarmKur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int saat=time.getCurrentHour();
                int dakika=time.getCurrentMinute();

                if(pazartesi.isChecked()){
                    alarm_db.child("Monday").setValue(saat+" "+dakika);
                    pazartesi.setChecked(false);
                    kuruluAlarmlar+=" Pazartesi";

                }



                if(sali.isChecked()){
                    alarm_db.child("Tuesday").setValue(saat+" "+dakika);
                    sali.setChecked(false);
                    kuruluAlarmlar+=" Salı";

                }


                if(carsamba.isChecked()){
                    alarm_db.child("Wednesday").setValue(saat+" "+dakika);
                    carsamba.setChecked(false);
                    kuruluAlarmlar+=" Çarşamba";

                }


                if(persembe.isChecked()){
                    alarm_db.child("Thursday").setValue(saat+" "+dakika);
                    persembe.setChecked(false);
                    kuruluAlarmlar+=" Perşembe";

                }

                if(cuma.isChecked()){
                    alarm_db.child("Friday").setValue(saat+" "+dakika);
                    cuma.setChecked(false);
                    kuruluAlarmlar+=" Cuma";

                }


                if(cumartesi.isChecked()){
                    alarm_db.child("Saturday").setValue(saat+" "+dakika);
                    cumartesi.setChecked(false);
                    kuruluAlarmlar+=" Cumartesi";

                }
                if(pazar.isChecked()){
                    alarm_db.child("Sunday").setValue(saat+" "+dakika);
                    pazar.setChecked(false);
                    kuruluAlarmlar+=" Pazar";


                }



                if(kuruluAlarmlar==null || kuruluAlarmlar.toString()=="null" ){
                    Toast.makeText(getApplicationContext(),"Alarm kurulumadı",Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(getApplicationContext(),kuruluAlarmlar+" gün/günlerine alarm kuruldu",Toast.LENGTH_SHORT).show();
                    kuruluAlarmlar=null;

                }

                komut.setValue("alarm aç");


            }
        });



        alarm_db.child("Monday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pazartesiAlarm.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        alarm_db.child("Tuesday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                saliAlarm.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        alarm_db.child("Wednesday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                carsambaAlarm.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        alarm_db.child("Thursday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                persembeAlarm.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        alarm_db.child("Friday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cumaAlarm.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        alarm_db.child("Saturday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cumartesiAlarm.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        alarm_db.child("Sunday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pazarAlarm.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        AlarmKaldir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(pazartesi.isChecked()){
                    alarm_db.child("Monday").setValue("bos");
                    pazartesi.setChecked(false);

                }



                if(sali.isChecked()){
                    alarm_db.child("Tuesday").setValue("bos");
                    sali.setChecked(false);

                }


                if(carsamba.isChecked()){
                    alarm_db.child("Wednesday").setValue("bos");
                    carsamba.setChecked(false);


                }


                if(persembe.isChecked()){
                    alarm_db.child("Thursday").setValue("bos");
                    persembe.setChecked(false);


                }

                if(cuma.isChecked()){
                    alarm_db.child("Friday").setValue("bos");
                    cuma.setChecked(false);

                }


                if(cumartesi.isChecked()){
                    alarm_db.child("Saturday").setValue("bos");
                    cumartesi.setChecked(false);


                }
                if(pazar.isChecked()){
                    alarm_db.child("Sunday").setValue("bos");
                    pazar.setChecked(false);



                }


                Toast.makeText(getApplicationContext(),"Alarmlar kaldırıldı",Toast.LENGTH_SHORT).show();

            }
        });






    }
}
