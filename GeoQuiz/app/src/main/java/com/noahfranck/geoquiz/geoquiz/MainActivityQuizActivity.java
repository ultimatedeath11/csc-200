package com.noahfranck.geoquiz.geoquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivityQuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Toast mToast;

    public MainActivityQuizActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);

        mTrueButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //true button pressed

                System.out.println("Gravity set");
                mToast = Toast.makeText(MainActivityQuizActivity.this,R.string.incorrect_answer,Toast.LENGTH_SHORT);
                mToast.setGravity(Gravity.TOP | Gravity.LEFT,410,200);
                mToast.show();
            }
        });
        mFalseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //false button pressed
                mToast = Toast.makeText(MainActivityQuizActivity.this,R.string.correct_answer,Toast.LENGTH_SHORT);
                mToast.setGravity(Gravity.TOP | Gravity.LEFT,410,200);
                mToast.show();
            }
        });
    }
}
