package drj.euler;

import java.io.File;

public class ProblemRunner {

	public static void main(String[] args) throws Exception {
		String classPrefix = "drj.euler.problems.";
		File problemsDir = new File("src/drj/euler/problems");
		for (File f : problemsDir.listFiles()) {
			String className = f.getName().replace(".java", "");
			Class<?> c = null;
			try {
				c = Class.forName(classPrefix + className);
			} catch (Exception e1) {
				System.out.println(f.getName() + " is not a java file.");
				continue;
			}
			if (c.isAnnotationPresent(Answer.class)) {
				try {
					Problem p = (Problem) (c.newInstance());
					System.out.println(p);
				} catch (ClassCastException e) {
					System.out.println(className + " must extend Problem");
				} catch (Exception e) {
					System.out.println(className + " threw "
							+ e.getClass().getSimpleName());
				}
			} else {
				System.out.println(className + " not solved.");
			}
		}
	}
}
