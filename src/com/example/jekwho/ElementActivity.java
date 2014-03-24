package com.example.jekwho;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.Time;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class ElementActivity extends Activity {
	ArrayList <MembroClass> Tab_Membros = new ArrayList<MembroClass>();
	String link = "www.jeknowlegde.com";
	long timer;
	//MembroClass membro = (MembroClass) getIntent().getSerializableExtra("Membro");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		this.setContentView(R.layout.activity_element);

		// CRIA A BASE DE DADOS
		inicializa_Tab();
		// RECEBE O INTENT E O VALOR
		 Intent intent = getIntent();
		final int membro = intent.getIntExtra("O", -1);
		//final View controlsView = findViewById(R.id.fullscreen_content_controls);

		final Button button_saber_mais = (Button) findViewById(R.id.sabermais_Button);
		final Button try_again = (Button) findViewById(R.id.tryagain_Button);


		final TextView contentView = (TextView) findViewById(R.id.textView1);

		final ImageView imagem_membro = (ImageView) findViewById(R.id.imageView1);


		if(membro == -1){

			contentView.setText("NAO EXISTE");
			button_saber_mais.setOnTouchListener(new View.OnTouchListener() {


				@Override
				public boolean onTouch(View v, MotionEvent event) {
					  if(event.getAction() == MotionEvent.ACTION_DOWN){
					  timer = System.currentTimeMillis()/1000 + 10;

						Log.i("TEMPO",String.valueOf(timer));  
					       }
					       else if(event.getAction() == MotionEvent.ACTION_UP){
					         Log.i("TEMPO,TIMER",String.valueOf(System.currentTimeMillis()/1000));
					    	if(System.currentTimeMillis()/1000 >= timer) {
					    		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://chupam.us"));
				        		startActivity(browserIntent);		

					    	}
					       }


						return false;
					     }	

					   });




		}
		else{

			String link = Tab_Membros.get(membro).getLink();
			contentView.setText(Tab_Membros.get(membro).getNome());
			imagem_membro.setImageResource((Integer) Tab_Membros.get(membro).getObject());
			button_saber_mais.setOnClickListener(new View.OnClickListener() {

	        	public void onClick(View v) {

	        		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Tab_Membros.get(membro).getLink()));
	        		startActivity(browserIntent);		
	            	// Perform action on click
	            }
	        });

			//imagem_membro.setImageResource(R.drawable.i0);
		}



		try_again.setOnClickListener(new OnClickListener(){

			public void onClick(View v){

				Intent intent = new Intent(ElementActivity.this, TransitionActivity.class);
				startActivity(intent);

			}
		});


	}
		// troca o abc_ic_menu pelo resorce drawable do user em questão
		// this activity.

	private OnTouchListener Listener(OnTouchListener onTouchListener) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		// Trigger the initial hide() shortly after the activity has been
		// created, to briefly hint to the user that UI controls
		// are available.

	}



	void inicializa_Tab(){



		boolean [] membro = {true,true,false,false};
		Tab_Membros.add(new MembroClass("José Marcelino",membro,0,"https://github.com/josemarcelino",R.drawable.ic_launcher));
		boolean [] membro_2 = {true,false,true,false};
		Tab_Membros.add(new MembroClass("Eduardo Preto",membro_2,1,"https://www.google.pt",R.drawable.ic_launcher));
		boolean [] membro_3 = {true,false,false,false};
		Tab_Membros.add(new MembroClass("David Gomes",membro_3,2,"https://www.davidgom.es",R.drawable.ic_launcher));
		boolean [] membro_4 = {true,false,false,true};
		Tab_Membros.add(new MembroClass("Bruno Conceição",membro_4,3,"https://www.bruno.com",R.drawable.ic_launcher));


	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(ElementActivity.this, MainActivity.class);
		startActivity(intent);
	}



}