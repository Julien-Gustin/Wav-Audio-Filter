# Using audio.jar

## Compilation

```sh
javac -d bin -cp bin:audio.jar src/*.java
Or use MakeFile by just tipping make
```

**N.B.:** if you are under Windows, the ":" must be replaced by ";".

## Run

```sh
java -cp audio.jar:bin Demo Audio_source/"name_source_file".wav Audio_Filtred/"name_filtred_file".wav
```
