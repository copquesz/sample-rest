package br.com.codezera.controller;

import br.com.codezera.model.Funcionario;
import br.com.codezera.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("funcionarios")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<Funcionario> save(@RequestBody Funcionario funcionario) {
        funcionario = this.funcionarioService.save(funcionario);
        return new ResponseEntity<>(funcionario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> update(@PathVariable Long id, @RequestBody Funcionario funcionarioUpdated) {
        Boolean status = this.funcionarioService.update(id, funcionarioUpdated);
        if (status) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> findById(@PathVariable Long id) {
        Funcionario funcionario = this.funcionarioService.findById(id);
        if (funcionario != null) {
            return new ResponseEntity<>(funcionario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> findAll() {
        List<Funcionario> funcionarios = this.funcionarioService.findAll();
        if (!funcionarios.isEmpty()) {
            return new ResponseEntity<>(funcionarios, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("HasRole('ADMIN')")
    public ResponseEntity<Funcionario> deleteById(@PathVariable Long id) {
        Boolean status = this.funcionarioService.delete(id);
        if (status) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
