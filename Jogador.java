package Dominio;

public class Jogador {

	private Jogador horario;
	private String nome;
	private Jogador antiHor;
	private int suspenso;
	
	public Jogador(String nome) {
		
		this.horario = null;
		this.nome = nome;
		this.antiHor = null;
		this.suspenso = 0;
	}

	public int getSuspenso() {
		return suspenso;
	}

	public void setSuspenso(int suspenso) {
		this.suspenso = suspenso;
	}

	public Jogador getHorario() {
		return horario;
	}

	public void setHorario(Jogador horario) {
		this.horario = horario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Jogador getAntiHor() {
		return antiHor;
	}

	public void setAntiHor(Jogador antiHor) {
		this.antiHor = antiHor;
	}
	
	
	
}
