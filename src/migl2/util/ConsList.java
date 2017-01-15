package migl2.util;

import java.util.function.Function;

public interface ConsList<E> {

	/**
	 * Insert a new element e in front of the list.
	 * 
	 * @param e
	 *            an element.
	 */
	ConsList<E> prepend(E e);

	/**
	 * Insert a new element e at the end of the list
	 * 
	 * @param e
	 *            an element
	 */
	ConsList<E> append(E e);

	/**
	 * Check if the list is empty or not.
	 * 
	 * @return
	 */
	boolean isEmpty();

	/**
	 * Retrieve the first element of the list.
	 * 
	 */
	E car();

	/**
	 * Return the sublist corresponding to all but the first element.
	 * 
	 */
	ConsList<E> cdr();

	/**
	 * Returns the size of the list (the number of elements it contains).
	 */
	int size();

	/**
	 * Create a new list by applying a function to each element of the list.
	 * 
	 * @param f
	 *            a function
	 * @return a list where each element is the result of applying f to an
	 *         element of the original list.
	 */
	<T> ConsList<T> map(Function<E, T> f);

}
