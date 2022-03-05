package pl.octicos.zakupy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Dane dane = new Dane();


		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void listaZakupow(View view) {
		Intent intent = new Intent(this, ListaZakupow.class);
		startActivity(intent);
	}
	public void twoiUzytkownicy(View view) {
		Intent intent = new Intent(this, TwoiUzytkownicy.class);
		startActivity(intent);
	}
	
	public void paragony(View view) {
		Intent intent = new Intent(this, Paragony.class);
		startActivity(intent);
	}
	
	public void zakoncz(View view) {
		finish();
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
