(ns tag-counter.core-test
  (:use [tag-counter.core])
  (:require [midje.sweet :refer [fact facts]]))

;; TODO - Look into using QuickCheck for more test coverage
(facts "Cleans a \"dirty\" tag"

       (fact "leaves a \"clean tag\" as it is"
             (clean-tag "clean") => "clean")

       (fact "cleans pre/post whitespaces"
             (clean-tag " tag") => "tag"
             (clean-tag "tag ") => "tag"
             (clean-tag " tag ") => "tag")

       (fact "cleans upper case letters"
             (clean-tag "TAG") => "tag"
             (clean-tag "TaG") => "tag")

       (fact "cleans punctuation characters"
             (clean-tag "up-date") => "update"
             (clean-tag "update!") => "update"
             (clean-tag "update?") => "update"
             (clean-tag "update.") => "update")

       (fact "cleans upper case and punctuation characters"
             (clean-tag "of-CourSE!!?") => "ofcourse"
             (clean-tag "OFCOUR!.-Se") => "ofcourse"
             (clean-tag "?oFCourse,") => "ofcourse"))
