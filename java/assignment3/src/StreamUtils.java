import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamUtils {

    public static <T> List<T> filterList(List<T> list, Predicate<T> predicate) {
        return list.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
