
(ns bybit-com.core.request.body)

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn raw-request-body-item
  ; @ignore
  ;
  ; @param (string) name
  ; @param (*) value
  ;
  ; @usage
  ; (raw-request-body-item "basePrice" 42)
  ; =>
  ; "\"basePrice\": \"42\""
  ;
  ; @return (string)
  [name value]
  (if value (str "\""name"\": \""value"\"")))

(defn raw-request-body
  ; @ignore
  ;
  ; @param (numbers or strings in vectors in vector) items
  ; [[(string) name
  ;   (number or string)(opt) value]]
  ;
  ; @usage
  ; (raw-request-body [["basePrice" "42"] ["symbol" "ETHUSDT"]])
  ; =>
  ; "{\"basePrice\": \"42\", \"symbol\": \"ETHUSDT\"}"
  ;
  ; @return (string)
  [items]
  (letfn [(f0 [result [name value]]
              (if value (str result (if result ", ") (raw-request-body-item name value))
                        (->  result)))]
         (let [raw-body (reduce f0 nil items)]
              (str "{"raw-body"}"))))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn POST-body
  ; @ignore
  ;
  ; @param (?) items
  [items]
  ; @todo
  ; ...
  (raw-request-body items))
