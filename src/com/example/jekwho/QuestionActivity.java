package com.example.jekwho;

import java.util.ArrayList;
import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class QuestionActivity extends Activity {


	 ArrayList <MembroClass> Tab_Membros = new ArrayList<MembroClass>();
	 ArrayList<MembroClass> Tab_Membros_Validos = new ArrayList<MembroClass>();
	 ArrayList<QuestionClass> Tab_Perguntas = new ArrayList<QuestionClass>();



	boolean ultima_resposta ;
	int numero_respostas ;
	int maximo ; // MAX PERGUNTAS
	int i ;

	Random generator = new Random(System.currentTimeMillis());

	int randomGenerator() {
	       return (int) (generator.nextInt()*0.5);
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {



		inicializa_Tab();

		super.onCreate(savedInstanceState);
       //Remove title bar
       this.requestWindowFeature(Window.FEATURE_NO_TITLE);

       //Remove notification bar
       this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


		this.setContentView(R.layout.activity_question);

		//final View controlsView = findViewById(R.id.fullscreen_content_controls);
		final TextView contentView = (TextView) findViewById(R.id.text);

		contentView.setText(Tab_Perguntas.get(i).getPergunta());




		// Set up an instance of SystemUiHider to control the system UI for

		Button yes = (Button) findViewById(R.id.button1);	
		Button no = (Button) findViewById(R.id.button2);	


		 yes.setOnClickListener(new View.OnClickListener() {

	        	public void onClick(View v) {
	        	ultima_resposta = true;


	        	for( MembroClass c : Tab_Membros){

	        		if(c.tab_atributos[numero_respostas] == ultima_resposta) Tab_Membros_Validos.add(c);
	        		// ADICIONA A UM NOVO ARRAY OS MEMBROS CUJA RESPOSTA É VALIDA

	        	}

	        	if(verifica()){

	        		sendtoElementActivity();
	        		// SE SO EXISTIR 1 MANDAR PARA A ACTIVITY A ORDEM DO ELEMENTO
	        	}

	        	else{
	        	// TAB VALIDA É A TAB TOTAL DE MEMBROS NO CICLO SEGUINTE
	        	Tab_Membros = Tab_Membros_Validos;
	        	Tab_Membros_Validos =new ArrayList<MembroClass>();
	        	numero_respostas++;	



	        	i++;
	        	// NEXT QUESTION
	        	 contentView.setText(Tab_Perguntas.get(i).getPergunta());
	        	}
	            	//Intent intent = new Intent(QuestionActivity.this,ElementActivity.class);	
	            	// startActivity(intent);
	           	// Perform action on click
	            }
	        });


	        no.setOnClickListener(new View.OnClickListener() {

	        	public void onClick(View v) {
	        		ultima_resposta = false;

	        		// SAME MAS EM CASO DA RESPOSTA SER NAO
	        		for( MembroClass c : Tab_Membros){

		        		if(c.tab_atributos[numero_respostas] == ultima_resposta) Tab_Membros_Validos.add(c);


		        	}

		        	if(verifica()){


		        		sendtoElementActivity();

		        	}

		        	else{
		        	Tab_Membros = Tab_Membros_Validos;
		        	Tab_Membros_Validos =new ArrayList<MembroClass>(); ;
		        	numero_respostas++;	

		        	i++;
		        	contentView.setText(Tab_Perguntas.get(i).getPergunta());
		        	}
	            	//Intent intent = new Intent(QuestionActivity.this,ElementActivity.class);	
	            	// startActivity(intent);
	            	// Perform action on click
	            }
	        });


		// Set up the user interaction to manually show or hide the system UI.



	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);



	}


	// 1,2,3 -> 
	void mix_tab(int max){ // TROCA 2 ELEMENTOS DA TAB DE PERGUNTAS E TROCA AS RESPOSTAS EQUIVALENTES EM CADA MEMBRO
		int random;
		int random_2;

		do{

		random = randomGenerator() % max;
		random_2 = randomGenerator()% max;

		}while(random < 0 || random_2 < 0);

		boolean [] auxtabmember = new boolean [max];

		QuestionClass auxpergunta = new QuestionClass("");
		MembroClass auxmember = new MembroClass("",null,0, null,null);

		boolean auxbool;


		auxpergunta = Tab_Perguntas.get(random);
		Tab_Perguntas.set(random,Tab_Perguntas.get(random_2));
		Tab_Perguntas.set(random_2, auxpergunta);


		for(int j = 0; j < maximo; j++){
		auxtabmember = Tab_Membros.get(j).tab_atributos;


		auxbool = auxtabmember[random];
		auxtabmember[random] = auxtabmember[random_2];
		auxtabmember[random_2] = auxbool;

		auxmember = Tab_Membros.get(j);


		auxmember.setTabBool(auxtabmember); 

		Tab_Membros.set(j, auxmember);
		}
	}


	void inicializa_Tab(){

		// INICIALIZA OS DADOS

		ultima_resposta = false;
		numero_respostas = 0;
		maximo = 4; // MAX PERGUNTAS
		 i = 0;

		Tab_Perguntas.add(new QuestionClass("Sabe programar?" ));
		Tab_Perguntas.add(new QuestionClass("É giro e criou esta app?"));
		Tab_Perguntas.add(new QuestionClass("... ON RAILS?"));
		Tab_Perguntas.add(new QuestionClass("vous le vou coucher avec moi?"));

		boolean [] membro = {true,true,false,false};
		Tab_Membros.add(new MembroClass("José Marcelino",membro,0,"https://github.com/josemarcelino",R.drawable.ic_launcher));
		boolean [] membro_2 = {true,false,true,false};
		Tab_Membros.add(new MembroClass("Eduardo Preto",membro_2,1,"https://www.google.pt",R.drawable.ic_launcher));
		boolean [] membro_3 = {true,false,false,false};
		Tab_Membros.add(new MembroClass("David Gomes",membro_3,2,"https://www.davidgom.es",R.drawable.ic_launcher));
		boolean [] membro_4 = {true,false,false,true};
		Tab_Membros.add(new MembroClass("Bruno Conceição",membro_4,3,"https://www.bruno.com",R.drawable.ic_launcher));

//random numero de trocas nas tab
		int random_number;
		do{
			random_number = randomGenerator()%100;

		}while(random_number < 0);

		for(int j = 0; j < random_number; j++){
		mix_tab(maximo); // 3 MAXIMO DE ATRIBUTOS == MAXIMO DE PERGUNTAS
		//Log.i("RANDOM", String.valueOf(random_number));
		}


	}

	void sendtoElementActivity(){
		// MANDA PARA O ELEMENT_ACTIVITY COM A INT DA ORDEM DO ELEMENTO, em caso de nao existir nao passa nada
		Intent intent = new Intent(QuestionActivity.this,ElementActivity.class);
		if(Tab_Membros_Validos.size() != 0) intent.putExtra("O", Tab_Membros_Validos.get(0).getOrdem());

		startActivity(intent);

	}

	boolean verifica(){
		// VERIFICA SE SO EXISTE 1
		if(Tab_Membros_Validos.size() < 2) return true;	
		else return false;

	}
	@Override
	public void onBackPressed() {
	}
}
