CMAKE_MINIMUM_REQUIRED(VERSION 3.1)

project(ROARING_BITMAP_SERIALIZE_TEST)

if (NOT CMAKE_BUILD_TYPE)
  message(STATUS "No build type selected, default to Release")
  set(CMAKE_BUILD_TYPE Release CACHE STRING "Choose the type of build." FORCE)
endif()

set(ROARING_BITMAP_TEST_LIB_NAME ROARING_BITMAP_SERIALIZE_TEST)
set(ROARING_BITMAP_TEST_VERSION 1.0.0)
set (CMAKE_CXX_STANDARD 11)

include_directories(lib/croaring)

add_subdirectory(src)

message( STATUS "CMAKE_SYSTEM_PROCESSOR: " ${CMAKE_SYSTEM_PROCESSOR})
message( STATUS "CMAKE_BUILD_TYPE: " ${CMAKE_BUILD_TYPE} )
message( STATUS "CMAKE_CXX_COMPILER: " ${CMAKE_CXX_COMPILER} ) # important to know which compiler is used
message( STATUS "CMAKE_CXX_FLAGS: " ${CMAKE_CXX_FLAGS} ) # important to know the flags
message( STATUS "CMAKE_CXX_FLAGS_DEBUG: " ${CMAKE_CXX_FLAGS_DEBUG} )
