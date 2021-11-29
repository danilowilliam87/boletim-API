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

		Professor p10 = new Professor();
		Professor p11 = new Professor();

		p10.setNome("Teste p10");
		p10.setEmail("teste@p10.com");

		p11.setNome("Teste p11");
		p11.setEmail("teste@p11.com");

		p1.setDisciplinas(Arrays.asList(d1));
		p10.setDisciplinas(Arrays.asList(d2));
		p11.setDisciplinas(Arrays.asList(d3));

		professorRepo.saveAll(Arrays.asList(p1, p10, p11));

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

		Notas nova = new Notas();
		nova.setNota(-1);

        notasServices.updatePatch(nova, "joana@email.com", "Portugues");


		List<Notas> listaNotasPorAluno = notasServices
				.findAllByAlunoAndSemestre("joana@email.com", "1");

		System.out.println("########################  NOTAS DO ALUNO "+listaNotasPorAluno.get(0).getAluno().getNome() +" ##############################");
		listaNotasPorAluno.forEach(notas -> System.out.println(
				         "-----------------------------------------------------------------------------\n"+
				                  "NOME DA DISCIPLINA : " +notas.getDisciplina().getNome() +
				                  "\nNOTA : "+notas.getNota()
						+"\nSITUAÇÃO : " + notas.getStatusAluno()
		                +"\n-----------------------------------------------------------------------------"));



		int linhas = listaNotasPorAluno.size();
		System.out.println("quantidade de registros : " + linhas);
		//professorRepo.save(p10);
		//professorRepo.save(p11);
		//notasServices.lancarNotas(n2);//Ok
		//alunoServices.save(a2);       //Ok
		//alunoServices.updatePatch(a3, id); Ok
		//alunoServices.updatePut(a3, id);  Ok
		// Aluno busca = alunoServices.findByEmail("joao@email.com"); Ok
		//List<Aluno>listaDeAlunos = alunoServices.findAll(); Ok
		/*System.out.println("######          lista de alunos : ######");
		listaDeAlunos.forEach(aluno-> System.out.println(aluno.getNome()));
		*/
		//alunoServices.delete(2L); //Ok
	}
}
