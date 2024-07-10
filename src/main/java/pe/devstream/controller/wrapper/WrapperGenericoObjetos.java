package pe.devstream.controller.wrapper;

import jakarta.validation.Valid;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class WrapperGenericoObjetos<T> implements Serializable {

  @Valid
  private transient T datos;
}
