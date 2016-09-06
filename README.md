# tag-counter

A tag counter is a server program that accepts text file uploads,
processes each upload and returns the top 10 tag list across all
processed uploads.

# Why/Purpose?/What for/Raison d'être
This is a coding challenge / exercise in coding

# Requirements

## 1) Tags are made up of letters and numbers.
A tag is essentially made of `a-z | A-Z | 0-9` in any combination

## 2) The Tags are case insensitive
By case insensitive, I mean `WaNtEd == wanted`

## 3) The Tags are punctuation ignorant/free
What is meant by this is that all punctuations are stripped from the words
Essentially `up-dated == updated? == updated! == updated. == updated, == updated`

## 4) Plural versions of a Tag are different
So `Can` is classed as a different tag to `Cans`

## 5) Empty White Spaces are NOT Tags
All empty spaces (e.g new lines, tabs, returns) are to be ignored.

## 6) Concurrent Connections/Processing
The Server should be capable of handling multiple text uploads and
return the the top 10 Tags, across all processed uploads, as a response.

## 7) Stateless
The server shouldn't hold on to state anywhere. Not during the processing
of each upload or across all uploads.

## 8) Limitation on 3rd Party Libraries
The actual processing of the uploads and the collecting of the top 10
should be done without any 3rd Party libraries.


## License

Copyright © 2016 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
