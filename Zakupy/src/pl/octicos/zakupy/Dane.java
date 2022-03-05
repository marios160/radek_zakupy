package pl.octicos.zakupy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.os.Environment;

public class Dane {
	public static List<Produkt> listaProduktow;
	public static List<Uzytkownik> listaUzytkownikow;
	public static File folder;

	public Dane() {
		
		this.listaProduktow = new ArrayList<Produkt>();
		this.listaUzytkownikow = new ArrayList<Uzytkownik>();
		File f = Environment.getExternalStorageDirectory();
        folder = new File(Environment.getExternalStorageDirectory() + File.separator + "Zakupy");
        if (!folder.exists()) {
            folder.mkdir();
        }
	}
	
	
	

}
