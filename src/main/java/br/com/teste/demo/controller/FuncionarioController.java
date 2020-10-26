package br.com.teste.demo.controller;

import br.com.teste.demo.dto.FuncionarioDTO;
import br.com.teste.demo.interceptor.JsonResponse;
import br.com.teste.demo.service.FuncionarioService;
import br.com.teste.demo.util.Beans;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/funcionario")
@Slf4j
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private Beans beans;

    @PostMapping(value = "")
    public JsonResponse save(@RequestBody FuncionarioDTO dto) {
        return JsonResponse.ok(funcionarioService.save(dto), "Funcionario created");
    }

    @PutMapping(value = "/{id}")
    public JsonResponse update(@RequestBody FuncionarioDTO dto,
                               @PathVariable("id") String id) {

        dto.setId(beans.parseLong(id));
        funcionarioService.update(dto);
        return JsonResponse.ok(funcionarioService.save(dto), "Funcionario updated");
    }

    @DeleteMapping(value = "/{id}")
    public JsonResponse delete(@PathVariable("id") String id) {
        funcionarioService.delete(beans.parseLong(id));
        return JsonResponse.ok("Funcionario excluido com sucesso");
    }

    @GetMapping(value = "")
    public JsonResponse getById(@PathVariable("id") String id) {
        return JsonResponse.ok(funcionarioService.getById(beans.parseLong(id)));
    }

    @GetMapping(value = "/all")
    public JsonResponse getAll() {
        return JsonResponse.ok(funcionarioService.getAll());
    }

}
