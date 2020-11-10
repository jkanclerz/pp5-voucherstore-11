package pl.jkanclerz.voucherstore.crm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
