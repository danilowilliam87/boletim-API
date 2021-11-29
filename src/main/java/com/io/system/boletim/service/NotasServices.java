package com.io.system.boletim.service;

import com.io.system.boletim.Enums.StatusAluno;
import com.io.system.boletim.domain.Aluno;
import com.io.system.boletim.domain.Disciplina;
import com.io.system.boletim.domain.Notas;
import com.io.system.boletim.repository.AlunoRepo;
import com.io.system.boletim.repository.DisciplinaRepo;
import com.io.system.boletim.repository.NotasRepo;
import org.aspectj.weaver.ast.Not;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotasServices {

    private final NotasRepo notasRepo;
    private final DisciplinaRepo disciplinaRepo;
    private final AlunoRepo alunoRepo;

    public NotasServices(NotasRepo notasRepo, DisciplinaRepo disciplinaRepo, AlunoRepo alunoRepo) {
        this.notasRepo = notasRepo;
        this.disciplinaRepo = disciplinaRepo;
        this.alunoRepo = alunoRepo;
    }

    //ainda não decidi se vou disponibiliar esse método em algum endpoint
    public Notas find(Long id){
        Optional<Notas> notas = notasRepo.findById(id);
        return notas.orElseThrow(()-> new ObjectNotFoundException("objeto não encontrado! Id :" + id,"Tipo :"
                + Notas.class.getName()));
    }

    public Notas findByAlunoAndDisciplina(String emailAluno, String nomeDisciplina){
        Notas n = new Notas();
        Optional<Notas> buscaNota = null;
        Optional<Aluno> buscaAluno = alunoRepo.findAlunoByEmailEquals(emailAluno);
        Optional<Disciplina> buscaDisciplina = disciplinaRepo.findDisciplinaByNomeEquals(nomeDisciplina);
        if(buscaAluno.isPresent() && buscaDisciplina.isPresent()){
            buscaNota = notasRepo
                    .findNotasByDisciplinaAndAluno(buscaDisciplina.get(), buscaAluno.get());
            n = buscaNota.get();
        }
        Notas finalN = n;
        return buscaNota.orElseThrow(()-> new ObjectNotFoundException("objeto não encontrado! Id :",
              finalN.getId() + "Tipo :"
                + Notas.class.getName()));
    }

    public List<Notas> findAllByAlunoAndSemestre(String emailAluno, String semestre){
        Optional<Aluno> busca = alunoRepo.findAlunoByEmailEquals(emailAluno);
        Aluno aluno = new Aluno();
        if(busca.isPresent()){
            aluno = busca.get();
        }
        List<Notas> list = notasRepo.findAllByAlunoAndSemestre(aluno, semestre);
        return list;
    }

    //metodo para alterar notas em caso de erro no lançamento...
    //nota tera que obrigatoriamente ser informada no corpo da requisição
    public void updatePatch(Notas notas, String email, String nomeDisciplina){
        Optional<Aluno> buscaAluno = alunoRepo
                .findAlunoByEmailEquals(email);
        Optional<Disciplina> buscaDisciplina = disciplinaRepo
                .findDisciplinaByNomeEquals(nomeDisciplina);
        notas.setAluno(Optional.ofNullable(buscaAluno.get())
                .orElse(notas.getAluno()));
        notas.setDisciplina(Optional.ofNullable(buscaDisciplina.get())
                .orElse(notas.getDisciplina()));
        Optional<Notas> buscaNota = notasRepo
                .findNotasByDisciplinaAndAluno(notas.getDisciplina(), notas.getAluno());
        notas.setSemestre(Optional.ofNullable(buscaNota.get().getSemestre())
                .orElse(notas.getSemestre()));
        notas.setId(buscaNota.get().getId());
        setStatus(notas);
        lancarNotas(notas);
    }

    public void setStatus(Notas notas){
            if (notas.getNota() >= 6.0 && notas.getNota() <= 10.0) {
                notas.setStatusAluno(StatusAluno.APROVADO);
            } else if (notas.getNota() > 4.0 && notas.getNota() < 6.0) {
                notas.setStatusAluno(StatusAluno.RECUPERACAO);
            } else if(notas.getNota() >= 0.0 && notas.getNota() < 4.0){
                notas.setStatusAluno(StatusAluno.REPROVADO);
            } else{
                notas.setStatusAluno(StatusAluno.INDEFINIDO);
            }
    }


    public Notas lancarNotas(Notas notas){
        try {
            Optional<Aluno> buscaAluno = alunoRepo
                    .findAlunoByEmailEquals(notas.getAluno().getEmail());
            Optional<Disciplina> buscaDisciplina = disciplinaRepo
                    .findDisciplinaByNomeEquals(notas.getDisciplina().getNome());
            if (buscaAluno.isPresent() && buscaDisciplina.isPresent()) {
                notas.setDisciplina(buscaDisciplina.get());
                notas.setAluno(buscaAluno.get());
               setStatus(notas);
            }
        } catch (Exception e){
            //tratar com exceção personalizada futuramente
            e.printStackTrace();
        }
        return notasRepo.save(notas);
    }


}
