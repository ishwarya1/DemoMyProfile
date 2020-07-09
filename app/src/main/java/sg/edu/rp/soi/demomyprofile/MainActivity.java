package sg.edu.rp.soi.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etGPA=findViewById(R.id.editTextGPA);
        etName=findViewById(R.id.editTextName);
        rgGender = findViewById(R.id.rgGender);
        btnSave = findViewById(R.id.btnsafe);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                Float GPA = Float.parseFloat(etGPA.getText().toString());
                int gender = rgGender.getCheckedRadioButtonId();
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.putString("name",name);
                prefEdit.putFloat("GPA",GPA);
                prefEdit.putInt("gender",gender);
                prefEdit.commit();
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        String name = etName.getText().toString();
        Float GPA = Float.parseFloat(etGPA.getText().toString());
        int gender = rgGender.getCheckedRadioButtonId();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putString("name",name);
        prefEdit.putFloat("GPA",GPA);
        prefEdit.putInt("gender",gender);
        prefEdit.commit();
    }
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String msgName = prefs.getString("name", "");
        Float msgGPA = prefs.getFloat("GPA", 0);
        int msgRadio = prefs.getInt("Gender", 0);
        etName.setText(msgName);
        etGPA.setText(msgGPA + "");
        rgGender.check(msgRadio);
    }
}