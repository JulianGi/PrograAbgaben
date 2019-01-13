
-- a)
data Optional a = Empty | Present a deriving Show

mapOptional :: (a -> b) -> Optional a -> Optional b
mapOptional _ Empty = Empty
mapOptional f (Present o) = Present (f o)

-- b)
filterOptional :: (a -> Bool) -> Optional a -> Optional a
filterOptional f Empty = Empty
filterOptional f (Present o) = if f o then Present o else Empty 

-- c)
foldOptional :: (a -> b) -> b -> Optional a -> b
foldOptional _ x Empty = x
foldOptional f _ (Present o) = f o

-- d)
data Product = Article String Int deriving Show

isHumanEatable :: Product -> Bool
isHumanEatable (Article "Dog Food" _) = False
isHumanEatable (Article _ _) = True 

adjustPrice :: Product -> Product
adjustPrice (Article n p) = if p < 1000 then Article n (p*2) else Article n p

stringify :: Product -> String
stringify (Article n p) = "The Article named '" ++ n ++ "' costs " ++ show(p) ++ " Cents"

-- e)
filterHumanEatable :: Product -> Optional Product
filterHumanEatable a = filterOptional isHumanEatable (Present a)

adjustPriceO :: Optional Product -> Optional Product
adjustPriceO a = mapOptional adjustPrice a

stringifyO :: Optional Product -> String
stringifyO a = foldOptional stringify "This article is unavailable" a

toPriceTag :: Product -> String
toPriceTag a = stringifyO (adjustPriceO (filterHumanEatable a))
