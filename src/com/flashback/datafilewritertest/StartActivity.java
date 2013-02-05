package com.flashback.datafilewritertest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class StartActivity extends Activity {

	public EditText etVorname, etNachname, etBruttoGehalt, etNettoGehalt;
	Button btnNext;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        
        etVorname = (EditText)findViewById(R.id.etVorname);
        etNachname = (EditText)findViewById(R.id.etNachname);
        etBruttoGehalt = (EditText)findViewById(R.id.etBruttoGehalt);
        etNettoGehalt = (EditText)findViewById(R.id.etNettoGehalt);
                
        btnNext = (Button)findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				startActivity(new Intent(getApplicationContext(), FileWriterActivity.class));
				
				Intent myIntent = new Intent(getApplicationContext(), FileWriterActivity.class);
				myIntent.putExtra("UserVorname", etVorname.getText().toString());
				myIntent.putExtra("UserNachname", etNachname.getText().toString());
				myIntent.putExtra("UserBruttoGehalt", etBruttoGehalt.getText().toString());
				myIntent.putExtra("UserNettoGehalt", etNettoGehalt.getText().toString());				
				startActivity(myIntent);	
			}
		});
        
    }
}
