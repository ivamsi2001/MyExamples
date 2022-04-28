import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import com.growdna.annotations.http.RequestModelParam;

import io.swagger.v3.core.util.PrimitiveType;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.parameters.QueryParameter;

@Component
public class OperationDocCustomizer implements OperationCustomizer {

	@Override
	public Operation customize(Operation operation, HandlerMethod handlerMethod) {
		MethodParameter[] methodParams = handlerMethod.getMethodParameters();
		for (MethodParameter methodParam : methodParams) {
			RequestModelParam annotation = methodParam.getParameterAnnotation(RequestModelParam.class);
			if (annotation != null) {
				typeToParameters(operation, methodParam.getParameterType());
			}
		}
		return operation;
	}

	private void typeToParameters(Operation operation, Class<?> type) {
		if (operation.getParameters() != null) {
			operation.getParameters().clear();
		}
		List<Field> fields = getDeclaredFields(type);
		for (Field field : fields) {
			QueryParameter parameter = buildParameter(field);
			if (parameter != null) {
				operation.addParametersItem(parameter);
			}
		}
	}
	
	private List<Field> getDeclaredFields(Class<?> cls) {
		if (cls == null || Object.class.equals(cls)) {
			return Collections.emptyList();
		}
		final List<Field> fields = new ArrayList<>();
		final Set<String> fieldNames = new HashSet<>();
		for (Field field : cls.getDeclaredFields()) {
			fields.add(field);
			fieldNames.add(field.getName());
		}
		for (Field field : getDeclaredFields(cls.getSuperclass())) {
			if (!fieldNames.contains(field.getName())) {
				fields.add(field);
			}
		}
		return fields;
	}

	private QueryParameter buildParameter(Field field) {
		QueryParameter param = null;
		Parameter paramDoc = field.getAnnotation(Parameter.class);
		boolean hidden = isHidden(paramDoc);
		if (!hidden) {
			param = ParameterBuilder.buildParameterFromDoc(field, paramDoc);
			if (param.getSchema() == null) {
				Type propType = field.getType();
				if (PrimitiveType.fromType(propType) != null) {
					param.setSchema(PrimitiveType.createProperty(propType));
				}
			}
		}
		return param;
	}

	private boolean isHidden(Parameter paramAnn) {
		boolean flag2 = paramAnn != null && paramAnn.hidden();
		boolean flag3 = paramAnn != null && paramAnn.schema() != null && paramAnn.schema().hidden();
		return flag2 || flag3;
	}
}
