import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] items;

	private int size;

	private int insertionIndex;

	public RandomizedQueue() {
		items = (Item[]) new Object[1];
		// construct an empty randomized queue
	}

	public boolean isEmpty() {
		return size == 0;
		// is the randomized queue empty?
	}

	public int size() {
		return size;
		// return the number of items on the randomized queue
	}

	public void enqueue(Item item) { // add the item
		if (item == null) {
			throw new IllegalArgumentException();
		}
		if (items.length == insertionIndex) {
			resize();
		}
		items[insertionIndex++] = item;
		size++;
	}

	private void resize() {
		insertionIndex = 0;
		int twice = size == 0 ? 1 : size * 2;
		Item[] newArray = (Item[]) new Object[twice];
		for (Item item : items) {
			if (item != null) {
				newArray[insertionIndex++] = item;
			}
		}
		items = newArray;
	}

	public Item dequeue() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		int random = StdRandom.uniform(insertionIndex);
		while (items[random] == null) {
			random = StdRandom.uniform(insertionIndex);
		}
		Item poppedItem = items[random];
		items[random] = null;
		size--;
		return poppedItem; // remove and return a random item
	}

	public Item sample() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		int random = StdRandom.uniform(insertionIndex);
		while (items[random] == null) {
			random = StdRandom.uniform(insertionIndex);
		}
		Item poppedItem = items[random];
		return poppedItem; // remove and return a random item
	}

	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator(); // return an independent iterator
												// over items in random order
	}

	public static void main(String[] args) {
	} // unit testing (optional)

	private class RandomizedQueueIterator implements Iterator<Item> {

		private int iteratorIndex;

		private int iteratorSize = size;

		private Item[] array;

		public RandomizedQueueIterator() {
			int index = 0;
			array = (Item[]) new Object[size];
			for (Item item : items) {
				if (item != null) {
					array[index++] = item;
				}
			}
			StdRandom.shuffle(array);
		}

		@Override
		public boolean hasNext() {
			return iteratorIndex < iteratorSize;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return array[iteratorIndex++];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}