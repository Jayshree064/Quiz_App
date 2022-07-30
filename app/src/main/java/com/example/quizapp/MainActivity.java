package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView questionTV,questionNumberTV;
    private Button option1Btn,option2Btn,option3Btn,option4Btn;
    private ArrayList<QuizModel> quizModelArrayList;
    Random random;
    int currentScore = 0,questionsAttempted = 1,currentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTV = findViewById(R.id.idTVQuestion);
        questionNumberTV = findViewById(R.id.idTVQuestionAttempted);
        option1Btn = findViewById(R.id.idBtnOption1);
        option2Btn = findViewById(R.id.idBtnOption2);
        option3Btn = findViewById(R.id.idBtnOption3);
        option4Btn = findViewById(R.id.idBtnOption4);
        quizModelArrayList = new ArrayList<>();
        random = new Random();
        getQuestion(quizModelArrayList);
        currentPos = random.nextInt(quizModelArrayList.size());
        setDataToViews(currentPos);

        option1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quizModelArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option1Btn.getText().toString().trim().toLowerCase())){
                    currentScore++;
                }
                questionsAttempted++;
                currentPos = random.nextInt(quizModelArrayList.size());
                setDataToViews(currentPos);
            }
        });

        option2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quizModelArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option2Btn.getText().toString().trim().toLowerCase())){
                    currentScore++;
                }
                questionsAttempted++;
                currentPos = random.nextInt(quizModelArrayList.size());
                setDataToViews(currentPos);
            }
        });

        option3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quizModelArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option3Btn.getText().toString().trim().toLowerCase())){
                    currentScore++;
                }
                questionsAttempted++;
                currentPos = random.nextInt(quizModelArrayList.size());
                setDataToViews(currentPos);
            }
        });

        option4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quizModelArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option4Btn.getText().toString().trim().toLowerCase())){
                    currentScore++;
                }
                questionsAttempted++;
                currentPos = random.nextInt(quizModelArrayList.size());
                setDataToViews(currentPos);
            }
        });
    }

    private void getQuestion(ArrayList<QuizModel> quizModelArrayList) {
        quizModelArrayList.add(new QuizModel("Android is - ",
                "an operating system",
                "an android app",
                "a web server",
                "None of the above","an operating system"));
        quizModelArrayList.add(new QuizModel("For which of the following Android is mainly developed? ",
                "Servers",
                "Laptops",
                "Computers / Desktops",
                "Mobile phones / Tablets","Mobile phones / Tablets"));
        quizModelArrayList.add(new QuizModel("Which of the following is the first mobile phone released that ran the Android OS? ",
                "HTC Hero",
                "Google gPhone",
                "T - Mobile G1",
                "None of the above"," T - Mobile G1"));
        quizModelArrayList.add(new QuizModel("Which of the following converts Java byte code into Dalvik byte code? ? ",
                "Dalvik converter",
                "Dex compiler",
                "Mobile interpretive compiler (MIC)",
                "None of the above","Dex compiler"));
        quizModelArrayList.add(new QuizModel("What is an activity in android ? ",
                "android class",
                "android package",
                "A single screen in an application with supporting java code",
                "None of the above","A single screen in an application with supporting java code"));
        quizModelArrayList.add(new QuizModel("How can we kill an activity in android?",
                "Using finish() method",
                "Using finishActivity(int requestCode)",
                "Both (a) and (b)",
                "Neither (a) nor (b)",
                "Both (a) and (b)"));
        quizModelArrayList.add(new QuizModel(" In which of the following tab an error is shown?",
                "CPU",
                "Memory",
                "ADB Logs",
                "Logcat",
                "Logcat"));
        quizModelArrayList.add(new QuizModel(" Which of the following is the name of the Android version 1.5?",
                "Eclair",
                "Froyo",
                "Cupcake",
                "Donut",
                "Cupcake"));
        quizModelArrayList.add(new QuizModel("Which of the following is the built-in database of Android?",
                "SQLite",
                "MySQL",
                "Oracle",
                "None of the above",
                "SQLite"));
        quizModelArrayList.add(new QuizModel("Which of the following android library provides access to the database?",
                "android.content",
                "android.database",
                "android.api",
                "None of the above",
                "android.database"));
    }

    private void setDataToViews(int currentPos){
        questionNumberTV.setText("Question Attempted :" + questionsAttempted + " /10");
        if(questionsAttempted == 10){
            showBottomSheet();
        }
        else {
            questionTV.setText(quizModelArrayList.get(currentPos).getQuestion());
            option1Btn.setText(quizModelArrayList.get(currentPos).getOption1());
            option2Btn.setText(quizModelArrayList.get(currentPos).getOption2());
            option3Btn.setText(quizModelArrayList.get(currentPos).getOption3());
            option4Btn.setText(quizModelArrayList.get(currentPos).getOption4());
        }

    }

    private void showBottomSheet(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
        View bottomSheetView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.score_bottom_sheet,(LinearLayout)findViewById(R.id.idLLScore));
        TextView scoreTV = bottomSheetView.findViewById(R.id.idTVScore);
        Button restartQuizBtn = bottomSheetView.findViewById(R.id.idBtnRestart);
        scoreTV.setText("Your Score is \n" + currentScore + "/10");
        restartQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPos = random.nextInt(quizModelArrayList.size());
                setDataToViews(currentPos);
                questionsAttempted = 1;
                currentScore = 0;
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

}