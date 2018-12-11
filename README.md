
# Wikipedia Entity Vectors Reader

This is a tool for reading the release data of [Wikipedia Entity Vectors](https://github.com/singletongue/WikiEntVec).


## Usage

Before use this tool, you should download [Wikipedia Entity Vectors release data](https://github.com/singletongue/WikiEntVec/releases) such as jawiki.word\_vectors.100d.txt.gz and copy into this project home directory.

And then do gradle command as follows:

```
./gradlew -Pargs="jawiki.word_vectors.100d.txt.gz iPhone"
```

Result:

```
iPhone
- iPad (score: 0.9539793718222197)
- Android (score: 0.9265376298574367)
- iOS (score: 0.8722529433633629)
- スマートフォン (score: 0.8578014917024938)
- iPod (score: 0.856802839072025)
- アプリ (score: 0.8551785318649682)
- 専用アプリ (score: 0.8394270126646978)
- iPhoneアプリ (score: 0.8356653257510369)
- スマホ (score: 0.8261666818231069)
- AppStore (score: 0.8226029096545175)
```

It's top 10 similarity words with the word __iPhone__.
