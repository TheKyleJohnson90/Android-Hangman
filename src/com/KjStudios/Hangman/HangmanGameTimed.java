package com.KjStudios.Hangman;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class HangmanGameTimed extends HangmanGame {

	// CountDownTimer class
	public class GameTimer extends CountDownTimer {

		public GameTimer(long startTime, long interval) {
			super(startTime, interval);
			time = (TextView) findViewById(R.id.guess);
			timeLeft = (TextView) findViewById(R.id.guess);
	}

		@Override
		public void onFinish() {
			timeLeft.setText("Time's up!");
			time.setText("Time Elapsed: " + String.valueOf(startTime));
		}

		@Override
		public void onTick(long millisUntilFinished) {
			timeLeft.setText("Time remain:" + millisUntilFinished);
			timeElapsed = startTime - millisUntilFinished;
			time.setText("Time Elapsed: " + String.valueOf(timeElapsed));
		}
	}
	private GameTimer countDownTimer;
	private final long interval = 1000;
	private final long startTime = 60000;
	private TextView time;
	private long timeElapsed;
	private TextView timeLeft;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
         setContentView(R.layout.mainmenu);

         timeLeft = (TextView) this.findViewById(R.id.timeleft);
         time = (TextView) this.findViewById(R.id.time);
         countDownTimer = new GameTimer(startTime, interval);
        timeLeft.setText(timeLeft.getText() + String.valueOf(startTime));
        countDownTimer.start();

	}
	
}