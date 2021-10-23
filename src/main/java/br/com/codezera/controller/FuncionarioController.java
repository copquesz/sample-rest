package br.com.codezera.controller;

import br.com.codezera.model.Funcionario;
import br.com.codezera.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Log
@RequestMapping("funcionarios")
public class FuncionarioController {

    private final FuncionarioRepository funcionarioRepository;

    @PostMapping
    public ResponseEntity<Funcionario> save(@RequestBody Funcionario funcionario) {
        funcionario = this.funcionarioRepository.save(funcionario);
        log.info("Usuário criado com sucesso :: ID " + funcionario.getId());
        return new ResponseEntity<>(funcionario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> update(@PathVariable Long id, @RequestBody Funcionario funcionarioUpdated) {
        Optional<Funcionario> optionalFuncionario = this.funcionarioRepository.findById(id);
        if (optionalFuncionario.isPresent()) {
            Funcionario funcionario = optionalFuncionario.get();
            funcionario.setNome(funcionarioUpdated.getNome());
            funcionario.setProfissao(funcionario.getProfissao());
            funcionario.setSalario(funcionarioUpdated.getSalario());
            this.funcionarioRepository.save(funcionario);
            log.info("Usuário atualizado com sucesso :: ID " + funcionario.getId());
            return new ResponseEntity<>(funcionario, HttpStatus.CREATED);
        } else {
            log.info("Usuário não encontrado :: ID " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> findById(@PathVariable Long id) {
        Optional<Funcionario> funcionario = this.funcionarioRepository.findById(id);
        if (funcionario.isPresent()) {
            log.info("Buscando usuário :: ID " + id);
            return new ResponseEntity<>(funcionario.get(), HttpStatus.OK);
        } else {
            log.info("Usuário não encontrado :: ID " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> findAll() {
        List<Funcionario> funcionarios = this.funcionarioRepository.findAll();
        if (!funcionarios.isEmpty()) {
            log.info("Listando usuários");
            return new ResponseEntity<>(funcionarios, HttpStatus.OK);
        } else {
            log.info("Não há usuários cadastrados");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Funcionario> deleteById(@PathVariable Long id) {
        Optional<Funcionario> funcionario = this.funcionarioRepository.findById(id);
        if (funcionario.isPresent()) {
            log.info("Deleta usuário :: ID " + id);
            this.funcionarioRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            log.info("Usuário não encontrado :: ID " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
