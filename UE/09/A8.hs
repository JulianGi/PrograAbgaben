data Rosebush = Rose | Cut | Stalk (Rosebush) | Fork (Rosebush) (Rosebush) deriving Show

generate :: Rosebush
generate = Stalk
	(Fork
		(Stalk
			(Stalk
				(Fork
					(Stalk
						(Stalk
							(Stalk
								generate
							)
						)
					)
					(Stalk
						Rose
					)
				)
			)
		)
		(Stalk
			(Fork
				(Stalk
					Rose
				)
				(Stalk
					generate
				)
			)
		)
	)

