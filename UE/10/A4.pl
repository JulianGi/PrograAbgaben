%a)
isList(nil).
isList(cons(_, Rest)) :- isList(Rest).

%b)
toPrologList(nil, []).
toPrologList(cons(N, Rest),[Head|Tail]) :- Head = N, toPrologList(Rest,Tail). 

%c)
flatten([],[]).
flatten([[]|Res], YS) :- flatten(Res, YS).
flatten([[X|XS]|Res], [X|YS]) :- flatten([XS|Res], YS).

%d)
appendElement(X,Y,Z) :- flatten([X,[Y]],Z).

%e)
reverseList([], []).
reverseList([X], [X]).
reverseList([X|XS], Y) :- reverseList(XS, Z), appendElement(Z, X, Y).

