
# Wikipedia Entity Vectors Reader

This is a tool for reading the release data of [Wikipedia Entity Vectors](https://github.com/singletongue/WikiEntVec).


## Usage

Before use this tool, you should download [Wikipedia Entity Vectors release data](https://github.com/singletongue/WikiEntVec/releases) such as jawiki.word\_vectors.100d.txt.gz and copy into this project home directory.

And then do gradle command as follows:

```
./gradlew -Pargs="jawiki.word_vectors.100d.txt.gz スターバックス"
```

Result:

```
> Task :run
スターバックス
- ケンタッキーフライドチキン (score: 0.7516587756941022)
- ハーゲンダッツ (score: 0.7492512830135285)
- ベルギービール (score: 0.7397523992289845)
- かごの屋 (score: 0.7360437281277835)
- セブンイレブン (score: 0.7358147931637159)
- ファミリーマート (score: 0.7334104630379378)
- ミスタードーナツ (score: 0.7320779172546913)
- カレーハウス (score: 0.7318742846601766)
- バーガーキング (score: 0.7272940610164202)
- ザ・テラス (score: 0.7272931343526212)
```

It's top 10 similarity words with the word スターバックス.
