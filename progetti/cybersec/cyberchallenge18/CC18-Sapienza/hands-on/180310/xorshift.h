#pragma once
#include <stdint.h>

uint32_t xorshift32(uint32_t state[1]);
uint32_t xorshift128(uint32_t state[4]);