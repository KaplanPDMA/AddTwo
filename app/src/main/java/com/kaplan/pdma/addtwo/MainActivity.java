package com.kaplan.pdma.addtwo;

import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

//    public enum Operation { Add, Minus }

    private int n1, n2, answer, operation, numCorrect, numWrong;
    //    Operation operation;
    private TextView textViewNum1, textViewNum2, textViewCorrect, textViewWrong;
    private EditText editTextAnswer;
    private RelativeLayout bg;
    private ImageView imageViewNext, imageViewOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewNum1 = (TextView) findViewById(R.id.textViewNum1);
        textViewNum2 = (TextView) findViewById(R.id.textViewNum2);
        textViewCorrect = (TextView) findViewById(R.id.textViewCorrect);
        textViewWrong = (TextView) findViewById(R.id.textViewWrong);
        editTextAnswer = (EditText) findViewById(R.id.editTextAnswer);
        bg = (RelativeLayout) findViewById(R.id.activity_main);
        imageViewNext = (ImageView) findViewById(R.id.imageViewNext);
        imageViewOperation = (ImageView) findViewById(R.id.imageViewOperation);

        numCorrect = 0;
        numWrong = 0;
        textViewCorrect.setText(null);
        textViewWrong.setText(null);

        generateQuestion();
        setQuestion();

        editTextAnswer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int n = -1;
                try {
                    n = Integer.parseInt(editTextAnswer.getText().toString());

                    if (n == answer) {
                        bg.setBackgroundColor(Color.parseColor("#C8E6C9"));
                        imageViewNext.setVisibility(View.VISIBLE);

                        numCorrect++;
                        textViewCorrect.setText("" + numCorrect);
                    } else {
                        bg.setBackgroundColor(Color.parseColor("#FFCDD2"));

                        numWrong++;
                        textViewWrong.setText("" + numWrong);
                    }
                } catch (NumberFormatException e) {

                }

            }
        });

        imageViewNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateQuestion();
                setQuestion();
                bg.setBackgroundColor(Color.WHITE);
            }
        });
    }


    private void generateQuestion() {
        Random r = new Random();
        n1 = r.nextInt(10) + 1; //generate a random integer from 1 to 10
        n2 = r.nextInt(10) + 1;
        operation = r.nextInt(2); // 0 or 1
        if (operation == 0) {
            answer = n1 + n2;
        } else {
            answer = n1 - n2;
        }
    }

    private void setQuestion() {
        textViewNum1.setText("" + n1);
        textViewNum2.setText("" + n2);
        editTextAnswer.setText("");
        if (operation == 0) {
            imageViewOperation.setImageResource(R.drawable.plus);
        } else {
            imageViewOperation.setImageResource(R.drawable.minus);
        }
        imageViewNext.setVisibility(View.INVISIBLE); //hide it
    }
}
