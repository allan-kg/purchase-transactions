package kg.allan.purchasetransactions.controller;

import kg.allan.purchasetransactions.controller.PurchaseTransactionController;
import kg.allan.purchasetransactions.dto.PurchaseTransactionDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 *
 * @author allan
 */
public class PurchaseTransactionDTOModel implements RepresentationModelAssembler<PurchaseTransactionDTO, EntityModel<PurchaseTransactionDTO>>{

    @Override
    public EntityModel<PurchaseTransactionDTO> toModel(PurchaseTransactionDTO dto) {
        return EntityModel.of(dto,
        linkTo(methodOn(PurchaseTransactionController.class).one(dto.getId())).withSelfRel(),
        linkTo(methodOn(PurchaseTransactionController.class).all()).withRel("orders"));
    }
    
}
