package com.KjStudios.Hangman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class HangmanActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		//Gird Based Menu
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainmenu);
		GridView gridview = (GridView) findViewById(R.id.mainMenu);
		gridview.setAdapter(new ImageAdapter(this));
		gridview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				//Menu Choices
				switch (position) {
				case 0:
					startActivity( (new Intent(HangmanActivity.this, HangmanGame.class)));
					break;
				case 1:
					startActivity( (new Intent(HangmanActivity.this, HangmanGameTimed.class)));
					break;
				case 2:
					startActivity( (new Intent(HangmanActivity.this, HangmanGameTwoPlayer.class)));
					//Toast.makeText(HangmanActivity.this, "Two Player Error",Toast.LENGTH_SHORT).show();
					break;
				case 3:
					Toast.makeText(HangmanActivity.this, "High Scores Error",Toast.LENGTH_SHORT).show();
					break;
				case 4:
					Toast.makeText(HangmanActivity.this, "Edit Words Error",Toast.LENGTH_SHORT).show();
					break;
				case 5:
					Toast.makeText(HangmanActivity.this, "Kill",Toast.LENGTH_SHORT).show();
	
					break;
				default:
					break;
				}
			}

		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//Options Menu
		new MenuInflater(this).inflate(R.xml.uimenu, menu);
		return(super.onCreateOptionsMenu(menu));
	}
	// GameComponnets
}