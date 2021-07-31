package com.davicarv.choperia;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.davicarv.choperia.domain.Barril;
import com.davicarv.choperia.domain.Cliente;
import com.davicarv.choperia.domain.Endereco;
import com.davicarv.choperia.domain.Funcionario;
import com.davicarv.choperia.domain.MarcaBarrilEnum;
import com.davicarv.choperia.domain.Telefone;
import com.davicarv.choperia.domain.TipoPessoa;
import com.davicarv.choperia.domain.Usuario;
import com.davicarv.choperia.repository.BarrilRepository;
import com.davicarv.choperia.repository.ClienteRepository;
import com.davicarv.choperia.repository.FuncionarioRepository;

@SpringBootApplication
public class ChoperiaApplication implements CommandLineRunner {

	@Autowired
	private BarrilRepository barrilRepo;
	@Autowired
	private ClienteRepository clienteRepo;
	@Autowired
	private FuncionarioRepository funcionarioRepo;

	public static void main(String[] args) {
		SpringApplication.run(ChoperiaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Usuario1
		Usuario usuario = new Usuario();
		usuario.setNomeUsuario("davicarv");
		usuario.setSenha("123");
		
		// Cliente
		Cliente c1 = new Cliente();
		c1.setNome("Davi");
		c1.setCpfOuCnpj(TipoPessoa.PESSOA_FISICA);
		c1.setEmail("davicarv13@gmail.com");
		c1.setSobrenome("Carvalho");
		c1.setDocumentacao("...");
		
		Telefone t1 = new Telefone();
		t1.setCodigoPais("55");
		t1.setDdd("22");
		t1.setNumero("998131913");
		List<Telefone> telefonesCliente = new ArrayList<>();
		telefonesCliente.add(t1);
		
		Endereco end = new Endereco();
		end.setRua("Rua das flores");
		end.setCidade("Campos");
		end.setEstado("RJ");
		end.setNumero(14);
		end.setBairro("Goyta");
		Calendar dataNascimentoCliente = Calendar.getInstance();
		dataNascimentoCliente.set(1997, 5, 8);
		
		List<Endereco> enderecosCliente = new ArrayList<>();
		enderecosCliente.add(end);
		
		c1.setEnderecos(enderecosCliente);
		c1.setDataNascimento(dataNascimentoCliente);
		c1.setTelefones(telefonesCliente);
		c1.setUsuario(usuario);
		
		clienteRepo.save(c1);

		
		// Barril
		Barril barril = new Barril();
		Calendar validade = Calendar.getInstance();
		validade.set(2021, 8, 10);
		Calendar fabricacao = Calendar.getInstance();
		fabricacao.set(2021, 8, 1);
		barril.setMarca(MarcaBarrilEnum.BRAHMA_CLARO);
		barril.setPreco(761);
		barril.setTamanho(50);
		barril.setValidade(validade);
		barril.setFabricacao(fabricacao);
		
		barrilRepo.save(barril);
		
		// Usuario1
		Usuario usuario2 = new Usuario();
		usuario2.setNomeUsuario("silascarv");
		usuario2.setSenha("123");
		
		// Funcionario
		Funcionario f1 = new Funcionario();
		f1.setNome("Silas");
		f1.setCpfOuCnpj(TipoPessoa.PESSOA_FISICA);
		f1.setEmail("silascarv@gmail.com");
		f1.setSobrenome("Carvalho");
		
		Telefone t2 = new Telefone();
		t2.setCodigoPais("55");
		t2.setDdd("22");
		t2.setNumero("998132013");
		List<Telefone> telefonesCliente2 = new ArrayList<>();
		telefonesCliente.add(t2);
		
		Endereco end2 = new Endereco();
		end2.setRua("Rua das flores");
		end2.setCidade("Campos");
		end2.setEstado("RJ");
		end2.setNumero(14);
		end2.setBairro("Goyta");
		Calendar dataNascimentoCliente2 = Calendar.getInstance();
		dataNascimentoCliente.set(1999, 2, 16);
		
		List<Endereco> enderecosCliente2 = new ArrayList<>();
		enderecosCliente.add(end);
		
		f1.setEnderecos(enderecosCliente2);
		f1.setDataNascimento(dataNascimentoCliente2);
		f1.setTelefones(telefonesCliente2);
		f1.setUsuario(usuario2);
		f1.setCargo("Entregador");
		f1.setSalario(1500);
		funcionarioRepo.save(f1);
		
	}
}
