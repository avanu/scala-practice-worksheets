val list = 1 to 10

for (i <- list) yield 2 * i

list.map { x => 2 * x }

for (i <- list if i % 2 == 0) yield i

