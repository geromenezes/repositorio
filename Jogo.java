package Dominio;
import java.util.Random;
public class Jogo {

	private Jogador ptJogAtual;//ponteiro para o ojeto Jogador tual
	private boolean horario; //vari�vel para controlar o sentido do jogo
	private boolean iniciado;//vari�vel para controlar o in�cio do jogo

	public Jogo() {//construtor

		this.ptJogAtual = null;
		this.horario = true;
		this.iniciado = false;
	}

	public boolean isHorario() { //retorna o conteudo da vari�vel horario
		return horario;
	}

	public void setHorario(boolean horario) { //altera a vari�vel horario
		this.horario = horario;
	}

	public boolean isIniciado() {
		return iniciado;
	}

	public void setIniciado(boolean iniciado) {
		this.iniciado = iniciado;
	}

	public Jogador getPtJogAtual() {
		return ptJogAtual;
	}
	public Jogador getPtProxJog() {//m�todo pare retornar o pr�ximo jogador
		if(this.isHorario()==true) {//se isHorario � true(o sentido atual do jogo � hor�rio) o pr�ximo � o pt horario, sen�o � o pt antiHor
			return ptJogAtual.getHorario();
		}else {
			return ptJogAtual.getAntiHor();
		}
	}

	public Jogador getPtJogAnt() {//m�todo pare retornar o jogador anterior(inverso do metodo getPtProxJog())
		if(this.isHorario()==true) {
			return ptJogAtual.getAntiHor();
		}else {
			return ptJogAtual.getHorario();
		}
	}

	public void setPtJogAtual(Jogador ptPrim) {
		this.ptJogAtual = ptPrim;
	}

	public void add(String nome) {//metodo que adiciona jogadores(n�) no jogo (lista)
		if(this.isIniciado()) {//se iniciado for true n�o � poss�vel mais adicionar jogadores
			System.out.println("Jogo j� iniciado. Ningu�m entra!");
			return;
		}
		Jogador novo = new Jogador(nome);// cria o n� jogador
		novo.setHorario(this.getPtJogAtual());//o ponteiro horario do jogador novo aponta para o ptJogAtual
		if (this.getPtJogAtual()==null) {//se o ptJogAtual for nulo, este pt passa a apontar para o novo n� 
			this.setPtJogAtual(novo);     //e este n� entra apontando para ele msm nos dois ponteiros
			novo.setAntiHor(novo);
			novo.setHorario(novo);
		}else {//sen�o significa que j� h� jogadores inseridos
			if(verificaNome(nome)) {//chama o m�todo verificaNome e espera o retorno true ou false
				System.out.println("Nome "+nome+" j� consta nesta lista de jogadores!");
				return;
			}
			Jogador ptAux = this.getPtJogAtual();//cria uma vari�vel ptAux apontando para o jogadorAtual(in�cio da lista)
			while(ptAux != this.getPtJogAtual().getAntiHor() ) {  //repeti��o para o ptAux apontar para o �ltimo jogador adicionado
				ptAux = ptAux.getHorario();
			}
			ptAux.setHorario(novo);//�ltimo jogador adicionado aponta para o novo jogador
			novo.setAntiHor(ptAux);//novo jogador aponta para o �ltimo jogador j� adicionado
			this.getPtJogAtual().setAntiHor(novo);//primeiro jogador aponta para o novo jogador
		}
	}

	public boolean verificaNome(String nome) {
		if(this.getPtJogAtual().getNome()==nome){
			return true;
		}
		Jogador ptAux = this.getPtJogAtual().getHorario();
		while(ptAux!=this.getPtJogAtual()) {
			if(ptAux.getNome()==nome) {
				return true;
			}
			ptAux = ptAux.getHorario();
		}
		return false;
	}

	public void imprimeJogadores() {
		System.out.println("Jogadores atuais(sentido Hor�rio):");
		Jogador ptAux = this.getPtJogAtual();
		while(ptAux.getNome()!=this.getPtJogAtual().getAntiHor().getNome()) {
			System.out.println(ptAux.getNome());
			ptAux = ptAux.getHorario();
		}
		System.out.println(ptAux.getNome());
		System.out.println("Jogadores atuais(sentido anti-hor�rio):");
		Jogador ptAux2 = this.getPtJogAtual();
		while(ptAux2.getNome()!=this.getPtJogAtual().getHorario().getNome()) {
			System.out.println(ptAux2.getNome());
			ptAux2 = ptAux2.getAntiHor();
		}
		System.out.println(ptAux2.getNome());

	}

	public void imprimeJogSentAtual() {
		if(this.isHorario()) {
			System.out.println("Jogadores atuais(sentido Hor�rio):");
			Jogador ptAux = this.getPtJogAtual();
			while(ptAux.getNome()!=this.getPtJogAtual().getAntiHor().getNome()) {
				System.out.println(ptAux.getNome());
				ptAux = ptAux.getHorario();
			}
			System.out.println(ptAux.getNome());
		}else {
			System.out.println("Jogadores atuais(sentido anti-hor�rio):");
			Jogador ptAux2 = this.getPtJogAtual();
			while(ptAux2.getNome()!=this.getPtJogAtual().getHorario().getNome()) {
				System.out.println(ptAux2.getNome());
				ptAux2 = ptAux2.getAntiHor();
			}
			System.out.println(ptAux2.getNome());
		}

	}



