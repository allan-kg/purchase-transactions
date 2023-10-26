/*
 * DataTRREJson
 *
 * v1.0
 *
 * 2023
 *
 * Author: Allan Krama Guimarães
 */

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
 * @author Allan Krama Guimarães
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataTRREJson {
     @JsonbProperty("data")
    private List<CountryTRREJson> data;
}
