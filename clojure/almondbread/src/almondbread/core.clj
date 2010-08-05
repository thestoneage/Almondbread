(ns almondbread.core
  (:gen-class)
  (:require [clojure.contrib.generic.arithmetic :as ga]
            [clojure.contrib.generic.comparison :as gc]
            [clojure.contrib.generic.math-functions :as gm]
            [clojure.contrib.complex-numbers :as cn]))

(defn escape-time
  ([c]
  (escape-time c (cn/complex 0 0) 0))
  ([c z step]
  (if (or (> step 255) (gc/> (gm/abs z) 4.0))
    step
    (recur c (ga/+(ga/* z z) c) (+ step 1)))))

(defn each-point
  [width height]
  (let [rs (/ 3.0 width)
        is (/ 2.0 height)]
    (for [y (range height) x (range width)]
      {:x x, :y y, :v (escape-time
               (cn/complex (+ -2.0 (* rs x)) (+ -1.0 (* is y))))})))

(defn mandelstr [width height] (map (fn [i] (.concat (if (= (:x i) 0) "\n" "") (if (< (:v i) 255) "-" "#"))) (each-point width height)))

(defn -main [& args] (println (apply str (interpose "" (mandelstr 80 24)))))

