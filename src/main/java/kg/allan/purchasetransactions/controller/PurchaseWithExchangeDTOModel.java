package kg.allan.purchasetransactions.controller;

import kg.allan.purchasetransactions.dto.PurchaseWithExchangeDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.stereotype.Component;

/**
 *
 * @author allan
 */
@Component
public class PurchaseWithExchangeDTOModel implements RepresentationModelAssembler<PurchaseWithExchangeDTO, EntityModel<PurchaseWithExchangeDTO>>{

    @Override
    public EntityModel<PurchaseWithExchangeDTO> toModel(PurchaseWithExchangeDTO dto) {
        return EntityModel.of(dto,
        linkTo(methodOn(PurchaseTransactionController.class).convert(dto.getId(), dto.getCountry())).withSelfRel(),
        linkTo(methodOn(PurchaseTransactionController.class).convertAll(dto.getCountry())).withRel("all"));
    }
    
}
