package mark.utils.awt.ann;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;

import mark.utils.reflec.ClassIntrospector;
import mark.utils.reflec.ClassIntrospector.AnnotatedElement;

public class ActionManager {
	private Object comp;
	private Class<?> clazz;

	public ActionManager(Object comp) throws NoSuchMethodException {
		clazz = comp.getClass();
		this.comp = comp;
		List<AnnotatedElement<Field, Action>> annotateds = new ClassIntrospector(
				clazz).getAnnotatedDeclaredFields(Action.class);
		for (AnnotatedElement<Field, Action> ann : annotateds) {
			AbstractButton button;
			try {
				ann.getElement().setAccessible(true);
				button = (AbstractButton) (JButton) ann.getElement().get(comp);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
			Class<?> listener = ann.getAnnotation().listener();
			String method = ann.getAnnotation().method();

			if (listener.equals(ActionListener.class))
				button.addActionListener(new MethodInvokerListener(method));
			else
				try {
					if ((listener.getModifiers() & Modifier.STATIC) != 0)
						button.addActionListener((ActionListener) listener
								.newInstance());
					else {
						Constructor<?> constr = listener
								.getDeclaredConstructor(clazz);
						constr.setAccessible(true);
						button.addActionListener((ActionListener) constr
								.newInstance(comp));
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
		}
	}

	private class MethodInvokerListener implements ActionListener {
		private Method method;

		public MethodInvokerListener(String method)
				throws NoSuchMethodException {
			this.method = clazz.getDeclaredMethod(method);
			this.method.setAccessible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				method.invoke(comp);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}
}
