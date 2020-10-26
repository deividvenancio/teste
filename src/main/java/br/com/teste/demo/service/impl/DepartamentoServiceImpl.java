package br.com.teste.demo.service.impl;

import br.com.teste.demo.dto.DepartamentoDTO;
import br.com.teste.demo.exception.BusinessRuleException;
import br.com.teste.demo.model.Departamento;
import br.com.teste.demo.repository.DepartamentoRepository;
import br.com.teste.demo.service.DepartamentoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Override
    public DepartamentoDTO save(DepartamentoDTO dto) {
        return montarDTO(departamentoRepository.save(montarEntity(dto)));
    }

    @Override
    public DepartamentoDTO update(DepartamentoDTO dto) {
        findByIdInterno(dto.getId());
        return montarDTO(departamentoRepository.save(montarEntity(dto)));
    }

    @Override
    public Departamento findByIdInterno(Long id) {
        return departamentoRepository.findById(id).orElseThrow( () ->
                new RuntimeException("Departamento não encontrado"));
    }

    @Override
    public Departamento findByName(String departamentoName) {
        return departamentoRepository.findByName(departamentoName).orElseThrow( () ->
                new BusinessRuleException("Departamento not found"));
    }

    @Override
    public void delete(Long id) {
        Departamento departamento = findByIdInterno(id);
        departamentoRepository.delete(departamento);
    }

    @Override
    public DepartamentoDTO montarDTO(Departamento entity) {
        return modelMapper.map(entity, DepartamentoDTO.class);
    }

    @Override
    public List<DepartamentoDTO> getAll() {
        return departamentoRepository.findAll().stream().map(this::montarDTO).collect(Collectors.toList());
    }

    @Override
    public DepartamentoDTO getById(Long id) {
        return montarDTO(findByIdInterno(id));
    }

    private Departamento montarEntity(DepartamentoDTO dto) {
        return modelMapper.map(dto, Departamento.class);
    }
}
