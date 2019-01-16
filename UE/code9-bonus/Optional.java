import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.graalvm.compiler.core.common.type.ArithmeticOpTable.Op;

public class Optional<T> {
	public static <T> Optional<T> empty() {
		return new Optional<>(null);
	}

	public static <T> Optional<T> present(T value) {
		if (value != null) {
			return new Optional<>(value);
		} else {
			throw new IllegalArgumentException("parameter cannot be null");
		}
	}

	private final T value;

	public Optional(T value) {
		this.value = value;
	}

	public boolean isPresent() {
		return value != null;
	}

	public T get() {
		if (value != null) {
			return value;
		} else {
			throw new IllegalStateException("cannot get empty value");
		}
	}

	public <R> Optional<R> map(Function<T, R> mapper) {
		if (isPresent()){
			return Optional.present(mapper.apply(value));
		}
		return Optional.empty();
	}

	public Optional<T> filter(Predicate<T> tester) {
		if (tester.test(value)){
			return this;
		}
		return Optional.empty();
	}

	public <R> R fold(Function<T, R> presentMapper, Supplier<R> emptyReplacer) {
		if (isPresent()){
			return presentMapper.apply(value);
		}
		return emptyReplacer.get();
	}
}

