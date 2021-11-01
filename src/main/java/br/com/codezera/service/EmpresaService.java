package br.com.codezera.service;

import br.com.codezera.model.Empresa;
import br.com.codezera.repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Log
@RequiredArgsConstructor
@Service

public class EmpresaService {

        private final EmpresaRepository empresaRepository;

        public Empresa save(Empresa empresa) {
            empresa = this.empresaRepository.save(empresa);
            log.info("Empresa cadastrada com sucesso :: ID " + empresa.getId());
            return empresa;
        }

        public Empresa findById(Long id) {
            Optional<Empresa> optionalEmpresa = this.empresaRepository.findById(id);
            if (optionalEmpresa.isPresent()) {
                log.info("Buscando Empresa :: ID " + id);
                return optionalEmpresa.get();
            } else {
                log.info("Empresa não encontrada :: ID " + id);
                return null;
            }
        }

        public Boolean update(Long id, Empresa empresaUpdated) {
            Empresa empresa = this.findById(id);
            if (empresa != null) {
                empresa.setNome(empresaUpdated.getNome());
                empresa.setCnpj(empresaUpdated.getCnpj());
                this.empresaRepository.save(empresa);
                log.info("Empresa atualizada com sucesso :: ID " + empresa.getId());
                return true;
            } else {
                return false;
            }
        }

        public List<Empresa> findAll() {
            List<Empresa> empresa = this.empresaRepository.findAll();
            return empresa;
        }

        public Boolean delete(Long id) {
            Empresa empresa = this.findById(id);
            if (empresa != null) {
                log.info("Deletando empresa :: ID " + id);
                this.empresaRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        }
    }

