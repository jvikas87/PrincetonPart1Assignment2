import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private int size;

	private LinkListNode front;

	private LinkListNode end;

	public Deque() {
		// construct an empty deque
		front = new LinkListNode();
		end = new LinkListNode();

		front.next = end;
		end.prev = front;
	}

	public boolean isEmpty() {
		return size == 0;
		// is the deque empty?
	}

	public int size() {
		return size;
		// return the number of items on the deque
	}

	public void addFirst(Item item) {
		validateItem(item);
		// add the item to the front
		LinkListNode next = front.next;
		LinkListNode node = new LinkListNode();
		node.item = item;
		node.next = next;
		node.prev = front;
		front.next = node;
		next.prev = node;
		size++;
	}

	private void validateItem(Item item) {
		if (item == null) {
			throw new IllegalArgumentException();
		}
	}

	public void addLast(Item item) {
		// add the item to the end
		validateItem(item);
		// add the item to the front
		LinkListNode prev = end.prev;
		LinkListNode node = new LinkListNode();
		node.item = item;
		node.next = end;
		node.prev = prev;
		end.prev = node;
		prev.next = node;
		size++;
	}

	public Item removeFirst() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		LinkListNode toBeRemoved = front.next;
		LinkListNode next = toBeRemoved.next;
		toBeRemoved.prev = null;
		toBeRemoved.next = null;
		front.next = next;
		next.prev = front;
		size--;
		return toBeRemoved.item;
		// remove and return the item from the front
	}

	public Item removeLast() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		LinkListNode toBeRemoved = end.prev;
		LinkListNode prev = toBeRemoved.prev;
		toBeRemoved.prev = null;
		toBeRemoved.next = null;
		end.prev = prev;
		prev.next = end;
		size--;
		return toBeRemoved.item;
	}

	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	public static void main(String[] args) {
	} // unit testing (optional)

	private class LinkListNode {
		private Item item;
		private LinkListNode next;
		private LinkListNode prev;
	}

	private class DequeIterator implements Iterator<Item> {

		private int iteratorSize = size;

		private int iteratorIndex;

		private LinkListNode head = front.next;

		@Override
		public boolean hasNext() {
			return iteratorIndex < iteratorSize;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			Item item = head.item;
			head = head.next;
			iteratorIndex++;
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}
}