package com.danielqueiroz.restaurantesystem.api.model.input;

import com.danielqueiroz.restaurantesystem.domain.model.Mesa;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class PedidoInput {

    @Valid
    @NotNull
    private MesaIdInput mesaIdInput;

    @Valid
    @Size(min = 1)
    @NotNull
    private List<ItemPedidoInput> itens;

}
