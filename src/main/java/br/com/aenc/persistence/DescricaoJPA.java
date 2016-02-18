package br.com.aenc.persistence;

import br.com.aenc.entity.DescricaoDesaparecido;

public class DescricaoJPA extends GenericJPA<DescricaoDesaparecido, Integer>{

	public DescricaoJPA() {
		super(new DescricaoDesaparecido());
	}
	
	public static void main(String[] args) {
		try{
		DescricaoDesaparecido desc = new DescricaoDesaparecido(0, "verdes", "castan", "negra", 1.60, 58.7,
				"magro com cicatriz na sombracelha", false, "w");
		
		new DescricaoJPA().gravarAtualizar(desc);
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
}
