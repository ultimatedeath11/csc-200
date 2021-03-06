package com.noahfranck.geoquiz.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.*;

public class MainActivityQuizActivity extends AppCompatActivity {
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "Index";

    private static final String GRADE_INDEX = "Grade";
    private static final int REQUEST_CODE_CHEAT = 0;
    //private static final String USER_ANSWERED_INDEX = "User Answered";

    private Button mCheatButton;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreviousQuestion;
    private TextView mQuestionTextView;
    private boolean mIsCheater;
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

    private void getAverage(){
       //mAverageToast.setText(Integer.toString(mCorrectAnswers/mQuestionBank.length));
	    Toast mAverageToast = Toast.makeText(this,Double.toString((double)mCorrectAnswers / (double)mQuestionBank.length * 100), LENGTH_SHORT);
	    mAverageToast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0,100);
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
	        mTrueButton.setEnabled(false);
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
        toggleButtons();
    }
    private void checkAnswer(boolean userPressedTrue)
    {
       boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
       int messageResId = 0;

       if(mIsCheater){
           messageResId = R.string.judgment_toast;
       }else {
           if (userPressedTrue == answerIsTrue) {
               messageResId = R.string.correct_answer;
               mCorrectAnswers = mCorrectAnswers + 1;
           } else {
               messageResId = R.string.incorrect_answer;
           }
       }
       makeText(this,messageResId, LENGTH_LONG).show();
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
        mCheatButton = (Button) findViewById(R.id.cheat_button);

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
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1);
                mIsCheater = false;
	            if (mCurrentIndex == mQuestionBank.length)
		            quizComplete = true;
                mCurrentIndex = mCurrentIndex % mQuestionBank.length;
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
        mCheatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //should start new activity
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent intent = CheatAcitivy.newIntent(MainActivityQuizActivity.this,answerIsTrue);
                startActivityForResult(intent,REQUEST_CODE_CHEAT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != Activity.RESULT_OK){
            return;
        }
        if(requestCode == REQUEST_CODE_CHEAT){
            if(data == null)
                return;
        }
        mIsCheater = CheatAcitivy.wasAnswerShown(data);
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

    //TODO save the number of answers answered correct so far
	//TODO save the quiz state
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
