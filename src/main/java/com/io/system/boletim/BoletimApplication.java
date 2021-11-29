package com.io.system.boletim;

import com.io.system.boletim.domain.*;
import com.io.system.boletim.repository.*;
import com.io.system.boletim.service.*;
import org.aspectj.weaver.ast.Not;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class BoletimApplication implements CommandLineRunner {

	private final AlunoRepo alunoRepo;
	private final DisciplinaRepo disciplinaRepo;
	private final NotasRepo notasRepo;
	private final ProfessorRepo professorRepo;
	private final CursoRepo cursoRepo;

	private final CursoServices cursoServices;
	private final NotasServices notasServices;
	private final AlunoServices alunoServices;
	private final DisciplinaServices disciplinaServices;
	private final ProfessorServices professorServices;

	public BoletimApplication(AlunoRepo alunoRepo, DisciplinaRepo disciplinaRepo, NotasRepo notasRepo, ProfessorRepo professorRepo, CursoRepo cursoRepo, CursoServices cursoServices, NotasServices notasServices, AlunoServices alunoServices, DisciplinaServices disciplinaServices, ProfessorServices professorServices) {
		this.alunoRepo = alunoRepo;
		this.disciplinaRepo = disciplinaRepo;
		this.notasRepo = notasRepo;
		this.professorRepo = professorRepo;
		this.cursoRepo = cursoRepo;
		this.cursoServices = cursoServices;
		this.notasServices = notasServices;
		this.alunoServices = alunoServices;
		this.disciplinaServices = disciplinaServices;
		this.professorServices = professorServices;
	}




	public static void main(String[] args) {
		SpringApplication.run(BoletimApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Curso curso1 = new Curso();
		Curso curso2 = new Curso();

		curso1.setNome("ANÁLISE E DESENVOLVIMENTO DE SISTEMAS");
		curso1.setQuantidadeDeSemestres(5);

		curso2.setNome("BANCO DE DADOS");
		curso2.setQuantidadeDeSemestres(5);

		Aluno a1 = new Aluno();
		a1.setNome("Pedro");
		a1.setEmail("pedro@gmail.com");

		Aluno a2 = new Aluno();
		a2.setNome("joão");
		a2.setEmail("joao@email.com");

		Aluno a3 = new Aluno();
		a3.setNome("maria");
		a3.setEmail("maria@email.com");

		Aluno a4 = new Aluno();
		a4.setNome("julia");
		a4.setEmail("julia@email.com");

		Aluno a5 = new Aluno();
		a5.setNome("joana");
		a5.setEmail("joana@email.com");


		Professor p1 = new Professor();
		p1.setNome("ana");
		p1.setEmail("ana@email.com");

		Disciplina d1 = new Disciplina();
		d1.setNome("Portugues");
		d1.setDesc("Curso de Língua Portuguesa");

		Disciplina d2 = new Disciplina();
		d2.setNome("Fisica");
		d2.setDesc("Curso de Física Quantica");

		Disciplina d3 = new Disciplina();
		d3.setNome("Fisica");
		d3.setDesc("Curso de Física Quantica");

		Disciplina bancoDeDados= new Disciplina();
		bancoDeDados.setNome("Banco de Dados com SQL");
		bancoDeDados.setDesc("Conceitos referentes a banco de dados");
        bancoDeDados.setCursos(Arrays.asList(curso1, curso2));

		Disciplina java = new Disciplina();
		java.setNome("Programação OO com Java");
		java.setDesc("POO com Java");java.setCursos(Arrays.asList(curso1, curso2));


		Professor p10 = new Professor();
		Professor p11 = new Professor();
        Professor pardal = new Professor();
		pardal.setNome("Pardal");
		pardal.setEmail("pardal@milhopist.com");
		pardal.setDisciplinas(Arrays.asList(java, bancoDeDados));

		p10.setNome("Teste p10");
		p10.setEmail("teste@p10.com");

		p11.setNome("Teste p11");
		p11.setEmail("teste@p11.com");

		p1.setDisciplinas(Arrays.asList(d1));
		p10.setDisciplinas(Arrays.asList(d2));
		p11.setDisciplinas(Arrays.asList(d3));



		cursoRepo.save(curso1);
		cursoRepo.save(curso2);
		professorRepo.saveAll(Arrays.asList(p1, p10, p11, pardal));

		Notas nota1 = new Notas();
		nota1.setNota(10.0);
		nota1.setDisciplina(d1);
		nota1.setSemestre("1");
		nota1.setAluno(a1);


		Notas nota2 = new Notas();
		nota2.setNota(10.0);
		nota2.setDisciplina(d1);
		nota2.setSemestre("1");
		nota2.setAluno(a3);

		Notas nota3 = new Notas();
		nota3.setNota(10.0);
		nota3.setDisciplina(d1);
		nota3.setSemestre("1");
		nota3.setAluno(a4);

		Notas nota4 = new Notas();
		nota4.setNota(10.0);
		nota4.setDisciplina(d1);
		nota4.setSemestre("1");
		nota4.setAluno(a2);

		Notas nota5 = new Notas();
		nota5.setNota(10.0);
		nota5.setDisciplina(d1);
		nota5.setSemestre("1");
		nota5.setAluno(a5);


		alunoServices.save(a1);
		alunoServices.save(a2);
		alunoServices.save(a3);
		alunoServices.save(a4);
		alunoServices.save(a5);

		notasServices.lancarNotas(nota1);
		notasServices.lancarNotas(nota2);
		notasServices.lancarNotas(nota3);
		notasServices.lancarNotas(nota4);
		notasServices.lancarNotas(nota5);

		professorRepo.delete(pardal);
        //disciplinaRepo.delete(java);
		//disciplinaRepo.delete(bancoDeDados);
        //cursoRepo.delete(curso1);

		Optional<Curso>buscaCurso = cursoRepo.findCursoByNomeEquals("ANÁLISE E DESENVOLVIMENTO DE SISTEMAS");

		if(buscaCurso.isPresent()){
			System.out.println("NOME DO CURSO : " + buscaCurso.get().getNome());
		} else{
			System.out.println("NÃO EXISTE CURSO COM ESSE NOME");
		}

		List<Curso> listaDeCursos = cursoRepo.findAll();

		listaDeCursos.forEach(curso -> System.out.println("nome do curso : " + curso.getNome()));





	}
}
