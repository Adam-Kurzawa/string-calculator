Rozwiązanie oddzielnie zajmuje się inputem jako text i oddzielnie wyciągniętymi z inputu wartościami liczbowymi.
Klasy obsługujące input tekstowy zajmują się wycinkiem procesu:
- DelimitierFinder - szuka nagłówka i wyciąga wartości, lub używa domyślnych odstępów
- InputValidator - rzuca wyjątek jeśli input jest nie poprawny (np. kończy się znakiem separatora)
- InputTokenizer - dzieli tekst na liczby jeszcze jako String, używając znalezionych separatorów
- StringToIntParser - przekształca String w Int  
  
Klasy są schowane za fasadą.
Kalkulator otrzymuje wartości liczbowe.
Wstępnie są one:
- IntegerValidator - rzuca wyjątek dla liczb ujemnych
- IntegerFilter - pomija liczby większe niż tysiąc
