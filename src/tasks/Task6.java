package tasks;

import common.Area;
import common.Person;
import common.Task;
import org.w3c.dom.ls.LSOutput;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Имеются
- коллекция персон Collection<Person>
- словарь Map<Integer, Set<Integer>>, сопоставляющий каждой персоне множество id регионов
- коллекция всех регионов Collection<Area>
На выходе хочется получить множество строк вида "Имя - регион". Если у персон регионов несколько, таких строк так же будет несколько
 */
public class Task6 implements Task {

  private Set<String> getPersonDescriptions(Collection<Person> persons,
                                            Map<Integer, Set<Integer>> personAreaIds,
                                            Collection<Area> areas) {

    //у меня была помощь зала, мне друг подсказывал :(
    final Map<Integer, Area> areaMap = new ConcurrentHashMap<>();
    return persons.stream()
            .flatMap(person -> {
              final Set<Integer> areaIds = personAreaIds.get(person.getId());
              if (areaIds != null) {
                return areaIds.stream()
                        .map(id -> areaMap.computeIfAbsent(id, i ->
                                areas.stream()
                                        .filter(area -> area.getId() != null && area.getId().equals(i))
                                        .findFirst()
                                        .orElse(null)))
                        .filter(Objects::nonNull)
                        .map(area -> person.getFirstName() + " - " + area.getName());
              } else {
                return Stream.empty();
              }
            })
            .collect(Collectors.toSet());
  }

  @Override
  public boolean check() {
    List<Person> persons = List.of(
        new Person(1, "Oleg", Instant.now()),
        new Person(2, "Vasya", Instant.now())
    );
    Map<Integer, Set<Integer>> personAreaIds = Map.of(1, Set.of(1, 2), 2, Set.of(2, 3));
    List<Area> areas = List.of(new Area(1, "Moscow"), new Area(2, "Spb"), new Area(3, "Ivanovo"));
    return getPersonDescriptions(persons, personAreaIds, areas)
        .equals(Set.of("Oleg - Moscow", "Oleg - Spb", "Vasya - Spb", "Vasya - Ivanovo"));
  }
}
