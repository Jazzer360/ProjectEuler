package drj.euler;

import java.util.Map;
import java.util.TreeMap;

public class ProblemRunner {

	public static void main(String[] args) throws Exception {
		String answersFile = "external data/answers.txt";
		Map<String, String> answers = new TreeMap<>();

		for (String line : Utility.getFileContents(answersFile)) {
			String[] entry = line.split(",");
			answers.put(entry[0], entry[1]);
		}

		for (String key : answers.keySet()) {
			String answer = answers.get(key);
			String className = "drj.euler.problems.Problem" + key;
			Problem p = (Problem) (Class.forName(className).newInstance());
			System.out.println(p.getClass().getSimpleName() + " answer: "
					+ p.getAnswer() + " - " + p.getSolveTime()
					+ (p.getAnswer().equals(answer)
							? "" : " - INCORRECT! (" + answer + ")"));
		}
	}
}
