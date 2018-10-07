package com.noahfranck.geoquiz.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatAcitivy extends AppCompatActivity {
	private static final String EXTRA_ANSWER_IS_TRUE = "com.noahfranck.android.geoquiz.answer_is_true";

	private boolean mAnswerIsTrue;
	private TextView mAnswerTextView;
	private Button mShowAnswerButton;

	public static Intent newIntent(Context packageContext, boolean answerIsTrue){
		Intent intent = new Intent(packageContext, MainActivityQuizActivity.class);
		intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
		return intent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);

		mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);

		mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
		mShowAnswerButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				if(mAnswerIsTrue){
					mAnswerTextView.setText(R.string.true_button);
				}else{
					mAnswerTextView.setText(R.string.false_button);
				}
			}
		});
	}
}
