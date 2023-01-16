package msv.epptec.service;

import jakarta.servlet.ServletException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RestEpptecServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void testAddUser() throws Exception {
        this.mockMvc.perform(post("/addUser").param("name", "jmenoTest").param("surname", "restPrijmeni").param("birthNumber", "8105071122"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void testGetUser() throws Exception {
        this.mockMvc.perform(get("/getUser").param("birthNumber", "8105071122"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    @Order(3)
    public void testDelUser() throws Exception {
        this.mockMvc.perform(delete("/delUser").param("birthNumber", "8105071122"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    @Order(4)
    public void testGetUserMissing() {
        assertThrows(ServletException.class, () -> this.mockMvc.perform(get("/getUser").param("birthNumber", "8105071122"))
                .andDo(print()));
    }

}
