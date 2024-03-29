
(ns bybit-com.core.response.utils
    (:require [fruits.json.api   :as json]
              [fruits.reader.api :as reader]
              [fruits.string.api :as string]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn time-now->epoch-ms
  ; @ignore
  ;
  ; @param (string) n
  ;
  ; @usage
  ; (time-now->epoch-ms "1645550000.123456")
  ; =>
  ; 1645550000123
  ;
  ; @return (ms)
  [n]
  (let [s  (string/before-first-occurence n "." {:return? false})
        ms (string/after-first-occurence  n "." {:return? false})]
       (reader/parse-edn (str s (subs ms 0 3)))))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn GET-response->body
  ; @ignore
  ;
  ; @param (map) response
  ; {:body (string)}
  ;
  ; @usage
  ; (GET-response->body {... :body "{\"result\":[{...},{...}]}"})
  ; =>
  ; {:result [{...} {...}]}
  ;
  ; @return (map)
  ; {}
  [{:keys [body]}]
  (-> body reader/parse-json json/keywordize-keys json/kebab-case-keys))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn POST-response->headers
  ; @ignore
  ;
  ; @param (map) response
  ; {:headers (string)}
  ;
  ; @usage
  ; (POST-response->headers {...})
  ; =>
  ; {...}
  ;
  ; @return (map)
  ; {}
  [response]
  (:headers response))

(defn POST-response->body
  ; @ignore
  ;
  ; @param (map) response
  ; {:body (string)}
  ;
  ; @usage
  ; (POST-response->body {...})
  ; =>
  ; {...}
  ;
  ; @return (map)
  ; {}
  [{:keys [body]}]
  (-> body reader/parse-json json/keywordize-keys json/hyphenize-keys))
