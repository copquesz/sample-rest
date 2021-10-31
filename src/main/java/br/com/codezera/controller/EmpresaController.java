package br.com.codezera.controller;

import br.com.codezera.model.Empresa;
import br.com.codezera.service.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("empresas")

public class EmpresaController {
    private final EmpresaService empresaService;

    @PostMapping
    public ResponseEntity<Empresa> save(@RequestBody Empresa empresa) {
        empresa = this.empresaService.save(empresa);
        return new ResponseEntity<>(empresa, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> update(@PathVariable Long id, @RequestBody Empresa empresaUpdated) {
        Boolean status = this.empresaService.update(id, empresaUpdated);
        if (status) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> findById(@PathVariable Long id) {
        Empresa empresa = this.empresaService.findById(id);
        if (empresa != null) {
            return new ResponseEntity<>(empresa, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> findAll() {
        List<Empresa> empresas = this.empresaService.findAll();
        if (!empresas.isEmpty()) {
            return new ResponseEntity<>(empresas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Empresa> deleteById(@PathVariable Long id) {
        Boolean status = this.empresaService.delete(id);
        if (status) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

