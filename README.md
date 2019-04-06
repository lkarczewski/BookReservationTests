# projekt1-lkarczewski
projekt1-lkarczewski created by GitHub Classroom

Projekt 6 (25 pkt)

Napiszmy program, który będzie realizował prostą wersję systemu rezerwacji (do wyboru: książek, hoteli, restauracji itd.).

Program ma następujące wymagania:

- Załadować bazę danych z pliku tekstowego do systemu.
- Ma zwracać listę zarezerwowanych rzeczy dla danego użytkownika (warto skorzystać tutaj z HashTable).
- Ma unikać podwójnego rezerwowania rzeczy w tym samym dniu i godzinie.
- Ma być odporny na wszelkie nieprawidłowe argumenty.
- Powinien rezerwować o pełnych godzinach lub np: 10.30, 11.30 itd.
- Powinien zapisywać identyfikator zamówienia (np. adres email).
- Powinien generować potwierdzenia zamówienia (zapis do pliku lub unikalny kod).
- Powinien obsługiwać wszelkie wyjątki (rezerwacja w złym dniu, o godzinach nieczynnej restauracji itd.)
- Powinien posiadać unikalne identyfikatory dla użytkowników o podobynch danych
- Powinien posiadać system generujący różne statystyki dotyczące bazy danych
- Plik tekstowy z bazą danych może być dowolnego formatu, ale powinien zawierać nazwę miejsca, godziny otwarcia, dni oraz godziny wolne do rezerwacji, opis dodatkowy.

Pod ocenę będą brane pod uwagę następujące elementy:

- (1 pkt) Kompilacja i uruchomienie bezbłędne projektu.
- (6 pkt) Uwzględnienie powyższych wymagań.
- (8 pkt) Przypadki testowe.
- (1 pkt) Użycie różnych asercji.
- (2 pkt) Uwzględnienie wyjątków.
- (1 pkt) Zastosowanie biblioteki Hamcrest.
- (1 pkt) Pokrycie kodu.
- (1 pkt) Styl kodu.
- (1 pkt) Zastosowanie metodyki TDD.
- (1 pkt) Zastosowanie testów parametrycznych.
- (1 pkt) Zastosowanie testów parametrycznych przy użyciu plików testowych.
- (1 pkt) Użycie biblioteki AssertJ.

Ponadto dodatkowo pod uwagę będą brane następujące elementy:

- (2 pkt) Możliwość wysłania potwierdzenia dla użytkownika (poprzez email)
- (2 pkt) Możliwość zachowania kopii bazy danych

Ponadto pod ocenę jest brane również: (Brak tych elementów: -1 pkt za podpunkt od obowiązkowej punktacji zadania!)

- Historia projektu w repozytorium.
- Ocena opisu commitów
- Stan repozytorium (żeby nie był śmietnikiem!!!)
