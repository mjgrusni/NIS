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

public class AddUpdateValues extends Activity implements OnClickListener {
	private Button btn_updaterecord;
	private EditText txtpname, txtpprice;
	DatabaseHelper db;
	ProductModel pm;
	Intent i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addupdatevalues);

		i = getIntent();

		txtpname = (EditText) findViewById(R.id.txt_udatepname);
		txtpprice = (EditText) findViewById(R.id.txt_udatepprice);

		txtpname.setText(i.getExtras().getString("name"));
		txtpprice.setText(i.getExtras().getString("price"));
		btn_updaterecord = (Button) findViewById(R.id.btn_updaterecord);
		btn_updaterecord.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_updaterecord:
			if (txtpname.getText().toString().equals("")
					|| txtpprice.getText().toString().equals("")) {
				Toast.makeText(AddUpdateValues.this, "Please add values..",
						Toast.LENGTH_LONG).show();
			} else {

				db = new DatabaseHelper(getApplicationContext());
				db.getWritableDatabase();
				pm = new ProductModel();
				pm.productname = txtpname.getText().toString();
				pm.productprice = txtpprice.getText().toString();
				pm.idno = i.getExtras().getString("id");

				Log.i(">>>>>productid<<<<<", "" + i.getExtras().getString("id"));
				db.updateProduct(pm);
				Toast.makeText(AddUpdateValues.this,
						"Record Update successfully.", Toast.LENGTH_LONG)
						.show();

				db.close();
				super.onResume();

			}
			break;
		}

	}

}
