package drj.euler;

import java.io.File;

public class ProblemRunner {

	public static void main(String[] args) {
		String classPrefix = "drj.euler.problems.";
		File problemsDir = new File("src/drj/euler/problems");
		for (File file : problemsDir.listFiles()) {
			String className = file.getName().replace(".java", "");
			Class<?> problem = null;
			try {
				problem = Class.forName(classPrefix + className);
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
				continue;
			}
			if (problem.isAnnotationPresent(Answer.class)) {
				try {
					Problem p = (Problem) (problem.newInstance());
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
