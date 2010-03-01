package mark.utils.el.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import mark.utils.bean.DefaultFormatter;
import mark.utils.el.handler.FieldHandler;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Resolvable {
	public String colName() default "";

	public Class<?> formatter() default DefaultFormatter.class;

	public Class<?> accessMethod() default FieldHandler.class;
}
