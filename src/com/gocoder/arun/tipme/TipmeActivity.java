package com.gocoder.arun.tipme;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TipmeActivity extends Activity {
	public EditText amountInput;
	public TextView finalTipView;
	public TextView tipAmountView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipme);
        amountInput = (EditText) findViewById(R.id.amount);
        finalTipView = (TextView) findViewById(R.id.finalTipView);
        tipAmountView = (TextView) findViewById(R.id.tip_amount);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tipme, menu);
        return true;
    }
    
    public void onCalculateTip(View v){
    	String amtString = amountInput.getText().toString();
    	String notification = "";
    	if(amtString.isEmpty()){
    		notification = "Please enter a valid amount";
    		Toast.makeText(getBaseContext(), notification, Toast.LENGTH_SHORT).show();
    	}
    	else{
    		InputMethodManager imm = (InputMethodManager)getSystemService(
    			      Context.INPUT_METHOD_SERVICE);
    			imm.hideSoftInputFromWindow(amountInput.getWindowToken(), 0);
    		float amt = Float.parseFloat(amtString),
    				finalAmount = amt, 
    				tipAmount=0,
    				tip=0;
    		switch(v.getId()){
	    		case R.id.tip1 : 
	    			tip=10;
	    			break;
	    		case R.id.tip2 : 
	    			tip=15;
	    			break;
	    		case R.id.tip3 : 
	    			tip=20;
	    			break;
    		}
    		tipAmount = calculateTip(amt, tip);
    		finalAmount = amt + tipAmount; 
    		tipAmountView.setText("$"+String.format("%.2f", tipAmount));
    		finalTipView.setText("$"+String.format("%.2f",finalAmount));
    	}
    }
    private float calculateTip(float amt,float tip){
    	return ((tip/100) * amt); 
    }
    
}
