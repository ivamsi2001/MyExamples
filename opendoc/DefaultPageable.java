
import java.util.List;

import javax.validation.constraints.Min;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DefaultPageable {

	@Min(0)
	@Parameter(description = "Zero-based page index (0..N)", schema = @Schema(type = "integer", defaultValue = "0"))
	private Integer page;

	@Min(1)
	@Parameter(description = "The size of the page to be returned", schema = @Schema(type = "integer", defaultValue = "20"))
	private Integer size;

	@Parameter(description = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.", name = "sort", array = @ArraySchema(schema = @Schema(type = "string")))
	private List<String> sort;
}
