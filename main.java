package Programa;


import Dominio.Jogo;


public class main {

	public static void main(String[] args) {
		
		
		

		Jogo jogo = new Jogo();
		
		jogo.add("Adriana");
		jogo.add("Pedro");
		jogo.add("Aline");
		jogo.add("Cintia");
		jogo.add("Gero");
		jogo.add("Sandro");
		jogo.add("Leandro");
		
		jogo.add("Alberto");
		jogo.imprimeJogadores();
		
		jogo.inicio();
	//	jogo.add("Alberto");
	//	jogo.imprimeJogadores();
		
	}

}
