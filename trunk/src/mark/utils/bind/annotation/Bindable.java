package mark.utils.bind.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import mark.utils.bean.DefaultFormatter;
import mark.utils.bean.Formatter;
import mark.utils.el.handler.FieldAccessHandler;
import mark.utils.el.handler.FieldHandler;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Bindable {
	String field();

	Class<? extends FieldAccessHandler> handler() default FieldHandler.class;

	Class<? extends Formatter> formatter() default DefaultFormatter.class;
}
