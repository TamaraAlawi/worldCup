package edu.cs.birzeit.inclassproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText edtName;
    private EditText edtTeam;
    private Button save;
    private boolean flagSave= false;
    private SharedPreferences prefs;// بقدر اخزن فيها داتا قد ما بدي read
    private SharedPreferences.Editor editor; // هاد بعدل write

    public static final  String Name= "Name";
    public static final String Team= "Team";
    private boolean FLAG=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtName= findViewById(R.id.edtName);
        edtTeam= findViewById(R.id.edtWinner);
        save= findViewById(R.id.btnSave);

        setupSharedPrefs();
        checkData();
    }


    private void setupSharedPrefs() {
        prefs= PreferenceManager.getDefaultSharedPreferences(this); //هاد مش اكيد يضل موجود
        editor=prefs.edit();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(!flagSave){
            String n= edtName.getText().toString().trim();
            String t = edtTeam.getText().toString().trim();

            editor.putString(Name,n);
            editor.putString(Team,t);
            editor.putBoolean("FLAG",FLAG);
            editor.commit();
        }
    }

    private void checkData() {

        boolean f=prefs.getBoolean("FLAG",false);
        if(f){
            String name=prefs.getString(Name,"");
            String team=prefs.getString(Team,"");
            edtName.setText(name);
            edtTeam.setText(team);
        }

    }

}