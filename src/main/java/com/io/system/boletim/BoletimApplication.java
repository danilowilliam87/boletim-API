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

		p1.setDisciplinas(Arrays.asList(d1,d2));
		p10.setDisciplinas(Arrays.asList(d1,d2));
		p11.setDisciplinas(Arrays.asList(d3));

		professorRepo.saveAll(Arrays.asList(p1, p10, p11));

		Notas nota1 = new Notas();
		nota1.setNota(10.0);
		nota1.setDisciplina(d1);
		nota1.setSemestre("1ºsemestre");
		nota1.setAluno(a1);

		Notas nota2 = new Notas();
		nota2.setNota(10.0);
		nota2.setDisciplina(d1);
		nota2.setSemestre("1ºsemestre");
		nota2.setAluno(a2);

		Notas nota3 = new Notas();
		nota3.setNota(10.0);
		nota3.setDisciplina(d3);
		nota3.setSemestre("1ºsemestre");
		nota3.setAluno(a2);


		alunoServices.save(a1);
		alunoServices.save(a2);
		notasServices.lancarNotas(nota1);
		notasServices.lancarNotas(nota2);
		notasServices.lancarNotas(nota3);

		System.out.println("########################  NOTAS DO ALUNO A1 ##############################");
		Notas busca = new Notas();
		busca = notasServices.find(1L);
		System.out.println("NOME DO ALUNO : " + busca.getAluno().getNome());
		System.out.println("NOTA DO ALUNO : " + busca.getNota());
		System.out.println("SEMESTRE : " + busca.getSemestre());
		System.out.println("SITUACAO : " + busca.getStatusAluno().toString());
		System.out.println("SITUACAO : " + busca.getDisciplina().getNome());


		System.out.println("########################  NOTAS DO ALUNO A2 ##############################");
		Notas busca1 = new Notas();
		busca1 = notasServices.findByAlunoAndDisciplina("joao@email.com","Portugues");
		System.out.println("NOME DO ALUNO : " + busca1.getAluno().getNome());
		System.out.println("NOTA DO ALUNO : " + busca1.getNota());
		System.out.println("SEMESTRE : " + busca1.getSemestre());
		System.out.println("SITUACAO : " + busca1.getStatusAluno().toString());
		System.out.println("SITUACAO : " + busca.getDisciplina().getNome());

		List<Notas> listaNotasPorAluno = notasServices
				.findAllByAlunoAndSemestre("joao@email.com", "1ºsemestre");

		System.out.println("########################  NOTAS DO ALUNO A2 ##############################");
		listaNotasPorAluno.forEach(notas -> System.out.println(
				         "-----------------------------------------------------------------------------\n"+
				                  "NOME DA DISCIPLINA : " +notas.getDisciplina().getNome() +
				                  "\nNOTA : "+notas.getNota()
						+"\nSITUAÇÃO : " + notas.getStatusAluno()
		                +"\n-----------------------------------------------------------------------------"));
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
