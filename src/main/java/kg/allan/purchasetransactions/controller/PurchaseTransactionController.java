/*
 * PurchaseTransactionController
 *
 * v1.0
 *
 * 2023
 *
 * Author: Allan Krama Guimarães
 */

package kg.allan.purchasetransactions.controller;

import java.util.List;
import java.util.stream.Collectors;
import kg.allan.purchasetransactions.dto.PurchaseWithExchangeDTO;
import kg.allan.purchasetransactions.dto.PurchaseTransactionDTO;
import kg.allan.purchasetransactions.exception.ConversionFailedException;
import kg.allan.purchasetransactions.exception.ElementNotFoundException;
import kg.allan.purchasetransactions.exception.FetchFailedException;
import kg.allan.purchasetransactions.exception.InvalidPurchaseTransactionException;
import kg.allan.purchasetransactions.exception.JsonParseException;
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
 * @author Allan Krama Guimarães
 */
@RestController
public class PurchaseTransactionController {

    @Autowired
    private PurchaseTransactionService service;

    @Autowired
    private PurchaseTransactionDTOModel assembler;

    @Autowired
    private PurchaseWithExchangeDTOModel currencyAssembler;

    @PostMapping("/transaction/new")
    ResponseEntity<?> newPurchaseTransaction(@RequestBody PurchaseTransactionDTO newPurchaseTransaction) {
        try {
            EntityModel<PurchaseTransactionDTO> entityModel = assembler.toModel(service.newPurchaseTransaction(newPurchaseTransaction));

            return ResponseEntity
                    .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);
        } catch (InvalidPurchaseTransactionException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not create new purchase transaction : " + e.getMessage(), e);
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

    @GetMapping("/transaction/{id}/{country}")
    EntityModel<PurchaseWithExchangeDTO> convert(@PathVariable Integer id, @PathVariable String country) {
        try {
            PurchaseWithExchangeDTO transaction = service.convert(id, country)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find purchase transaction \"" + id + "\""));
            return currencyAssembler.toModel(transaction);
        } catch (FetchFailedException | JsonParseException | ConversionFailedException | ElementNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not retrieve purchase transaction with exchange : " + e.getMessage(), e);
        }
    }

    @GetMapping("/transactions/{country}")
    CollectionModel<EntityModel<PurchaseWithExchangeDTO>> convertAll(@PathVariable String country) {
        try {
            List<EntityModel<PurchaseWithExchangeDTO>> transactions = service.convertAll(country).stream()
                    .parallel()
                    .map(currencyAssembler::toModel)
                    .collect(Collectors.toList());

            return CollectionModel.of(transactions, linkTo(methodOn(PurchaseTransactionController.class).all()).withSelfRel());
        } catch (FetchFailedException | JsonParseException | ConversionFailedException | ElementNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not retrieve purchase transactions with exchange : " + e.getMessage(), e);
        }
    }

}
