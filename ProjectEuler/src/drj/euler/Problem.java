package drj.euler;

public abstract class Problem {

	private String answer;
	private String time;

	/**
	 * Default constructor for a problem. The problem is solved as part of the
	 * creation of the object. The answer and time required to solve may be
	 * obtained via the accessor methods.
	 */
	public Problem() {
		solve();
	}

	/**
	 * Subclasses must handle all logic related to solving the problem in this
	 * method and return the answer to the problem in string format.
	 * 
	 * @return the answer to the problem
	 */
	protected abstract String onSolve();

	private final void solve() {
		Utility.Timer t = new Utility.Timer();
		t.start();
		answer = onSolve();
		time = t.toDecimalString();
	}

	@Override
	public String toString() {
		boolean solved = false;
		boolean correct = false;
		Answer answer = getClass().getAnnotation(Answer.class);
		if (answer != null) {
			solved = true;
			correct = answer.value().equals(this.answer);
		}

		String status = "";

		if (!solved) {
			status = " - Not solved.";
		} else if (!correct) {
			status = " - INCORRECT! (" + answer.value() + ")";
		}

		return this.getClass().getSimpleName()
				+ " answer: " + this.answer
				+ " - " + this.time
				+ status;
	}
}
