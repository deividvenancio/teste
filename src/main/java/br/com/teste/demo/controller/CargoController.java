package br.com.teste.demo.controller;

import br.com.teste.demo.dto.CargoDTO;
import br.com.teste.demo.interceptor.JsonResponse;
import br.com.teste.demo.service.CargoService;
import br.com.teste.demo.util.Beans;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cargo")
@Slf4j
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @Autowired
    private Beans beans;

    @PostMapping(value = "")
    public JsonResponse save(@RequestBody CargoDTO dto) {
        return JsonResponse.ok(cargoService.save(dto), "Cargo created");
    }

    @PutMapping(value = "/{id}")
    public JsonResponse update(@RequestBody CargoDTO dto,
                               @PathVariable("id") String id) {

        dto.setId(beans.parseLong(id));
        cargoService.update(dto);
        return JsonResponse.ok(cargoService.save(dto), "Cargo updated");
    }

    @DeleteMapping(value = "/{id}")
    public JsonResponse delete(@PathVariable("id") String id) {
        cargoService.delete(beans.parseLong(id));
        return JsonResponse.ok("Cargo excluido com sucesso");
    }

    @GetMapping(value = "")
    public JsonResponse getById(@PathVariable("id") String id) {
        return JsonResponse.ok(cargoService.getById(beans.parseLong(id)));
    }

    @GetMapping(value = "/all")
    public JsonResponse getAll() {
        return JsonResponse.ok(cargoService.getAll());
    }

}
