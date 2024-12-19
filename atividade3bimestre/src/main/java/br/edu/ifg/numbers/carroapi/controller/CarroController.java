package br.edu.ifg.numbers.carroapi.controller;


import br.edu.ifg.numbers.carroapi.model.Carro;
import br.edu.ifg.numbers.carroapi.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carros")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @GetMapping
    public List<Carro> getAllCarros() {
        return carroService.findAll();
    }

    @GetMapping("/{placa}")
    public ResponseEntity<Carro> getCarroByPlaca(@PathVariable("placa") String placa) {
        return carroService.findById(placa)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Carro createCarro(@RequestBody Carro carro) {
        return carroService.save(carro);
    }

    @PutMapping("/{placa}")
    public ResponseEntity<Carro> updateCarro(@PathVariable("placa") String placa, @RequestBody Carro carro) {
        if (!carroService.findById(placa).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        carro.setPlaca(placa);
        return ResponseEntity.ok(carroService.save(carro));
    }

    @DeleteMapping("/{placa}")
    public ResponseEntity<Void> deleteCarro(@PathVariable("placa") String placa) {
        if (!carroService.findById(placa).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        carroService.deleteById(placa);
        return ResponseEntity.noContent().build();
    }
}

