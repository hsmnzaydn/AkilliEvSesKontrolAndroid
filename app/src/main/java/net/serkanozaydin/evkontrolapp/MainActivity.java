package net.serkanozaydin.evkontrolapp;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView speak,butonKumanda,butonAlarm,butonHatirlatici;
    TextView komut,cikti;
    public Intent intent;
    public static final int request_code_voice = 1;
    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference komut_db=db.getReference("Komut");
    DatabaseReference cikti_db=db.getReference("Çıktı");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();

        //Sistemde oluşan uyarılar ve bildirilerin gösterildiği kısım
        cikti_db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cikti.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Kumanda activitysine geçmek için kullanılan buton
        butonKumanda.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP: {

                        ImageView view = (ImageView) v;
                        view.clearColorFilter();
                        view.invalidate();
                        Toast.makeText(getApplicationContext(),"Kumanda kontrol",Toast.LENGTH_SHORT).show();
                        Intent ileri=new Intent(getApplicationContext(),KumandaKontrolActivity.class);
                        startActivity(ileri);
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


        //Alarm activitysine geçmek için kullanılan buton
        butonAlarm.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP: {

                        ImageView view = (ImageView) v;
                        view.clearColorFilter();
                        view.invalidate();
                        Toast.makeText(getApplicationContext(),"Kumanda kontrol",Toast.LENGTH_SHORT).show();
                        Intent ileri=new Intent(getApplicationContext(),AlarmKontrolActivity.class);
                        startActivity(ileri);
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

   /*     //Hatırlatıcı activitysine geçmek için kullanılan buton
        butonHatirlatici.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP: {

                        ImageView view = (ImageView) v;
                        view.clearColorFilter();
                        view.invalidate();
                        Toast.makeText(getApplicationContext(),"Hatırlatıcı kontrol",Toast.LENGTH_SHORT).show();
                        Intent ileri=new Intent(getApplicationContext(),HatirlaticiActivity.class);
                        startActivity(ileri);
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



*/


        //Sesi yazıya geçirmek için kullanılan alan
        speak.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP: {
                        Toast.makeText(getApplicationContext(), "Konuşmaya başlayabilirsiniz", Toast.LENGTH_SHORT).show();
                        ImageView view = (ImageView) v;
                        view.clearColorFilter();
                        view.invalidate();


                        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH); // intent i oluþturduk sesi tanýyabilmesi için
                        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

                        try{
                            startActivityForResult(intent, request_code_voice);  // activityi baþlattýk belirlediðimiz sabit deðer ile birlikte
                        }catch(ActivityNotFoundException e)
                        {
                            e.printStackTrace();
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setMessage("Üzgünüz Telefonunuz bu sistemi desteklemiyor!!!")
                                    .setTitle("Ev Sistemi")
                                    .setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });
                            AlertDialog alert = builder.create();
                            alert.show();
                        }

                        break;
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




    }

    //Xml dosyasındanki verileri javaya bağlamak için oluşturulan yöntem
    public void init(){
        speak= (ImageView)findViewById(R.id.ImgSpeak);
        komut= (TextView) findViewById(R.id.cikti);
        cikti= (TextView) findViewById(R.id.gelen);
        butonKumanda= (ImageView) findViewById(R.id.BtnKumanda);
        butonAlarm= (ImageView) findViewById(R.id.BtnAlarm);
       // butonHatirlatici= (ImageView) findViewById(R.id.BtnHatirlatici);
    }

    //ses komutlarını texte çevirmek için kullandık
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case request_code_voice: {

                if (resultCode == RESULT_OK && data != null)
                {
                    ArrayList<String> speech = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    komut.setText(speech.get(0));
                    komut_db.setValue(speech.get(0));
                }
                break;
            }

        }
    }
}
