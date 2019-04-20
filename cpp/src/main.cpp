#include <roaring.hh>
#include <assert.h>
#include <climits>
#include <iostream>
#include <fstream>

int main() {
    Roaring64Map outBitmap;

    // write
    for (uint64_t i = 100; i < 1000; i++) {
      outBitmap.add(i);
    }

    outBitmap.add((uint64_t) UINT_MAX);
    outBitmap.add((uint64_t) ULLONG_MAX);

    uint64_t size = outBitmap.getSizeInBytes();

    char *outBuffer = new char[size];
    size_t bytesWritten = outBitmap.write(outBuffer);

    std::ofstream outfile("testcpp.bin", std::ios::binary);
    outfile.write(outBuffer, size);

    outfile.close();
    delete[] outBuffer;

    // read - verify
    char* inBuffer = new char[bytesWritten];
    std::ifstream infile("testcpp.bin", std::ifstream::binary);

    infile.read(inBuffer, bytesWritten);

    Roaring64Map inBitmap = Roaring64Map::read(inBuffer);

    infile.close();

    for (uint64_t i = 100; i < 1000; i++) {
      assert(inBitmap.contains(i));
    }

    inBitmap.contains((uint64_t) UINT_MAX);
    inBitmap.contains((uint64_t) ULLONG_MAX);
}
