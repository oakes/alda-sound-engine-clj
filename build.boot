(set-env!
  :source-paths   #{"src"}
  :dependencies   '[; dev
                    [adzerk/bootlaces            "0.1.13" :scope "test"]
                    [alda/core                   "0.2.2"  :scope "test"]
                    [org.clojure/tools.namespace "0.2.11" :scope "test"]

                    ; dependencies
                    [com.taoensso/timbre "4.10.0"]
                    [com.jsyn/jsyn       "20170328"]]
  :repositories (conj (get-env :repositories)
                  ["clojars" {:url "https://clojars.org/repo/"
                              :username (System/getenv "CLOJARS_USER")
                              :password (System/getenv "CLOJARS_PASS")}]))

(require '[adzerk.bootlaces :refer :all])

(def ^:const +version+ "0.4.0-1")

(bootlaces! +version+)

(task-options!
  pom     {:project 'org.clojars.oakes/sound-engine-clj
           :version +version+
           :description "A Clojure implementation of an Alda sound engine"
           :url "https://github.com/oakes/alda-sound-engine-clj"
           :scm {:url "https://github.com/oakes/alda-sound-engine-clj"}
           :license {"name" "Eclipse Public License"
                     "url" "http://www.eclipse.org/legal/epl-v10.html"}}
  push {:repo "clojars"})

(deftask local []
  (comp (pom) (jar) (install)))

(deftask deploy []
  (comp (pom) (jar) (push)))

