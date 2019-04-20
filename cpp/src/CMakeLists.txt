set(ROARING_BITMAP_TEST_SRC ../lib/croaring/roaring.c)

add_library(${ROARING_BITMAP_TEST_LIB_NAME} ${ROARING_BITMAP_TEST_SRC})
target_include_directories (${ROARING_BITMAP_TEST_LIB_NAME} PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})
add_executable(main main.cpp)
target_link_libraries(main ${ROARING_BITMAP_TEST_LIB_NAME})
