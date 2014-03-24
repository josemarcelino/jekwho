package com.example.jekwho;

public class MembroClass {
	String nome;
	boolean [] tab_atributos = new boolean [3]; 
	int ordem;//tamanho maximo de atributos ORDEM É O NUMERO DA FOTO

	String site;
	Object foto;



	MembroClass(String n,boolean [] tab, int o,String link,Object object){


		nome = n;
		tab_atributos = tab;
		ordem = o;
		site = link;
		foto = object;
	}

	Object getObject(){

		return this.foto;

	}


	String getNome(){

		return this.nome;

	}

	int getOrdem(){


		return this.ordem;
	}

	boolean[] getTabBool(){
		return this.tab_atributos;
	}

	void setTabBool(boolean [] tab){

		tab_atributos =tab;
	}

	String getLink(){

		return this.site;
	}

}
