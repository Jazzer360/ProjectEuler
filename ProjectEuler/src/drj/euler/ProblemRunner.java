package drj.euler;

import java.io.File;

public class ProblemRunner {

	public static void main(String[] args) throws Exception {
		String classPrefix = "drj.euler.problems.";
		File problemsDir = new File("src/drj/euler/problems");
		for (File f : problemsDir.listFiles()) {
			String className = f.getName().replace(".java", "");
			Class<?> c = Class.forName(classPrefix + className);
			if (c.getAnnotation(Answer.class) != null) {
				try {
					Problem p = (Problem) (c.newInstance());
					System.out.println(p);
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
