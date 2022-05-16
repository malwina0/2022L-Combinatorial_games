# Gry kombinatoryczne
JAKBYŚCIE PISALI KOD TO SPÓJRZCIE DO DOKUMENTACJI TEORETYCZNEJ NA STRATEGIE KOMPUTERA, TAM JEST OPISANE CO JUŻ JEST I DALSZA IDEA, MOŻECIE WPROWADZIĆ ZMIANY ALE FAJNIE BY BYŁO TO OPISAC W TEJ DOKUMENTACJI

dokumentacja teoretyczna: https://www.overleaf.com/8929169113mrxxxkjfwqny

dokumentacja końcowa: https://www.overleaf.com/2836256242ychddkxfyphp

Do fixnięcia:
1. Generowanie listy wszystkich k-elementowych


Pre Game:
1. Generuj liste MySetów k-elementowych
2. Stwórz globalne sety pokolorowanych przez komputer i przez gracza

Algorytm pierwszego ruchu:
1. Wybierz niepokolorowaną liczbe występującą w największej liczbie MySetów. Jeżeli takiej nie ma to losowo (?). Zapisz liczbe pod zmienną `x`
2. Zupdateuj wszystkie MySety, w których ta liczba występuje i oznacz ją jako pokolorowaną

Algorytm kolejnych ruchów:
1. Sprawdź czy blokować (czy gracz jest o 1 od ułożenia jakiegoś z ciągów), jeśli tak przejdź do 5
2. Wybierz MySety z najmniejszą pozostałą liczbą liczb do pokolorowania, takie, że żadna z liczb nie jest pokolorowana przez gracza
3. Wybierz niepokolorowaną liczbę występującą w największej liczbie wybranych MySetów.
4. Zupdateuj wszystkie MySety, w których ta liczba występuje i oznacz ją jako pokolorowaną, przejdź do 7
5. Mając MySet, w którym graczowi brakuje 1 do wygranej, wybierz tę liczbę
6. Zupdateuj wszystkie MySety, w których ta liczba występuje i oznacz ją jako pokolorowaną
7. Koniec ruchu

Algorytm gamemastera:
1. Ruch pierwszego gracza
2. Spr wygrana
3. Spr remis (jeśli lista wybranych MySetów w pkt. 2 algorytmu ruchów będzie pusta dla obu)
4. Ruch drugiego
5. Spr wygrana
6. Spr remis
