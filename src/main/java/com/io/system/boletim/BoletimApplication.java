package com.io.system.boletim;

import com.io.system.boletim.domain.*;
import com.io.system.boletim.repository.*;
import com.io.system.boletim.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

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


		Curso c1 = new Curso();
		c1.setNome("Ensino médio");
		c1.setQuantidadeDeSemestres(2);
		c1.setAlunos(Arrays.asList(a1));
		c1.setDisciplinas(Arrays.asList(d1));

		Notas n1 = new Notas();
		n1.setSemestre("1º semestre");
		n1.setAluno(a1);
		n1.setDisciplina(d1);
		n1.setNota(10.0);

		p1.setDisciplinas(Arrays.asList(d1));

		alunoRepo.save(a1);
		professorRepo.save(p1);
		disciplinaRepo.save(d1);
		disciplinaServices.save(d2);
	    notasRepo.save(n1);

		cursoRepo.save(c1);

		Notas n2 = new Notas();
		n2.setAluno(a1);
		n2.setSemestre("2º semestre");
		n2.setDisciplina(d1);
		n2.setNota(8.0);

		//notasServices.lancarNotas(n2);//Ok
		//alunoServices.save(a2);       //Ok

		Aluno a3 = new Aluno();
		a3.setNome("João de Jesus");
		a3.setEmail("jj@gmail.com");
		Long id = 2L;

		//alunoServices.updatePatch(a3, id); Ok
		//alunoServices.updatePut(a3, id);  Ok

		// Aluno busca = alunoServices.findByEmail("joao@email.com"); Ok
		//List<Aluno>listaDeAlunos = alunoServices.findAll(); Ok

		/*System.out.println("######          lista de alunos : ######");
		listaDeAlunos.forEach(aluno-> System.out.println(aluno.getNome()));
		*/

		//alunoServices.delete(2L); //Ok

        Professor busca = professorServices.findByEmail("ana@email.com");
		System.out.println(busca.getNome());

	}
}
