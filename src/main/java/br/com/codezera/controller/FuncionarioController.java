package br.com.codezera.controller;

import br.com.codezera.model.Funcionario;
import br.com.codezera.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log
public class FuncionarioController {

    private final FuncionarioRepository funcionarioRepository;

    @PostMapping("funcionarios/salvar")
    public ResponseEntity<Funcionario> save(@RequestBody Funcionario funcionario) {
        funcionario = this.funcionarioRepository.save(funcionario);
        log.info("Usuário criado com sucesso :: ID " + funcionario.getId());
        return new ResponseEntity<>(funcionario, HttpStatus.CREATED);
    }

}
