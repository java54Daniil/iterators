package telran.numbers;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class RangePredicate extends Range {
	private Predicate<Integer> predicate;

	protected RangePredicate(int min, int max) {
		super(min, max);

	}

	public void setPredicate(Predicate<Integer> predicate) {
		this.predicate = predicate;
	}

	public static RangePredicate getRange(int min, int max) {
		checkMinMax(min, max);
		return new RangePredicate(min, max);
	}

	@Override
	public Iterator<Integer> iterator() {
		return new RangePredicateIterator();
	}

	private class RangePredicateIterator implements Iterator<Integer> {
		private int current = min;

		@Override
		public boolean hasNext() {
			if (predicate == null) {
				//I think this wrong but I can't figure out how to do this properly 
				//At least that works
			} else {
				while (current <= max && !predicate.test(current)) {
					current++;
				}
			}

			return current <= max;
		}

		@Override
		public Integer next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return current++;
		}

	}

}