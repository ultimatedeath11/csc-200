package com.noahfranck.geoquiz.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CheatAcitivy extends AppCompatActivity {
	private static final String EXTRA_ANSWER_IS_TRUE = "com.noahfranck.android.geoquiz.answer_is_true";

	public static Intent newIntent(Context packageContext, boolean answerIsTrue){
		Intent intent = new Intent(packageContext, MainActivityQuizActivity.class);
		intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
		return intent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);
	}
}
