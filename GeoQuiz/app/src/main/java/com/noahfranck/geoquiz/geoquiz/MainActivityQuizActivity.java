package com.noahfranck.geoquiz.geoquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class
MainActivityQuizActivity extends AppCompatActivity {
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "Index";

    private Toast mAverageToast;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreviousQuestion;
    private TextView mQuestionTextView;
    private Question[] mQuestionBank = new Question[]
            {
                    new Question(R.string.question_australia, true),
                    new Question(R.string.question_ocean, true),
                    new Question(R.string.question_mideast, false),
                    new Question(R.string.question_africa, false),
                    new Question(R.string.question_americas, true),
                    new Question(R.string.question_asia, true),
            };
	private boolean quizComplete = false;
    private int mCorrectAnswers = 0;
    private int mCurrentIndex = 0;
    public MainActivityQuizActivity() {
    }
    //TODO implament the average of correct quesitons only call after answers bank is full
    private void getAverage(){
        mAverageToast.setText(mCorrectAnswers/mQuestionBank.length);
        mAverageToast.show();
    }

    private void toggleButtons()
    {
    	Log.d(TAG,"Toggle Button" + quizComplete);
        if (mTrueButton.isEnabled() == true && quizComplete == false)
        {
            mTrueButton.setEnabled(false);
            mFalseButton.setEnabled(false);
            mNextButton.setEnabled(true);
        }else
        if(mTrueButton.isEnabled() == false && quizComplete == false)
        {
            mTrueButton.setEnabled(true);
            mFalseButton.setEnabled(true);
            mNextButton.setEnabled(false);
        }
        if(quizComplete == true) {
	        mNextButton.setEnabled(true);
	        mPreviousQuestion.setEnabled(true);
	        mFalseButton.setEnabled(false);
	        mTrueButton.setEnabled(true);
	        getAverage();
        }
    }

    private void disableButton(Button n){
        n.setEnabled(false);
    }
    private void enableButton(Button n){
        n.setEnabled(true);
    }

    private void updateQuestion()
    {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
        if (mCurrentIndex == mQuestionBank.length - 1)
        	quizComplete = true;
        toggleButtons();
    }
    //TODO add their answer into the answer's bank array
    private void checkAnswer(boolean userPressedTrue)
    {
       boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
       int messageResId = 0;

       if(userPressedTrue == answerIsTrue)
       {
           messageResId = R.string.correct_answer;
           mCorrectAnswers ++;
       }else{
           messageResId = R.string.incorrect_answer;
       }

       Toast.makeText(this,messageResId,Toast.LENGTH_LONG).show();
        toggleButtons();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Log.d(TAG,"onCreate called");
        if(savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX);
        }
        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mNextButton = (Button) findViewById(R.id.next_button);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mPreviousQuestion = (Button) findViewById(R.id.previous_button);
        toggleButtons();
        updateQuestion();
		disableButton(mPreviousQuestion);
        mTrueButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkAnswer(true);

            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkAnswer(false);

            }
        });
        mNextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        mPreviousQuestion.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mCurrentIndex ==  0)
                    mCurrentIndex = mQuestionBank.length;
                mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
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
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG,"onSaveInstance");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
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
