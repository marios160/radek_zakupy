package pl.octicos.zakupy;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.Toast;

public class Galeria extends Activity {

	private Cursor cursor;
	private int columnIndex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_galeria);
		Gallery g = (Gallery) findViewById(R.id.gallery1);
		String[] projection = { MediaStore.Images.ImageColumns._ID, MediaStore.Images.Thumbnails.IMAGE_ID,
				MediaStore.Images.Thumbnails.KIND };
		// Create the cursor pointing to the SDCard
		String selection = MediaStore.Images.Thumbnails.KIND + "=" + MediaStore.Images.Thumbnails.MINI_KIND;
		cursor = managedQuery(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, projection, selection, null, null);
		// Get the column index of the image ID
		columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
		g.setAdapter(new ImageAdapter(this));

		g.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View v, int position, long id) {
				Toast.makeText(Galeria.this, "" + position, Toast.LENGTH_SHORT).show();
			}
		});

	}
}
