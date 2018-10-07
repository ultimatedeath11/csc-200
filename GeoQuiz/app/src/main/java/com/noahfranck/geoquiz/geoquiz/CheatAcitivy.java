package com.noahfranck.geoquiz.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatAcitivy extends AppCompatActivity {
	private static final String EXTRA_ANSWER_IS_TRUE = "com.noahfranck.android.geoquiz.answer_is_true";
	private static final String EXTRA_ANSWER_SHOWN = "com.noahfranck.android.geoquiz.answer_shown";
	private static final String TAG = "CheatActivity";

	private boolean mAnswerIsTrue;
	private TextView mAnswerTextView;
	private Button mShowAnswerButton;

	public static Intent newIntent(Context packageContext, boolean answerIsTrue){
		Intent intent = new Intent(packageContext, CheatAcitivy.class);
		intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
		return intent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);

		mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);

		mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
		mShowAnswerButton = (Button) findViewById(R.id.show_answer_button);

		mShowAnswerButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				if(mAnswerIsTrue){
					mAnswerTextView.setText(R.string.true_button);
				}else{
					mAnswerTextView.setText(R.string.false_button);
				}
				setAnswerShownResult(true);
			}
		});
	}

	private void setAnswerShownResult(Boolean isAnswerShown){
		Intent data = new Intent();
		data.putExtra(EXTRA_ANSWER_SHOWN,isAnswerShown);
		setResult(RESULT_OK,data);
	}

	@Override
	public void onStart(){
		super.onStart();
		Log.d(TAG,"onStart Called");
	}

	@Override
	public void onResume(){
		super.onResume();
		Log.d(TAG,"onResume Called");
	}

	@Override
	public void onPause(){
		super.onPause();
		Log.d(TAG,"onPaused Called");
	}

	@Override
	public void onDestroy(){
		super.onDestroy();
		Log.d(TAG,"onDestroy Called");
	}

	@Override
	public void onStop(){
		super.onStop();
		Log.d(TAG,"onStop Called");
	}
}
