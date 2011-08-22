package com.KjStudios.Hangman;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
public class FileIO extends Activity{
	//Need some sort of dialog?
	
	public String ReadSettings(Context context) {

		FileInputStream fIn = null;

		InputStreamReader isr = null;

		char[] inputBuffer = new char[255];

		String data = null;

		try {

			fIn = openFileInput("settings.dat");

			isr = new InputStreamReader(fIn);

			isr.read(inputBuffer);

			data = new String(inputBuffer);

			Toast.makeText(context, "Settings read", Toast.LENGTH_SHORT).show();

		}

		catch (Exception e) {

			e.printStackTrace();

			Toast.makeText(context, "Settings not read", Toast.LENGTH_SHORT)
					.show();

		}

		finally {

			try {

				isr.close();

				fIn.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

		return data;

	}
	//Actual Functions
	public void WriteSettings(Context context, String data) {

		FileOutputStream fOut = null;

		OutputStreamWriter osw = null;

		try {

			fOut = openFileOutput("settings.dat", Context.MODE_PRIVATE);

			osw = new OutputStreamWriter(fOut);

			osw.write(data);

			osw.flush();

			Toast.makeText(context, "Settings saved", Toast.LENGTH_SHORT)
					.show();

		}

		catch (Exception e) {

			e.printStackTrace();

			Toast.makeText(context, "Settings not saved", Toast.LENGTH_SHORT)
					.show();

		}

		finally {

			try {

				osw.close();

				fOut.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

	}

}