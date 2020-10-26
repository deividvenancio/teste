package br.com.teste.demo.controller;

import br.com.teste.demo.dto.FuncionarioDepartamentoDTO;
import br.com.teste.demo.interceptor.JsonResponse;
import br.com.teste.demo.service.FuncionarioDepartamentoService;
import br.com.teste.demo.util.Beans;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/funcionario_departamento")
@Slf4j
public class FuncionarioDepartamentoController {

    @Autowired
    private FuncionarioDepartamentoService funcionarioDepartamentoService;

    @Autowired
    private Beans beans;

    @PostMapping(value = "")
    public JsonResponse save(@RequestBody FuncionarioDepartamentoDTO dto) {
        return JsonResponse.ok(funcionarioDepartamentoService.save(dto), "FuncionarioDepartamento created");
    }

    @PutMapping(value = "/{id}")
    public JsonResponse update(@RequestBody FuncionarioDepartamentoDTO dto,
                               @PathVariable("id") String id) {

        dto.setId(beans.parseLong(id));
        funcionarioDepartamentoService.update(dto);
        return JsonResponse.ok(funcionarioDepartamentoService.save(dto), "FuncionarioDepartamento updated");
    }

    @DeleteMapping(value = "/{id}")
    public JsonResponse delete(@PathVariable("id") String id) {
        funcionarioDepartamentoService.delete(beans.parseLong(id));
        return JsonResponse.ok("FuncionarioDepartamento excluido com sucesso");
    }

    @GetMapping(value = "")
    public JsonResponse getById(@PathVariable("id") String id) {
        return JsonResponse.ok(funcionarioDepartamentoService.getById(beans.parseLong(id)));
    }

    @GetMapping(value = "/all")
    public JsonResponse getAll() {
        return JsonResponse.ok(funcionarioDepartamentoService.getAll());
    }

    @GetMapping(value = "/all/by/departamento/{departamento}")
    public JsonResponse getAllByDepartamentoName(@PathVariable("departamento") String departamentoName) {
        return JsonResponse.ok(funcionarioDepartamentoService.getByDepartamento(departamentoName));
    }

    @GetMapping(value = "/all/by/funcionarioId/{funcionarioId}")
    public JsonResponse getAllByFuncionarioName(@PathVariable("funcionarioId") String funcionarioId) {
        return JsonResponse.ok(funcionarioDepartamentoService.getByFuncionarioId(beans.parseLong(funcionarioId)));
    }
}
