(ns almondbread.gui
  (:gen-class)
  (:require [almondbread.core :as core]))

(import '(javax.swing JFrame JPanel)
        '(java.awt Dimension)
        '(java.awt.image BufferedImage))

(defn shift [n]
  (bit-or n (bit-or (bit-shift-left n 8) (bit-shift-left n 16))))

(defn paint-mandelbrot [g width height]
  (let [image (BufferedImage. width height BufferedImage/TYPE_INT_RGB)]
    (doseq [i (core/each-point width height)]
      (.setRGB image (:x i) (:y i) (shift (:v i))))
    (.drawImage g image 0 0 nil)))

(def panel (proxy [JPanel] []
  (getPreferredSize [] (Dimension. 320 200))
  (paintComponent [g]
    (proxy-super paintComponent g)
    (paint-mandelbrot g (.getWidth this) (.getHeight this)))))

(defn -main [& args]
  (let [frame (JFrame. "Mandelbrot")]
        (doto frame
          (.add panel)
          (.pack)
          (.setVisible true))))



