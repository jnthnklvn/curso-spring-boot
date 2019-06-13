package com.jonkelvin.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jonkelvin.cursomc.domain.Categoria;
import com.jonkelvin.cursomc.domain.Cidade;
import com.jonkelvin.cursomc.domain.Cliente;
import com.jonkelvin.cursomc.domain.Endereco;
import com.jonkelvin.cursomc.domain.Estado;
import com.jonkelvin.cursomc.domain.Produto;
import com.jonkelvin.cursomc.domain.enums.TipoCliente;
import com.jonkelvin.cursomc.repositories.CategoriaRepository;
import com.jonkelvin.cursomc.repositories.CidadeRepository;
import com.jonkelvin.cursomc.repositories.ClienteRepository;
import com.jonkelvin.cursomc.repositories.EnderecoRepository;
import com.jonkelvin.cursomc.repositories.EstadoRepository;
import com.jonkelvin.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "Computador", 2000);
		Produto p2 = new Produto(null, "Impressora", 800);
		Produto p3 = new Produto(null, "Mouse", 80);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado s1 = new Estado(null, "Minas Gerais");
		Estado s2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", s1);
		Cidade c2 = new Cidade(null, "São Paulo", s2);
		Cidade c3 = new Cidade(null, "Campinas", s2);

		s1.getCidades().addAll(Arrays.asList(c1));
		s2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(s1,s2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Maria Silva",  "maria@gmail.com", "39801293822", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("7999999999","7198888888"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "3218903", cli1, c1);
		Endereco e2 = new Endereco(null, "Av Matos", "105", "Sala 800", "Centro", "3847664", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}

}
