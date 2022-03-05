package pl.octicos.zakupy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import pl.octicos.zakupy.R.id;

public class ListaZakupow extends Activity {

	private ListaZakupowAdapter listaZakupowAdapter;
	private ListView listaZakupow;
	private int produktIndex;
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_zakupow);
		aktualizujListe();
		context = this;
		registerForContextMenu(listaZakupow);
		listaZakupow.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				TextView nazwa = (TextView) view.findViewById(R.id.produktText);
				TextView ilosc = (TextView) view.findViewById(R.id.iloscText);
				Produkt produkt = (Produkt) parent.getItemAtPosition(position);
				if (produkt.isKupiony()) {
					nazwa.setTextColor(Color.BLACK);
					ilosc.setTextColor(Color.BLACK);
					Dane.listaProduktow.remove(produkt);
					Dane.listaProduktow.add(0, produkt);
					produkt.setKupiony(false);
					Toast.makeText(context, "Wycofano produkt "+produkt.getNazwa()+" z listy zakupow",Toast.LENGTH_SHORT).show();

				} else {
					nazwa.setTextColor(Color.GRAY);
					ilosc.setTextColor(Color.GRAY);
					Dane.listaProduktow.remove(produkt);
					Dane.listaProduktow.add(Dane.listaProduktow.size(), produkt);
					produkt.setKupiony(true);
					Toast.makeText(context, "Produkt "+produkt.getNazwa()+" zosta³ kupiony",Toast.LENGTH_SHORT).show();
				}

				aktualizujListe();
			}

		});

	}
	
	public void aktualizujListe(){
		List<Produkt> lista = new ArrayList<Produkt>(Dane.listaProduktow);
		Collections.reverse(lista);
		listaZakupow = (ListView) findViewById(R.id.listaZakupowList);
		listaZakupowAdapter = new ListaZakupowAdapter(this, R.layout.produkt, Dane.listaProduktow);
		listaZakupow.setAdapter(listaZakupowAdapter);
	}
	
	@Override
	protected void onResume() {
		aktualizujListe();
		super.onResume();
	}

	public void dodaj(View view) {
		EditText nazwa = (EditText) findViewById(id.podajProduktField);
		EditText ilosc = (EditText) findViewById(id.iloscField);
		Produkt produkt = new Produkt(nazwa.getText().toString(), ilosc.getText().toString(), false);
		Dane.listaProduktow.add(produkt);
		nazwa.setText(null);
		ilosc.setText(null);
		Toast.makeText(this, "Dodano produkt " + produkt.getNazwa() + " - " + produkt.getIlosc(), Toast.LENGTH_SHORT)
				.show();

		aktualizujListe();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_zakupow, menu);
		return true;
	}

	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		if (v.getId() == R.id.listaZakupowList) {
			ListView lv = (ListView) v;
			AdapterView.AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) menuInfo;
			produktIndex = Dane.listaProduktow.indexOf((Produkt) lv.getItemAtPosition(acmi.position));

		}
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.menu_kontekstowe_produkt, menu);

	}

	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.edytujProdukt: {
			edytujProdukt(item.getItemId());
		}
			break;
		case R.id.usunProdukt: {
			usunProdukt(item.getItemId());
		}
			break;
		default:

		}
		return true;
	}

	public void edytujProdukt(int id) {
		
		
		Intent intent = new Intent(this, EdytujProdukt.class);
		intent.putExtra("index", produktIndex);
		startActivity(intent);
	}

	void usunProdukt(int id) {
		
		Produkt produkt = Dane.listaProduktow.get(produktIndex);
		Toast.makeText(this, "Usuniêto produkt "+produkt.getNazwa()+" - "+produkt.getIlosc(),Toast.LENGTH_SHORT).show();
		Dane.listaProduktow.remove(produktIndex);
		aktualizujListe();
	}
	
	public void wyczyscListe(View view) {
		Dane.listaProduktow.clear();
		aktualizujListe();
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
