package br.com.codezera.service;

import br.com.codezera.model.Funcionario;
import br.com.codezera.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Lucas Copque
 * @since 30/10/2021
 */

@Log
@RequiredArgsConstructor
@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public Funcionario save(Funcionario funcionario) {
        funcionario = this.funcionarioRepository.save(funcionario);
        log.info("Usuário criado com sucesso :: ID " + funcionario.getId());
        return funcionario;
    }

    public Funcionario findById(Long id) {
        Optional<Funcionario> optionalFuncionario = this.funcionarioRepository.findById(id);
        if (optionalFuncionario.isPresent()) {
            log.info("Buscando usuário :: ID " + id);
            return optionalFuncionario.get();
        } else {
            log.info("Usuário não encontrado :: ID " + id);
            return null;
        }
    }

    public Boolean update(Long id, Funcionario funcionarioUpdated) {
        Funcionario funcionario = this.findById(id);
        if (funcionario != null) {
            funcionario.setNome(funcionarioUpdated.getNome());
            funcionario.setProfissao(funcionario.getProfissao());
            funcionario.setSalario(funcionarioUpdated.getSalario());
            this.funcionarioRepository.save(funcionario);
            log.info("Usuário atualizado com sucesso :: ID " + funcionario.getId());
            return true;
        } else {
            return false;
        }
    }

    public List<Funcionario> findAll() {
        List<Funcionario> funcionarios = this.funcionarioRepository.findAll();
        return funcionarios;
    }

    public Boolean delete(Long id) {
        Funcionario funcionario = this.findById(id);
        if (funcionario != null) {
            log.info("Deleta usuário :: ID " + id);
            this.funcionarioRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
