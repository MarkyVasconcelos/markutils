package mark.utils.validator;

public @interface Validate {
	String condition();

	String conditionParam();

	boolean required() default false;
}
