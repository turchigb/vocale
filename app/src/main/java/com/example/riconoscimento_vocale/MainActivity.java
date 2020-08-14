package com.example.riconoscimento_vocale;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.speech.RecognizerIntent;
import android.widget.TextView;
//import android.support.v4.app.NavUtils;


public class MainActivity extends Activity implements OnClickListener
{

    public ListView mList;
    public Button speakButton;
    public TextView tv;
    String  contenuto="";

    public static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        speakButton = (Button) findViewById(R.id.bottone);
        tv=(TextView) findViewById(R.id.textView3);
        speakButton.setOnClickListener(this);

        //voiceinputbuttons();
    }

    /*
    public void voiceinputbuttons() {
        speakButton = (Button) findViewById(R.id.bottone);
        tv=(TextView) findViewById(R.id.textView1);
       // mList = (ListView) findViewById(R.id.lista);
    }
*/

    public void startVoiceRecognitionActivity() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Parla pure...");
        startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        startVoiceRecognitionActivity();
    }

    @Override

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        String frase="";
        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {
            // Fill the list view with the strings the recognizer thought it
            // could have heard
            ArrayList matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

           // mList.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, matches));
         frase=matches.get(0).toString();
         contenuto=contenuto+"\n"+frase;
         tv.setText(contenuto);
         //Toast.makeText(MainActivity.this, "Frase="+contenuto, Toast.LENGTH_SHORT).show();


            /*
            Il sistema produce una lista delle possibili interpretazioni delle parole
            pronunciate.  Se ad esempio ho pronunciato:

            latte pasta uno

            potrei ottenere una lista del tipo

            latte pasta 1
            latte pasta uno
            latte basta uno
            latte basta 1

            Qui prendiamo solo la prima interpretazione (1^ elem. della lista)

             */

            // matches is the result of voice input. It is a list of what the
            // user possibly said.
            // Using an if statement for the keyword you want to use allows the
            // use of any activity if keywords match
            // it is possible to set up multiple keywords to use the same
            // activity so more than one word will allow the user
            // to use the activity (makes it so the user doesn't have to
            // memorize words from a list)
            // to use an activity from the voice input information simply use
            // the following format;
            // if (matches.contains("keyword here") { startActivity(new
            // Intent("name.of.manifest.ACTIVITY")

            if (matches.contains("information")) {
                //fai qualcosa
            }
        }
    }
}