import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServico {
    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    public List<Produto> listarTodos() {
        return produtoRepositorio.findAll();
    }

    public Produto salvar(Produto produto) {
        return produtoRepositorio.save(produto);
    }

    public Produto buscarPorId(Long id) {
        return produtoRepositorio.findById(id).orElse(null);
    }


    public void deletar(Long id) {
        produtoRepositorio.deleteById(id);
    }
}