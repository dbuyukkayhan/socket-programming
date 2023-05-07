import java.util.Stack;

class Resource {
	private Stack<Integer> stack;
	private final int MAX = 5;

	public Resource() {
		stack = new Stack<>();
	}

	public synchronized void addOne() {
		try {
			while (stack.size() >= MAX)
				wait();

			stack.push((int) (Math.random() * 100));
			System.out.println("PUSHED ITEM = " + stack.peek());

			// 'Wake up' any waiting consumer...
			notifyAll();
		} catch (InterruptedException interruptEx) {
			System.out.println(interruptEx);
		}
	}

	public synchronized int takeOne() {
		int numResources = 0;
		try {
			while (stack.isEmpty())
				wait();

			numResources = stack.pop();
			System.out.println("POPED ITEM = " + numResources);

			// 'Wake up' waiting producer...
			notify();
		} catch (InterruptedException interruptEx) {
			System.out.println(interruptEx);
		}
		return numResources;
	}

	public boolean isFull() {
		return stack.size() == MAX;
	}

	public boolean isEmpty() {
		return stack.isEmpty();
	}
}

