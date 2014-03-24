package com.example.jekwho;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);

    //Remove title bar
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);

    //Remove notification bar
    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

   //set content view AFTER ABOVE sequence (to avoid crash)
    this.setContentView(R.layout.activity_main); 

 

    final Button start_game = (Button) findViewById(R.id.start_button_1);
    start_game.setOnClickListener(new View.OnClickListener() {
       
    	public void onClick(View v) {
    		// ON CLICK MANDA PARA O QUESTION
        	Intent intent = new Intent(MainActivity.this,TransitionActivity.class);	
        	 startActivity(intent);
        	// Perform action on click
        }
    });
    
    

    }

@Override
public void onBackPressed() {
}
}
