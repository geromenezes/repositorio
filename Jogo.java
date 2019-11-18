package Dominio;
import java.util.Random;
public class Jogo {

	private Jogador ptJogAtual;//ponteiro para o ojeto Jogador tual
	private boolean horario; //variável para controlar o sentido do jogo
	private boolean iniciado;//variável para controlar o início do jogo

	public Jogo() {//construtor

		this.ptJogAtual = null;
		this.horario = true;
		this.iniciado = false;
	}

	public boolean isHorario() { //retorna o conteudo da variável horario
		return horario;
	}

	public void setHorario(boolean horario) { //altera a variável horario
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
	public Jogador getPtProxJog() {//método pare retornar o próximo jogador
		if(this.isHorario()==true) {//se isHorario é true(o sentido atual do jogo é horário) o próximo é o pt horario, senão é o pt antiHor
			return ptJogAtual.getHorario();
		}else {
			return ptJogAtual.getAntiHor();
		}
	}

	public Jogador getPtJogAnt() {//método pare retornar o jogador anterior(inverso do metodo getPtProxJog())
		if(this.isHorario()==true) {
			return ptJogAtual.getAntiHor();
		}else {
			return ptJogAtual.getHorario();
		}
	}

	public void setPtJogAtual(Jogador ptPrim) {
		this.ptJogAtual = ptPrim;
	}

	public void add(String nome) {//metodo que adiciona jogadores(nó) no jogo (lista)
		if(this.isIniciado()) {//se iniciado for true não é possível mais adicionar jogadores
			System.out.println("Jogo já iniciado. Ninguém entra!");
			return;
		}
		Jogador novo = new Jogador(nome);// cria o nó jogador
		novo.setHorario(this.getPtJogAtual());//o ponteiro horario do jogador novo aponta para o ptJogAtual
		if (this.getPtJogAtual()==null) {//se o ptJogAtual for nulo, este pt passa a apontar para o novo nó 
			this.setPtJogAtual(novo);     //e este nó entra apontando para ele msm nos dois ponteiros
			novo.setAntiHor(novo);
			novo.setHorario(novo);
		}else {//senão significa que já há jogadores inseridos
			if(verificaNome(nome)) {//chama o método verificaNome e espera o retorno true ou false
				System.out.println("Nome "+nome+" já consta nesta lista de jogadores!");
				return;
			}
			Jogador ptAux = this.getPtJogAtual();//cria uma variável ptAux apontando para o jogadorAtual(início da lista)
			while(ptAux != this.getPtJogAtual().getAntiHor() ) {  //repetição para o ptAux apontar para o último jogador adicionado
				ptAux = ptAux.getHorario();
			}
			ptAux.setHorario(novo);//último jogador adicionado aponta para o novo jogador
			novo.setAntiHor(ptAux);//novo jogador aponta para o último jogador já adicionado
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
		System.out.println("Jogadores atuais(sentido Horário):");
		Jogador ptAux = this.getPtJogAtual();
		while(ptAux.getNome()!=this.getPtJogAtual().getAntiHor().getNome()) {
			System.out.println(ptAux.getNome());
			ptAux = ptAux.getHorario();
		}
		System.out.println(ptAux.getNome());
		System.out.println("Jogadores atuais(sentido anti-horário):");
		Jogador ptAux2 = this.getPtJogAtual();
		while(ptAux2.getNome()!=this.getPtJogAtual().getHorario().getNome()) {
			System.out.println(ptAux2.getNome());
			ptAux2 = ptAux2.getAntiHor();
		}
		System.out.println(ptAux2.getNome());

	}

	public void imprimeJogSentAtual() {
		if(this.isHorario()) {
			System.out.println("Jogadores atuais(sentido Horário):");
			Jogador ptAux = this.getPtJogAtual();
			while(ptAux.getNome()!=this.getPtJogAtual().getAntiHor().getNome()) {
				System.out.println(ptAux.getNome());
				ptAux = ptAux.getHorario();
			}
			System.out.println(ptAux.getNome());
		}else {
			System.out.println("Jogadores atuais(sentido anti-horário):");
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
		//System.out.println("Jogadores atuais(sentido Horário):");

		int cont = random.nextInt(contaJogadores())+1;//sorteio um número entre 1 e o máximo de jogadores
		for(int i=1;i<cont;i++) { //repetição para mudar o jogador atual baseado no sorteio
			this.setPtJogAtual(this.getPtJogAtual().getHorario());
		}
		this.setIniciado(true);
		System.out.println("Jogo iniciado. Jogador sorteado para iniciar: "+this.getPtJogAtual().getNome()+".");
		tirarCarta(selecionaCarta());
	}

	public int selecionaCarta() {//Método que seleciona um número aleatoreamente
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
				System.out.println("Não existe carta com o valor informado!");
				return;

			case 2:
				System.out.println("Jogador "+this.getPtProxJog().getNome()+" perdeu a vez.");
				this.setPtJogAtual(this.getPtProxJog());//avanço o ponteiro ptJogAtual
				break;

			case 3:
				System.out.println("Jogador "+this.getPtProxJog().getNome()+" ficou suspenso");
				this.getPtProxJog().setSuspenso(3);//o atributo suspenso do jogador atual vira 3
				break;


			case 5:

				if(this.getPtJogAtual().getHorario().getHorario()==this.getPtJogAtual()) {//se o próximo jogador depois do próximo é o jogador atual(apenas dois jogadores)
					System.out.println("Jogador "+this.getPtProxJog().getNome()+" perdeu a vez.");
					this.setPtJogAtual(this.getPtProxJog());//avanço o ponteiro ptJogAtual
				}else {
					System.out.println("Sentido do jogo mudou. O próximo Jogador seria "+this.getPtProxJog().getNome()+", agora será: "+this.getPtJogAnt().getNome());
					if(this.isHorario()) {//se a variável horario for verdadeiro vira falso e vice-versa
						this.setHorario(false);
					}else {
						this.setHorario(true);
					}

				}
				break;


			case 7:
				System.out.println("Jogador atual: "+this.getPtJogAtual().getNome()+" foi ELIMINADO!!! Restam apenas "+(this.contaJogadores()-1)+" jogadores.");
				Jogador ptAtux = this.getPtProxJog(); //cria um ponteiro auxiliar para o próximo jogador
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
				this.setPtJogAtual(this.getPtProxJog());//avanço o ponteiro ptJogAtual se a carta atual não for 7 ou o jogador atual estiver suspenso
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
