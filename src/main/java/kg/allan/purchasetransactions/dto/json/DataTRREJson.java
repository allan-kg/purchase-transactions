package kg.allan.purchasetransactions.dto.json;

import jakarta.json.bind.annotation.JsonbProperty;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author allan
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataTRREJson {
     @JsonbProperty("data")
    private List<CountryTRREJson> data;
}
