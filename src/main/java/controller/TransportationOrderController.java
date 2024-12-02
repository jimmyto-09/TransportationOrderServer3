package es.upm.dit.apsv.transportationorderserver.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.upm.dit.apsv.transportationorderserver.repository.TransportationOrderRepository;
import es.upm.dit.apsv.transportationorderserver.model.TransportationOrder;

@RestController
public class TransportationOrderController {

    private final TransportationOrderRepository repository;
    private static final Logger log = LoggerFactory.getLogger(TransportationOrderController.class);

    public TransportationOrderController(TransportationOrderRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/transportationorders")
    public List<TransportationOrder> all() {
        return (List<TransportationOrder>) repository.findAll();
    }

    @PostMapping("/transportationorders")
    public TransportationOrder newOrder(@RequestBody TransportationOrder newOrder) {
        return repository.save(newOrder);
    }

    @GetMapping("/transportationorders/{truck}")
    public ResponseEntity<TransportationOrder> getByTruck(@PathVariable String truck) {
        Optional<TransportationOrder> order = repository.findById(truck);
        if (order.isPresent()) {
            return new ResponseEntity<>(order.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/transportationorders")
    public ResponseEntity<TransportationOrder> update(@RequestBody TransportationOrder updatedOrder) {
        TransportationOrder updated = repository.save(updatedOrder);
        if (updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/transportationorders/{truck}")
    public void deleteOrder(@PathVariable String truck) {
        repository.deleteById(truck);
    }
}

