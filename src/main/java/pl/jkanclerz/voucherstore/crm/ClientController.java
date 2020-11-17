package pl.jkanclerz.voucherstore.crm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @PostMapping("/api/clients")
    public void addClient(@Valid @RequestBody Client client) {
        clientRepository.save(client);
    }

    @GetMapping("/api/clients")
    public List<Client> clients() {
        return clientRepository.findAll();
    }

    @DeleteMapping("/api/clients/{id}")
    public void deleteById(@Param("id") Integer id) {
        clientRepository.deleteById(id);
    }
}