	public void inicio() {
		Random random = new Random();
		//System.out.println("Jogadores atuais(sentido Hor�rio):");

		int cont = random.nextInt(contaJogadores())+1;//sorteio um n�mero entre 1 e o m�ximo de jogadores
		for(int i=1;i<cont;i++) { //repeti��o para mudar o jogador atual baseado no sorteio
			this.setPtJogAtual(this.getPtJogAtual().getHorario());
		}
		this.setIniciado(true);
		System.out.println("Jogo iniciado. Jogador sorteado para iniciar: "+this.getPtJogAtual().getNome()+".");
		tirarCarta(selecionaCarta());
	}

	public int selecionaCarta() {//M�todo que seleciona um n�mero aleatoreamente
		Random random = new Random();
		switch (random.nextInt(5)) {
		case 0:
			return 2;
		case 1:
			return 3;
		case 2:
			return 5;
		case 3:
			return 7;
		case 4:
			return 9;

		}
		return 0;

	}

	public void tirarCarta(int carta) {

		if(this.ptJogAtual.getSuspenso()==0) {//se o atributo suspenso do jogador atual for 0 ele pode tirar uma carta
			System.out.println("Jogador atual: "+this.ptJogAtual.getNome()+" retirou a carta: "+carta+".");
			switch(carta) {
			default:
				System.out.println("N�o existe carta com o valor informado!");
				return;

			case 2:
				System.out.println("Jogador "+this.getPtProxJog().getNome()+" perdeu a vez.");
				this.setPtJogAtual(this.getPtProxJog());//avan�o o ponteiro ptJogAtual
				break;

			case 3:
				System.out.println("Jogador "+this.getPtProxJog().getNome()+" ficou suspenso");
				this.getPtProxJog().setSuspenso(3);//o atributo suspenso do jogador atual vira 3
				break;


			case 5:

				if(this.getPtJogAtual().getHorario().getHorario()==this.getPtJogAtual()) {//se o pr�ximo jogador depois do pr�ximo � o jogador atual(apenas dois jogadores)
					System.out.println("Jogador "+this.getPtProxJog().getNome()+" perdeu a vez.");
					this.setPtJogAtual(this.getPtProxJog());//avan�o o ponteiro ptJogAtual
				}else {
					System.out.println("Sentido do jogo mudou. O pr�ximo Jogador seria "+this.getPtProxJog().getNome()+", agora ser�: "+this.getPtJogAnt().getNome());
					if(this.isHorario()) {//se a vari�vel horario for verdadeiro vira falso e vice-versa
						this.setHorario(false);
					}else {
						this.setHorario(true);
					}

				}
				break;


			case 7:
				System.out.println("Jogador atual: "+this.getPtJogAtual().getNome()+" foi ELIMINADO!!! Restam apenas "+(this.contaJogadores()-1)+" jogadores.");
				Jogador ptAtux = this.getPtProxJog(); //cria um ponteiro auxiliar para o pr�ximo jogador
				this.getPtJogAtual().getHorario().setAntiHor(this.getPtJogAtual().getAntiHor());//retirando o jogador atual do jogo(lista) 
				this.getPtJogAtual().getAntiHor().setHorario(this.getPtJogAtual().getHorario());
				this.getPtJogAtual().setHorario(null);
				this.getPtJogAtual().setAntiHor(null);
				this.setPtJogAtual(ptAtux);
				imprimeJogSentAtual();
				break;


			case 9:
				System.out.println("Jogador anterior: "+this.getPtJogAnt().getNome()+" foi ELIMINADO!!! Restam apenas "+(this.contaJogadores()-1)+" jogadores.");
				Jogador ptAtux2 = this.getPtJogAnt(); //cria um ponteiro auxiliar para o jogador anterior
				ptAtux2.getHorario().setAntiHor(ptAtux2.getAntiHor());//retirando o jogador anterior do jogo(lista) 
				ptAtux2.getAntiHor().setHorario(ptAtux2.getHorario());
				ptAtux2.setHorario(null);
				ptAtux2.setAntiHor(null);
				imprimeJogSentAtual();
				break;



			}
		}else {
			System.out.println("Jogador "+ this.getPtJogAtual().getNome() +" suspenso por "+ this.getPtJogAtual().getSuspenso() +" jogadas.");
			this.ptJogAtual.setSuspenso(this.ptJogAtual.getSuspenso()-1);//diminui um do atributo suspenso do jogador atual
		}
			//se os dois ponteiros do jogador atual apontam para ele mesmo(apenas um jogador)
		if((this.getPtJogAtual().getAntiHor() == this.getPtJogAtual()) && (this.getPtJogAtual()==this.getPtJogAtual().getHorario())) {
			System.out.println("O jogador "+this.getPtJogAtual().getNome()+" venceu!");
			return;
		}else {
			if((carta!=7)||(this.ptJogAtual.getSuspenso()>0)) {
				this.setPtJogAtual(this.getPtProxJog());//avan�o o ponteiro ptJogAtual se a carta atual n�o for 7 ou o jogador atual estiver suspenso
				tirarCarta(selecionaCarta());
			}else {
				tirarCarta(selecionaCarta());
			}

		}
	}

	private int contaJogadores() {
		Jogador ptAux = this.getPtJogAtual().getHorario();
		int cont = 1;
		while(ptAux!=this.getPtJogAtual()) {
			//System.out.println(ptAux.getNome());
			ptAux = ptAux.getHorario();
			cont ++;
		}
		//System.out.println(ptAux.getNome());
		return cont;
	}

}
