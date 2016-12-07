package com.androidadvance.screen;

import com.androidadvance.db.DatabaseHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class LookupRecord extends Activity implements OnClickListener{

	private Button btn_scan, btn_lookup;
	private TextView txt_scanname, txt_scanprice;
	private EditText txt_upc;
	DatabaseHelper db;
	
	public Button.OnClickListener mScan = new Button.OnClickListener() {
	    public void onClick(View v) {
	        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
	        intent.putExtra("com.google.zxing.client.android.SCAN_MODE", "QR_CODE_MODE");
	        startActivityForResult(intent, 0);
	    }
	};
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lookuprecord);
		
		btn_scan = (Button) findViewById(R.id.btn_lookscan);
		btn_lookup = (Button) findViewById(R.id.btn_search);
		txt_upc = (EditText) findViewById(R.id.field_lookup);
		txt_scanname = (TextView) findViewById(R.id.txt_scanname);
		txt_scanprice = (TextView) findViewById(R.id.txt_scanprice);
		
		btn_scan.setOnClickListener(mScan);
		btn_lookup.setOnClickListener(this);
	}
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btn_search:
			Log.d("NIS","hiiiiiiiiiiiiiiii");
			db = new DatabaseHelper(getApplicationContext());
			db.getWritableDatabase();
			ProductModel product = db.getProduct(txt_upc.getText().toString());
			txt_scanname.setText(product.getProductname());
			txt_scanprice.setText(product.getProductprice());
			break;
		default:
			break;
		}
		
		
	}
	
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
	    if (requestCode == 0) {
	        if (resultCode == RESULT_OK) {
	            String contents = intent.getStringExtra("SCAN_RESULT");
	            String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
	            txt_upc.setText("");
	            txt_upc.setText(contents);
	            db = new DatabaseHelper(getApplicationContext());
				db.getWritableDatabase();
				ProductModel product = db.getProduct(contents);
				txt_scanname.setText(product.getProductname());
				txt_scanprice.setText(product.getProductprice());
	        } else if (resultCode == RESULT_CANCELED) {
	            // Handle cancel
	        }
	    }
	}
	

}
