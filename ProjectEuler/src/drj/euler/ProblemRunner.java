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
				Problem p = (Problem) (c.newInstance());
				System.out.println(p);
			}
		}
	}
}
