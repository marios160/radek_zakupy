package pl.octicos.zakupy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import pl.octicos.zakupy.R.id;

public class TwoiUzytkownicy extends Activity {

	private ListView listaUzytkownikow;
	private ListaUzytkownikowAdapter adapter;
	private int uzytkownikIndex;
	Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_twoi_uzytkownicy);
		context = this;
		aktualizujListe();
		registerForContextMenu(listaUzytkownikow);
		listaUzytkownikow.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				TextView email = (TextView) view.findViewById(R.id.uzytkownikText);
				Uzytkownik uzytkownik = (Uzytkownik) parent.getItemAtPosition(position);
				if (uzytkownik.isPolaczony()) {
					email.setTextColor(Color.BLACK);
					uzytkownik.setPolaczony(false);
					Toast.makeText(context, "Roz³¹czono z u¿ytkownikiem "+uzytkownik.getEmail(),Toast.LENGTH_SHORT).show();
				} else {
					for (Uzytkownik el : Dane.listaUzytkownikow) {
						if(el.isPolaczony()){
							el.setPolaczony(false);
							Toast.makeText(context, "Roz³¹czono z u¿ytkownikiem "+el.getEmail(),Toast.LENGTH_SHORT).show();
							break;
						}		
					}
					aktualizujListe();
					email.setTextColor(Color.GREEN);
					uzytkownik.setPolaczony(true);
					Toast.makeText(context, "Po³¹czono z u¿ytkownikiem "+uzytkownik.getEmail(),Toast.LENGTH_SHORT).show();
				}
				// TODO ³¹czenie sie z uzytkownikiem
				
			}
		});
		
		
	}
	
	@Override
	protected void onResume() {
		aktualizujListe();
		super.onResume();
	}
	
	public void aktualizujListe(){
		adapter = new ListaUzytkownikowAdapter(this, R.layout.uzytkownicy, Dane.listaUzytkownikow);
        listaUzytkownikow = (ListView) findViewById(R.id.listaTwoiUzytkownicy);
        listaUzytkownikow.setAdapter(adapter);
	}
	
	public void dodaj(View view) {
		EditText uzytkownik = (EditText) findViewById(R.id.uzytkownicyField);
		String email = uzytkownik.getText().toString();
		uzytkownik.setText(null);
		System.out.println(email);
		//TODO zrobic uwierzytelnienie
		
		Dane.listaUzytkownikow.add(new Uzytkownik(email, false));
		Toast.makeText(this, "Dodano u¿ytkownika " + email, Toast.LENGTH_SHORT).show();

		aktualizujListe();

	}

	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		if (v.getId() == R.id.listaTwoiUzytkownicy) {
			ListView lv = (ListView) v;
			AdapterView.AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) menuInfo;
			uzytkownikIndex = Dane.listaUzytkownikow.indexOf((Uzytkownik) lv.getItemAtPosition(acmi.position));

		}
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.menu_kontekstowe_uzytkownik, menu);

	}

	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.usunUzytkowika: {
			usunUzytkownika(item.getItemId());
		}
			break;
		default:

		}
		return true;
	}

	public void usunUzytkownika(int id) {
		
		Uzytkownik uzytkownik = Dane.listaUzytkownikow.get(uzytkownikIndex);
		Toast.makeText(this, "Usuniêto u¿ytkownika "+uzytkownik.getEmail(),Toast.LENGTH_SHORT).show();
		Dane.listaUzytkownikow.remove(uzytkownik);
		aktualizujListe();
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.twoi_uzytkownicy, menu);
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
