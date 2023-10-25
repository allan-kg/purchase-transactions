package kg.allan.purchasetransactions.controller;

import java.util.List;
import java.util.stream.Collectors;
import kg.allan.purchasetransactions.dto.PurchaseTransactionDTO;
import kg.allan.purchasetransactions.exception.InvalidPurchaseTransactionException;
import kg.allan.purchasetransactions.service.PurchaseTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 *
 * @author allan
 */
@RestController
//@RequestMapping
public class PurchaseTransactionController {

    @Autowired
    private PurchaseTransactionService service;

    private final PurchaseTransactionDTOModel assembler;

    public PurchaseTransactionController(PurchaseTransactionDTOModel assembler) {
        this.assembler = assembler;
    }

    @PostMapping("/transaction/new")
    ResponseEntity<?> newPurchaseTransaction(@RequestBody PurchaseTransactionDTO newPurchaseTransaction) {
        try {
            EntityModel<PurchaseTransactionDTO> entityModel = assembler.toModel(service.newPurchaseTransaction(newPurchaseTransaction));

            return ResponseEntity
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);
        } catch (InvalidPurchaseTransactionException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not create new purchase transaction : " + e.getMessage(), e);
        }
    }

    @PostMapping("/transactions/insert_all")
    CollectionModel<EntityModel<PurchaseTransactionDTO>> insertAllPurchaseTransactions(@RequestBody List<PurchaseTransactionDTO> listPurchaseTransactions) {
        List<PurchaseTransactionDTO> insertions = service.addAll(listPurchaseTransactions);

        List<EntityModel<PurchaseTransactionDTO>> transactions = insertions.stream()
                .parallel()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(transactions,
                linkTo(methodOn(PurchaseTransactionController.class).all()).withSelfRel());
    }

    @GetMapping("/transaction/{id}")
    EntityModel<PurchaseTransactionDTO> one(@PathVariable Integer id) {

        PurchaseTransactionDTO transaction = service.find(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find purchase transaction \"" + id + "\""));

        return assembler.toModel(transaction);
    }

    @GetMapping("/transactions")
    CollectionModel<EntityModel<PurchaseTransactionDTO>> all() {

        List<EntityModel<PurchaseTransactionDTO>> transactions = service.all()
                .stream()
                .parallel()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(transactions, linkTo(methodOn(PurchaseTransactionController.class).all()).withSelfRel());
    }

}
