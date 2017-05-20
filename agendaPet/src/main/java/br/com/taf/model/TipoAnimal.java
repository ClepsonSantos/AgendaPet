package br.com.taf.model;

public enum TipoAnimal {
	
	CACHORRO("Cachorro"),
	GATO("Gato");
	
	private String descricao;
	
	private TipoAnimal(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	@Override
    public String toString() {
        return descricao;
    }
}
