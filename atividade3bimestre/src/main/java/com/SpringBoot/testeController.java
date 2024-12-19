import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
public class testeController {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testListarProdutos() throws Exception {
        mockMvc.perform(get("/api/produtos"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCriarProduto() throws Exception {
        String produtoJson = "{\"nome\": \"Produto A\", \"descricao\": \"Descrição do Produto A\", \"preco\": 100.00, \"quantidade\": 10}";

        mockMvc.perform(post("/api/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(produtoJson))
                .andExpect(status().isOk());
    }
}