package br.com.teste.demo.service.impl;

import br.com.teste.demo.dto.FuncionarioDTO;
import br.com.teste.demo.model.Cargo;
import br.com.teste.demo.model.Funcionario;
import br.com.teste.demo.repository.FuncionarioRepository;
import br.com.teste.demo.service.CargoService;
import br.com.teste.demo.service.FuncionarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CargoService cargoService;

    @Override
    public FuncionarioDTO save(FuncionarioDTO dto) {
        return montarDTO(funcionarioRepository.save(montarEntity(dto)));
    }

    @Override
    public FuncionarioDTO update(FuncionarioDTO dto) {
        findByIdInterno(dto.getId());
        return montarDTO(funcionarioRepository.save(montarEntity(dto)));
    }

    @Override
    public Funcionario findByIdInterno(Long id) {
        return funcionarioRepository.findById(id).orElseThrow( () ->
                new RuntimeException("Funcionario não encontrado"));
    }

    @Override
    public void delete(Long id) {
        Funcionario funcionario = findByIdInterno(id);
        funcionarioRepository.delete(funcionario);
    }

    @Override
    public FuncionarioDTO montarDTO(Funcionario entity) {
        FuncionarioDTO dto = modelMapper.map(entity, FuncionarioDTO.class);
        dto.setCargoDTO(cargoService.montarDTO(entity.getCargo()));
        return dto;
    }

    @Override
    public List<FuncionarioDTO> getAll() {
        return funcionarioRepository.findAll().stream().map(this::montarDTO).collect(Collectors.toList());
    }

    @Override
    public FuncionarioDTO getById(Long id) {
        return montarDTO(findByIdInterno(id));
    }

    private Funcionario montarEntity(FuncionarioDTO dto) {
        Funcionario funcionario = modelMapper.map(dto, Funcionario.class);
        Cargo cargo = cargoService.findByIdInterno(dto.getCargoDTO().getId());

        funcionario.setCargo(cargo);
        return funcionario;
    }
}
