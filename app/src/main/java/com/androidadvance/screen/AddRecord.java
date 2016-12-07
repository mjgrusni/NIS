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
import android.widget.Toast;

public class AddRecord extends Activity implements OnClickListener {

	private Button btn_addrecord, btn_scan;
	private EditText txtpname, txttopprice, txtbotprice, txtpid;
	DatabaseHelper db;
	ProductModel pm;
	
	public Button.OnClickListener mScan = new Button.OnClickListener() {
	    public void onClick(View v) {
	        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
	        intent.putExtra("com.google.zxing.client.android.SCAN_MODE", "QR_CODE_MODE");
	        startActivityForResult(intent, 0);
	    }
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addrecord);

		txtpname = (EditText) findViewById(R.id.txtpname);
		txttopprice = (EditText) findViewById(R.id.txttopprice);
		txtbotprice = (EditText) findViewById(R.id.txttopprice);
		btn_addrecord = (Button) findViewById(R.id.btn_addrecord);
		btn_scan = (Button) findViewById(R.id.btn_addscan);

		txtpid = (EditText) findViewById(R.id.txtpid);
		btn_addrecord.setOnClickListener(this);
		btn_scan.setOnClickListener(mScan);
		

	}
	

	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
	    if (requestCode == 0) {
	        if (resultCode == RESULT_OK) {
	            String contents = intent.getStringExtra("SCAN_RESULT");
	            String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
	            txtpid.setText("");
	            txtpid.setText(contents);
	        } else if (resultCode == RESULT_CANCELED) {
	            // Handle cancel
	        }
	    }
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_addrecord:

			if (txtpname.getText().toString().equals("")
					|| txttopprice.getText().toString().equals("")) {
				Toast.makeText(AddRecord.this, "Please add values..",
						Toast.LENGTH_LONG).show();
			} else {

				db = new DatabaseHelper(getApplicationContext());
				db.getWritableDatabase();
				pm = new ProductModel();
				pm.idno = (txtpid.getText().toString());
				pm.productname = txtpname.getText().toString();
				pm.productprice = txttopprice.getText().toString();

				/*Log.i("idno,productname,productprice", "" + pm.idno + ""
						+ pm.productname + "" + pm.productprice);*/
				db.addProduct(pm);
				Toast.makeText(AddRecord.this, "Record Added successfully.",
						Toast.LENGTH_LONG).show();
			}
			break;

		default:
			break;
		}

	}
}
