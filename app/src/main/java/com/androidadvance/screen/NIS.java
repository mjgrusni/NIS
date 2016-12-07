package com.androidadvance.screen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NIS extends Activity implements
		OnClickListener {

	private Button btn_add, btn_view, btn_lookup;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		btn_add = (Button) findViewById(R.id.btn_add);
		btn_view = (Button) findViewById(R.id.btn_view);
		btn_lookup = (Button) findViewById(R.id.btn_lookup);
		btn_add.setOnClickListener(this);
		btn_view.setOnClickListener(this);
		btn_lookup.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_add:
			Intent addIntent = new Intent(NIS.this,
					AddRecord.class);
			startActivity(addIntent);
			break;

		case R.id.btn_view:
			Intent viewIntent = new Intent(NIS.this,
					ViewRecord.class);
			startActivity(viewIntent);
			break;
			
		case R.id.btn_lookup:
			Intent lookupIntent = new Intent(NIS.this,
					LookupRecord.class);
			startActivity(lookupIntent);
			break;
		default:
			break;
		}

	}
}