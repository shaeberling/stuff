#include <stdio.h>
#include <stdlib.h>
#include <android/log.h>

int main()
{
  __android_log_print(ANDROID_LOG_INFO, "ABIN", "Fourty-two!");
  printf("Fourty-two\n");
  return 0;
}

