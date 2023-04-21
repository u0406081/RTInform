def array = [1, 3, 4, 5, 1, 5, 4] as int[]
print firstSolution(array)


def firstSolution(def array) {
    def map = [:] as HashMap

    for (element in array) {
        def amount = map.get(element);

        if (amount == null) {
            map.put(element, 1);
        } else {
            map.put(element, (amount + 1));
        }
    }

    map.collect {entry -> [entry.getKey(), entry.getValue()] as int[]}
}