package pe.devstream.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@Setter
@ToString
@AllArgsConstructor
public class HeaderRequest {
  private String transactionId;
  private String applicationName;
  private String userConsumerId;
}
