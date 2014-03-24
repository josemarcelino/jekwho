package com.example.jekwho;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class TransitionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


		this.setContentView(R.layout.activity_transition);


        final Button start_question_button = (Button) findViewById(R.id.next_button);
        start_question_button.setOnClickListener(new View.OnClickListener() {
           
        	public void onClick(View v) {
        		// ON CLICK MANDA PARA O QUESTION
            	Intent intent = new Intent(TransitionActivity.this,QuestionActivity.class);	
            	 startActivity(intent);
            	// Perform action on click
            }
        });

	}





}
