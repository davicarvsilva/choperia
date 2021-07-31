package com.davicarv.choperia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.davicarv.choperia.domain.Barril;
import com.davicarv.choperia.domain.Cliente;
import com.davicarv.choperia.domain.Endereco;
import com.davicarv.choperia.domain.MarcaBarrilEnum;
import com.davicarv.choperia.domain.Telefone;
import com.davicarv.choperia.domain.TipoPessoa;
import com.davicarv.choperia.repository.BarrilRepository;


@SpringBootApplication
public class ChoperiaApplication implements CommandLineRunner{
	
	@Autowired
	private BarrilRepository barrilRepo;

	public static void main(String[] args) {
		SpringApplication.run(ChoperiaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Cliente 
		Cliente c1 = new Cliente();
		c1.setNome("Davi");
		c1.setCpfOuCnpj(TipoPessoa.PESSOA_FISICA);
		c1.setDocumentacao("...");
		Telefone t1 = new Telefone();
		t1.setCodigoPais("55");
		t1.setDdd("22");
		t1.setDdd("998131913");
		Endereco  end = new Endereco();
		end.setRua("Rua das flores");
		end.setCidade("Campos");
		end.setEstado("RJ");
		end.setNumero("14");
		end.setBairro("Goyta");
		
		
	}
}
