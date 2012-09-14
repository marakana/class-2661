#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <com_marakana_android_fibonaccinative_FibLib.h>

int main (int argc, char* argv[]) {
  if (argc != 2) {
    fprintf(stderr, "Usage: %s <n>\n", argv[0]);
    exit(2);
  } else {
    int n = atoi(argv[1]);
    long result = fib(n);
    printf("\nfib(%d)=%ld", n, result);
  }
  return 0;
}
