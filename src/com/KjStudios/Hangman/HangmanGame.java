package com.KjStudios.Hangman;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;


public class HangmanGame extends Activity{

	public class MyOnItemSelectedListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			HandleEvents(parent.getItemAtPosition(pos).toString().charAt(0));

		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// Do nothing.
		}
	}
	final Random m_Random = new Random();
	public final int MAXTRYS = 6;
	public boolean mb_Correct;
	public char mc_Guess[] = null;
	public char mc_Letter;
	public int mi_Hungstate;
	public int mi_Size;
	public int mi_Subscript;
	public int mi_Word;
	public String ms_Copy = null;
	public String ms_Guessed;
	//
	public String ms_Wordlist[] = null;
	//
	public ImageView picture;
	public TextView word;
	public void Draw() {
		picture = (ImageView) findViewById(R.id.hungstate);
		picture.setBackgroundResource(R.drawable.hung0);
		
		word.setText(String.copyValueOf(mc_Guess));
		word = (TextView) findViewById(R.id.guessed);
		word.setText(ms_Guessed);
		if (mi_Hungstate == 8) {
			picture.setBackgroundResource(R.drawable.hung8);
		} else if (mi_Hungstate == 7) {
			picture.setBackgroundResource(R.drawable.hung7);
		} else if (mi_Hungstate == 6) {
			picture.setBackgroundResource(R.drawable.hung6);
		} else if (mi_Hungstate == 5) {
			picture.setBackgroundResource(R.drawable.hung5);
		} else if (mi_Hungstate == 4) {
			picture.setBackgroundResource(R.drawable.hung4);
		} else if (mi_Hungstate == 3) {
			picture.setBackgroundResource(R.drawable.hung3);
		} else if (mi_Hungstate == 2) {
			picture.setBackgroundResource(R.drawable.hung2);
		} else if (mi_Hungstate == 1) {
			picture.setBackgroundResource(R.drawable.hung1);
		}
	}
	public void HandleEvents(char letter) {
		// We will use only Upper case letters
		mc_Letter = Character.toUpperCase(letter);
		ms_Guessed+=mc_Letter;
		// Loop through the word
		for (mi_Subscript = 0; mi_Subscript < mi_Size; mi_Subscript++) {

			// if the guess is good tell the user and update Guess
			if (ms_Copy.charAt(mi_Subscript) == mc_Letter) {
				mc_Guess[mi_Subscript] = mc_Letter;
				mb_Correct = true;
				//Toast.makeText(this, "Good Guess", Toast.LENGTH_SHORT).show();

				// If guess equals the word they won so exit
				if (ms_Wordlist[mi_Word].equalsIgnoreCase(mc_Guess.toString())) {
					//Toast.makeText(this, "You Won!", Toast.LENGTH_SHORT).show();
					Draw();
					Init();
					// Call back to next word?
					return;
				}
			}
		}
		// if not correct guess
		if (!mb_Correct) {
			//Toast.makeText(this, "Bad Guess", Toast.LENGTH_SHORT).show();
			if (mi_Hungstate++ > MAXTRYS) {
				//Toast.makeText(this, "You Lost", Toast.LENGTH_SHORT).show();
				Draw();
				Init();
			}
		}
		//Toast.makeText(this, "Your Guess: " + (String.copyValueOf(mc_Guess)),
		//		Toast.LENGTH_SHORT).show();
		// m_word.setText(String.copyValueOf(mc_Guess));
		mb_Correct = false; // reset Correct
		Draw();
	}
	public void Init() {
		
		ms_Wordlist = "BAD,HAPPY,CAT,SAW,FAMILY,JACKET,SEVEN,HAT,CORD,GAME,COMPUTER,QUARTER,TEN,BLUE,DRIVE,MOUNTIAN,ROBOT,PLUDGER,BROOM,HOUSE,WOODS,TREE,PUTTER,STOOL,GLASSES,IDEA,ORANGE,SOAP,BEND,MOP,BUCKET,HUMAN,DONKEY,BOTTOM,SHOE,SANDEL,BALL,CIRCLE,SQUARE,EYE,EAR,TOE,PROJECT,SOCKET,GATORADE,WATER,MILK,JUICE,DEATH,LIFE".split(",").clone();
		ms_Guessed=null;
		mi_Word = m_Random.nextInt(ms_Wordlist.length - 1);
		ms_Copy = ms_Wordlist[mi_Word];
		mi_Size = ms_Copy.length(); // Get the word's size
		//
		// ms_Guess=ms_Copy;
		mc_Guess = new char[mi_Size];
		// Create a null terminated string to represent the word as the
		// player knows it.
		for (mi_Subscript = 0; mi_Subscript < mi_Size; mi_Subscript++) {
			mc_Guess[mi_Subscript] = '-';
			// ms_Guess.replace(ms_Copy.charAt(mi_Subscript), '0');
		}
		// Toast.makeText(this, "Word:"+ms_Guess,Toast.LENGTH_SHORT).show();
		//Toast.makeText(this, "Word:" + ms_Copy, Toast.LENGTH_SHORT).show();
		// Default Values
		mi_Hungstate = 0;
		//
		picture = (ImageView) findViewById(R.id.hungstate);
		word = (TextView) findViewById(R.id.guess);
	}
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scene);
		Init();
		Spinner spinner = (Spinner) findViewById(R.id.spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.letterbank, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		new MenuInflater(this).inflate(R.xml.uimenu, menu);

		return(super.onCreateOptionsMenu(menu));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId()==R.id.menu) {
			startActivity(new Intent(this, HangmanActivity.class));
			
			return(true);
		}
		else if (item.getItemId()==R.id.pref) {
			//startActivity(new Intent(this, EditPreferences.class));
		
			return(true);
		}else if (item.getItemId()==R.id.quit) {
			//super.onDestroy();
			return(true);
		}

		return(super.onOptionsItemSelected(item));
	}
	
}