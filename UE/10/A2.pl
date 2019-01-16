%a)
istUnterkunft(wh).
istUnterkunft(bh).
istUnterkunft(sh).
istUnterkunft(ph).
istUnterkunft(jh).
istUnterkunft(fb).

hatEinenStern(ph).
hatZweiSterne(sh).
hatDreiSterne(jh).
hatVierSterne(wh).
hatFuenfSterne(fb).
hatFuenfSterne(bh).

%c)
hatEinenSternWeniger(X,Y) :- hatVierSterne(X), hatFuenfSterne(Y).
hatEinenSternWeniger(X,Y) :- hatDreiSterne(X), hatVierSterne(Y).
hatEinenSternWeniger(X,Y) :- hatZweiSterne(X), hatDreiSterne(Y).
hatEinenSternWeniger(X,Y) :- hatEinenStern(X), hatZweiSterne(Y).

%e)
hatWenigerSterne(X,Y) :-  hatEinenSternWeniger(X,Y).
hatWenigerSterne(X,Y) :- hatEinenSternWeniger(X,Z), hatWenigerSterne(Z,Y).