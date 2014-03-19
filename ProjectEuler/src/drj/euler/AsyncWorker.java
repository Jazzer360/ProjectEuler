package drj.euler;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Function;

/**
 * A class to handle the asynchronous computation of a set of input as well as
 * the retrieval of the results of that input.
 *
 * @param <I> type of input for the computation
 * @param <O> type of output for the computation
 */
public class AsyncWorker<I, O> {

	private class ComputationTask implements Runnable {
		private boolean interrupted = false;
		@Override
		public void run() {
			try {
				while (true) {
					try {
						I in;
						synchronized (input) {
							if (shutdown && input.isEmpty()) {
								break;
							} else {
								in = input.take();
							}
						}
						O out = computation.apply(in);
						if (out != null) output.put(in, out);
					} catch (InterruptedException e) {
						interrupted = true;
					}
				}
			} finally {
				latch.countDown();
				if (interrupted) Thread.currentThread().interrupt();
			}
		}
	}

	private final Function<I, O> computation;
	private final ExecutorService exec;
	private final BlockingQueue<I> input;
	private final Map<I, O> output;
	private final CountDownLatch latch;
	private volatile boolean shutdown;

	/**
	 * Creates a new {@link AsyncWorker} with the specified computation to be
	 * performed on submitted input.
	 * 
	 * @param computation the computation to perform on supplied input
	 */
	public AsyncWorker(Function<I, O> computation) {
		this.computation = computation;
		int threads = Runtime.getRuntime().availableProcessors() + 1;
		exec = Executors.newFixedThreadPool(threads);
		input = new LinkedBlockingQueue<>();
		output = new ConcurrentHashMap<>();
		latch = new CountDownLatch(threads);
		shutdown = false;
		for (int i = 0; i < threads; i++) {
			exec.execute(new ComputationTask());
		}
	}

	/**
	 * Adds the desired input to the work queue to be computed some time in the
	 * future. If the queue is full, this method blocks until room is available.
	 * 
	 * @param input the input to run a computation on
	 * @throws InterruptedException if interrupted while waiting for room 
	 */
	public void submit(I input) throws InterruptedException {
		if (shutdown) throw new IllegalStateException(this + 
				" cannot accept work after output requested.");
		this.input.put(input);
	}

	/**
	 * Blocks until all submitted input has been processed. Tells the worker
	 * to shut down, so no more work may be submitted.
	 * 
	 * @throws InterruptedException if interrupted while waiting
	 */
	public void finish() throws InterruptedException {
		shutdown = true;
		exec.shutdown();
		latch.await();
	}

	/**
	 * Returns a map containing all associated output of a specified
	 * computation mapped to the inputs given. Blocks until the results of all
	 * submitted input are ready.
	 * 
	 * @return	the results of the computation done on the submitted input
	 * @throws InterruptedException if interrupted while waiting for results
	 */
	public Map<I, O> getOutput() throws InterruptedException {
		finish();
		return output;
	}
}
