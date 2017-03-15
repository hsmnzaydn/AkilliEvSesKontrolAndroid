package net.serkanozaydin.evkontrolapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/*Hüseyin Serkan Özaydin*/
public class KumandaKontrolActivity extends AppCompatActivity {
    ImageView next,stop,previous,start,plus,negative;
    Button btnMp3Guncelle;
    ListView playlist;
    int sayac=0;
    int ses_seviyesi;



    String[] muzik_list={"pop","rock","damar"};

    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference komut_db=db.getReference("Komut");
    DatabaseReference ses_db=db.getReference("Ses");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kumanda_kontrol);
        init();


        //Firebasedeki ses seviyesini kontrol ediyor
        ses_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ses_seviyesi=Integer.parseInt(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //playlistte bulunan mp3 dosyasını çalıştırmak için kullanıyor
        start.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP: {

                        ImageView view = (ImageView) v;
                        view.clearColorFilter();
                        view.invalidate();

                        if(sayac==0) {
                            start.setBackgroundResource(R.drawable.pause);
                            sayac++;
                            komut_db.setValue("mp3 beklet");
                            Toast.makeText(getApplicationContext(),"Mp3 pause edildi",Toast.LENGTH_SHORT).show();

                        }
                        else{
                            start.setBackgroundResource(R.drawable.start);
                            sayac=0;
                            komut_db.setValue("mp3 devam et");
                            Toast.makeText(getApplicationContext(),"Mp3 devam ediyor",Toast.LENGTH_SHORT).show();

                        }
                    }
                    case MotionEvent.ACTION_DOWN: {
                        ImageView view = (ImageView) v;
                        view.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_CANCEL: {
                        ImageView view = (ImageView) v;
                        view.clearColorFilter();
                        view.invalidate();
                        break;

                    }
                }


                return true;
            }
        });


        //playlistte bulunan bir sonraki mp3 dosyasını çalıştırmak için kullanıyor

        next.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP: {

                        ImageView view = (ImageView) v;
                        view.clearColorFilter();
                        view.invalidate();
                       komut_db.setValue("mp3 ileri");
                    }
                    case MotionEvent.ACTION_DOWN: {
                        ImageView view = (ImageView) v;
                        view.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_CANCEL: {
                        ImageView view = (ImageView) v;
                        view.clearColorFilter();
                        view.invalidate();
                        break;

                    }
                }


                return true;
            }
        });

        //playlistte bulunan bir önceki mp3 dosyasını çalıştırmak için kullanıyor
        previous.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP: {

                        ImageView view = (ImageView) v;
                        view.clearColorFilter();
                        view.invalidate();
                        komut_db.setValue("mp3 geri");

                    }
                    case MotionEvent.ACTION_DOWN: {
                        ImageView view = (ImageView) v;
                        view.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_CANCEL: {
                        ImageView view = (ImageView) v;
                        view.clearColorFilter();
                        view.invalidate();
                        break;

                    }
                }


                return true;
            }
        });

        //çalan mp3 dosyasını durdurmak için kullanıyor
        stop.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP: {

                        ImageView view = (ImageView) v;
                        view.clearColorFilter();
                        view.invalidate();
                        komut_db.setValue("mp3 durdur");
                        Toast.makeText(getApplicationContext(),"Mp3 durduruldu",Toast.LENGTH_SHORT).show();

                    }
                    case MotionEvent.ACTION_DOWN: {
                        ImageView view = (ImageView) v;
                        view.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_CANCEL: {
                        ImageView view = (ImageView) v;
                        view.clearColorFilter();
                        view.invalidate();
                        break;

                    }
                }


                return true;
            }
        });

        //İstenen playlisti çalmak için kullanılıyor
        ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,muzik_list);
        playlist.setAdapter(adapter);


        playlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                komut_db.setValue(((TextView) view).getText());
                Toast.makeText(getApplicationContext(),"Mp3 başlatıldı",Toast.LENGTH_SHORT).show();

            }
        });


        //mp3 ses seviyesi arttırır
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                komut_db.setValue("ses degistir");
                Toast.makeText(getApplicationContext(),"Ses arttırılıyor",Toast.LENGTH_SHORT).show();

                ses_seviyesi=ses_seviyesi+1;
                if(ses_seviyesi>=9){
                    Toast.makeText(getApplicationContext(),"Müzik son ses",Toast.LENGTH_SHORT).show();
                    ses_seviyesi=9;
                }

                    ses_db.setValue(ses_seviyesi);

            }
        });
        //mp3 ses seviyesi azaltır
        negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                komut_db.setValue("ses degistir");
                ses_seviyesi=ses_seviyesi-1;
                Toast.makeText(getApplicationContext(),"Ses azaltılıyor",Toast.LENGTH_SHORT).show();

                if(ses_seviyesi<=0){
                    Toast.makeText(getApplicationContext(),"Müzik sesi azaltılamaz",Toast.LENGTH_SHORT).show();
                    ses_seviyesi=0;
                }

                ses_db.setValue(ses_seviyesi);



            }
        });

        //mp3 dosyalarını güncellemek için kullanıyor

        btnMp3Guncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                komut_db.setValue("mp3 güncelle");//Komut değişkenine "mp3 güncelle" textini yollar
                Log.i("Bilgi","mp3 güncelleye tıkladı");
            }
        });




    }


    public void init(){
        next= (ImageView) findViewById(R.id.IMGnext);
        stop= (ImageView) findViewById(R.id.IMGstop);
        previous= (ImageView) findViewById(R.id.IMGprevious);
        start= (ImageView) findViewById(R.id.IMGplay);
        plus= (ImageView) findViewById(R.id.IMGplus);
        negative= (ImageView) findViewById(R.id.IMGnegative);

        playlist= (ListView) findViewById(R.id.LISTmp3liste);
        btnMp3Guncelle= (Button) findViewById(R.id.btnMp3Guncelle);

    }
}
