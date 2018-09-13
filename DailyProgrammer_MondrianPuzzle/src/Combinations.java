import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Combinations {
	
	public Combinations() {}
	
	public <T> List<Collection<T>> get(List<T> list) {
		return generate(list).collect(Collectors.toList());
	}
	
	public <T> List<List<T>> get(List<T> list, Predicate<List<T>> predicate) {
		return generate(list)
				.filter(predicate)
				.collect(Collectors.toList());
	}
	
	public <T> List<List<T>> get(List<T> list, int elements) {
		return generate(list)
				.filter(l -> l.size() == elements)
				.collect(Collectors.toList());
	}
	
	public <T> List<List<T>> get(List<T> list, Predicate<List<T>> predicate, int elements) {
		return generate(list)
				.filter(l -> l.size() == elements)
				.filter(predicate)
				.collect(Collectors.toList());
	}
	
	private <T> Stream<List<T>> generate(List<T> list) {
		return generate(list, Collections.emptyList());
	}

	private <T> Stream<List<T>> generate(List<T> list, List<T> combo) {
		return IntStream.range(0, list.size()).boxed()
				.flatMap(i -> {
					List<T> newCombo = createNewCombo(combo, list.get(i));
					return Stream.concat(Stream.of(newCombo),
							generate(list.subList(i+1, list.size()), newCombo).parallel());
				});
	}

	private <T> List<T> createNewCombo(List<T> combo, T t) {
		return Stream.concat(combo.stream(), Stream.of(t)).collect(Collectors.toList());
	}
}
