/*
 * Credits:
 * Filewriter: http://androidgps.blogspot.de/2008/09/writing-to-sd-card-in-android.html
 * Check OS VersionsCode: http://stackoverflow.com/questions/3993924/get-android-api-level-of-phone-currently-running-my-application 
 * 						  http://stackoverflow.com/questions/3093365/how-can-i-check-the-system-version-of-android
 */

package com.flashback.datafilewritertest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FileWriterActivity extends Activity {

	public TextView lbl01, lbl02, lbl03, lbl04, lbl05;
	Button btnUserData, btnCreateFile, btnDiffBrNe;
	String FolderDir = " ";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_file_writer);
		
		lbl01 = (TextView)findViewById(R.id.lbl01);
		lbl02 = (TextView)findViewById(R.id.lbl02);
		lbl03 = (TextView)findViewById(R.id.lbl03);
		lbl04 = (TextView)findViewById(R.id.lbl04);
		lbl05 = (TextView)findViewById(R.id.lbl05);
		
		final Intent myIntent = getIntent();
		
		btnUserData = (Button)findViewById(R.id.btnUserData);
		btnUserData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				Intent myIntent = getIntent();
				lbl01.setText(myIntent.getStringExtra("UserVorname"));
				lbl02.setText(myIntent.getStringExtra("UserNachname"));
				lbl03.setText(myIntent.getStringExtra("UserBruttoGehalt"));
				lbl04.setText(myIntent.getStringExtra("UserNettoGehalt"));
			}		
		});
		
		btnCreateFile = (Button)findViewById(R.id.btnCreateFile);
		btnCreateFile.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
								
				if (Build.VERSION.SDK_INT == Build.VERSION_CODES.JELLY_BEAN_MR1)
				{
					FolderDir = "/storage/emulated/0/AAAtest/";
				}
				if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1)
				{
					FolderDir = "/sdcard/AAAtest";
				}
				
				try {
					File newDir = new File(FolderDir);
					if (!newDir.exists())
					{
					newDir.mkdir();
					Toast.makeText(getBaseContext(),"Done creating folder",Toast.LENGTH_SHORT).show();
					}
										
					File root = Environment.getExternalStorageDirectory();
				    if (root.canWrite()){
				        File txtfile = new File(FolderDir, "TestFilev2.txt");
				        FileWriter txtwriter = new FileWriter(txtfile);
				        BufferedWriter out = new BufferedWriter(txtwriter);
				        out.write("Vorname: " + myIntent.getStringExtra("UserVorname"));
				        out.write("\n");
				        out.write("Nachname: " + myIntent.getStringExtra("UserNachname"));
				        out.write("\n");
				        out.write("Bruttogehalt: " + myIntent.getStringExtra("UserBruttoGehalt"));
				        out.write("\n");
				        out.write("Nettogehalt: " + myIntent.getStringExtra("UserNettoGehalt"));
				        out.write("\n");
				        out.write("Differenz: " + lbl05.getText().toString());
				        out.close();
				        Toast.makeText(getApplicationContext(), "Done writing File!", Toast.LENGTH_SHORT).show();
				    }
				} catch (IOException e) {
				    Log.e("Could not write file " + e.getMessage(), null, e);
				}
			}
		});
		
		btnDiffBrNe = (Button)findViewById(R.id.btnDiffBrNe);
		btnDiffBrNe.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				double mDiff = 0.0;
				double mBrutto = Double.parseDouble(myIntent.getStringExtra("UserBruttoGehalt"));
				double mNetto = Double.parseDouble(myIntent.getStringExtra("UserNettoGehalt"));
				
				mDiff = mBrutto - mNetto;
				
				lbl05.setText("" + Math.round(mDiff*100.00)/100.00);
			}
		});
			
	}
}
