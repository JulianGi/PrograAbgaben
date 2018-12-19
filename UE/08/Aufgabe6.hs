fib :: Int -> Int
fib n   | n < 1    = 0
        | n == 1    = 1
        | otherwise = fib(n-2) + fib(n-1)    



pow :: Int -> Int -> Int
pow a b | b == 0    = 1
        | otherwise = a * pow a (b-1)



modulo :: Int -> Int -> Int
modulo a b  | a == 0     = 0
            | a < 0      = a + b
            | otherwise  = modulo (a - b) b

isDiv :: Int -> Int -> Bool
isDiv a b   | modulo a b == 0   = True
            | otherwise         = False


sumUp :: [Int] -> Int
sumUp []    = 0
sumUp (x:xs) = x + sumUp xs


multLists :: [Int] -> [Int] -> [Int]
multLists (x:[]) (y:[]) = [x*y]
multLists (x:xs) (y:ys) = [x*y] ++ multLists xs ys
multLists _ _ = []


binRep :: Int -> (Int, [Int])
binRep n    | n == 0    = (0, [0])
            | n > 1     = (1, binRepHelper n)
            | n < 1     = (-1, binRepHelper ((-1)*n))


binRepHelper :: Int -> [Int]
binRepHelper n  | n == 0 = []
                | otherwise = binRepHelper (div n 2) ++ [modulo n 2]
