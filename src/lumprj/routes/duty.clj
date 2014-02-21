(ns lumprj.routes.duty
  (:use compojure.core)
  (:require [lumprj.controller.duty :as duty]
            [noir.response :as resp]
            )
  )


(defroutes duty-routes

  (GET "/getworkmanagers" []
    (duty/dutylist)
    )

  (GET "/getcurrentduty" [day]
    (duty/getcurrentduty day)
    )
  (GET "/getmissions" []
    (duty/getmissions)

    )
  (GET "/getdutymissions" [day]
    (duty/getdutymissions day)
    )

  (GET "/maketodaymission" [day userid]
    (duty/maketodaymission day userid)
    )

  (POST "/addnewduty" [day userid]
    (duty/insertduty day userid)
    )
  (POST "/addnewmission" [missionname missiontime missioninterval]
    (duty/insertmission missionname missiontime missioninterval)
    )
  (POST "/delenumbyid" request

    (resp/json  (duty/delenumbyid request))
    ;;(resp/json {:success false})
    )

)


