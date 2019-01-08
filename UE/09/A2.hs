
-- a)
data VariableName = X | Y deriving Show

getValue :: VariableName -> Int
getValue X = 5
getValue Y = 13

-- b)
data Expression = Constant Int | Variable VariableName | Add Expression Expression |  Multiply Expression Expression deriving Show

-- c)
evaluate :: Expression -> Int
evaluate (Constant c) = c
evaluate (Variable v) = getValue v
evaluate (Add ex1 ex2) = (evaluate ex1) + (evaluate ex2)
evaluate (Multiply ex1 ex2) = (evaluate ex1) * (evaluate ex2)

-- d)
tryOptimize :: Expression -> Expression
tryOptimize (Add (Constant c1) (Constant c2)) = Constant (c1 + c2)
tryOptimize (Multiply (Constant c1) (Constant c2)) = Constant (c1 * c2)
tryOptimize ex = ex

-- e)
evaluatePartially :: Expression -> Expression
evaluatePartially (Add ex1 ex2) = tryOptimize (Add (evaluatePartially ex1) (evaluatePartially ex2))
evaluatePartially (Multiply ex1 ex2) = tryOptimize (Multiply (evaluatePartially ex1) (evaluatePartially ex2))
evaluatePartially ex = ex

-- Example Provided
exampleExpression = Add
                        ( Add
                            ( Constant 20)
                            ( Constant 17))
                        ( Add
                            ( Variable X )
                            ( Multiply
                                ( Add
                                    ( Constant 14)
                                    ( Constant 7))
                                ( Constant 2)))