package br.com.teste.demo.service.impl;

import br.com.teste.demo.dto.DepartamentoDTO;
import br.com.teste.demo.dto.FuncionarioDTO;
import br.com.teste.demo.dto.FuncionarioDepartamentoDTO;
import br.com.teste.demo.model.Departamento;
import br.com.teste.demo.model.Funcionario;
import br.com.teste.demo.model.FuncionarioDepartamento;
import br.com.teste.demo.repository.FuncionarioDepartamentoRepository;
import br.com.teste.demo.service.DepartamentoService;
import br.com.teste.demo.service.FuncionarioDepartamentoService;
import br.com.teste.demo.service.FuncionarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioDepartamentoServiceImpl implements FuncionarioDepartamentoService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FuncionarioDepartamentoRepository funcionarioDepartamentoRepository;

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private FuncionarioService funcionarioService;

    @Override
    public FuncionarioDepartamentoDTO save(FuncionarioDepartamentoDTO dto) {
        FuncionarioDepartamento entity = montarEntity(dto);
        entity.setAtivo(true);

        Optional<FuncionarioDepartamento> funcionarioDepartamentoOld = funcionarioDepartamentoRepository
                .findByFuncionarioAndAtivo(entity.getFuncionario(),true);

        if (funcionarioDepartamentoOld.isPresent()) {
            funcionarioDepartamentoOld.get().setAtivo(false);
            funcionarioDepartamentoRepository.save(funcionarioDepartamentoOld.get());
        }

        return montarDTO(funcionarioDepartamentoRepository.save(entity));
    }

    @Override
    public FuncionarioDepartamentoDTO update(FuncionarioDepartamentoDTO dto) {
        findByIdInterno(dto.getId());
        return montarDTO(funcionarioDepartamentoRepository.save(montarEntity(dto)));
    }

    @Override
    public FuncionarioDepartamento findByIdInterno(Long id) {
        return funcionarioDepartamentoRepository.findById(id).orElseThrow( () ->
                new RuntimeException("FuncionarioDepartamento não encontrado"));
    }

    @Override
    public void delete(Long id) {
        FuncionarioDepartamento cargo = findByIdInterno(id);
        funcionarioDepartamentoRepository.delete(cargo);
    }

    @Override
    public FuncionarioDepartamentoDTO montarDTO(FuncionarioDepartamento entity) {
        FuncionarioDepartamentoDTO dto = modelMapper.map(entity, FuncionarioDepartamentoDTO.class);
        FuncionarioDTO funcionarioDTO = funcionarioService.montarDTO(entity.getFuncionario());
        DepartamentoDTO departamentoDTO = departamentoService.montarDTO(entity.getDepartamento());

        dto.setFuncionarioDTO(funcionarioDTO);
        dto.setDepartamentoDTO(departamentoDTO);

        return dto;
    }

    @Override
    public List<FuncionarioDepartamentoDTO> getAll() {
        return funcionarioDepartamentoRepository.findAll().stream().map(this::montarDTO).collect(Collectors.toList());
    }

    @Override
    public List<FuncionarioDepartamentoDTO> getByDepartamento(String departamentoName) {
        Departamento departamento = departamentoService.findByName(departamentoName);
        return funcionarioDepartamentoRepository.findByDepartamentoAndAtivo(departamento, true)
                .stream()
                .map(this::montarDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FuncionarioDepartamentoDTO> getByFuncionarioId(Long funcionarioId) {
        Funcionario funcionario = funcionarioService.findByIdInterno(funcionarioId);
        return funcionarioDepartamentoRepository.findByFuncionario(funcionario)
                .stream()
                .map(this::montarDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FuncionarioDepartamentoDTO getById(Long id) {
        return montarDTO(findByIdInterno(id));
    }

    private FuncionarioDepartamento montarEntity(FuncionarioDepartamentoDTO dto) {

        FuncionarioDepartamento entity = modelMapper.map(dto, FuncionarioDepartamento.class);
        Departamento departamento = departamentoService.findByIdInterno(dto.getDepartamentoDTO().getId());
        Funcionario funcionario = funcionarioService.findByIdInterno(dto.getFuncionarioDTO().getId());

        entity.setFuncionario(funcionario);
        entity.setDepartamento(departamento);
        return entity;
    }
}
