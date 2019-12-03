package tasks;

import common.Person;
import common.Task;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 implements Task {

  //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  public List<String> getNames(List<Person> persons) {
    //по идее тут мы не должны думать о том, список какого размера мы получили
    return persons.stream()
            .skip(1)
            .map(Person::getFirstName)
            .collect(Collectors.toList());
  }

  //ну и различные имена тоже хочется
  public Set<String> getDifferentNames(List<Person> persons) {
    //в Set и так будут уникальные имена
    //так же можно не вызывать метод getNames, чтобы пропустить доп перенос имен в лист, а затем в сет
    return persons.stream()
            .map(Person::getFirstName)
            .collect(Collectors.toSet());
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  //можно переименовать имя в getFullName, но т.к. это метод апи, мы не можем просто так его менять )
  public String convertPersonToString(Person person) {
    //добавлена проверка на null
    //кажется, там была очепятка при получении отчества
    //ну и в целом это было нормально описано, т.к. любое из этих значений может быть null
    //так же был вариант с String.join(...), но тот конкатенирует null к строке
    String result = "";
    if(person != null) {
      result = (person.getSecondName() != null) ? person.getSecondName() : result;
      result = (person.getFirstName() != null) ? result + " " + person.getFirstName() : result;
      result = (person.getMiddleName() != null) ? result + " " + person.getMiddleName() : result;
    }
    return result;
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    //по идее id персон должны быть уникальными
    return persons.stream()
            .collect(Collectors.toMap(Person::getId, this::convertPersonToString));
  }

  // есть ли совпадающие в двух коллекциях персоны?
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    //за меня уже придумали проверку совпадающих элементов коллекций :)
    return !Collections.disjoint(persons1, persons2);
  }

  //Выглядит вроде неплохо...
  public long countEven(Stream<Integer> numbers) {
    //для подсчета итераций можно воспользоваться методами stream
    return numbers.filter(num -> num % 2 == 0).count();
  }

  @Override
  public boolean check() {
    //ну, в целом фейл таски исправлен :D
    return true;
  }
}
