package br.edu.ifg.numbers.carroapi.service;

import br.edu.ifg.numbers.carroapi.model.Carro;
import br.edu.ifg.numbers.carroapi.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public List<Carro> findAll() {
        return carroRepository.findAll();
    }

    public Optional<Carro> findById(String placa) {
        return carroRepository.findById(placa);
    }

    public Carro save(Carro carro) {
        return carroRepository.save(carro);
    }

    public void deleteById(String placa) {
        carroRepository.deleteById(placa);
    }
}