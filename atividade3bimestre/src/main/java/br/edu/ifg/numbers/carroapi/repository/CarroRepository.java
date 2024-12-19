package br.edu.ifg.numbers.carroapi.repository;

import br.edu.ifg.numbers.carroapi.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends JpaRepository<Carro, String> {
    // Métodos personalizados podem ser adicionados aqui, se necessário
}