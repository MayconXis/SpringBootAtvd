import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/SpringBoot/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoServico ProdutoServico;

    @GetMapping
    public List<Produto> listar() {
        return ProdutoServico.listarTodos();
    }

    @PostMapping
    public Produto criar(@RequestBody Produto produto) {
        return ProdutoServico.salvar(produto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody Produto produto) {
        produto.setId(id);
        Produto updatedProduto = ProdutoServico.save(produto);
        return ResponseEntity.ok(updatedProduto);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscar(@PathVariable Long id) {
        Produto produto = ProdutoServico.buscarPorId(id);
        return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        ProdutoServico.deletar(id);
        return ResponseEntity.noContent().build();
    }
}