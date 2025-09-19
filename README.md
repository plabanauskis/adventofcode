# Advent of Code Solutions

Clojure solutions for Advent of Code challenges.

## Running Solutions

```bash
AOC_YEAR=2015 AOC_DAY=1 clojure -M -m main
```

## Project Structure

- `src/aoc/yYYYY/day_N.clj` - Day solutions with `part-1` and `part-2` functions
- `resources/YYYY/N.txt` - Input files
- `test/core_test/yYYYY/day_N_test.clj` - Tests
- `test-resources/YYYY-test/N.txt` - Test inputs

## Testing

```bash
# Run all tests
clojure -M:test

# Run tests for specific namespace
clojure -M:test -n core-test.y2015.day-1-test

# Run tests matching a pattern
clojure -M:test -r "y2015"
