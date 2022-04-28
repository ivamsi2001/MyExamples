import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.SpringDocAnnotationsUtils;

import io.swagger.v3.core.util.AnnotationsUtils;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.core.util.PrimitiveType;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.Explode;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.QueryParameter;

@SuppressWarnings("rawtypes")
public class ParameterBuilder {

	private ParameterBuilder() {
		super();
	}

	public static QueryParameter buildParameterFromDoc(Field field, Parameter parameterDoc) {
		QueryParameter parameter = new QueryParameter();
		parameter.setName(field.getName());
		parameter.setIn("query");
		if (parameterDoc == null) {
			return parameter;
		}
		if (StringUtils.isNotBlank(parameterDoc.description()))
			parameter.setDescription(parameterDoc.description());
		if (StringUtils.isNotBlank(parameterDoc.name()))
			parameter.setName(parameterDoc.name());
		if (StringUtils.isNotBlank(parameterDoc.in().toString()))
			parameter.setIn(parameterDoc.in().toString());
		if (StringUtils.isNotBlank(parameterDoc.example())) {
			try {
				parameter.setExample(Json.mapper().readTree(parameterDoc.example()));
			} catch (IOException e) {
				parameter.setExample(parameterDoc.example());
			}
		}
		if (parameterDoc.deprecated()) {
			parameter.setDeprecated(parameterDoc.deprecated());
		}
		if (parameterDoc.required()) {
			parameter.setRequired(parameterDoc.required());
		}
		if (parameterDoc.allowEmptyValue()) {
			parameter.setAllowEmptyValue(parameterDoc.allowEmptyValue());
		}
		if (parameterDoc.allowReserved()) {
			parameter.setAllowReserved(parameterDoc.allowReserved());
		}
		if (parameterDoc.content().length > 0) {
			Optional<Content> optionalContent = getContent(parameterDoc);
			optionalContent.ifPresent(parameter::setContent);
		} else {
			setSchema(parameter, parameterDoc);
		}
		setExamples(parameter, parameterDoc);
		setExtensions(parameter, parameterDoc);
		setParameterStyle(parameter, parameterDoc);
		setParameterExplode(parameter, parameterDoc);
		return parameter;
	}

	private static Optional<Content> getContent(Parameter parameterDoc) {
		return AnnotationsUtils.getContent(parameterDoc.content(), null, null, null, null, null);
	}

	private static void setSchema(QueryParameter parameter, Parameter parameterDoc) {
		if (StringUtils.isNotBlank(parameterDoc.ref())) {
			parameter.$ref(parameterDoc.ref());
		} else {
			io.swagger.v3.oas.models.media.Schema schema = null;
			try {
				schema = getSchemaWithOutArray(parameterDoc).orElse(null);
				if (schema != null && schema.getDefault() != null) {
					PrimitiveType primitiveType = PrimitiveType.fromTypeAndFormat(schema.getType(), schema.getFormat());
					if (primitiveType != null) {
						io.swagger.v3.oas.models.media.Schema<?> primitiveSchema = primitiveType.createProperty();
						primitiveSchema.setDefault(schema.getDefault());
						schema.setDefault(primitiveSchema.getDefault());
					}
				}
			} catch (Exception e) {
				//
			}
			if (schema == null) {
				schema = getSchemaWithArray(parameterDoc).orElse(null);
				if (schema != null) {
					String defaultValueStr = parameterDoc.array().arraySchema().defaultValue();
					schema.setDefault(SpringDocAnnotationsUtils.resolveDefaultValue(defaultValueStr));
				}
			}
			parameter.setSchema(schema);
		}
	}

	private static Optional<? extends Schema> getSchemaWithOutArray(Parameter parameterDoc) {
		return AnnotationsUtils.getSchema(parameterDoc.schema(), null, false,
				parameterDoc.array().schema().implementation(), null, null);
	}

	private static Optional<? extends Schema> getSchemaWithArray(Parameter parameterDoc) {
		return AnnotationsUtils.getSchema(parameterDoc.schema(), parameterDoc.array(), true,
				parameterDoc.array().schema().implementation(), null, null);
	}

	private static void setExamples(QueryParameter parameter, Parameter parameterDoc) {
		Map<String, Example> exampleMap = new HashMap<>();
		if (parameterDoc.examples().length == 1 && StringUtils.isBlank(parameterDoc.examples()[0].name())) {
			Optional<Example> exampleOptional = AnnotationsUtils.getExample(parameterDoc.examples()[0]);
			exampleOptional.ifPresent(parameter::setExample);
		} else {
			for (ExampleObject exampleObject : parameterDoc.examples()) {
				AnnotationsUtils.getExample(exampleObject)
						.ifPresent(example -> exampleMap.put(exampleObject.name(), example));
			}
		}
		if (exampleMap.size() > 0) {
			parameter.setExamples(exampleMap);
		}
	}

	private static void setExtensions(QueryParameter parameter, Parameter parameterDoc) {
		if (parameterDoc.extensions().length > 0) {
			Map<String, Object> extensionMap = AnnotationsUtils.getExtensions(parameterDoc.extensions());
			extensionMap.forEach(parameter::addExtension);
		}
	}

	private static void setParameterStyle(QueryParameter parameter, Parameter parameterDoc) {
		if (StringUtils.isNotBlank(parameterDoc.style().toString())) {
			parameter.setStyle(io.swagger.v3.oas.models.parameters.Parameter.StyleEnum.valueOf(parameterDoc.style().toString().toUpperCase()));
		}
	}

	private static void setParameterExplode(QueryParameter parameter, Parameter parameterDoc) {
		if (isExplodable(parameterDoc)) {
			if (Explode.TRUE.equals(parameterDoc.explode())) {
				parameter.setExplode(Boolean.TRUE);
			} else if (Explode.FALSE.equals(parameterDoc.explode())) {
				parameter.setExplode(Boolean.FALSE);
			}
		}
	}

	private static boolean isExplodable(Parameter parameterDoc) {
		io.swagger.v3.oas.annotations.media.Schema schema = parameterDoc.schema();
		io.swagger.v3.oas.annotations.media.ArraySchema arraySchema = parameterDoc.array();
		boolean explode = true;
		Class<?> implementation = schema.implementation();
		if (implementation == Void.class && !schema.type().equals("object") && !schema.type().equals("array") && !AnnotationsUtils.hasArrayAnnotation(arraySchema)) {
			explode = false;
		}
		return explode;
	}

}
