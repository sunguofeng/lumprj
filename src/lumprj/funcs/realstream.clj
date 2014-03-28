(ns lumprj.funcs.realstream

  (:import
           (java.io  BufferedReader InputStreamReader)
           (edu.iris.miniseedutils.steim GenericMiniSeedRecord)
           (java.lang.Math)
           (java.util StringTokenizer))
  (:require
            [lumprj.funcs.conmmon :as conmmon]
            )
  )


;;解码minidata
(defn decodeminirtdata  [x bis buf recLen]
  (.read bis buf 0 recLen)

  (let [
         gmsRec (GenericMiniSeedRecord/buildMiniSeedRecord buf)
         updata (make-array Integer/TYPE (.getNumSamples gmsRec))]
    (if(.decompress gmsRec updata)()())

    {:stationname (str (.getStation gmsRec) "/" (.getChannel gmsRec))
     :data (into [] updata)
     :time (.getStartTime gmsRec)
     }
    )

  )
;;相关分析
(defn correlation-analysis [reallist realbegin samplelist samplebegin len ]

  (let [aw1arr (drop realbegin (take (+ len realbegin) reallist))
        aw2arr (drop samplebegin (take (+ len samplebegin) samplelist))
        aw1 (conmmon/average aw1arr)
        aw2 (conmmon/average aw2arr )
        sw1 (conmmon/sum (map #(Math/pow (- (* %) aw1) 2) aw1arr))
        sw2 (conmmon/sum (map #(Math/pow (- (* %) aw2) 2) aw2arr))
        sw12 (conmmon/sum (for [i (range 0 (count aw1arr))
                   :let [x (nth aw1arr i)
                         y (nth aw2arr i)
                         ]
                   ]
               (* (- (* y) aw2) (- (* x) aw1))
               ) )
        ]
    (println sw12)
    (println (Math/sqrt (* sw1 sw2)))
    (/ sw12 (Math/sqrt (* sw1 sw2)))
    )
  )