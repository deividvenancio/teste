package br.com.teste.demo.controller;

import br.com.teste.demo.dto.DepartamentoDTO;
import br.com.teste.demo.interceptor.JsonResponse;
import br.com.teste.demo.service.DepartamentoService;
import br.com.teste.demo.util.Beans;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/departamento")
@Slf4j
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private Beans beans;

    @PostMapping(value = "")
    public JsonResponse save(@RequestBody DepartamentoDTO dto) {
        return JsonResponse.ok(departamentoService.save(dto), "Departamento created");
    }

    @PutMapping(value = "/{id}")
    public JsonResponse update(@RequestBody DepartamentoDTO dto,
                               @PathVariable("id") String id) {

        dto.setId(beans.parseLong(id));
        departamentoService.update(dto);
        return JsonResponse.ok(departamentoService.save(dto), "Departamento updated");
    }

    @DeleteMapping(value = "/{id}")
    public JsonResponse delete(@PathVariable("id") String id) {
        departamentoService.delete(beans.parseLong(id));
        return JsonResponse.ok("Departamento excluido com sucesso");
    }

    @GetMapping(value = "")
    public JsonResponse getById(@PathVariable("id") String id) {
        return JsonResponse.ok(departamentoService.getById(beans.parseLong(id)));
    }

    @GetMapping(value = "/all")
    public JsonResponse getAll() {
        return JsonResponse.ok(departamentoService.getAll());
    }

}
