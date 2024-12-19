import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class testeServico {

    @InjectMocks
    private ProdutoServico ProdutoServico;

    @Mock
    private ProdutoRepositorio ProdutoRepositorio;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testeListarTodos() {
        Produto produto1 = new Produto();
        produto1.setId(1L);
        produto1.setNome("Produto A");

        Produto produto2 = new Produto();
        produto2.setId(2L);
        produto2.setNome("Produto B");

        when(produtoRepository.findAll()).thenReturn(Arrays.asList(produto1, produto2));

        List<Produto> produtos = produtoService.listarTodos();
        assertEquals(2, produtos.size());
    }

    @Test
    public void testeBuscarPorId() {
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto A");

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        Produto encontrado = produtoService.buscarPorId(1L);
        assertNotNull(encontrado);
        assertEquals("Produto A", encontrado.getNome());
    }
}