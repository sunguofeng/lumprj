(ns lumprj.routes.duty
  (:use compojure.core)
  (:require [lumprj.models.db :as db]
            [lumprj.controller.duty :as duty]
            [noir.response :as resp]
            )

  )


(defroutes duty-routes


  (GET "/getworkmanagers" []
    (duty/dutylist)
    )

  (POST "/addnewduty" [day userid]
    (duty/insertduty day userid)
    )


)


