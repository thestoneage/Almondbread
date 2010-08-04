(ns almondbread.core
  (:gen-class)
  (:require [clojure.contrib.generic.arithmetic :as ga]
            [clojure.contrib.generic.comparison :as gc]
            [clojure.contrib.generic.math-functions :as gm]
            [clojure.contrib.complex-numbers :as cn]))

(defn escape-time
  [c z step]
  (if (or (> step 256) (gc/> (gm/abs z) 4.0))
    step
    (recur c (ga/+(ga/* z z) c) (+ step 1))))

(defn escape-time-single
  [c] (escape-time c (cn/complex 0 0) 0))

(defn each-point
  [width height f]
  (let [rs (/ 3.0 width)
        is (/ 3.0 height)]
    (for [y (range height) x (range width)]
      (f x y (escape-time-single
               (cn/complex (+ -2.0 (* rs x)) (+ -1.0 (* is y))))))))

(defn print-mandelset [width height]
  (each-point width height (fn [x y v]
                      (if (= 0 x)
                        (println ""))
                      (if (< v 255) (print "-") (print "#")))))

(defn -main [& args] (print-mandelset))
