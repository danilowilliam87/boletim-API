package com.io.system.boletim;

import com.io.system.boletim.domain.*;
import com.io.system.boletim.repository.*;
import com.io.system.boletim.service.CursoServices;
import com.io.system.boletim.service.NotasServices;
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

	public BoletimApplication(AlunoRepo alunoRepo, DisciplinaRepo disciplinaRepo, NotasRepo notasRepo, ProfessorRepo professorRepo, CursoRepo cursoRepo, CursoServices cursoServices, NotasServices notasServices) {
		this.alunoRepo = alunoRepo;
		this.disciplinaRepo = disciplinaRepo;
		this.notasRepo = notasRepo;
		this.professorRepo = professorRepo;
		this.cursoRepo = cursoRepo;
		this.cursoServices = cursoServices;
		this.notasServices = notasServices;
	}




	public static void main(String[] args) {
		SpringApplication.run(BoletimApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Aluno a1 = new Aluno();
		a1.setNome("Pedro");
		a1.setEmail("pedro@gmail.com");

		Professor p1 = new Professor();
		p1.setNome("ana");
		p1.setEmail("ana@email.com");

		Disciplina d1 = new Disciplina();
		d1.setNome("Portugues");


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
	    notasRepo.save(n1);

		cursoRepo.save(c1);

		Notas n2 = new Notas();
		n2.setAluno(a1);
		n2.setSemestre("2º semestre");
		n2.setDisciplina(d1);
		n2.setNota(8.0);

		notasServices.lancarNotas(n2);


	}
}
