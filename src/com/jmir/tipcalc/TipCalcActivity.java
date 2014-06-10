package com.jmir.tipcalc;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Build;
import android.graphics.Color;

public class TipCalcActivity extends ActionBarActivity {

	// These need to be public, otherwise certain methods don't show up at first like getText
	public EditText billAmtTxt;  // editable view with full amount of bill
	public EditText pctCustomTxt; 
	TextView tipAmtTxt;   // readonly view to display calculated tip
	public String tipAmt;     // readonly calculated tip sending out to display
	public static Float billfloat;  // avoid warnings about access "in a static way"
	public static Float customfloat;
	public Button customBtn; 
	private static final String TAG = "TipCalcActivity";
	int btnclear = Color.LTGRAY;
	int btnyellow = Color.YELLOW;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tip_calc);
		findViewById(R.id.pct10btn).setBackgroundColor(btnclear);
		findViewById(R.id.pct15btn).setBackgroundColor(btnclear);
		findViewById(R.id.pct15btn).setBackgroundColor(btnclear);
	}

	
	private void calculateTip(Float tipAmt) {
		billAmtTxt = (EditText) findViewById(R.id.amtDecimal1);
		// its a decimal format but only enforced on the XML side; a string result is sent back.
		String bill = billAmtTxt.getText().toString();
		billfloat = Float.valueOf(bill);
		Log.d(TAG,"billfloat = "+ billfloat + "tipAmt = " + tipAmt);
		Float tipfloat = (float) (billfloat * tipAmt);
		//Log.d(TAG,"tipfloat = " + tipfloat);
		
		tipAmtTxt = (TextView) findViewById(R.id.tipAmt);
		//tipAmtTxt.setText(tipfloat.toString());  does not format currency
		tipAmtTxt.setText(String.format("$%.2f",tipfloat));
		String tipfloattest = tipAmtTxt.getText().toString();
		Log.d(TAG,"tipfloattest = " + tipfloattest);
	}
	
	public void amtDecClick(View v) {
	   billAmtTxt = (EditText) findViewById(R.id.amtDecimal1);
	   billAmtTxt.setText("");
	   tipAmtTxt = (TextView) findViewById(R.id.tipAmt);
	   tipAmtTxt.setText(String.format("$%.2f",0.0));
	   findViewById(R.id.pct10btn).setBackgroundColor(btnclear);
	   findViewById(R.id.pct15btn).setBackgroundColor(btnclear);
	   findViewById(R.id.pct20btn).setBackgroundColor(btnclear);
	   findViewById(R.id.pctCustombtn).setBackgroundColor(btnclear);
	}
	
	public void on10Click(View v){
		calculateTip((float)0.10);
		findViewById(R.id.pct10btn).setBackgroundColor(btnyellow);
		findViewById(R.id.pct15btn).setBackgroundColor(btnclear);
		findViewById(R.id.pct20btn).setBackgroundColor(btnclear);
		findViewById(R.id.pctCustombtn).setBackgroundColor(btnclear);
	}
	
	public void on15Click(View v){
		calculateTip((float)0.15);
		findViewById(R.id.pct10btn).setBackgroundColor(btnclear);
		findViewById(R.id.pct15btn).setBackgroundColor(btnyellow);
		findViewById(R.id.pct20btn).setBackgroundColor(btnclear);
		findViewById(R.id.pctCustombtn).setBackgroundColor(btnclear);
	}
	
	public void on20Click(View v) {
		calculateTip((float)0.20);
		findViewById(R.id.pct10btn).setBackgroundColor(btnclear);
		findViewById(R.id.pct15btn).setBackgroundColor(btnclear);
		findViewById(R.id.pct20btn).setBackgroundColor(btnyellow);
		findViewById(R.id.pctCustombtn).setBackgroundColor(btnclear);
	}
	
	public void onCustomClickBtn(View v) {
		pctCustomTxt = (EditText) findViewById(R.id.pctCustom);
		String pctCustom = pctCustomTxt.getText().toString();
		if (!pctCustom.isEmpty())
		{
		customfloat = Float.valueOf(pctCustom);
		Log.d(TAG,"customfloat = " + customfloat);
		Float customfloatpercent = customfloat / ((float) 100.00);
		Log.d(TAG,"customfloatpercent = " + customfloatpercent);
		customBtn = (Button) findViewById(R.id.pctCustombtn);
		customBtn.setText(String.format("%.1f",customfloat));
		calculateTip((float)customfloatpercent);
		findViewById(R.id.pct10btn).setBackgroundColor(btnclear);
		findViewById(R.id.pct15btn).setBackgroundColor(btnclear);
		findViewById(R.id.pct20btn).setBackgroundColor(btnclear);
		findViewById(R.id.pctCustombtn).setBackgroundColor(btnyellow);
		}
		
	}
	
	public void pctCustomClick(View v) {  
		// this is actually a click on the editable field, meant to clear out customs.
		pctCustomTxt = (EditText) findViewById(R.id.pctCustom);
		pctCustomTxt.setText("");
		customBtn = (Button) findViewById(R.id.pctCustombtn);
		customBtn.setText(R.string.customTipStr2);
		findViewById(R.id.pct10btn).setBackgroundColor(btnclear);
		findViewById(R.id.pct15btn).setBackgroundColor(btnclear);
		findViewById(R.id.pct20btn).setBackgroundColor(btnclear);
		findViewById(R.id.pctCustombtn).setBackgroundColor(btnclear);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tip_calc, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


}
