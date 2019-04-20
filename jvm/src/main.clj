(ns main
  (:use [clojure.java.io :only [output-stream]])
  (:import [java.io File FileOutputStream DataOutputStream FileInputStream DataInputStream]
           [org.roaringbitmap.longlong Roaring64NavigableMap]))

(defn serialize [map filename]
  (with-open [outp (-> (File. filename)
                       FileOutputStream.
                       DataOutputStream.)]
    (.serialize map outp)))

(defn deserialize [map filename]
  (with-open [inp (-> (File. filename)
                      FileInputStream.
                      DataInputStream.)]
    (.deserialize map inp)))

(defn -main []
  (let [out-bitmap (Roaring64NavigableMap.)]
    (doseq [i (range 100 1000)]
      (.addLong out-bitmap i))
    (.addLong out-bitmap (Long/parseUnsignedLong "4294967295"))
    (.addLong out-bitmap (Long/parseUnsignedLong "18446744073709551615"))
    (serialize out-bitmap "testjvm.bin")
    (let [in-bitmap (doto (Roaring64NavigableMap.) (deserialize "testjvm.bin"))]
      (doseq [i (range 100 1000)]
        (assert (.contains in-bitmap i)))
      (.contains in-bitmap (Long/parseUnsignedLong "4294967295"))
      (.contains in-bitmap (Long/parseUnsignedLong "18446744073709551615"))
      (assert (= in-bitmap out-bitmap)))))
